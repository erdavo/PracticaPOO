import java.io.Serializable;
import java.util.ArrayList;

public class Plantilla implements Serializable {

    private ArrayList<Usuario> plantilla;
    public Plantilla(){
        plantilla= new ArrayList<>();
        // Usuarios de ejemplo cargados por defecto
        plantilla.add(new Admin("1", 1234567890L));                          // Admin general
        plantilla.add(new AdminCentroSalud("2", 2234567890L, "Centro Norte")); // Admin de centro
        plantilla.add(new Medico("3", 3234567890L, "Centro Norte", "Cardiología")); // Médico


    }
    public ArrayList<Usuario> getPlantilla() {
        return plantilla;
    }

    public boolean anadirPlantilla(Usuario usuario) {
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

}
