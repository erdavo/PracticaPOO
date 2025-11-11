import java.time.LocalDateTime;

public class Cita {
    private LocalDateTime fechaHora;
    private boolean anulada= false;
    private String causaAnulacion;
    private Paciente paciente;
    private Medico medico;

    public Cita(LocalDateTime fechaHora, boolean anulada, Paciente paciente, Medico medico, String causaAnulacion) {
        this.fechaHora = fechaHora;
        this.anulada = anulada;
        if (anulada == true) {
            this.causaAnulacion = causaAnulacion;
        }
        this.paciente = paciente;
        this.medico = medico;
    }
    public Cita(Cita cita) {
        this.fechaHora = cita.getFechaHora();
        this.anulada = cita.isAnulada();
        this.causaAnulacion = cita.getCausaAnulacion();
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public String getCausaAnulacion() {
        return causaAnulacion;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public void setCausaAnulacion(String causaAnulacion) {
        this.causaAnulacion = causaAnulacion;
    }


}
