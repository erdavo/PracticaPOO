/**
 * Representa a un paciente.
 * Hereda de Usuario y añade los atributos 'nombre', 'direccion', 'telefono'.
 * Por requisitos, el paciente sólo puede modificar su teléfono (enunciado).
 */

public class Paciente extends Usuario{
    private String nombre;
    private String direccion;
    private long telefono;
    private Cita[] cita;
    private Historial historial;

    public Paciente(String dni, long cipa, String nombre, String direccion, long telefono, Cita[] cita, Historial historial) {
        //preguntar a estefania porque no deja hacer la validacion dentro de la llamada a super
        super(dni, cipa);
        this.nombre=validarStrings(nombre);
        this.direccion=validarStrings(direccion);
        this.telefono=validarTelefono(telefono);
        this.cita= new Cita[cita.length];    //Esto deberia de ser array  ya que el paciente no tien porque tener solo una cita
        this.historial= new Historial();
    }

    private long validarTelefono(long telefono){
        if (telefono <= 0) throw new IllegalArgumentException("El teléfono tiene que ser positivo.");
        return telefono;
    }

    //getters

    public String getNombre(){
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public long getTelefono() {
        return telefono;
    }
    //public Cita getCita() {return cita;}

    public Historial getHistorial() {
        return historial;
    }

    //Setters

    //cualquier paciente puede modificar su propio número de telefono
    public void setNombre(String nombre) {
        this.nombre = validarStrings(nombre);
    }

    //Es necesario ser AdminCentroSalud para poder hacer cualquiera de los siguientes metodos setters.
    /**
     * Definimos un método auxiliar que compruebe que el usuario que llama a los setters protegidos sea ACS o Admin
     * Ademas como el enunciado dice:
     * "El administrador de la aplicación:
     * - Tendrá acceso a poder realizar cualquier operación sobre la aplicación."
     * Hemos añadido la comprobacion correspondiente: validarAdminCentroSalud.p
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
    public void setTelefono(Usuario usuario, long telefono) {
        validarAdminCentroSalud(usuario);
        this.telefono = validarTelefono(telefono);
    }

    //public void setCita(Cita cita) {this.cita = cita;}
    public void setHistorial(Historial historial) {
        this.historial = historial;
    }

    public Cita nuevaCita(){ //Esta mal asi pero es para ir poniendolo
        Cita aux;
        return cita[cita.length];
    }


}
