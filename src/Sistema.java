import java.util.ArrayList;

public class Sistema {
    //Esta clase la usaremos para centralizar las operaciones
    private ListaPaciente listaPacientes;
    private AgendaConsultas  agendaConsultas;
    private AgendaCita agendaCitas;
    private Plantilla plantilla;
    public Sistema(ListaPaciente listaPacientes,  AgendaConsultas agendaConsultas,  AgendaCita agendaCitas, Plantilla plantilla) {
        this.listaPacientes = listaPacientes;
        this.agendaConsultas = agendaConsultas;
        this.agendaCitas = agendaCitas;
        this.plantilla= plantilla;
    }

    //Getter de los arraylist
    public ArrayList<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public ArrayList<Consulta> getAgendaConsultas() {
        return agendaConsultas;
    }

    public ArrayList<Cita> getAgendaCitas() {
        return agendaCitas;
    }

    public ArrayList<Usuario> getPlantilla() {
        return plantilla;
    }

    //Metodos para añadir elementos a los arraylist
    public boolean anadirPaciente(Paciente paciente) {  //No se si tenemos que hacerlo mas veces sin que este creado el contacto pongo debajo la funcion que me refiero
        if (!(paciente.getDni().isEmpty()) && !listaPacientes.contains(paciente)) {
            listaPacientes.add(paciente);
            return true;
        }
        return false;
    }
    /* Esta no la tenemos que implementar en esta entrega
    public boolean anadirConsulta(Consulta consulta) {
        if (!() && !agendaConsultas.contains(consulta)) {
            agendaConsultas.add(consulta);
            return true;
        }
        return false;
    }
     */
    public boolean anadirCita(Cita cita) {
        if (!(cita.getPaciente().getDni().isEmpty() && cita.getMedico().getDni().isEmpty()) && !agendaCitas.contains(cita)) {
            agendaCitas.add(cita);
            return true;
        }
        return false;
    }
    public boolean anadirPlantilla(Usuario usuario) {
        if (!(usuario instanceof Paciente) && !plantilla.contains(usuario)) {
            plantilla.add(usuario);
            return true;
        }
        return false;
    }
}
