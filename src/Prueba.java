import java.time.LocalDateTime;

public abstract class Prueba {
    private LocalDateTime fecha;
    private String centro;
    private String rutaInforme;


    public Prueba(LocalDateTime fecha, String centro, String rutaInforme){
        this.fecha = fecha;
        this.centro = centro;
        this.rutaInforme = rutaInforme;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getRutaInforme() {
        return rutaInforme;
    }

    public void setRutaInforme(String rutaInforme) {
        this.rutaInforme = rutaInforme;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Centro: " + centro + ", Fecha: " + fecha + ", Informe: " + rutaInforme;
    }
}
