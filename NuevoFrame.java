/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframeslevy;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NuevoFrame extends JFrame {
    public NuevoFrame() {
        setTitle("Nuevo Frame");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        Lamina miLamina = new Lamina();
        
        add(miLamina);
    }
}
class Lamina extends JPanel{
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        g.drawString("BIENVENIDO",200,100);
    }
}
