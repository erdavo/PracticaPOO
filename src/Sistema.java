    import javax.management.Notification;
    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;
    import java.util.ArrayList;
    import java.util.Scanner;

    public class Sistema {
        private ListaPaciente listaPacientes;
        private AgendaConsultas agendaConsultas;
        private AgendaCita agendaCitas;
        private Plantilla plantilla;

        private static final DateTimeFormatter FMT_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        public Sistema() {
            this.listaPacientes = new ListaPaciente();
            this.agendaConsultas = new AgendaConsultas();
            this.plantilla = new Plantilla();
            this.agendaCitas = new AgendaCita();
        }

        public void inicarPrograma(){
            iniciarSesion();
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
                            System.out.println("Bienvenido " + usuario);
                            mostrarMenuPorTipo(sc, usuario);
                        }
                    }
                    case 2 -> {
                        System.out.println("Saliendo del sistema...");
                        sc.close();
                        salir = true;
                    }

                    default ->
                        System.out.println("Opción incorrecta. Seleccione una opción válida.");

                }

            } while (!salir);
        }

        /**
         * Metodo privado que se utiliza en inciarSesion().
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
                menuAdminCentroSalud(sc, (AdminCentroSalud) usuario);
            } else if (usuario instanceof Medico) {
                menuMedico(sc, (Medico) usuario);
            } else if (usuario instanceof Admin) {
                menuAdministrador(sc, (Admin) usuario);
            } else {
                System.out.println("Tipo de usuario desconocido.");
            }
        }

        // Menú del paciente
        private void menuPaciente(Scanner sc, Paciente paciente){
            int opcion = -1;
            do {
                System.out.println("\n--- MENÚ PACIENTE ---");
                System.out.println("1. Solicitar una nueva cita");
                System.out.println("2. Modificar mis citas");
                System.out.println("3. Cancelar cita");
                System.out.println("4. Ver mis citas");
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
                        System.out.println("Ha seleccionado: Solicitar cita médica (implementado).");
                        solicitarCitaMedica(sc, paciente);
                    }

                    case 2 -> {
                        System.out.println("Ha seleccionado: Modificar cita (implementado).");
                        modificarCitaConMismoMedico(sc, paciente);
                    }
                    case 3 -> {
                        System.out.println("Cancelar cita (implementando).");
                        cancelarCitaMedica(sc, paciente);
                    }
                    case 4 -> {
                        System.out.println("Ha seleccionado mostrar mis citas. (implementado).");

                        if(agendaCitas.obtenerCitas(paciente).isEmpty()){
                            System.out.println("No hay citas programadas todavía.");
                        }
                        agendaCitas.mostrarCitas(agendaCitas.obtenerCitas(paciente));
                    }
                    case 0 -> System.out.println("Cerrando sesión...");
                    default -> System.out.println("Opción incorrecta.");
                }
            } while (opcion != 0);
        }

        // Menu del medico
        private void menuMedico(Scanner sc, Medico medico){
            int opcion = -1;
            do {
                System.out.println("\n--- MENÚ PERSONAL SANITARIO ---");
                System.out.println("1. Ver agenda de citas");
                System.out.println("2. Reagendar todas las citas de un dia completo a otro"); //A cada cita se le pregunta si quiere modificarla en caso de que si lo unico que se tiene que hace es llamar a modificarfecha
                // pedir al medico un dia concreto y verificar citas ese dia para el
                // modificas cada una de las citas que existan (estrán almacenadas en un array o tal vez solo cada vez qeu llegues a la fecha de un dic concreto que se modifique)
                // se notifica el cambio ("Se muestra un mensaje por pantalla diciendo "Se ha modificado la cita para x paciente"")
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
                    case 1 -> {
                        System.out.println("Mostrando agenda (implementado).");
                        if(agendaCitas.obtenerCitas(medico).isEmpty()){
                            System.out.println("No hay citas programadas todavía.");
                        }
                        agendaCitas.mostrarCitas(agendaCitas.obtenerCitas(medico));
                    }
                    case 2 -> System.out.println("Registrar consulta médica (no implementado).");
                    case 3 -> System.out.println("Prescribir medicación (no implementado).");
                    case 4 -> System.out.println("Consultar historial del paciente (no implementado).");
                    case 0 -> System.out.println("Cerrando sesión...");
                    default -> System.out.println("Opción incorrecta.");
                }
            } while (opcion != 0);
        }

        // Menú del AdminCentroSalud
        private void menuAdminCentroSalud(Scanner sc, AdminCentroSalud adminCentroSalud) {
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
        private void menuAdministrador(Scanner sc, Admin admin) {
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


        // Logica del paciente
        private void solicitarCitaMedica(Scanner sc, Paciente paciente) {
            // mostramos especialidades
            System.out.println("Estas son las especialidades disponibles:");
            mostrarEspecialidades();

            int opcion;
            do {
                System.out.print("Introduce una opción válida: ");
                while (!sc.hasNextInt()) {
                    System.out.println("Entrada no válida. Debes introducir un número.");
                    System.out.print("Elige una opción: ");
                    sc.next();
                }

                opcion = sc.nextInt();
                sc.nextLine();

                if (opcion <= 0 || opcion > Especialidad.values().length) {
                    System.out.println("Opción fuera de rango. Intente de nuevo.");
                }

            } while (opcion <= 0 || opcion > Especialidad.values().length);


            //almacenamos la que sea que nos diga
            Especialidad especialidad = Especialidad.values()[opcion -1];


            // tenemos que mostrar las citas disponibles para que pueda elegir
            ArrayList<Medico> listMedicos = plantilla.getMedicosPorEspecialidad(especialidad);
            if(listMedicos.isEmpty()){
                System.out.println("No hay médicos disponibles de esta especialidad.");
                return;
            }
            //buscamos por cada médico los huecos disponibles
            ArrayList<LocalDateTime> huecosDisponibles = agendaCitas.obtenerHuecosDisponibles(listMedicos);

            if (huecosDisponibles.isEmpty()) {
                System.out.println("No hay citas disponibles en este momento.");
                return;
            }

            //mostramos que huecos hay.
            System.out.println("\nCitas disponibles:");

            for (int i = 0; i < huecosDisponibles.size(); i++) {
                System.out.println((i + 1) + ". " + huecosDisponibles.get(i).format(FMT_FECHA));
            }

            // Le pedimos al usuario uno de los huecos disponibles ¡OJO!: Esto todavía no es una cita
            System.out.print("Seleccione una opción: ");
            int citaElegida;
            do {
                citaElegida = sc.nextInt();
                sc.nextLine();
            } while(citaElegida <= 0 || citaElegida > huecosDisponibles.size());

            //guardamos la hora que ha seleccionado el usuario
            LocalDateTime fechaElegida = huecosDisponibles.get(citaElegida-1);

            //buscamos que medico tiene la hroa libre y el primero que encuentre con la hora libre, lo guardamos
            Medico medicoAsignado = null;
            for (Medico m : listMedicos){
                if (agendaCitas.estaLibre(m, fechaElegida)){
                    medicoAsignado = m;
                    break;
                }
            }

            if (medicoAsignado == null) {
                System.out.println("Error al asignar médico. Intente más tarde.");
                return;
            }

            Cita nuevaCita = new Cita(fechaElegida, paciente, medicoAsignado);
            agendaCitas.addCita(nuevaCita);
            System.out.println("Cita creada correctamente:");
            System.out.println("Fecha: " + fechaElegida.format(FMT_FECHA));
            System.out.println("Médico: " + medicoAsignado);


        }

        private void modificarCitaConMismoMedico(Scanner sc, Paciente paciente){

            Cita citaAntigua = seleccionarCitaConcretaDePaciente(sc, paciente);
            if (citaAntigua == null) return;

            Medico medicoCita = citaAntigua.getMedico();

            ArrayList<Medico> soloEseMedico = new ArrayList<>();
            soloEseMedico.add(medicoCita);
            ArrayList<LocalDateTime> huecosDeEseMedico =  agendaCitas.obtenerHuecosDisponibles(soloEseMedico);

            if(huecosDeEseMedico.isEmpty()){
                System.out.println("Este medico no tiene huecos disponibles. No puede modificar la cita");
                return;
            }

            System.out.println("Estas con las dictas con su mismo médico: ");

            for (int i = 0; i < huecosDeEseMedico.size(); i++) {
                System.out.println((i+1) + ". " + huecosDeEseMedico.get(i).format(FMT_FECHA));
            }

            System.out.println("\n Indique la nueva hora de la cita: ");
            int opcion;
            do {
                opcion = sc.nextInt();
                sc.nextLine();
            } while (opcion <= 0 || opcion > huecosDeEseMedico.size());

            LocalDateTime huecoNuevo = huecosDeEseMedico.get(opcion-1);
//            for (Cita c : agendaCitas.getArrayListCitas()) {
//                if (c.equals(citaAntigua)){
//                    c.setFechaCita(huecoNuevo);
//                    agendaCitas.getArrayListCitas().set(pos , c);
//                }
//                pos++;
//            }
            for (int pos = 0; pos < agendaCitas.getArrayListCitas().size(); pos++) {
                Cita c = agendaCitas.getArrayListCitas().get(pos);
                if (c.equals(citaAntigua)){
                    c.setFechaCita(huecoNuevo);
                    agendaCitas.getArrayListCitas().set(pos , c);
                }
            }
            System.out.println("\n La cita ha sido modificada correctamente. Gracias");

        }

        private void cancelarCitaMedica(Scanner sc, Paciente paciente) {
            Cita cita = seleccionarCitaConcretaDePaciente(sc, paciente);
            if (cita == null) return;

            System.out.println("¿Seguro que desea cancelar esta cita? (y/n)");
            String confirm = sc.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                System.out.println("Por favor, especifique el motivo de la cancelación: ");
                String motivo = sc.nextLine();
                cita.cancelarCita(motivo);
                System.out.println("La cita ha sido cancelada correctamente.");
            } else {
                System.out.println("Operación cancelada.");
            }
        }

        /** Metodo privado que hemos sacado de abstraer partes comunes en modificarCita y cancelarCita
         *
         *
         * */
        private Cita seleccionarCitaConcretaDePaciente(Scanner sc, Paciente paciente) {
            ArrayList<Cita> citasPaciente = agendaCitas.obtenerCitas(paciente);
            if (citasPaciente.isEmpty()) {
                System.out.println("No tienes citas todavía.");
                return null;
            }
            agendaCitas.mostrarCitas(citasPaciente);

            int opcion;
            do {
                System.out.print("Introduzca una opción, si desea volver atrás, pulse 0: ");

                while (!sc.hasNextInt()) {
                    System.out.println("Entrada no válida. Debe introducir un número.");
                    sc.next(); // limpiar
                }

                opcion = sc.nextInt();
                sc.nextLine();

                if (opcion == 0) {
                    System.out.println("Volviendo atrás...");
                    return null;
                }

                if (opcion < 1 || opcion > citasPaciente.size()) {
                    System.out.println("Opción fuera de rango.");
                    continue;
                }

                if (citasPaciente.get(opcion - 1).isAnulada()) {
                    System.out.println("La cita está anulada. Elija otra.");
                    continue;
                }

                return citasPaciente.get(opcion - 1);

            } while (true); //para que se ejecute todo el rato, y que solo pueda salir con 0 o una opcion dentro del rango
        }


        // Métodos adicionales
        private void mostrarEspecialidades() {
            int i = 1;
            for (Especialidad e : Especialidad.values()) {
                System.out.println(i + ". " + e);
                i++;
            }
        }
    }
