import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    private ListaPaciente listaPacientes;
    private AgendaConsultas agendaConsultas;
    private AgendaCita agendaCitas;
    private Plantilla plantilla;

    public Sistema() {
        this.listaPacientes = new ListaPaciente();
        this.agendaConsultas = new AgendaConsultas();
        this.agendaCitas = new AgendaCita();
        this.plantilla = new Plantilla();
    }

    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n====== SISTEMA MiSanidad ======");
            System.out.println("1. Añadir paciente");
            System.out.println("2. Solicitar cita médica");
            System.out.println("3. Mostrar lista de pacientes");
            System.out.println("4. Mostrar citas programadas");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            while (!sc.hasNextInt()) {
                System.out.print("Opción no válida. Introduce un número: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    anadirPaciente();
                    break;
                case 2:
                    System.out.print("Introduce el CIPA o DNI del paciente: ");
                    String identificador = sc.nextLine();
                    System.out.print("Introduce la especialidad (Ej. Cardiología, Medicina General...): ");
                    String especialidad = sc.nextLine();
                    System.out.println("Funcionalidad 'solicitar cita' todavía no implementada.");
                    break;
                case 3:
                    System.out.println("Funcionalidad 'mostrar lista de pacientes' todavía no implementada.");
                    break;
                case 4:
                    System.out.println("Funcionalidad 'mostrar citas programadas' todavía no implementada.");
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

        } while (opcion != 5);
    }

    public void anadirPaciente() {
        System.out.println("Funcionalidad 'añadir paciente' todavía no implementada.");
    }

    public void solicitarCita(Paciente paciente, String especialidad) {
        System.out.println("Funcionalidad 'solicitar cita' todavía no implementada.");
    }

    /*
     * Métodos para añadir elementos a los ArrayList
     *
     * Esta parte no se implementa en esta entrega.
     *
     * public boolean anadirConsulta(Consulta consulta) {
     *     if (!() && !agendaConsultas.contains(consulta)) {
     *         agendaConsultas.add(consulta);
     *         return true;
     *     }
     *     return false;
     * }
     */
}
