import java.time.LocalDateTime;
import java.util.ArrayList;

public class AgendaCita {
    private ArrayList<Cita> agendaCitas;
    private static final int TIEMPO_CITA = 30;
    private static final int DIAS_BUSQUEDA = 2;

    public AgendaCita(){
        agendaCitas = new ArrayList<>();
    }

    public ArrayList<Cita> getArrayListCitas() {
        return agendaCitas;
    }

    public void addCita(Cita cita) {
        if(agendaCitas.contains(cita)){
            throw new IllegalArgumentException("La cita ya existe.");
        }
        agendaCitas.add(cita);
    }

    public void addCita(LocalDateTime fechaCita, Paciente paciente, Medico medico){
        addCita(new Cita(fechaCita,paciente, medico));
    }

    /** Este metodo recorre el arraylist, mirando que horas están disponibles,
     * pero no dice que medico es, por lo que hay que buscarlo luego
     * */
    public boolean estaLibre(Medico medico, LocalDateTime fecha) {
        for (Cita c : agendaCitas){
            //tenemos que mirar que el medico sea el mismo, y la misma fecha y que no este anulada
            if (c.getMedico().equals(medico) && c.getFechaCita().equals(fecha) && !c.isAnulada()){
                return false; // esta ocupada
            }
        }
        return true; // No se ha encontrado ninguna cita, por tanto está libre
    }

    /** Dada una lista de medicos de una misma especialidad, devuelve todas las horas en las
     * que al menos un médico de esos está libre en los proximos 7 días, por ejemplo
     */
    //TODO LO HE HECHO EN UN MOMENTO DE LUCIDED, NO ENTIENDO MUY BIEN COMO FUNCIONA, PERO FUNCIONA QUE ES LO IMPORTANTE
    public ArrayList<LocalDateTime> obtenerHuecosDisponibles(ArrayList<Medico> medicos) {

        ArrayList<LocalDateTime> huecos = new ArrayList<>();

        //para no dar citas desde el mismo momento en el que se llama al metodo le sumamos TIEMPO_CITA
        LocalDateTime ahora = siguienteHueco(LocalDateTime.now());

        for (int dia = 1; dia < DIAS_BUSQUEDA; dia++) {

            // Día que estamos analizando
            LocalDateTime fechaDia = ahora.toLocalDate().plusDays(dia).atStartOfDay();

            // Horarios laborales
            LocalDateTime inicio = fechaDia.withHour(7).withMinute(30);
            LocalDateTime fin = fechaDia.withHour(17).withMinute(0);

            // con esto evitamos dar horas ANTES de ahora, suena enrevesado, pero no puedes dar una cita que ya ha pasado.
            if (dia == 0 && inicio.isBefore(ahora)) {
                inicio = ahora; // esto hace que el while no se ejecute, y pase al siguiente dia
            }

            LocalDateTime actual = inicio;

            while (!actual.isAfter(fin)) {

                //miramos si para el hueco hay almenos un medico disponible
                for (Medico m : medicos) {
                    // contar las citas del medico
                    int citasDia = 0;
                    for (Cita c : agendaCitas) {
                        if (!c.isAnulada()
                                && c.getMedico().equals(m)
                                && c.getFechaCita().toLocalDate().equals(actual.toLocalDate())) {
                            citasDia++;
                        }
                    }

                    // Si ya ha alcanzado el máximo de citas, ignorarlo para el resto de huecos del día
                    if (citasDia >= m.getMAXCITASDIARIAS()) continue;

                    // Si está libre, añadimos hueco y pasamos al siguiente
                    if (estaLibre(m, actual)) {
                        huecos.add(actual);
                        break;
                    }
                }

                actual = actual.plusMinutes(TIEMPO_CITA);
            }
        }

        return huecos;
    }

    /**
     * Este metodo privado hace que si esta solicitando la cita a las 11:07, te
     */
    private LocalDateTime siguienteHueco(LocalDateTime t) {
        int minutoActual = t.getMinute();

        if (minutoActual == 0 || minutoActual == 30) {
            return t;
        }

        //si es menos que y media, se puede solicitar la cita de y media
        if (minutoActual < 30) {
            return t.withMinute(30).withSecond(0).withNano(0);
        }

        // si los minutos son mas 30: pasar a hora siguiente
        return t.plusHours(1).withMinute(0).withSecond(0).withNano(0);
    }

    public ArrayList<Cita> buscarCitasPaciente(Paciente paciente){
        ArrayList<Cita> citasPaciente = new ArrayList<>();
        for(Cita c: agendaCitas){
            if (c.getPaciente().equals(paciente)) {
                citasPaciente.add(c);
            }
        }
        return citasPaciente;
    }

    public void mostrarCitasPaciente(ArrayList<Cita> arrayListCitas){
        //LLamar al metodo de buscar citas de paciente para crear un arraylist
        //Mostar todas las citas del paciente

        for (int i = 0; i < arrayListCitas.size(); i++) {
            System.out.println((i + 1) + ". " + arrayListCitas.get(i));
        }

    }

    public void eliminarCita(Cita cita){
        for (Cita c: agendaCitas){
            if(c.equals(cita)){
                c.cancelarCita("Modificar");
            }
        }
    }

    // =========== LOGICA DE ADMIN ========
}
