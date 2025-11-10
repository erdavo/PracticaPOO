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
}
