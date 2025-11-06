public class Paciente extends Usuario{
    private String nombre;
    private String direccion;
    private long telefono;
    private Cita cita;
    private Historial historial;

    public Paciente(String dni, long cipa, String nombre, String direccion, long telefono, Cita cita, Historial historial) {
        super(dni, cipa);
        this.nombre=nombre;
        this.direccion=direccion;
        this.telefono=telefono;
        this.cita=cita;
        this.historial=historial;
    }


}
