/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author mumir
 */
public class Configuracion {

    boolean existeArchivo = false;
    File archivo = new File("prod.txt");
    private String nombreBanco;
    private int cantidadCajas;
    private int cantidadPreferencial;
    private int cantidadRapida;
    
    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public int getCantidadCajas() {
        return cantidadCajas;
    }

    public void setCantidadCajas(int cantidadCajas) {
        this.cantidadCajas = cantidadCajas;
    }

    public int getCantidadPreferencial() {
        return cantidadPreferencial;
    }

    public void setCantidadPreferencial(int cantidadPreferencial) {
        this.cantidadPreferencial = cantidadPreferencial;
    }

    public int getCantidadRapida() {
        return cantidadRapida;
    }

    public void setCantidadRapida(int cantidadRapida) {
        this.cantidadRapida = cantidadRapida;
    }

    public void checkFile() {
        try {
            if (!archivo.exists()) {
                System.out.println("El archivo no existe. Se va a crear.");
                archivo.createNewFile();
                existeArchivo = false;
            } else {
                existeArchivo = true;
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        try {
            FileReader reader = new FileReader(archivo);
            String textoArchivo = "";
            int i;

            while ((i = reader.read()) != -1) {
                textoArchivo += (char) i;
            }

            reader.close();

            String[] partes = textoArchivo.split(",");

            nombreBanco = partes[0];
            cantidadCajas = Integer.parseInt(partes[1]);
            cantidadRapida = Integer.parseInt(partes[2]);
            cantidadPreferencial = Integer.parseInt(partes[3]);

        } catch (IOException | NumberFormatException e) {
        }
    }

    public  void startMenu() {
        if (existeArchivo == false) {

            try {
                nombreBanco = JOptionPane.showInputDialog(null, "Ingrese el nombre del Banco");
                cantidadCajas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cajas no preferenciales"));
                cantidadRapida = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cajas rapidas"));

                cantidadPreferencial = Integer.parseInt(JOptionPane.showInputDialog(
                        "Ingrese cuantas cajas preferenciales desea tener \\n"
                        + "Tenga en consideracion que tiene que ser minimo 1"));

                if (cantidadPreferencial < 1) {
                    JOptionPane.showMessageDialog(null, "Error: Por ley debe tener minimo una caja preferencial");
                }

                //Escribe los datos en el archivo
                FileWriter writer = new FileWriter(archivo);
                writer.write(nombreBanco + "," + cantidadCajas + "," + cantidadRapida + "," + cantidadPreferencial);
                writer.close();

            } catch (HeadlessException | IOException | NumberFormatException e) {
            }

        } else if (existeArchivo == true){
            readFile();
            JOptionPane.showMessageDialog(null,
                    "Bienvenido a el Banco"
                    + nombreBanco
                    + "\\n Actualmente hay \\n"
                    + cantidadCajas
                    + "cajas normales.\\n"
                    + cantidadRapida
                    + "cajas rapidas. \\n"
                    + cantidadPreferencial
                    + "cajas preferenciales.");
        }
        
        
    }

    

}
