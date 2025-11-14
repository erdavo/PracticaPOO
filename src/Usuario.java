import java.io.Serializable;


/**
 * Clase base abstracta para cualquier usuario del sistema (paciente, médico, admin...).
 */

public abstract class Usuario implements Serializable {
    private static final int MAXDNI=9;
    private final String dni;  // Identificador (String)
    private final long cipa; // Código de 10 dígitos

    //añadido a pesar del uml, explicado en "leeme.txt".
    private String nombre;

    public Usuario(String dni, long cipa){
        this(dni,cipa, "No definido");
    }

    public Usuario(String dni, long cipa, String nombre){

        this.dni = validarStrings(dni);

        if (cipa < 1000000000L || cipa > 9999999999L) {
            throw new IllegalArgumentException("Error en CIPA, por favor, compruebe que el CIPA intriducido es valido.");
        }
        this.cipa=cipa;

        this.nombre = validarStrings(nombre);

    }

    
    public String validarStrings(String string){
        if (string == null || string.isEmpty()){
            throw new IllegalArgumentException("Error, por favor, compruebe que los datos introducidos(DNI o Nombre) son validos.");
        }
        return string;
    }

    //Getters
    public String getDni() { return dni; }
    public long getCipa() { return cipa; }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString(){
        return nombre;
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

    @Override
    public int hashCode() {
        return getDni().toUpperCase().hashCode();
    }

}