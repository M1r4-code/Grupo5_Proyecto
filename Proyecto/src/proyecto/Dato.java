package proyecto;

public class Dato {
    private String nombre;
    private String id;
    private int edad;
    private String tramite;
    private String tipo; 
    private String horaCreacion;
    private int horaAtencion;

    public Dato(String nombre, String id, int edad, String tramite, String tipo, String horaCreacion, int horaAtencion) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.tramite = tramite;
        this.tipo = tipo;
        this.horaCreacion = horaCreacion;
        this.horaAtencion = horaAtencion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(String horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public int getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(int horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
