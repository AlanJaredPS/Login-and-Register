/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframeslevy;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

public class Login2 extends JFrame {

    private JTextField Nombre_Usuario;
    private JPasswordField cajaDeContraseña;
    private JButton loginButton;
    private JButton BotonRegistro;
    private JLabel Nombre, Contraseña;
    private File archivo;

    public Login2() {
        super("Prueba de login");
        setLayout(new FlowLayout());

        Nombre = new JLabel("Usuario");
        add(Nombre);
        Nombre_Usuario = new JTextField(20);
        add(Nombre_Usuario);

        Contraseña = new JLabel("Contraseña");
        add(Contraseña);
        cajaDeContraseña = new JPasswordField(20);
        add(cajaDeContraseña);

        BotonRegistro = new JButton("Registrarse");
        BotonRegistro.addActionListener(new HandlerRegistro());
        add(BotonRegistro);

        loginButton = new JButton("Iniciar Sesion");
        loginButton.addActionListener(new HandlerLogin());
        add(loginButton);

        archivo = new File("usuarios.txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class HandlerRegistro implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == BotonRegistro) {
                String usuario = Nombre_Usuario.getText();
                String contrasenia = new String(cajaDeContraseña.getPassword());

                String contraseni = Encriptador.encriptar(contrasenia);
                // Agregar código para procesar los datos de registro aquí
                try {
                    FileWriter fw = new FileWriter(archivo, true);
                    fw.write(usuario + "," + contraseni + "\n");
                    fw.close();
                    JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private class HandlerLogin implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginButton) {
                String usuario = Nombre_Usuario.getText();
                String contraseña = new String(cajaDeContraseña.getPassword());

                String contraseni = Encriptador.encriptar(contraseña);
                try {
                    FileReader fr = new FileReader(archivo);
                    BufferedReader br = new BufferedReader(fr);
                    String linea;
                    boolean encontrado = false;
                    while ((linea = br.readLine()) != null) {
                        String[] partes = linea.split(",");
                        if (partes[0].equals(usuario) && partes[1].equals(contraseni)) {
                            encontrado = true;
                            break;
                        }
                    }
                    br.close();
                    fr.close();
                    if (encontrado) {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                        NuevoFrame nuevoFrame = new NuevoFrame();
                        nuevoFrame.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
