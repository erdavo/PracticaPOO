import java.util.ArrayList;

public class AgendaConsultas {
    private ArrayList<Consulta> agendaConsultas;
    public AgendaConsultas(){
        agendaConsultas= new ArrayList<>();
    }
    public ArrayList<Consulta> getAgendaConsultas() {
        return agendaConsultas;
    }


    /* De momento esto no lo tenemos que implementar
    public boolean anadirConsulta(Consulta consulta) {
        if (!() && !agendaConsultas.contains(consulta)) {
            agendaConsultas.add(consulta);
            return true;
        }
        return false;
    }

     */
}
