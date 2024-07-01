public class Pasaje {

    private String nombre;
    private Vuelo vuelo;
    private int numeroDeReserva;
    private int cantAsientosReservados;
    private MetodoDePago metodoDePago;
    private int montoFinal;


    public Pasaje(String nombre, Vuelo vuelo, int cantAsientosReservados, MetodoDePago metodoDePago) {
        this.nombre = nombre;
        this.vuelo = vuelo;
        this.cantAsientosReservados = cantAsientosReservados;
        this.metodoDePago = metodoDePago;
    }

    public void setNumeroDeReserva(int numeroDeReserva) {
        this.numeroDeReserva = numeroDeReserva;
    }

    public String getNombre() {
        return nombre;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public int getNumeroDeReserva() {
        return numeroDeReserva;
    }

    public int getCantAsientosReservados() {
        return cantAsientosReservados;
    }

    public MetodoDePago getMetodoDePago() {
        return metodoDePago;
    }

    public int getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(int montoFinal) {
        this.montoFinal = montoFinal;
    }
}
