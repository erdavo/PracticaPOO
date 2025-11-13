import java.util.Scanner;

public class Sistema {
    private ListaPaciente listaPacientes;
    private AgendaConsultas agendaConsultas;
    private Plantilla plantilla;

    public Sistema() {
        this.listaPacientes = new ListaPaciente();
        this.agendaConsultas = new AgendaConsultas();
        this.plantilla = new Plantilla();
    }

    /**
     * Punto de entrada principal: identificación del usuario.
     * Dependiendo del tipo de usuario que sea (mediante el instanceof),
     * se llama al menú correspondiente.
     */
    public void iniciarSesion() {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        do {
            System.out.println("\n=== SISTEMA MiSanidad ===");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");

            //si entra en esto es porque ha introducido algo dif de 1 o 2 y ha saltado el default del switch y por tanto no termina el do while.
            if (!sc.hasNextInt()) { //miramos que lo introducido es un numero
                System.out.println("Opción incorrecta. Introduzca un número.");
                sc.next();
                continue;
            }

            //Lectura de la opcion
            int opcion = sc.nextInt();
            sc.nextLine(); //limpiamos el salto de linea del buffer

            switch (opcion) {
                case 1 -> {
                    Usuario usuario = identificarUsuario(sc);
                    if (usuario == null) {
                        System.out.println("No existe ningún usuario con ese DNI o CIPA. Por favor, vuelva a intentarlo.\n");
                    } else {
                        System.out.println("Bienvenido " + usuario.getDni());
                        mostrarMenuPorTipo(sc, usuario);
                    }
                }
                case 2 -> {
                    System.out.println("Saliendo del sistema...");
                    salir = true;
                }

                default ->
                    System.out.println("Opción incorrecta. Seleccione una opción válida.");

            }

        } while (!salir);
    }

    /**
     * Identifica un usuario pidiendo su DNI o CIPA y devolviendo la instancia de usuario concreta.
     */

    private Usuario identificarUsuario(Scanner sc) {
        System.out.print("\nIntroduzca su DNI o CIPA para identificarse: ");
        String entrada = sc.nextLine().trim();

        // Buscar primero por DNI
        Usuario usuario = plantilla.identificarConDni(entrada);
        if (usuario == null) { //Si no es un usuario de la plantilla, miramos si es paciente
            usuario = listaPacientes.buscarPorDni(entrada);
        }

        // Si no se encontró buscar por CIPA
        if (usuario == null) {
            //Aquí el usuario podría haber introducido por ejemplo 12345678A y al parsear lanzaria una excepcion, por lo que hay que controlar esa excpecion
            try {
                long cipa = Long.parseLong(entrada);
                usuario = plantilla.identificarConCipa(cipa);
                if (usuario == null) {
                    usuario = listaPacientes.buscarPorCipa(cipa);
                }
            } catch (NumberFormatException e){
                //No es un numero, por lo que no es un cipa, por tanto usuario sigue en null.
            }
        }
        return usuario;
    }




    /**
     * Usa instanceof para dirigir al menú que corresponde al tipo de usuario.
     */
    private void mostrarMenuPorTipo(Scanner sc, Usuario usuario) {
        if (usuario instanceof Paciente) {
            menuPaciente(sc, (Paciente) usuario);
        } else if (usuario instanceof AdminCentroSalud) {
            menuAdministracion(sc);
        } else if (usuario instanceof Medico) {
            menuSanitario(sc);
        } else if (usuario instanceof Admin) {
            menuAdministrador(sc);
        } else {
            System.out.println("Tipo de usuario desconocido.");
        }
    }

