public class Medico extends Usuario{
    private String especialidad;
    private String centro;
    private final int MAXCITASDIARIAS=20;
    private Cita[] citas;

    public Medico(String dni, long cipa, String centro, String especialidad){
        super(dni,cipa);
        this.especialidad=especialidad;
        this.centro=centro;
        this.citas= new Cita[MAXCITASDIARIAS];
    }
    public String getEspecialidad() {
        return especialidad;
    }

    public String getCentro() {
        return centro;
    }
}
