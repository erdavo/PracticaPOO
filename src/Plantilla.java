import java.io.Serializable;
import java.util.ArrayList;

public class Plantilla implements Serializable {

    private ArrayList<Usuario> plantilla;

    public Plantilla(){
        plantilla= new ArrayList<Usuario>();
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
}
