import java.util.ArrayList;

public class    AgendaCita {
    //esto no es una clase, es un atributo dentro de nuestra clase general
    // y trendremos un ArrayList<> de citas.
    private ArrayList<Cita> arrayListCitas;

    public AgendaCita(){
        arrayListCitas = new ArrayList<>();
    }

    public ArrayList<Cita> getArrayListCitas() {
        return arrayListCitas;
    }


}
