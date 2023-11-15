package cr.ac.una.estudiantes.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import cr.ac.una.util.conversion.xml.SqlDateAdapter;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlType
@DatabaseTable(tableName = "estudiante")
public class Estudiante implements Serializable {

    @Getter
    @Setter
    @DatabaseField(id = true)
    private String id;
    @Getter
    @Setter
    @DatabaseField
    private String apellido1;
    @Getter
    @Setter
    @DatabaseField
    private String apellido2;
    @Getter
    @Setter
    @DatabaseField
    private String nombre;

    public java.sql.Date getNacimiento() {
        return nacimiento;
    }

    @XmlJavaTypeAdapter(SqlDateAdapter.class)
    public void setNacimiento(java.sql.Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    @DatabaseField
    private java.sql.Date nacimiento;
}
