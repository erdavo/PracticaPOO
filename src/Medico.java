import javax.tools.JavaCompiler;

/**
 * Representa a un médico.
 * Hereda de Usuario y hay que añadir 'especialidad' y 'centro'.
 * Incluye la constante MAXCITASDIARIAS = 20 (tal como refleja el diagrama).
 */

public class Medico extends Usuario{
    private final Especialidad especialidad;
    private final String centro;
    private final int MAXCITASDIARIAS = 20;
    // private AgendaCita citas; esto no porque el medico no tiene una agenda propia, solo visualiza la agenda general
    // por ejemplo, en un hospital, cada medico no tiene una agenda con sus citas, se mete al ordenador y
    // mira las citas de una base de datos de todas las citas

    public Medico(String dni, long cipa, String centro, Especialidad especialidad){
        super(dni,cipa);
        this.especialidad=especialidad;
        if (centro == null || centro.isEmpty()){
            throw new IllegalArgumentException("Centro no valido.");
        }
        this.centro=centro;
//        this.citas= new AgendaCita(); por consecuente con lo del atributo, esto tampoco
    }


    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public String getCentro() {
        return centro;
    }

    public int getMAXCITASDIARIAS() {
        return MAXCITASDIARIAS;
    }

    @Override
    public String toString(){
        return "Medico: { " +
                "  Dni:" + getDni() +
                ", Cipa: " + getCipa() +
                ", Especialidad: " + getEspecialidad() +
                ", Centro: " + getCentro() + " }";
    }


}
