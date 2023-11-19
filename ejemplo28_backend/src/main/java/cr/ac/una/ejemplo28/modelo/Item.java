package cr.ac.una.ejemplo28.modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "campo1",
    "campo2",
    "campo3",
    "campo4"
})
@XmlRootElement(name = "item")
@DatabaseTable(tableName = "item")
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    public Item(String id, long campo1, long campo2, long campo3, long campo4) {
        this.id = id;
        this.campo1 = new BigDecimal(campo1);
        this.campo2 = new BigDecimal(campo2);
        this.campo3 = new BigDecimal(campo3);
        this.campo4 = new BigDecimal(campo4);
    }

    @Override
    public String toString() {
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.setPrettyPrinting().create();
        return gson.toJson(this);
    }

    @XmlElement(required = true)
    @Getter
    @Setter
    @DatabaseField(id = true)
    protected String id;
    @XmlElement(required = true)
    @Getter
    @Setter
    @DatabaseField
    protected BigDecimal campo1;
    @XmlElement(required = true)
    @Getter
    @Setter
    @DatabaseField
    protected BigDecimal campo2;
    @XmlElement(required = true)
    @Getter
    @Setter
    @DatabaseField
    protected BigDecimal campo3;
    @XmlElement(required = true)
    @Getter
    @Setter
    @DatabaseField
    protected BigDecimal campo4;
}
