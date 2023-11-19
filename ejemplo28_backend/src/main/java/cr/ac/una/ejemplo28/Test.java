package cr.ac.una.ejemplo28;

import cr.ac.una.ejemplo28.modelo.GestorItems;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
        try {
            GestorItems g = GestorItems.obtenerInstancia();
            System.out.println(g);
            System.out.println();
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }

}
