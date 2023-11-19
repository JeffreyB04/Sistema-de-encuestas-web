package cr.ac.una.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.DataSourceConnectionSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Georges Alfaro S.
 * @version 3.0.0 2023-09-25
 */
public class Database {

    public Database(String configurationPath) {
        this.configuration = new Properties();
        this.ds = null;

        try {
            initDS(configurationPath);
        } catch (IOException | NamingException ex) {
            System.err.printf("Exception: '%s'%n", ex.getMessage());
        }
    }

    public Database() {
        this(DEFAULT_CONFIGURATION_PATH);
    }

    private void initDS(String configurationPath) throws IOException, NamingException {
        configuration.loadFromXML(getClass().getResourceAsStream(configurationPath));
        try {
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(configuration.getProperty("JNDI_name"));
            System.out.println("Using JNDI to access database..");

        } catch (NamingException | NullPointerException ex) {
            System.err.println("Cannot access JNDI resource.");
            throw ex;
        }
    }

    public static Database createMySQLDatabase(String configurationPath) throws SQLException {
        Database db = new Database(configurationPath);
        if (db.ds == null) {
            MysqlDataSource mds = new MysqlDataSource();

            mds.setURL(String.format("%s//%s/%s",
                    db.configuration.getProperty("protocol"),
                    db.configuration.getProperty("server_url"),
                    db.configuration.getProperty("database")
            ));
            mds.setUser(db.configuration.getProperty("user"));
            mds.setPassword(db.configuration.getProperty("password"));
            mds.setCharacterEncoding("UTF-8");

            db.ds = mds;
            System.out.println("Using MySQL JDBC driver to access database..");
        }
        return db;
    }

    public static Database createOracleDatabase(String configurationPath) throws SQLException {
        Database db = new Database(configurationPath);
        if (db.ds == null) {
            OracleDataSource ods = new OracleDataSource();

            ods.setDriverType("oci8");
            ods.setNetworkProtocol("ipc");
            ods.setDatabaseName(db.configuration.getProperty("database"));
            ods.setUser(db.configuration.getProperty("user"));
            ods.setPassword(db.configuration.getProperty("password"));

            db.ds = ods;
            System.out.println("Using Oracle JDBC driver to access database..");
        }
        return db;
    }

    public DataSource getDataSource() {
        return ds;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public <D extends Dao<T, ?>, T> D getDAO(Class<T> clazz) throws SQLException {
        String url = getConnection().getMetaData().getURL();
        DataSourceConnectionSource connectionSource
                = new DataSourceConnectionSource(ds, url);
        return DaoManager.createDao(connectionSource, clazz);
    }

    public String listConfigurationData() {
        StringBuilder r = new StringBuilder("{\n");
        configuration.stringPropertyNames().forEach(key -> {
            r.append(String.format("\t%s: '%s', %n",
                    key, configuration.getProperty(key)));
        });
        r.append("\n}");
        return r.toString();
    }

    @Override
    public String toString() {
        return String.format("%s: %s",
                getClass().getCanonicalName(),
                listConfigurationData());
    }

    protected Properties getConfiguration() {
        return configuration;
    }

    protected static final String DEFAULT_CONFIGURATION_PATH = "./db.properties.xml";
    private Properties configuration = null;
    protected DataSource ds = null;
}
