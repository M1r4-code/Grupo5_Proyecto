/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Login {
    ColaA colaA = new ColaA();
    ColaB colaB = new ColaB();
    ColaP colaP = new ColaP();

    private String nombreUsuario = "admin";
    private String contraseña = "1234";

    public void MenuLogin() {

        JOptionPane.showMessageDialog(
                null,
                "Bienvenido al Sistema de Atención.\nIngrese sus credenciales.",
                "Login",
                JOptionPane.INFORMATION_MESSAGE
        );

        boolean accesoConcedido = false;

        while (!accesoConcedido) {
            String username = JOptionPane.showInputDialog(null, "Usuario:", "Login", JOptionPane.QUESTION_MESSAGE);
            if (username == null) return;

            String usercont = JOptionPane.showInputDialog(null, "Contraseña:", "Login", JOptionPane.QUESTION_MESSAGE);
            if (usercont == null) return;

            if (username.equals(nombreUsuario) && usercont.equals(contraseña)) {
                JOptionPane.showMessageDialog(null, "Acceso concedido.");
                accesoConcedido = true;
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Usuario o contraseña incorrectos. Inténtelo de nuevo.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        String opcion = "";
        while (!opcion.equals("3")) {
            opcion = JOptionPane.showInputDialog(
                    null,
                    "Seleccione una opción:\n\n"
                            + "1. Registrar un cliente\n"
                            + "2. Atender un cliente\n"
                            + "3. Salir",
                    "Menu Principal",
                    JOptionPane.QUESTION_MESSAGE
            );
            if (opcion == null) return;

            switch (opcion) {

                case "1":
                    registrarCliente();
                    break;

                case "2":
                    atenderCliente();
                    break;

                case "3":
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
                    break;
            }
        }
    }

    private void registrarCliente() {
        try {
            String nombre = JOptionPane.showInputDialog("Nombre del cliente:");
            if (nombre == null) return;

            String id = JOptionPane.showInputDialog("ID del cliente:");
            if (id == null) return;

            int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad del cliente:"));

            String tramite = JOptionPane.showInputDialog("Trámite:");
            if (tramite == null) return;

            String tipoC = JOptionPane.showInputDialog("Tipo de cliente (P, A, B):");
            if (tipoC == null) return;

            tipoC = tipoC.toUpperCase();
            TipoCliente tipo;
            switch (tipoC) {
                case "P":
                    tipo = TipoCliente.P;
                    colaP.asignarP(nombre, id, edad, tramite, tipo);
                    guardarClienteEnArchivo("clientesP.txt", nombre, id, edad, tramite, tipo);
                    break;
                case "A":
                    tipo = TipoCliente.A;
                    colaA.asignarA(nombre, id, edad, tramite, tipo);
                    guardarClienteEnArchivo("clientesA.txt", nombre, id, edad, tramite, tipo);
                    break;
                case "B":
                    tipo = TipoCliente.B;
                    colaB.asignarB(nombre, id, edad, tramite, tipo);
                    guardarClienteEnArchivo("clientesB.txt", nombre, id, edad, tramite, tipo);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Tipo de cliente inválido.");
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar cliente. Verifique los datos.");
        }
    }

    private void atenderCliente() {
        String colaStr = JOptionPane.showInputDialog(
                "Seleccione la cola a atender:\nP - Preferencial\nA - Adulto mayor\nB - Regular"
        );
        if (colaStr == null) return;
        colaStr = colaStr.toUpperCase();

        switch (colaStr) {
            case "P":
                cargarClientesDesdeArchivo(colaP, "clientesP.txt");
                if (!colaP.esVacia()) {
                    Dato atendido = colaP.getPrimDato();
                    colaP.TiqueteAtendidoP();
                    eliminarClienteDelArchivo("clientesP.txt", atendido);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay clientes en la cola P.");
                }
                break;
            case "A":
                cargarClientesDesdeArchivo(colaA, "clientesA.txt");
                if (!colaA.esVacia()) {
                    Dato atendido = colaA.getPrimDato();
                    colaA.TiqueteAtendidoA();
                    eliminarClienteDelArchivo("clientesA.txt", atendido);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay clientes en la cola A.");
                }
                break;
            case "B":
                cargarClientesDesdeArchivo(colaB, "clientesB.txt");
                if (!colaB.esVacia()) {
                    Dato atendido = colaB.getPrimDato();
                    colaB.TiqueteAtendidoB();
                    eliminarClienteDelArchivo("clientesB.txt", atendido);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay clientes en la cola B.");
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Cola inválida.");
        }
    }

    // Métodos para manejar archivos

    private void guardarClienteEnArchivo(String archivoNombre, String nombre, String id, int edad, String tramite, TipoCliente tipo) {
        try (FileWriter writer = new FileWriter(archivoNombre, true)) {
            writer.write(nombre + "," + id + "," + edad + "," + tramite + "," + tipo + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarClientesDesdeArchivo(ColaP cola, String archivoNombre) {
        File archivo = new File(archivoNombre);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",");
                String nombre = partes[0];
                String id = partes[1];
                int edad = Integer.parseInt(partes[2]);
                String tramite = partes[3];
                TipoCliente tipo = TipoCliente.valueOf(partes[4]);
                cola.asignarP(nombre, id, edad, tramite, tipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarClientesDesdeArchivo(ColaA cola, String archivoNombre) {
        File archivo = new File(archivoNombre);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",");
                String nombre = partes[0];
                String id = partes[1];
                int edad = Integer.parseInt(partes[2]);
                String tramite = partes[3];
                TipoCliente tipo = TipoCliente.valueOf(partes[4]);
                cola.asignarA(nombre, id, edad, tramite, tipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarClientesDesdeArchivo(ColaB cola, String archivoNombre) {
        File archivo = new File(archivoNombre);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",");
                String nombre = partes[0];
                String id = partes[1];
                int edad = Integer.parseInt(partes[2]);
                String tramite = partes[3];
                TipoCliente tipo = TipoCliente.valueOf(partes[4]);
                cola.asignarB(nombre, id, edad, tramite, tipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eliminarClienteDelArchivo(String archivoNombre, Dato cliente) {
        File archivo = new File(archivoNombre);
        if (!archivo.exists()) return;

        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.startsWith(cliente.getNombre() + ",")) {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(archivo, false)) {
            for (String l : lineas) {
                writer.write(l + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
