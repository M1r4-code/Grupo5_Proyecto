/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

/**
 *
 * @author mumir
 */
public class Nodo {
   private String nombre;
    private String id;
    private int edad;
    private String tramite;
    private char tipo;
    private String horaCreacion;
    private String horaAtencion;
    private Nodo sig;

    public Nodo(String nombre, String id, int edad, String tramite, char tipo) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.tramite = tramite;
        this.tipo = tipo;
        this.horaCreacion = java.time.LocalTime.now().toString();
        this.horaAtencion = "-1"; 
        this.sig = null;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getTramite() { return tramite; }
    public void setTramite(String tramite) { this.tramite = tramite; }

    public char getTipo() { return tipo; }
    public void setTipo(char tipo) { this.tipo = tipo; }

    public String getHoraCreacion() { return horaCreacion; }
    public void setHoraCreacion(String horaCreacion) { this.horaCreacion = horaCreacion; }

    public String getHoraAtencion() { return horaAtencion; }
    public void setHoraAtencion(String horaAtencion) { this.horaAtencion = horaAtencion; }

    public Nodo getSig() { return sig; }
    public void setSig(Nodo sig) { this.sig = sig; }

    public int getPrioridad() {
        switch (tipo) {
            case 'P': return 1;
            case 'B': return 2;
            case 'A': return 3;
            default: return 4;
        }
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
               ", ID: " + id +
               ", Edad: " + edad +
               ", Trámite: " + tramite +
               ", Tipo: " + tipo +
               ", Hora creación: " + horaCreacion +
               ", Hora atención: " + horaAtencion;
    } 
}
