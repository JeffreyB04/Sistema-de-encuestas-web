/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.encuestas.entidades;

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
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlType
@DatabaseTable(tableName = "Encuestas")
public class Encuesta {

    @Getter
    @Setter
    @DatabaseField(generatedId = true)
    private int id;

    @Getter
    @Setter
    @DatabaseField
    private Date fecha_creacion;

    @Getter
    @Setter
    @DatabaseField
    private Date fecha_inicio_aplicacion;

    @Getter
    @Setter
    @DatabaseField
    private Date fecha_fin_aplicacion;
}
