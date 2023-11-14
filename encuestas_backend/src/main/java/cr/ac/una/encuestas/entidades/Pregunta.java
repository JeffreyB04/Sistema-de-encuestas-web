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

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.dao.ForeignCollection;

/**
 *
 * @author jeffr
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlType
@DatabaseTable(tableName = "Preguntas")
public class Pregunta {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, columnName = "encuesta_id")
    private Encuesta encuesta;

    @DatabaseField(dataType = DataType.ENUM_STRING)
    private TipoPregunta tipo_pregunta;

    @DatabaseField
    private String enunciado;

    // Resto de los campos y métodos de la clase

    // Getter y Setter para ForeignCollection si es necesario
    @ForeignCollectionField(eager = true)
    private ForeignCollection<RespuestaValoracion> respuestasValoracion;
}