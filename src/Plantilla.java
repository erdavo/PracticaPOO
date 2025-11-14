import java.io.Serializable;
import java.util.ArrayList;

public class Plantilla implements Serializable {
    private final ArrayList<Usuario> plantilla;

    public Plantilla(){
        plantilla= new ArrayList<>();
        // Usuarios de ejemplo cargados por defecto
        plantilla.add(new Admin("1", 1234567890L)); // Administrador general
        plantilla.add(new AdminCentroSalud("2", 2234567890L, "Centro Norte")); // Admin de un centro de salud
        plantilla.add(new Medico("3", 3234567890L, "Centro Norte", Especialidad.CARDIOLOGIA, "NombreMedico")); // Médico


    }
    public ArrayList<Usuario> getPlantilla() {
        return plantilla;
    }

    public boolean anadirUsuarioAPlantilla(Usuario usuario) {
        if (!(usuario instanceof Paciente) && !plantilla.contains(usuario)) {
            plantilla.add(usuario);
            return true;
        }
        return false;
    }

    public Usuario identificarConCipa(long cipa) {
        for (Usuario u : plantilla) {
            if (cipa == u.getCipa()){
                return u;
            }
        }
        return null;
    }
    public Usuario identificarConDni(String dni){
        for (Usuario u : plantilla) {
            if (dni.equalsIgnoreCase(u.getDni())){
                return u;
            }
        }
        return null;
    }

    public ArrayList<Medico> getMedicosPorEspecialidad(Especialidad especialidad){
        ArrayList<Medico> list = new ArrayList<>();
        for(Usuario u : plantilla){
            if (u instanceof Medico medico){// es lo mismo que luego castear u a Medico
                if (medico.getEspecialidad().equals(especialidad)){
                    list.add(medico);
                }
            }
        }
        return list;
    }

}
