package analizador;

public class notacionCientifica {

    // Recibe un String 
    public static boolean esCientifica(String s) {

        try {
            // Si no tiene "e" Y tampoco "E", no es numero cientifico
            if (!s.contains("e") && !s.contains("E")) return false;

            //convertir a numero decimal
            Double.parseDouble(s);

            // numero cientifico valido
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}