import java.time.LocalDateTime;

public class Cita {
    private Paciente paciente;
    private Medico medico;

    private LocalDateTime fechaCita;
    private boolean anulada;

    private LocalDateTime fechaCancelacion;
    private String causaAnulacion;

    public Cita(LocalDateTime fechaCita, Paciente paciente, Medico medico) {
        this.fechaCita = fechaCita;
        this.paciente = paciente;
        this.medico = medico;
    }

    private String validarCausa(String string){
        if (string == null || string.isEmpty()){
            throw new IllegalArgumentException("Error en la causa, compruebe que es valida. Debe de existir una causa válida.");
        }
        return string;
    }

    //Getters
    public LocalDateTime getFechaCita(){
        return fechaCita;
    }
    public boolean isAnulada() {
        return anulada;
    }
    public String getCausaAnulacion() {
        return causaAnulacion;
    }
    public Paciente getPaciente() {return paciente;}
    public Medico getMedico() {return medico;}

    //Setters
    public void setFechaCita(LocalDateTime fechaNueva) {
        if (fechaNueva.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("La fecha tiene que ser mayor a hoy.");
        }
        this.fechaCita = fechaNueva;
    }

    //esto reprograma la cita que llama al metodo, no otra
    public void reprogramarCita(LocalDateTime fechaNueva){
        if(isAnulada()) throw new IllegalStateException("No se puede reagendar una cita anulada.");
        this.setFechaCita(fechaNueva);
    }


    //esto cancela la cita que llama al metodo, no otra.
    public void cancelarCita(String causaAnulacion){
        if (!anulada){
            this.anulada = true;
            this.causaAnulacion = validarCausa(causaAnulacion);
            this.fechaCancelacion = LocalDateTime.now();
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        Cita otra = (Cita) obj;

        return this.fechaCita.equals(otra.fechaCita)
                && this.medico.equals(otra.medico)
                && this.paciente.equals(otra.paciente);
    }

    @Override
    public int hashCode() {
        return fechaCita.hashCode() + medico.hashCode() + paciente.hashCode();
    }

}
