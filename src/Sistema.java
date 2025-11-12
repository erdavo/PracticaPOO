import java.util.ArrayList;

public class Sistema {
    private ListaPaciente listaPacientes;
    private AgendaConsultas agendaConsultas;
    private AgendaCita agendaCitas;
    private Plantilla plantilla;

    public Sistema() {
        this.listaPacientes = new ListaPaciente();
        this.agendaConsultas = new AgendaConsultas();
        this.agendaCitas = new AgendaCita();
        this.plantilla = new Plantilla();
    }

    public void anadirPaciente(){
        -
    }

    public void solicitarCita(Paciente paciente, String especialidad){
        Medico medicoDeseado = buscarMedicoDisponible();
        this.agendaCitas.addCita(cita);
    }

    //Metodos para añadir elementos a los arraylist

    /* Esta no la tenemos que implementar en esta entrega
    public boolean anadirConsulta(Consulta consulta) {
        if (!() && !agendaConsultas.contains(consulta)) {
            agendaConsultas.add(consulta);
            return true;
        }
        return false;
    }
     */
}
