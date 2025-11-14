import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Representa a un paciente.
 * Hereda de Usuario y añade los atributos 'nombre', 'direccion', 'telefono'.
 * Por requisitos, el paciente sólo puede modificar su teléfono (enunciado).
 */

public class Paciente extends Usuario{
    //Datos administrativos
    private String nombre;
    private String direccion;
    private long telefono;

    //Datos clinicos
//    private AgendaCita listaCitasPropias; lo mismo que en paciente TODO QUITARLO
    private Historial historialClinico;

    public Paciente(String dni, long cipa, String nombre, String direccion, long telefono) {
        super(dni, cipa);
        this.nombre = validarStrings(nombre);
        this.direccion = validarStrings(direccion);
        this.telefono = validarTelefono(telefono);
//        this.listaCitasPropias = new AgendaCita(); TODO QUITARLO
        this.historialClinico = new Historial();
    }

    public static Paciente crearPaciente() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Alta de nuevo paciente ---");

        // DNI
        String dni = "";
        while (dni.isEmpty()) {
            System.out.print("DNI: ");
            dni = sc.nextLine().trim();
            if (dni.isEmpty()) {
                System.out.println("Error: el DNI no puede estar vacío.");
            }
        }

        // CIPA
        String cipaStr = "";
        while (true) {
            System.out.print("CIPA (10 dígitos): ");
            cipaStr = sc.nextLine().trim();

            if (cipaStr.length() != 10 || !cipaStr.matches("\\d+")) {
                System.out.println("Error: el CIPA debe tener exactamente 10 dígitos numéricos.");
            } else {
                break;
            }
        }
        long cipa = Long.parseLong(cipaStr);

        // Nombre
        String nombre = "";
        while (nombre.isEmpty()) {
            System.out.print("Nombre completo: ");
            nombre = sc.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("Error: el nombre no puede estar vacío.");
            }
        }

        // Dirección
        String direccion = "";
        while (direccion.isEmpty()) {
            System.out.print("Dirección: ");
            direccion = sc.nextLine().trim();
            if (direccion.isEmpty()) {
                System.out.println("Error: la dirección no puede estar vacía.");
            }
        }

        // Teléfono
        String telefonoStr = "";
        while (true) {
            System.out.print("Teléfono: ");
            telefonoStr = sc.nextLine().trim();


            if (!telefonoStr.matches("\\d+")) {
                System.out.println("Error: el teléfono debe contener solo números.");
            } else {
                break;
            }
        }
        long telefono = Long.parseLong(telefonoStr);


        Paciente nuevo = new Paciente(dni, cipa, nombre, direccion, telefono);
        System.out.println(" Paciente creado correctamente.");
        return nuevo;
    }


    private long validarTelefono(long telefono){
        if (telefono <= 0) throw new IllegalArgumentException("El teléfono tiene que ser positivo.");
        return telefono;
    }

    //getters


    public String getNombre(){
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public long getTelefono() {
        return telefono;
    }
    //public Cita getCita() {return cita;}

    public Historial getHistorial() {
        return historialClinico;
    }

    //Setters

    //cualquier paciente puede modificar su propio número de telefono
    public void setNombre(String nombre) {
        this.nombre = validarStrings(nombre);
    }

    //Es necesario ser AdminCentroSalud para poder hacer cualquiera de los siguientes metodos setters.
    /**
     * Definimos un método auxiliar que compruebe que el usuario que llama a los setters protegidos sea ACS o Admin
     * Ademas como el enunciado dice:
     * "El administrador de la aplicación:
     * - Tendrá acceso a poder realizar cualquier operación sobre la aplicación."
     * Hemos añadido la comprobacion correspondiente: validarAdminCentroSalud.
     */
    private void validarAdminCentroSalud(Usuario usuario) {
        if (!(usuario instanceof Admin || usuario instanceof AdminCentroSalud)) {
            throw new IllegalArgumentException("El usuario no tiene permisos para realizar esta operación. " +
                    "Comuníquese con su centro de salud para modificar datos importantes.");
        }
    }

    // Aplicamos la validación de usuario a ambos métodos
    public void setDireccion(Usuario usuario, String direccion) {
        validarAdminCentroSalud(usuario);
        this.direccion = direccion;
    }
    public void setTelefono(Usuario usuario, long telefono) {
        validarAdminCentroSalud(usuario);
        this.telefono = validarTelefono(telefono);
    }

    public String getDatosAdministrativos() {
        return "Nombre: " + nombre + "\n" +
                "DNI: " + getDni() + "\n" +
                "CIPA: " + getCipa() + "\n" +
                "Teléfono: " + telefono + "\n" +
                "Dirección: " + direccion;
    }

    public String getDatosClinicos() {
        return historialClinico.toString();
    }


}
