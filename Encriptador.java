/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframeslevy;

import javax.swing.JPasswordField;

/**
 *
 * @author Jorge
 */
public class Encriptador {

    private static final String ABECEDARIO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CIFRADO = "DEFGHIJKLMNOPQRSTUVWXYZABC";

    public static String encriptar(String frase) {
        frase = frase.toUpperCase();
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < frase.length(); i++) {
            char letra = frase.charAt(i);
            int indice = ABECEDARIO.indexOf(letra);
            if (indice != -1) {
                letra = CIFRADO.charAt(indice);
            }
            resultado.append(letra);
        }
        return resultado.toString();
    }

    static String encriptar(JPasswordField cajaDeContraseña) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}