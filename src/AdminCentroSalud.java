import java.lang.classfile.instruction.ReturnInstruction;

/**
 * Personal de administración de un centro de salud/hospital concreto.
 * Se añade el atributo centro pq lo dice le UML
 */

public class AdminCentroSalud extends Usuario{
    private final String centro; //se supone que no cambia asiq lo ponemos final

    public AdminCentroSalud(String dni, long cipa, String centro) {
        super(dni, cipa);
        if (centro == null || centro.isEmpty()){
            throw new IllegalArgumentException("El centro no puede estar vacío ni ser null.");
        }
        this.centro = centro;
    }

    public String getCentro(){
        return centro;
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
