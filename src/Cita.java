import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }

    Cita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
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


    //esto cancela la cita que llama al metodo, no otra.
    public void cancelarCita(String causaAnulacion){
        if (!anulada){
            this.anulada = true;
            this.causaAnulacion = causaAnulacion;
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
                && this.medico.equals(otra.medico);
    }

    @Override
    public int hashCode() {
        return fechaCita.hashCode() + medico.hashCode();
    }

    @Override
    public String toString() {
        String out = "";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        out += "Fecha de la cita: " + fechaCita.format(fmt) +
                "\nMédico: " + medico + " - Especialidad: "
                + getMedico().getEspecialidad()
                + "\nPaciente: " + paciente  + "\n";

        if(isAnulada()){
            out += "\nEsta cita ha sido anulada el dia: " + fechaCancelacion +
            "\nMotivo: " + causaAnulacion;
        }

        return out;
    }
}
