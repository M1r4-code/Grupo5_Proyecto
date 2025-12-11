package proyecto;

import java.io.*;

public class Reportes {

    private ColaA colaA;
    private ColaB colaB;
    private ColaP colaP;

    public Reportes(ColaA colaA, ColaB colaB, ColaP colaP) {
        this.colaA = colaA;
        this.colaB = colaB;
        this.colaP = colaP;
    }

    private int contarAtendidos(String archivo) {
        File f = new File(archivo);
        if (!f.exists()) return 0;

        int cont = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            while (br.readLine() != null) cont++;
        } catch (Exception e) {}
        return cont;
    }

    private double promedioTiempo(String archivo) {
        File f = new File(archivo);
        if (!f.exists()) return 0;

        double suma = 0;
        int cont = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(",");
                long t = Long.parseLong(p[5]);
                suma += t;
                cont++;
            }
        } catch (Exception e) {}

        if (cont == 0) return 0;
        return suma / cont;
    }

    // 1. Caja con más atendidos
    public String cajaConMasAtendidos() {

        int a = contarAtendidos("atendidosA.txt");
        int b = contarAtendidos("atendidosB.txt");
        int p = contarAtendidos("atendidosP.txt");

        String r = "\n CAJA CON MÁS ATENDIDOS\n";

        if (a == 0 && b == 0 && p == 0)
            return r + "No hay clientes atendidos aún.\n";

        if (a >= b && a >= p) r += "• Caja A: " + a + "\n";
        else if (b >= a && b >= p) r += "• Caja B: " + b + "\n";
        else r += "• Caja P: " + p + "\n";

        return r;
    }

    // 2. Total atendidos
    public String totalAtendidos() {

        int total =
                contarAtendidos("atendidosA.txt") +
                contarAtendidos("atendidosB.txt") +
                contarAtendidos("atendidosP.txt");

        return "\n TOTAL DE CLIENTES ATENDIDOS\n• Total: " + total + "\n";
    }

    // 3. Mejor tiempo promedio
    public String mejorTiempoPromedio() {

        double promA = promedioTiempo("atendidosA.txt");
        double promB = promedioTiempo("atendidosB.txt");
        double promP = promedioTiempo("atendidosP.txt");

        String r = "\nMEJOR TIEMPO PROMEDIO\n";

        if (promA == 0 && promB == 0 && promP == 0)
            return r + "No hay datos suficientes.\n";

        double mejor = Math.min(promA, Math.min(promB, promP));

        if (mejor == promA) r += "• Caja A: " + promA + " seg\n";
        else if (mejor == promB) r += "• Caja B: " + promB + " seg\n";
        else r += "• Caja P: " + promP + " seg\n";

        return r;
    }

    // 4. Promedio general
    public String promedioGeneral() {
        double promA = promedioTiempo("atendidosA.txt");
        double promB = promedioTiempo("atendidosB.txt");
        double promP = promedioTiempo("atendidosP.txt");

        int atA = contarAtendidos("atendidosA.txt");
        int atB = contarAtendidos("atendidosB.txt");
        int atP = contarAtendidos("atendidosP.txt");

        int total = atA + atB + atP;

        if (total == 0)
            return "\n PROMEDIO GENERAL\nNo hay datos.\n";

        double promGeneral = (promA * atA + promB * atB + promP * atP) / total;

        return "\n PROMEDIO GENERAL\n• Promedio: " + promGeneral + " seg\n";
    }
}
