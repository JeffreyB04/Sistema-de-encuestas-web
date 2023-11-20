package cr.ac.una.ejemplo28.modelo;
/*
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "items"
})
@XmlRootElement(name = "lista-items")
public class ListaItems {

    public ListaItems() {
        items = new ArrayList<>();
    }

    public void add(Item newItem) {
        items.add(newItem);
    }

    public void addAll(Collection<Item> items) {
        this.items.addAll(items);
    }

    @Override
    public String toString() {
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.setPrettyPrinting().create();
        return gson.toJson(getItems());
    }

    public List<Item> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return this.items;
    }

    @XmlElement(name = "item", required = true)
    protected List<Item> items;
}
*/