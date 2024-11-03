/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simulacionpuente;

/**
 *
 * @author navarro
 */
public class Puente {

    private int personasEnPuente = 0;
    private int personasDireccionA = 0;
    private int personasDireccionB = 0;
    private int pesoTotal = 0;
    private final int MAX_PERSONAS = 4;
    private final int MAX_PERSONAS_POR_DIRECCION = 3;
    private final int MAX_PESO = 300;

    public synchronized void entrarPuente(Persona persona) throws InterruptedException {
        while (!puedeEntrar(persona)) {
            System.out.println(persona + " no puede entrar al puente y está esperando. " +
                "Personas cruzando Dirección A: " + personasDireccionA + ", " +
                "Personas cruzando Dirección B: " + personasDireccionB + ", " +
                "Peso total en el puente: " + pesoTotal + "kg.");
            wait();
        }

        personasEnPuente++;
        if (persona.getDireccion() == 'A') {
            personasDireccionA++;
        } else {
            personasDireccionB++;
        }
        pesoTotal += persona.getPeso();

        System.out.println(persona + " entró al puente. Total personas en el puente: " + personasEnPuente);
    }

    public synchronized void salirPuente(Persona persona) {
        personasEnPuente--;
        if (persona.getDireccion() == 'A') {
            personasDireccionA--;
        } else {
            personasDireccionB--;
        }
        pesoTotal -= persona.getPeso();

        System.out.println(persona + " salió del puente. Total personas en el puente: " + personasEnPuente);
        notifyAll();
    }

    /**
     * Verifica si una persona puede entrar al puente.
     */
    private boolean puedeEntrar(Persona persona) {
        if (personasEnPuente >= MAX_PERSONAS) {
            return false;
        }
        if (persona.getDireccion() == 'A' && personasDireccionA >= MAX_PERSONAS_POR_DIRECCION) {
            return false;
        }
        if (persona.getDireccion() == 'B' && personasDireccionB >= MAX_PERSONAS_POR_DIRECCION) {
            return false;
        }
        if ((pesoTotal + persona.getPeso()) > MAX_PESO) {
            return false;
        }
        return true;
    }
}
