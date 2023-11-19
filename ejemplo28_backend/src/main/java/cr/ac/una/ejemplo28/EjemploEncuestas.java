/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.ejemplo28;

import cr.ac.una.ejemplo28.modelo.GestorEncuestas;


public class EjemploEncuestas {
    public static void main(String[] args) {
        new EjemploEncuestas().test();
    }
    public void test() {
        GestorEncuestas gestorEncuestas = GestorEncuestas.obtenerInstancia();
        System.out.println(gestorEncuestas);
        System.out.println();
        gestorEncuestas.actualizar();
    }
}