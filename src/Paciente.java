import java.util.Scanner;

/**
 * Representa a un paciente.
 * Hereda de Usuario y añade los atributos 'nombre', 'direccion', 'telefono'.
 * Por requisitos, el paciente sólo puede modificar su teléfono (enunciado).
 */

public class Paciente extends Usuario{
    //Datos administrativos
    private String direccion;
    private long telefono;

    private Historial historialClinico;

    public Paciente(String dni, long cipa, String nombre, String direccion, long telefono) {
        super(dni, cipa, nombre);
        this.direccion = validarStrings(direccion);
        this.telefono = validarTelefono(telefono);
        this.historialClinico = new Historial();
    }


    //Falta implementar el metodo de crearPaciente, que se ha eliminado pq estaba mal pero está en commits anteriores

    private long validarTelefono(long telefono){
        if (telefono <= 0) throw new IllegalArgumentException("El teléfono tiene que ser positivo.");
        return telefono;
    }

    //getters
    public String getDireccion() {
        return direccion;
    }
    public long getTelefono() {
        return telefono;
    }
    public Historial getHistorial() {
        return historialClinico;
    }


    //Setters

    //cualquier paciente puede modificar su propio número de telefono

    public void setTelefono(Usuario usuario, long telefono) {
        this.telefono = validarTelefono(telefono);
    }
    //Es necesario ser AdminCentroSalud para poder hacer cualquiera de los siguientes metodos setters.
    /**
     * Definimos un método auxiliar que compruebe que el usuario que llama a los setters protegidos sea ACS o Admin
     * Ademas como el enunciado dice:
     * "El administrador de la aplicación:
     * - Tendrá acceso a poder realizar cualquier operación sobre la aplicación."
     * Hemos añadido la comprobacion correspondiente: validarAdminCentroSalud.
     */
    private void validarAdminCentroSalud(Usuario usuario) {
        if (!(usuario instanceof Admin || usuario instanceof AdminCentroSalud)) {
            throw new IllegalArgumentException("El usuario no tiene permisos para realizar esta operación. " +
                    "Comuníquese con su centro de salud para modificar datos importantes.");
        }
    }

    // Aplicamos la validación de usuario a ambos métodos
    public void setDireccion(Usuario usuario, String direccion) {
        validarAdminCentroSalud(usuario);
        this.direccion = direccion;
    }
    public void setNombre(String nombre) {
        setNombre(validarStrings(nombre));
    }

    public String getDatosAdministrativos() {
        return "Nombre: " + getNombre() + "\n" +
                "DNI: " + getDni() + "\n" +
                "CIPA: " + getCipa() + "\n" +
                "Teléfono: " + getTelefono() + "\n" +
                "Dirección: " + getDireccion();
    }

    public String getDatosClinicos() {
        return historialClinico.toString();
    }
}
