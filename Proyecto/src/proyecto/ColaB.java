package proyecto;

import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class ColaB {
    private int totalAtendidos = 0;
    private long tiempoAcumulado = 0;
    
    private Nodo prim, ult;
    private int cantClientes = 0;
    LocalDateTime hora = LocalDateTime.now();

    public boolean esVacia() {
        return prim == null;
    }

    //Encolar
    public void asignarB(String nombre, String id, int edad, String tramite, TipoCliente tipo) {
        Nodo nuevo = new Nodo(new Dato(nombre, id, edad, tramite, tipo));
        
        if (esVacia()) {
            prim = ult = nuevo;
            JOptionPane.showMessageDialog(null, nuevo + " es su turno. Pase a la caja 1 para ser atendido");
        } else {
                if (nuevo.getSig() == null) {
                    ult.setSig(nuevo);
                    ult = nuevo;
                    JOptionPane.showMessageDialog(null, nuevo + " usted será atendido en la caja 1. \nHay " + cantClientes + " clientes adelante suyo.");
                }
            } 
        cantClientes += 1;
    }

    //Atender
    public void TiqueteAtendidoB() {
    if (esVacia()) {
        JOptionPane.showMessageDialog(null, "Error, clientes no encontrados");
    } else {

        Nodo atendido = prim;
        atendido.getDato().setHoraAtencion(LocalDateTime.now());

        long segundos = Duration.between(
            atendido.getDato().getHoraCreacion(),
            atendido.getDato().getHoraAtencion()
        ).getSeconds();

        tiempoAcumulado += segundos;
        totalAtendidos++;

        JOptionPane.showMessageDialog(null,
                "Se está atendiendo a: " + atendido.getDato().getNombre()
                + "\nTiempo de espera: " + segundos + " segundos");

        prim = prim.getSig();

        if (prim == null) {
            ult = null;
        }

        cantClientes--;
    }
}
    public int getTotalAtendidos() {
    return this.totalAtendidos;
}

public long getPromedio() {
    if (totalAtendidos == 0) return Long.MAX_VALUE; 

    return tiempoAcumulado / totalAtendidos;
}

public Dato getPrimDato() {
    return prim != null ? prim.getDato() : null;
}

public int getAtendidos() {
    return totalAtendidos;
}

public double getPromedioTiempo() {
    if (totalAtendidos == 0) return 0.0;
    return (tiempoAcumulado / 60.0) / totalAtendidos; // promedio en minutos
}

    @Override
    public String toString() {
        String r = "COLA:\n";
        Nodo aux = prim;
        while (aux != null) {
            r += aux + "\n";
            aux = aux.getSig();
        }
        return r;
    }

    
}
