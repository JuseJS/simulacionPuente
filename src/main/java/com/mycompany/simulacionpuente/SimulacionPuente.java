/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.simulacionpuente;

import java.util.Random;

/**
 *
 * @author navarro
 */
public class SimulacionPuente {
    private static int TOTAL_PERSONAS = 20;
        
    public static void main(String[] args) {
        Puente puente = new Puente();
        Random random = new Random();
        
        for (int i = 0; i < TOTAL_PERSONAS; i++) {
            int peso = 40 + random.nextInt(81); // nextInt(81) da 0 a 80
            char direccion = random.nextBoolean() ? 'A' : 'B';
            int tiempoCruce = 10 + random.nextInt(41);

            Persona persona = new Persona(i + 1, peso, direccion, puente, tiempoCruce);
            persona.start();

            int tiempoLlegada = 1 + random.nextInt(30);
            try {
                Thread.sleep(tiempoLlegada * 100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
