import java.util.ArrayList;

public class ListaPaciente {
    private ArrayList<Paciente> listaPacientes;

    public ListaPaciente(){

        listaPacientes= new ArrayList<>();
        listaPacientes.add(new Paciente("4A", 4234567890L, "Pedro", "Direccion1", 1));
    }

    public ArrayList<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public boolean addPaciente(Paciente paciente) {
        if (!(paciente.getDni().isEmpty()) && !listaPacientes.contains(paciente)) {
            listaPacientes.add(paciente);
            return true;
        }
        return false;
    }
    public boolean addPaciente(String dni, long cipa,String nombre, String direccion, long telefono, AgendaCita cita, Historial historial){
        return addPaciente(new Paciente(dni, cipa, nombre, direccion, telefono));
    }

    // metodos para identificarse en al app.
    public Paciente buscarPorCipa(long cipa) {
        for (Paciente p : listaPacientes) {
            if (cipa == p.getCipa()){
                return p;
            }
        }
        return null;
    }
    public Paciente buscarPorDni(String dni){
        for (Paciente p : listaPacientes) {
            if (dni.equalsIgnoreCase(p.getDni())){
                return p;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ListaPaciente{" +
                "listaPacientes=" + listaPacientes +
                '}';
    }
}
