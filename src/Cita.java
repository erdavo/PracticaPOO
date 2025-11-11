import java.time.LocalDateTime;

public class Cita {
    private LocalDateTime fechaHora;
    private boolean anulada = false;
    private String causaAnulacion;
    private LocalDateTime fechaCancelacion;
    private Paciente paciente;
    private Medico medico;

    public Cita(LocalDateTime fechaHora, boolean anulada, Paciente paciente, Medico medico, String causaAnulacion) {
        this.fechaHora = fechaHora;
        this.anulada = anulada;
        if (anulada == true) {
            this.causaAnulacion = validarCausa(causaAnulacion);
        }
        this.paciente = paciente;
        this.medico = medico;
    }

    private String validarCausa(String string){
        if (string == null || string.isEmpty()){
            throw new IllegalArgumentException("Error en la causa, compruebe que es valida. Debe de existir una causa válida.");
        }
        return string;
    }

    /* No le veo el sentido
    public Cita(Cita cita) {
        this.fechaHora = cita.getFechaHora();
        this.anulada = cita.isAnulada();
        this.causaAnulacion = cita.getCausaAnulacion();
    }
    */

    //Getters
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    public boolean isAnulada() {
        return anulada;
    }
    public String getCausaAnulacion() {
        return causaAnulacion;
    }

    //Setters
    public void setFechaHora(LocalDateTime fechaNueva) {
        if (fechaNueva.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("La fecha tiene que ser mayor a hoy.");
        }
        this.fechaHora = fechaNueva;
    }

    //esto reprograma la cita que llama al metodo, no otra
    public void reprogramarCita(LocalDateTime fechaNueva){
        if(isAnulada()) throw new IllegalStateException("No se puede reagendar una cita anulada.");
        this.setFechaHora(fechaNueva);
    }

    //esto cancela la cita que llama al metodp, no otra.
    public void cancelar(String causaAnulacion){
        if (!anulada){
            this.anulada = true;
            this.causaAnulacion = validarCausa(causaAnulacion);
            this.fechaCancelacion = LocalDateTime.now();
        }

    }


}
