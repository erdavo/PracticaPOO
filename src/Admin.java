/**
 * Rol de admin de la app (lo dice el enunciado).
 * No añadimos atributos respecto a Usuario (el UML no los define).
 */

public class Admin extends Usuario{

    public Admin(String dni, long cipa, String nombre) {
        super(dni, cipa, nombre);
    }

    public Admin(String dni, long cipa) {
        super(dni, cipa);
    }

}
