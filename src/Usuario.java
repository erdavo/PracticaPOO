public abstract class Usuario{
    private static final int MAXDNI=9;
    private String dni;
    private long cipa;

    public Usuario(String dni, long cipa){
        if(dni == null || dni.length() != MAXDNI){
            throw new IllegalArgumentException("Error en DNI, por favor, compruebe que el DNI intriducido es valido.");
        }
        this.dni = dni;

        if (cipa < 1000000000L || cipa > 9999999999L) {
            throw new IllegalArgumentException("Error en CIPA, por favor, compruebe que el CIPA intriducido es valido.");
        }
        this.cipa=cipa;

    }

    //Getters
    public String getDni() {
        return dni;
    }
    public long getCipa() {
        return cipa;
    }

    //Setters
    public void setDni(String dni) {
        this.dni = dni;
    }
    public void setCipa(long cipa) {
        this.cipa = cipa;
    }

    @Override
    public String toString(){
        return "\nDNI: " + dni + "\nCIPA: " + cipa;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        Usuario usuario = (Usuario) obj;
        return usuario.getDni().equalsIgnoreCase(this.getDni());
        //he puesto IgnoreCase para que si tenemos 12345678A y 12345678a,
        // sean lo mismo sin tener que cambiar de minus a mayus
    }
}