package cr.ac.una.ejemplo28.modelo;

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

    @Getter
    @Setter
    @DatabaseField(generatedId = true)
    private int id;

    @Getter
    @Setter
    @DatabaseField(foreign = true, columnName = "encuesta_id")
    private Encuesta encuesta;

    // Cambiado el tipo de DataType a STRING
    @Getter
    @Setter
    @DatabaseField(dataType = DataType.STRING)
    private String tipo_pregunta;

    @Getter
    @Setter
    @DatabaseField
    private String enunciado;


    // Getter y Setter para ForeignCollection si es necesario
    //@Getter
    //@Setter
    //@ForeignCollectionField(eager = true)
    //private ForeignCollection<RespuestaValoracion> respuestasValoracion;
}
