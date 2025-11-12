import javax.tools.JavaCompiler;

/**
 * Representa a un médico.
 * Hereda de Usuario y hay que añadir 'especialidad' y 'centro'.
 * Incluye la constante MAXCITASDIARIAS = 20 (tal como refleja el diagrama).
 */

public class Medico extends Usuario{
    private final String especialidad;
    private final String centro;
    private final int MAXCITASDIARIAS = 20;
    private int numCitasAsignadas;
    private AgendaCita citas;

    public Medico(String dni, long cipa, String centro, String especialidad){
        super(dni,cipa);

        if (especialidad == null || especialidad.isEmpty()){
            throw new IllegalArgumentException("Especialidad no valida.");
        }
        this.especialidad=especialidad;

        if (centro == null || centro.isEmpty()){
            throw new IllegalArgumentException("Centro no valido.");
        }
        this.centro=centro;

        this.citas= new AgendaCita();
    }


    public String getEspecialidad() {
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
