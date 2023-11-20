package cr.ac.una.db;

import java.sql.Connection;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
        try {
            Database db = Database.createMySQLDatabase("mysql_db.properties.xml");
            System.out.println(db);

            System.out.println("Connecting..");
            try (Connection cnx = db.getConnection()) {
                System.out.println(cnx);
                System.out.println("Successful connection..");
            }
            System.out.println("Connection closed.");

        } catch (SQLException ex) {
            System.err.printf("Exception: '%s'%n", ex.getMessage());
        }
    }

}
