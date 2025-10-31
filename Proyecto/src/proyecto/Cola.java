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
public class Cola {
    private Nodo prim, ult;
    private int cantClientes = 0;
    
    public boolean esVacia() {
        return prim==null;
    }
    
    public void asignar(String nombre, String id, int edad, String tramite, String tipo, String horaCreacion, int horaAtencion){
        Nodo nuevo=new Nodo(new Dato(nombre, id, edad, tramite, tipo, horaCreacion, horaAtencion));
        if (esVacia()) {
            prim=ult=nuevo;
            JOptionPane.showMessageDialog(null, nuevo + " es su turno. Pase a la caja 1 para ser atendido");
        } else {
            ult.setSig(nuevo);
            ult=nuevo;
            JOptionPane.showMessageDialog(null, nuevo + " usted será atendido en la caja 1. \nHay " + cantClientes + " clientes adelante suyo.");
        }
        cantClientes += 1;
    }

    @Override
    public String toString() {
        String r="COLA:\n";
        Nodo aux=prim;
        while(aux!=null){
            r+=aux+"\n"; 
            aux=aux.getSig();
        }
        return r;
    }
}
