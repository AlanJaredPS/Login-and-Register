/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframeslevy;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Jorge
 */
public class LoginTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Login2 Ventana = new Login2();
        Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Ventana.setVisible(true);

        Toolkit miVentana = Toolkit.getDefaultToolkit();
        Dimension tamañoPantalla = miVentana.getScreenSize();

        int alturaPantalla = tamañoPantalla.height;
        int anchoPantalla = tamañoPantalla.width;

        Ventana.setSize(anchoPantalla / 2, alturaPantalla / 2);
        Ventana.setLocation(anchoPantalla / 4, alturaPantalla / 4);

        Ventana.setResizable(false);
        
        Image MiIcono = miVentana.getImage("increible.jpg");
        Ventana.setIconImage(MiIcono);
    }
    
}
