/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import javax.swing.JOptionPane;

/**
 *
 * @author mumir
 */
public class Login {
    private String nombre;
    private String contraseña;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
    
    public void MenuLogin(){
        String username =  JOptionPane.showInputDialog("Ingrese su nombre de usuario:");
        String usercont = JOptionPane.showInputDialog("Ingrese su contraseña:");
        
        if(username.equals(nombre) && usercont.equals(contraseña)){
            JOptionPane.showMessageDialog(null, "Bienvenido.");
        } else {
            JOptionPane.showMessageDialog(null, "Error: Usuario o contraseña incorrectos.");
        }
            
    }   
}
