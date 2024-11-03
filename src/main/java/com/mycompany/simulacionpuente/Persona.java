/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simulacionpuente;

/**
 *
 * @author navarro
 */
public class Persona extends Thread {
    private int peso;
    private char direccion; // 'A' o 'B'
    private Puente puente;
    private int tiempoCruce;

    public Persona(int id, int peso, char direccion, Puente puente, int tiempoCruce) {
        super("Persona-" + id);
        this.peso = peso;
        this.direccion = direccion;
        this.puente = puente;
        this.tiempoCruce = tiempoCruce;
    }

    public int getPeso() {
        return peso;
    }

    public char getDireccion() {
        return direccion;
    }

    @Override
    public void run() {
        try {
            puente.entrarPuente(this);
            System.out.println(this + " está cruzando el puente.");
            Thread.sleep(tiempoCruce * 100L);
            puente.salirPuente(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return getName() + " [" + peso + "kg, Dirección " + direccion + "]";
    }
}
