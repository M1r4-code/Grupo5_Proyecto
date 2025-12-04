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
    ColaA colaA = new ColaA();
    ColaB colaB = new ColaB();
    ColaP colaP = new ColaP();

    private String nombreUsuario;
    private String contraseña;

    public String getNombre() {
        return nombreUsuario;
    }

    public void setNombre(String nombre) {
        this.nombreUsuario = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void MenuLogin() {
        String username = JOptionPane.showInputDialog("Ingrese su nombre de usuario:");
        String usercont = JOptionPane.showInputDialog("Ingrese su contraseña:");

        if (username.equals(nombreUsuario) && usercont.equals(contraseña)) {
            JOptionPane.showMessageDialog(null, "Bienvenido.");
        } else {
            JOptionPane.showMessageDialog(null, "Error: Usuario o contraseña incorrectos.");
        }
        String opcion = "";
        while (!opcion.equals("3")) {
            opcion = JOptionPane.showInputDialog("Ingrese la acción que desea realizar en el sistema:\n1: Ingresar un usuario\n2:Atender un tiquete\n3:Salir del sistema");
            if (opcion.equals("1")) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
                String id = JOptionPane.showInputDialog("Ingrese el ID del cliente:");
                int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del cliente:"));
                String tramite = JOptionPane.showInputDialog("Ingrese el trámite:");
                String tipoC = JOptionPane.showInputDialog("Ingrese el tipo de cliente: ");
                TipoCliente tipo = null;

                if (tipoC.equals("P")) {
                    tipo = TipoCliente.P;
                } else if (tipoC.equals("A")) {
                    tipo = TipoCliente.A;
                } else if (tipoC.equals("B")) {
                    tipo = TipoCliente.B;
                }
                if (tipo == TipoCliente.P) {
                    colaP.asignarP(nombre, id, edad, tramite, tipo);
                } else if (tipo == TipoCliente.A) {
                    colaA.asignarA(nombre, id, edad, tramite, tipo);
                } else if (tipo == TipoCliente.B) {
                    colaB.asignarB(nombre, id, edad, tramite, tipo);
                }
            } else if (opcion.equals("2")) {
                String tipo = JOptionPane.showInputDialog("¿De qué cola desea atender un cliente?");
                if (tipo.equals("P")) {
                    colaP.TiqueteAtendidoP();
                } else if (tipo.equals("A")) {
                    colaA.TiqueteAtendidoA();
                } else if (tipo.equals("B")) {
                    colaB.TiqueteAtendidoB();
                }
            }
        }
    }
}
