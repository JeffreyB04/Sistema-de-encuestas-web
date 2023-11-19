/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.ejemplo28.modelo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
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
@DatabaseTable(tableName = "Usuario")
public class Usuario {

    @Getter
    @Setter
    @DatabaseField(id = true)
    private String cedula;
    
    @Getter
    @Setter
    @DatabaseField(foreign = true, columnName = "encuesta_id")
    private Encuesta encuesta;
    
    @Getter
    @Setter
    @DatabaseField(foreign = true, columnName = "respuesta_valoracion_id")
    private RespuestaValoracion respuestaValoracion;

    @Getter
    @Setter
    @DatabaseField(foreign = true, columnName = "respuesta_seleccion_id")
    private RespuestaSeleccion respuestaSeleccion;

    @Getter
    @Setter
    @DatabaseField(foreign = true, columnName = "respuesta_abierta_id")
    private RespuestaAbierta respuestaAbierta;
}

