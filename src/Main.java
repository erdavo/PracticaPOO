import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Prueba lab = new Laboratorio(LocalDateTime.now(), "Hospital Central", "informe_lab.pdf");
        Prueba img = new Imagen(LocalDateTime.now(), "Hospital Central", "informe_img.pdf", "tac001.jpg");

        System.out.println(lab);
        System.out.println(img);
    }
}

