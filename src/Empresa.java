import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Empresa {

    private HashSet<Vuelo> listaVuelos;
    private HashSet<Pasaje> listaPasajes;

    public Empresa() {
        listaVuelos = new HashSet<>();
        listaPasajes = new HashSet<>();
    }

    public void guardarVuelo(Vuelo vuelo) {
        listaVuelos.add(vuelo);
    }

    public void mostrarVuelo() {
        System.out.println(listaVuelos);
    }

    public void mostrarPasajes(){
        System.out.println(listaPasajes);
    }



    public Set<Vuelo> filtrarPorFecha(String fecha) {
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getFecha().equals(fecha))
                .collect(Collectors.toSet());
    }

    public Set<Vuelo> filtrarOrigenDestino(Ciudad origen, Ciudad destino) {
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getOrigen().equals(origen) && vuelo.getDestino().equals(destino))
                .collect(Collectors.toSet());
    }

    public Set<Vuelo> filtrarPorDuracion(int duracionMaxima) {
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getDuracion() <= duracionMaxima).
                collect(Collectors.toSet());
    }


    public Set<Vuelo> filtrarPorAerolinea(Aerolinea aerolinea) {
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getAerolinea().equals(aerolinea))
                .collect(Collectors.toSet());

    }

    public Set<Vuelo> filtrarPorEscalas(int cantEscalas) {
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getEscalas() <= cantEscalas).
                collect(Collectors.toSet());
    }


    public void solicitarPasaje(int idVuelo, String persona, int cantidadPasajes, MetodoDePago metodoSeleccionado) { //aca pido la cantidad de pasajes que voy a querer, muestro el precio total y confirmo la compra

        Optional<Vuelo> optionalVuelo = getVueloByID(idVuelo);
        Vuelo vuelo = optionalVuelo.orElseThrow();
        int precioAPagar = mostrarPrecios(vuelo,cantidadPasajes);
        boolean pagoConfirmado = verificarPago(precioAPagar,metodoSeleccionado);

        if (pagoConfirmado){
            Pasaje pasajeNuevo = generarPasaje(vuelo, persona, cantidadPasajes, metodoSeleccionado);
        } else {
            System.out.println("El pago no fue realizado correctamente. Intentenlo nuevamente por favor.");
        }

    }

    //Orden del proceso:
    //Verifico el id recibido por parametro
    //Si existe, muestro el precio y realizo la comprobacion del pago
    //Si el pago se hizo correctamente imprimo el pasaje
    //Lo guardo en mi set de pasajes

    public int mostrarPrecios(Vuelo vueloSeleccionado, int cantPasajes) { //Voy a mostrar los precios disponibles y espero recibir un 1 2 o 3 dependiendo del asiento deseado

        System.out.println("Los precios de asientos para su vuelo seleccionado son:");
        System.out.println("");
        System.out.println("1) Precio Economy: " + vueloSeleccionado.getPrecioEconomy());
        System.out.println("2) Precio Super: " + vueloSeleccionado.getPrecioSuper());
        System.out.println("3) Precio Primera: " + vueloSeleccionado.getPrecioPrimera());
        System.out.println("-----------------------------------------------------------");
        System.out.print("Seleccione el tipo de asiento (1, 2, o 3): ");

        int precioFinal = devolverPrecio(vueloSeleccionado,cantPasajes);
        System.out.println(" ");
        System.out.println("Su precio final es de: " + precioFinal + " para " + cantPasajes + " pasajes con nivel de equipaje " + vueloSeleccionado.getEquipajeVuelo() );
        System.out.println(" ");
        return  precioFinal;


    }
    private int devolverPrecio(Vuelo vueloSeleccionado, int cantAsientos){
        int precio = 0;
        int seleccion = opcionSeleccionada();
        if (seleccion == 1){
            precio = vueloSeleccionado.getPrecioEconomy();
        } else if (seleccion == 2) {
            precio = vueloSeleccionado.getPrecioSuper();
        } else {
            precio = vueloSeleccionado.getPrecioPrimera();
        }
        int precioFinal = precio * cantAsientos;
        return precioFinal;
    }

    public int opcionSeleccionada(){
        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

            while (!entradaValida) {
                if (scanner.hasNextInt()) {
                    seleccion = scanner.nextInt();
                    if (seleccion >= 1 && seleccion <= 3) {
                        entradaValida = true;
                    } else {
                        System.out.println("El numero ingresado no es valido. Por favor, ingrese 1, 2, o 3 ");
                    }
                } else {
                    System.out.println("La opcion ingresada no es valida. Por favor, ingrese un número.");
                    scanner.next();
                }
            }
            return seleccion;
        }


    public boolean verificarPago(int precio, MetodoDePago metodoDePago){ //Aca deberia ir el proceso de verificacion de compra
        boolean pagoConfirmado = false;
        System.out.println("Su pago de " + precio + " se procesara con su metodo de pago " + metodoDePago);
        System.out.println("Procesando su pago");
        System.out.println(".");
        System.out.println("..");
        System.out.println("..."); //buscar un timer para los sout
        System.out.println(".");
        System.out.println("..");
        System.out.println("...");
        System.out.println("...");
        System.out.println("Sigue procesando...");
        System.out.println("Su pago se realizo correctamente :)");
        pagoConfirmado = true;
        return pagoConfirmado;
    }


    public Optional<Vuelo> getVueloByID(int id){
        Optional<Vuelo> optionalVuelo = listaVuelos
                .stream()
                .filter(vuelo -> vuelo.getId() == id)
                .findFirst();

            return optionalVuelo;

        }

    public Pasaje generarPasaje(Vuelo vuelo, String persona, int cantAsientos, MetodoDePago metodoDePago){ // puedo hacer que esta funcion solo lo genere recibiendo un booleano de pago confirmado

        Pasaje pasaje = new Pasaje(persona, vuelo, cantAsientos, metodoDePago);
        listaPasajes.add(pasaje);
        return pasaje;

    }
    //Combinaciones entre estos metodos en un nuevo metodo --> Ojo con las listas que me quedan entre metodos.
    //Mostrar ID en la lista del metodo para luego poder reservar con ese ID
}
