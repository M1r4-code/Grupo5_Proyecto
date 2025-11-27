/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import javax.swing.JOptionPane;
import static proyecto.TipoCliente.P;
import java.time.LocalDateTime;

/**
 *
 * @author mumir
 */
public class Cola {
    private Nodo prim, ult;
    private int cantClientes = 0;
    LocalDateTime hora = LocalDateTime.now();
    
    public boolean esVacia() {
        return prim==null;
    }
    
    public int PrioridadValor(TipoCliente tipo) {
        switch (tipo) {
            case P:
                return 1;
            case A:
                return 2;
            case B:
                return 3;
            default:
                return 4;
        }
    }
    
    //Encolar
    public void asignar(String nombre, String id, int edad, String tramite, TipoCliente tipo, String horaCreacion, int horaAtencion){
        Nodo nuevo=new Nodo(new Dato(nombre, id, edad, tramite, tipo, horaCreacion, horaAtencion));
        if (esVacia()) {
            prim=ult=nuevo;
            JOptionPane.showMessageDialog(null, nuevo + " es su turno. Pase a la caja 1 para ser atendido");
        } else {
            //**Codigo de cola normal, descomentar si es necesario.**//
            //ult.setSig(nuevo);
            //ult=nuevo;
            //JOptionPane.showMessageDialog(null, nuevo + " usted será atendido en la caja 1. \nHay " + cantClientes + " clientes adelante suyo.");
            
            //**Nuevo codigo con prioridad**//
            //Recorrer cola para encontrar su lugar.
            //Toma el valor del cliente actual y lo compara con el primero en la cola
            if( PrioridadValor(tipo) < PrioridadValor( prim.getDato().getTipo() ) ){
                nuevo.setSig(prim);
                prim = nuevo;
                //Si el cliente actual tiene prioridad mas alta coloca al frente. 
            } else {
                //Si no se recorre la cola para encontrar su lugar. 
                Nodo actual = prim;
                //Inicia un ciclo que verifica que no se este al final de la cola y la recorre hasta que encuentre uno de tenga menor prioridad.
                while ( actual.getSig()!= null && PrioridadValor(tipo) >= PrioridadValor( actual.getSig().getDato().getTipo() ) ){
                    actual = actual.getSig();
                }
                //Se inserta en la posicion encontrada. 
                nuevo.setSig( actual.getSig() );
                actual.setSig(nuevo);
                
                //Si queda al final se actualiza ult. Indicandole que el nuevo que se acaba de crear va de ultimo.  
                if (nuevo.getSig() == null){
                    ult = nuevo;
                }
            }
            JOptionPane.showMessageDialog(null, nuevo + " usted será atendido en la caja 1. \nHay " + cantClientes + " clientes adelante suyo.");
        }
        cantClientes += 1;
    }

    //Atender
    public void TiqueteAtendido() {
        if (esVacia()) {
            JOptionPane.showMessageDialog(null, "Error, nombres no encontrados");
        } else {
            if (prim==null) {
                prim.getDato().setHoraAtencion(hora); //Cuando se atiende, se asigna la hora actual a HoraAtencion
                prim=ult=null;
            } else {
                prim=prim.getSig();
                prim.getDato().setHoraAtencion(hora);
                JOptionPane.showMessageDialog(null, "Es su turno de ser atendido, pase a la caja, " + prim); //Mensaje para indicarle al cliente que es su turno
            }
        }
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
