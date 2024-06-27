public class Pasaje {

    private String nombre;
    private Vuelo vuelo;
    private int numeroDeReserva;
    private int cantAsientosReservados;
    private String metodoDePago;
    private boolean PagoConfirmado;


    public Pasaje(String nombre, Vuelo vuelo) {
        this.nombre = nombre;
        this.vuelo = vuelo;
    }
}