    // ==================== MENÚ PACIENTE ====================
    private void menuPaciente(Scanner sc, Paciente paciente){
        int opcion = -1;
        do {
            System.out.println("\n--- MENÚ PACIENTE ---");
            System.out.println("1. Solicitar nueva cita");
            System.out.println("2. Modificar teléfono");
            System.out.println("3. Consultar mis datos");
            System.out.println("4. Cancelar cita");
            System.out.println("5. Ver mis citas");
            System.out.println("0. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            if (!sc.hasNextInt()) {
                System.out.println("Opción incorrecta.");
                sc.next();
                continue;
            }

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Ha seleccionado: Solicitar cita médica (implementando).");
                    solicitarCitaMedica(sc, paciente);
                }

                case 2 -> System.out.println("Modificar teléfono (no implementado).");
                case 3 -> System.out.println("Mostrando datos personales...");
                case 4 -> System.out.println("Cancelar cita (no implementado).");
                case 5 -> System.out.println("Mostrando citas programadas (no implementado).");
                case 0 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción incorrecta.");
            }
        } while (opcion != 0);
    }

    // ==================== MENÚ SANITARIO ====================
    private void menuSanitario(Scanner sc) {
        int opcion = -1;
        do {
            System.out.println("\n--- MENÚ PERSONAL SANITARIO ---");
            System.out.println("1. Ver agenda de citas");
            System.out.println("2. Registrar consulta");
            System.out.println("3. Prescribir medicación");
            System.out.println("4. Consultar historial de paciente");
            System.out.println("0. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            if (!sc.hasNextInt()) {
                System.out.println("Opción incorrecta.");
                sc.next();
                continue;
            }

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> System.out.println("Mostrando agenda (no implementado).");
                case 2 -> System.out.println("Registrar consulta médica (no implementado).");
                case 3 -> System.out.println("Prescribir medicación (no implementado).");
                case 4 -> System.out.println("Consultar historial del paciente (no implementado).");
                case 0 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción incorrecta.");
            }
        } while (opcion != 0);
    }

    // ==================== MENÚ ADMINISTRACIÓN ====================
    private void menuAdministracion(Scanner sc) {
        int opcion = -1;
        do {
            System.out.println("\n--- MENÚ ADMINISTRACIÓN ---");
            System.out.println("1. Gestionar datos administrativos de pacientes");
            System.out.println("2. Gestionar citas médicas");
            System.out.println("3. Buscar paciente");
            System.out.println("0. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            if (!sc.hasNextInt()) {
                System.out.println("Opción incorrecta.");
                sc.next();
                continue;
            }

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> System.out.println("Gestión administrativa (no implementado).");
                case 2 -> System.out.println("Gestión de citas (no implementado).");
                case 3 -> System.out.println("Buscar paciente (no implementado).");
                case 0 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción incorrecta.");
            }
        } while (opcion != 0);
    }

    // ==================== MENÚ ADMINISTRADOR ====================
    private void menuAdministrador(Scanner sc) {
        int opcion = -1;
        do {
            System.out.println("\n--- MENÚ ADMINISTRADOR ---");
            System.out.println("1. Alta/Baja de usuarios");
            System.out.println("2. Gestionar citas (todas)");
            System.out.println("3. Consultar/modificar historiales");
            System.out.println("4. Prescribir medicación");
            System.out.println("5. Exportar datos del sistema");
            System.out.println("0. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            if (!sc.hasNextInt()) {
                System.out.println("Opción incorrecta.");
                sc.next();
                continue;
            }

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> System.out.println("Alta/Baja de usuarios (no implementado).");
                case 2 -> System.out.println("Gestión completa de citas (no implementado).");
                case 3 -> System.out.println("Gestión de historiales clínicos (no implementado).");
                case 4 -> System.out.println("Prescribir medicación (no implementado).");
                case 5 -> System.out.println("Exportar datos (no implementado).");
                case 0 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción incorrecta.");
            }
        } while (opcion != 0);
    }


    // ==== LOGICA DEL PACIENTE ====
    private void solicitarCitaMedica(Scanner sc, Paciente p) {
        // teneemos el paciente, ahora tenemos que mostrarle las citas disponibles para que pueda elegir
    }
}
