public class Pasaje {

    private String nombre;
    private Vuelo vuelo;
    private int numeroDeReserva; //Una vez creado el pasaje hacer un set con un numero de reserva random
    private int cantAsientosReservados;
    private MetodoDePago metodoDePago;


    public Pasaje(String nombre, Vuelo vuelo, int cantAsientosReservados, MetodoDePago metodoDePago) {
        this.nombre = nombre;
        this.vuelo = vuelo;
        this.cantAsientosReservados = cantAsientosReservados;
        this.metodoDePago = metodoDePago;
    }
}
