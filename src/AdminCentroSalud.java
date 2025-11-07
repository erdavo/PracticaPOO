public class AdminCentroSalud extends Usuario{
    private String centro;

    public AdminCentroSalud(String dni, long cipa, String centro) {
        super(dni, cipa);
        this.centro = centro;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
