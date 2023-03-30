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
    private JPasswordField cajaDeContrasena;
    private JButton loginButton;
    private JButton BotonRegistro;
    private JLabel Nombre, Contrasena;
    private File archivo;

    public Login2() {
        super("Prueba de login");
        setLayout(new FlowLayout());

        Nombre = new JLabel("Usuario");
        add(Nombre);
        Nombre_Usuario = new JTextField(20);
        add(Nombre_Usuario);

        Contrasena = new JLabel("Contraseña");
        add(Contrasena);
        cajaDeContrasena = new JPasswordField(20);
        add(cajaDeContrasena);

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
            JPasswordField contraseña = new JPasswordField();
            Object[] message = {"Introduce la contraseña", contraseña};
            int option = JOptionPane.showConfirmDialog(null, message, "Introduce la contraseña", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                char[] inputPassword = contraseña.getPassword();
                String password = new String(inputPassword);
                if (password.equals("Fiera2414")) {
                    JOptionPane.showMessageDialog(null, "Contraseña correcta, ahora puedes agregar un usuario nuevo");

                    if (e.getSource() == BotonRegistro) {

                        String usuario = Nombre_Usuario.getText();
                        String contrasenia = new String(cajaDeContrasena.getPassword());

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
                }else{
                    JOptionPane.showMessageDialog(null,"Contraseña Incorrecta");
                }
            }
        }
    }

    private class HandlerLogin implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == loginButton) {
                String usuario = Nombre_Usuario.getText();
                String contrasena = new String(cajaDeContrasena.getPassword());

                String contraseni = Encriptador.encriptar(contrasena);
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
