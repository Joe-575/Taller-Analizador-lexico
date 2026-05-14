package analizador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IngresoSentencias {

    // 1. Definimos los patrones Regex para cada componente léxico
    // Se utiliza la sintaxis de "Grupos con nombre" (?<nombre>patron) para facilitar la extracción
    private static final String REGEX = 
            "(?<FLOTANTE>\\d+\\.\\d+)|" +                                      // Números con decimales (ej. 3.14)
            "(?<ENTERO>\\b\\d+\\b)|" +                                         // Números enteros (ej. 42)
            "(?<RESERVADA>\\b(if|else|while|for|int|float|public|class|return|void|String|boolean)\\b)|" + // Palabras reservadas
            "(?<IDVALIDO>\\b[a-zA-Z_][a-zA-Z0-9_]*\\b)|" +                     // Identificadores válidos (letras/guion bajo seguido de alfanuméricos)
            "(?<IDINVALIDO>\\b\\d+[a-zA-Z_][a-zA-Z0-9_]*\\b)|" +               // Identificadores inválidos (ej. empiezan con número como 123var)
            "(?<SIMBOLO>[\\+\\-\\*\\/=\\<\\>\\!\\&\\|\\;\\,\\(\\)\\{\\}\\[\\]])|" + // Símbolos y operadores
            "(?<DESCONOCIDO>[^\\s]+)";                                         // Cualquier otra cosa que no sea espacio en blanco

    // Compilamos el patrón una sola vez para mejorar el rendimiento
    private static final Pattern PATRON = Pattern.compile(REGEX);

    /**
     * Método para analizar una sola línea de texto e imprimir sus tokens.
     */
    public static void analizarLinea(String linea) {
        System.out.println("Analizando línea: " + linea);
        System.out.println("--------------------------------------------------");
        
        Matcher matcher = PATRON.matcher(linea);

        // Iteramos encontrando cada coincidencia en la línea
        while (matcher.find()) {
            if (matcher.group("FLOTANTE") != null) {
                System.out.println("Flotante           -> " + matcher.group("FLOTANTE"));
            } else if (matcher.group("ENTERO") != null) {
                System.out.println("Entero             -> " + matcher.group("ENTERO"));
            } else if (matcher.group("RESERVADA") != null) {
                System.out.println("Palabra Reservada  -> " + matcher.group("RESERVADA"));
            } else if (matcher.group("IDVALIDO") != null) {
                System.out.println("Identificador Vál. -> " + matcher.group("IDVALIDO"));
            } else if (matcher.group("IDINVALIDO") != null) {
                System.out.println("Identificador Inv. -> " + matcher.group("IDINVALIDO"));
            } else if (matcher.group("SIMBOLO") != null) {
                System.out.println("Símbolo            -> " + matcher.group("SIMBOLO"));
            } else if (matcher.group("DESCONOCIDO") != null) {
                System.out.println("Error/Desconocido  -> " + matcher.group("DESCONOCIDO"));
            }
        }
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Método para leer un archivo TXT línea por línea enviándolo al analizador.
     */
    public static void leerArchivo(String rutaRelativa) {
        File archivo = new File(rutaRelativa);
        
        // Verificamos si el archivo existe antes de intentar leerlo
        if (!archivo.exists()) {
            System.err.println("Error: No se encontró el archivo en la ruta: " + archivo.getAbsolutePath());
            return;
        }

        // Usamos try-with-resources para asegurar que el archivo se cierre automáticamente
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            System.out.println("Iniciando lectura del archivo...\n");
            
            // Leemos línea por línea hasta que no haya más texto (null)
            while ((linea = br.readLine()) != null) {
                // Ignoramos líneas vacías para no ensuciar la salida
                if (!linea.trim().isEmpty()) {
                    analizarLinea(linea);
                }
            }
            System.out.println("Fin del archivo alcanzado.");
        } catch (IOException e) {
            System.err.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // 1. Prueba rápida de una sola línea
        System.out.println("=== PRUEBA MANUAL DE UNA LÍNEA ===");
        String sentenciaPrueba = "int 1numero = 5 ; float pi = 3.14 ;";
        analizarLinea(sentenciaPrueba);

        // 2. Prueba de lectura de archivo
        System.out.println("=== PRUEBA DE LECTURA DE ARCHIVO ===");
        // Ajusta el nombre de tu archivo TXT aquí. 
        // Si ejecutas el código desde la raíz del proyecto, esta ruta apuntará a la carpeta que mencionaste.
        String rutaArchivo = "src/analizador/codigo.txt"; 
        
        leerArchivo(rutaArchivo);
    }
}