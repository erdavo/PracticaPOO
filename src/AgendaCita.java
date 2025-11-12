import java.time.LocalDateTime;
import java.util.ArrayList;

public class AgendaCita {
    private ArrayList<Cita> agendaCitas; //aqui se almacenan todas las citas
    private static final int SLOT_MIN = 30;

    public AgendaCita(){
        agendaCitas = new ArrayList<>();
    }

    public ArrayList<Cita> getArrayListCitas() {
        return agendaCitas;
    }

    public void addCita(Cita cita) {
        if (agendaCitas.contains(cita)){
            throw new IllegalArgumentException("La cita ya está en el sistema.");
        }
        agendaCitas.add(cita);

    }

    public void addCita(LocalDateTime fechaCita, Paciente paciente, Medico medico){
        addCita(new Cita(fechaCita,paciente, medico));
    }

    public ArrayList<Cita> AgendaMedico(Medico medico){
        ArrayList<Cita> aux;
        aux=new ArrayList<>();
        for (int i = 0; i < agendaCitas.size(); i++) {
            if (agendaCitas.get(i).getMedico().equals(medico)) {
                aux.add(agendaCitas.get(i));
            }
        }
        return aux;
    }

}
