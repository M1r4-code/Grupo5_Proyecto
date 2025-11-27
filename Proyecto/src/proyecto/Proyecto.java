/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto;

import javax.swing.JOptionPane;

/**
 *
 * @author mumir
 */
public class Proyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cola cola = new Cola();
        
        cola.asignar("Juliana", "1", 25, "Depositos", TipoCliente.P, "horaCreacion", -1);
        cola.asignar("Allan", "2", 42, "Retiros", TipoCliente.A, "horaCreacion", -1);
        cola.asignar("Jonathan", "3", 37, "Cambio de Divisas", TipoCliente.B, "horaCreacion", -1);
        cola.asignar("Maria", "4", 18, "Retiros", TipoCliente.P, "horaCreacion", -1);
        JOptionPane.showMessageDialog(null, cola);

        cola.TiqueteAtendido();
        JOptionPane.showMessageDialog(null, cola);

        cola.TiqueteAtendido();
        JOptionPane.showMessageDialog(null, cola);
    }
}

