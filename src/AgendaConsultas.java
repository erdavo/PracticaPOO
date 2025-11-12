import java.util.ArrayList;

public class AgendaConsultas {
    //esto no es una clase, es un atributo dentro de nuestra clase general
    // y trendremos un ArrayList<> de Consultas.

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
