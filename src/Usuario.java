public abstract class Usuario{
    private static final int MAXCIPA=10;
    private static final int MAXDNI=9;
    private String dni;
    private long cipa;

    public Usuario(String dni, long cipa){
        if(dni.length() == MAXDNI){
            this.dni = dni;
        } else{
            throw new IllegalArgumentException("Error en DNI, por favor, compruebe que el DNI intriducido es valido.");
        }
        if (cipa >= 1000000000 && cipa <= 9999999999L){
            this.cipa=cipa;
        } else{
            throw new IllegalArgumentException("Error en CIPA, por favor, compruebe que el CIPA intriducido es valido.");
        }
    }
    @Override
    public String toString(){
        return System.out.println("\tDNI: "+dni+"\tCIPA: "+ cipa);
    }
}