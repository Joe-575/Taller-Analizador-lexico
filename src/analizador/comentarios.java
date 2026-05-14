package analizador;

public class comentarios {
    public boolean esComentario(String palabraVer) {
        // ver si está vacío o no tiene los caracteres necesarios
        if (palabraVer == null || palabraVer.length() < 2) {
            return false;
        }
        
        // Verificar comentario normal
        if (palabraVer.charAt(0) == '/' && palabraVer.charAt(1) == '/') {
            return true;
        }
        
        // Verificar comentario largo cuando son varias lineas /* */
        if (palabraVer.charAt(0) == '/' && palabraVer.charAt(1) == '*') {
            if (palabraVer.length() >= 4 && palabraVer.charAt(palabraVer.length() - 2) == '*' && 
            palabraVer.charAt(palabraVer.length() - 1) == '/') {
                return true; 
            }
        }
        
        return false;
    }
    
}
