import java.time.LocalDateTime;

public class Imagen extends Prueba{
    private String rutaImagen;

    public Imagen(LocalDateTime fecha, String centro, String rutaInforme, String rutaImagen){
        super(fecha, centro, rutaInforme);
        this.rutaImagen = rutaImagen;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    @Override
    public String toString(){
        return  super.toString() + "Ruta imagen: " + rutaImagen;
    }
}
