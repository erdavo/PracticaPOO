import java.time.LocalDateTime;

public class Laboratorio extends Prueba{

    public Laboratorio(LocalDateTime fecha, String centro, String rutaInforme){
        super(fecha, centro, rutaInforme);
    }


    @Override
    public String toString(){
        return "Prueba de laboratorio -> " + super.toString();
    }
}
