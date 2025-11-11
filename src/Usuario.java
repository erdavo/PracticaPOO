import java.io.Serializable;


/**
 * Clase base abstracta para cualquier usuario del sistema (paciente, médico, admin...).
 */

public abstract class Usuario implements Serializable {
    private static final int MAXDNI=9;
    private final String dni;  // Identificador (String)
    private final long cipa; // Código de 10 dígitos

    // "Plantilla" (es de la composición en el UML).
    private final Plantilla plantilla;

    public Usuario(String dni, long cipa){

        this.dni = validarStrings(dni);

        if (cipa < 1000000000L || cipa > 9999999999L) {
            throw new IllegalArgumentException("Error en CIPA, por favor, compruebe que el CIPA intriducido es valido.");
        }
        this.cipa=cipa;

        // esto pq lo pone el UML no sé muy bien que es lo de plantilla
        this.plantilla = new Plantilla();
    }

    public String validarStrings(String string){
        if (string == null || string.isEmpty()){
            throw new IllegalArgumentException("Error en DNI, por favor, compruebe que el DNI introducido es valido.");
        }
        return string;
    }

    //Getters
    public String getDni() { return dni; }
    public long getCipa() { return cipa; }

    @Override
    public String toString(){
        return "\nDNI: " + dni + "\nCIPA: " + cipa;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        Usuario usuario = (Usuario) obj;
        return usuario.getDni().equalsIgnoreCase(this.getDni());
        //he puesto IgnoreCase para que si tenemos 12345678A y 12345678a,
        // sean lo mismo sin tener que cambiar de minus a mayus
    }
}