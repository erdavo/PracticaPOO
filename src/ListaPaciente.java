import java.util.ArrayList;

public class ListaPaciente {
    private ArrayList<Paciente> listaPacientes;

    public ListaPaciente(){
        listaPacientes= new ArrayList<Paciente>();
    }

    public ArrayList<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public boolean addPaciente(Paciente paciente) {  //No se si tenemos que hacerlo mas veces sin que este creado el contacto pongo debajo la funcion que me refiero
        if (!(paciente.getDni().isEmpty()) && !listaPacientes.contains(paciente)) {
            listaPacientes.add(paciente);
            return true;
        }
        return false;
    }
    public boolean addPaciente(String dni, long cipa,String nombre, String direccion, long telefono, AgendaCita cita, Historial historial){
        return addPaciente(new Paciente(dni, cipa, nombre, direccion, telefono));
    }
}
