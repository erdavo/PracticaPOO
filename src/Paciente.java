public class Paciente extends Usuario{
    private String nombre;
    private String direccion;
    private long telefono;
    private Cita[] cita;
    private Historial historial;

    public Paciente(String dni, long cipa, String nombre, String direccion, long telefono, Cita[] cita, Historial historial) {
        super(dni, cipa);
        this.nombre=nombre;
        this.direccion=direccion;
        this.telefono=telefono;
        this.cita= new Cita[cita.length];    //Esto deberia de ser array  ya que el paciente no tien porque tener solo una cita
        this.historial= new Historial();
    }

    public String getNombre(){
        return nombre;
    }
    //public void setNombre(String nombre) {this.nombre = nombre;}  Creo que esto no deberia poder actualizarse
    public String getDireccion() {
        return direccion;
    }
    //public void setDireccion(String direccion) {this.direccion = direccion; } Creo que no deberia de poder actualizarse
    public long getTelefono() {
        return telefono;
    }
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
    //public Cita getCita() {return cita;}
    //public void setCita(Cita cita) {this.cita = cita;}
    public Historial getHistorial() {
        return historial;
    }
    public void setHistorial(Historial historial) {
        this.historial = historial;
    }

    public Cita nuevaCita(){ //Esta mal asi pero es para ir poniendolo
        Cita aux;
        return cita[cita.length];
    }


}
