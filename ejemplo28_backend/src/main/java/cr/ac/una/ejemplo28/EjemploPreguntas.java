/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.ejemplo28;

import cr.ac.una.ejemplo28.modelo.GestorPreguntas;

/**
 *
 * @author jeffr
 */
public class EjemploPreguntas {
    
    public static void main(String[] args) {
        new EjemploPreguntas().test();
    }
    public void test() {
        GestorPreguntas gestorPreguntas = GestorPreguntas.obtenerInstancia();
        System.out.println(gestorPreguntas);
        System.out.println();
        gestorPreguntas.actualizar();
    }
}


