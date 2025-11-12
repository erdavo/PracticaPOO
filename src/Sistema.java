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
     * Dependiendo del tipo de usuario identificado (instanceof),
     * se llama a su menú correspondiente.
     */
    public void registrarse() {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        do {
            System.out.println("\n=== SISTEMA MiSanidad ===");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");

            if (!sc.hasNextInt()) {
                System.out.println("Opción incorrecta. Introduzca un número.");
                sc.next();
                continue;
            }

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    Usuario usuario = identificarUsuario(sc);
                    if (usuario == null) {
                        System.out.println("❌ Usuario no encontrado. Intente de nuevo.");
                    } else {
                        System.out.println("✅ Bienvenido " + usuario.getDni());
                        mostrarMenuPorTipo(sc, usuario);
                    }
                }
                case 2 -> {
                    System.out.println("Saliendo del sistema...");
                    salir = true;
                }
                default -> System.out.println("Opción incorrecta.");
            }

        } while (!salir);
    }

    /**
     * Identifica un usuario pidiendo su DNI o CIPA y devolviendo la instancia concreta.
     * Luego podrás implementar aquí la búsqueda real en tus estructuras.
     */

    private Usuario identificarUsuario(Scanner sc) {
        System.out.print("\nIntroduzca su DNI o CIPA: ");
        String entrada = sc.nextLine().trim();

        // Buscar primero por DNI
        Usuario usuario = plantilla.identificarConDni(entrada);
        if (usuario == null) {
            usuario = listaPacientes.buscarPorDni(entrada);
        }

        // Si no se encontró y la entrada es numérica, buscar por CIPA
        if (usuario == null) {
            long cipa = Long.parseLong(entrada);
            usuario = plantilla.identificarConCipa(cipa);
            if (usuario == null) {
                usuario = listaPacientes.buscarPorCipa(cipa);
            }
        }
        System.out.println("Has iniciado sesion con el usuario" + usuario);
        return usuario;
    }




    /**
     * Usa instanceof para dirigir al menú que corresponde al tipo de usuario.
     */
    private void mostrarMenuPorTipo(Scanner sc, Usuario usuario) {
        if (usuario instanceof Admin) {
            menuAdministrador(sc);
        } else if (usuario instanceof AdminCentroSalud) {
            menuAdministracion(sc);
        } else if (usuario instanceof Medico) {
            menuSanitario(sc);
        } else if (usuario instanceof Paciente) {
            menuPaciente(sc);
        } else {
            System.out.println("Tipo de usuario desconocido.");
        }
    }

    // ==================== MENÚ PACIENTE ====================
    private void menuPaciente(Scanner sc) {
        int opcion = -1;
        do {
            System.out.println("\n--- MENÚ PACIENTE ---");
            System.out.println("1. Consultar mis datos");
            System.out.println("2. Modificar teléfono");
            System.out.println("3. Solicitar cita médica");
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
                case 1 -> System.out.println("Mostrando datos personales...");
                case 2 -> System.out.println("Modificar teléfono (no implementado).");
                case 3 -> System.out.println("Solicitar cita médica (no implementado).");
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
}
