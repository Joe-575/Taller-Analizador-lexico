package analizador;

public class hexaYoctal {

    public boolean is_hexadecimal(String token){
        int longitud = token.length();
        
        // Debe tener al menos 3 caracteres
        if (longitud < 3) 
            return false; 
        
        // Valida el prefijo obligatorio 0x o 0X
        if (token.charAt(0) != '0' || (token.charAt(1) != 'x' && token.charAt(1) != 'X')) {
            return false;
        }
        
        // Valida el cuerpo del número (dígitos 0-9, letras a-f, A-F)
        for (int i = 2; i < longitud; i++) {
            char t = token.charAt(i);
            boolean esDigito = (t >= '0' && t <= '9');
            boolean esLetraMinuscula = (t >= 'a' && t <= 'f');
            boolean esLetraMayuscula = (t >= 'A' && t <= 'F');
            
            if (!esDigito && !esLetraMinuscula && !esLetraMayuscula) {
                return false; 
            }
        }
        return true;
    }

    public boolean is_octal(String token){
        int longitud = token.length();
        
        // Debe tener al menos 2 caracteres y que comienze con 0 
        if (longitud < 2 || token.charAt(0) != '0') 
            return false; 
        
        // Valida el cuerpo del numero (0 a 7)
        for (int i = 1; i < longitud; i++) {
            char c = token.charAt(i);
            if (!(c >= '0' && c <= '7')) {
                return false; 
            }
        }
        return true;
    }

}
