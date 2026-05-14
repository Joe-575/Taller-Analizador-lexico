import analizador.IngresoSentencias;
import analizador.comentarios;
import analizador.hexaYoctal;
import analizador.notacionCientifica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) {
        String rutaArchivo = args.length > 0 ? args[0] : "src/analizador/codigo.txt";
        System.out.println("Ruta de archivo: " + rutaArchivo);

        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            System.err.println("Error: No se encontró el archivo en la ruta: " + archivo.getAbsolutePath());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            System.out.println("Iniciando lectura del archivo...\n");
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                System.out.println("===== LÍNEA LEÍDA =====");
                System.out.println(linea);
                IngresoSentencias.analizarLinea(linea);
                verificarLineasConOtrasClases(linea);
            }
            System.out.println("Fin del archivo alcanzado.");
        } catch (IOException e) {
            System.err.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }

    private static void verificarLineasConOtrasClases(String linea) {
        comentarios comprobadorComentarios = new comentarios();
        hexaYoctal comprobadorHexOct = new hexaYoctal();

        System.out.println("Verificaciones adicionales para la línea:");
        System.out.println("- Es comentario: " + comprobadorComentarios.esComentario(linea));
        System.out.println("- Es hexadecimal: " + comprobadorHexOct.is_hexadecimal(linea));
        System.out.println("- Es octal: " + comprobadorHexOct.is_octal(linea));
        System.out.println("- Es notación científica: " + notacionCientifica.esCientifica(linea));
        System.out.println();
    }
}
