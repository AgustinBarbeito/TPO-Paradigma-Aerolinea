import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Scanner;

public class Empresa {

    private HashSet<Vuelo> listaVuelos;
    private HashSet<Pasaje> listaPasajes;
    private ArrayList<Ciudad> listaCiudades;
    private ArrayList<Aerolinea> listaAerolineas;
    private ArrayList<MetodoDePago> listaMetodosDePago;

    public Empresa(ArrayList<Ciudad> listaCiudad, ArrayList<Aerolinea> listaAerolinea,ArrayList<MetodoDePago> listaMetodos) {
        listaVuelos = new HashSet<>();
        listaPasajes = new HashSet<>();
        listaCiudades = listaCiudad;
        listaAerolineas = listaAerolinea;
        listaMetodosDePago = listaMetodos;
    }

    public void guardarVuelo(Vuelo vuelo) {
        listaVuelos.add(vuelo);
    }

    public void mostrarVuelo() {
        System.out.println(listaVuelos);
    }


    public void mostrarPasajes(){
        System.out.println("Lista de pasajes completa: ");
        for (Pasaje pasaje: this.listaPasajes){
            System.out.println("");
            System.out.println("----------------------------------------------");
            System.out.println("Numero de reserva del pasaje: " + pasaje.getNumeroDeReserva());
            System.out.println("Nombre del titular: " + pasaje.getNombre());
            System.out.println("ID del vuelo: " + pasaje.getVuelo().getId());
            System.out.println("Cantidad de asientos reservados: " + pasaje.getCantAsientosReservados());
            System.out.println("Monto final: " + pasaje.getMontoFinal());
            System.out.println("Metodo de pago: " + pasaje.getMetodoDePago().getNombre());
            System.out.println("----------------------------------------------");
            System.out.println("");

        }

    }
    //for (Aerolinea aerolinea: this.listaAerolineas){
        //System.out.println(this.listaAerolineas.indexOf(aerolinea) + "-" + aerolinea.getNombre());

    //--------------------------------------Menu Filtrado---------------------------------------------------------------//

    public Set<Vuelo> menuFiltradoVuelos(){
        System.out.println("Seleccione la opcion deseada para filtrar su vuelo: ");
        System.out.println("");
        System.out.println("--------------------------------------------------------");
        System.out.println("1) Filtrar por fecha");
        System.out.println("2) Filtrar por origen y destino");
        System.out.println("3) Filtrar por duracion");
        System.out.println("4) Filtrar por aerolinea");
        System.out.println("5) Filtrar por cantidad de escalas maximas");
        System.out.println("--------------------------------------------------------");
        System.out.println("");
        int opcionSeleccionada = opcionSeleccionadaFiltrar();
        Set<Vuelo> vuelosFiltrados = llamarMetodo(opcionSeleccionada);
        return vuelosFiltrados;





    }

    private Set<Vuelo> llamarMetodo(int opcion){
        Set<Vuelo> vuelosFiltrados = new HashSet<>();
        if (opcion == 1){
             vuelosFiltrados = filtrarFecha();
        } else if (opcion == 2) {
             vuelosFiltrados = filtrarOrigenDestino();
        } else if (opcion == 3){
             vuelosFiltrados = filtrarPorDuracion();
        } else if (opcion == 4){
             vuelosFiltrados = filtrarPorAerolinea();
        } else{
             vuelosFiltrados = filtrarPorEscalas();
        }

        return vuelosFiltrados;
    }

    private int opcionSeleccionadaFiltrar(){
        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion >= 1 && seleccion <= 5) {
                    entradaValida = true;
                } else {
                    System.out.println("");
                    System.out.println("El numero ingresado no es valido. Por favor, ingrese 1 como minimo y 5 como maximo");
                }
            } else {
                System.out.println("");
                System.out.println("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }

    public void mostrarIDVuelos(Set<Vuelo> misVuelos){
        if (misVuelos.size() == 0){
            System.out.println("");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("No se encontraron vuelos disponibles con su criterio de filtrado. Intente nuevamente :)");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("");
        } else{
            System.out.println("");
            System.out.println("---------------------------------------------");
            System.out.println(("        IDs de mis vuelos filtrados:      "));
            misVuelos.forEach(vuelo -> System.out.println(vuelo.getId()));
            System.out.println("---------------------------------------------");
            System.out.println("");
        }
    }


    //--------------------------------------Seccion filtrado de vuelos--------------------------------------------------//

    //Filtrar por fecha---
    private Set<Vuelo> filtrarFecha() {
        String fecha = inputFecha();
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getFecha().equals(fecha))
                .collect(Collectors.toSet());
    }
    private String inputFecha() {
        Scanner scanner = new Scanner(System.in);
        String fechaIngresada;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println("");
            System.out.println("Ingrese una fecha en formato dd/MM/yyyy:");
            fechaIngresada = scanner.nextLine();

            if (esFormatoFechaValido(fechaIngresada)) {
                entradaValida = true;
                return fechaIngresada;
            } else {
                System.out.println("");
                System.out.println("La fecha ingresada no tiene el formato correcto (dd/MM/yyyy). Por favor, intente nuevamente.");
            }
        }

        return null;
    }

    private boolean esFormatoFechaValido(String fecha) {
        // Verificar que la fecha tenga el formato dd/MM/yyyy
        if (fecha == null || fecha.length() != 10) {
            return false;
        }

        try {
            int dia = Integer.parseInt(fecha.substring(0, 2));
            int mes = Integer.parseInt(fecha.substring(3, 5));
            int año = Integer.parseInt(fecha.substring(6));

            return dia >= 1 && dia <= 31 && mes >= 1 && mes <= 12 && año >= 1000 && año <= 9999;
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            return false;
        }
    }

    //Filtrar por origen y destino
    private Set<Vuelo> filtrarOrigenDestino() {

        System.out.println("Seleccione la ciudad de origen: ");
        for (Ciudad ciudad: this.listaCiudades){
            System.out.println(this.listaCiudades.indexOf(ciudad) + "-" + ciudad.getNombre());
        }
        int origenIndex = inputCiudad();
        Ciudad origen = listaCiudades.get(origenIndex);

        System.out.println("Seleccione la ciudad de destino: ");
        for (Ciudad ciudad: this.listaCiudades){
            System.out.println(this.listaCiudades.indexOf(ciudad) + "-" + ciudad.getNombre());
        }
        int destinoIndex = inputCiudad();
        Ciudad destino = listaCiudades.get(destinoIndex);

        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getOrigen().equals(origen) && vuelo.getDestino().equals(destino))
                .collect(Collectors.toSet());
    }

    private int inputCiudad() {

        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion >= 0 && seleccion <= 6) {
                    entradaValida = true;
                } else {
                    System.out.println("");
                    System.out.println("El numero ingresado no es valido. Por favor, ingrese 0 como minimo y 6 como maximo");
                }
            } else {
                System.out.println("");
                System.out.println("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }


    //Filtrar por duracion

    private Set<Vuelo> filtrarPorDuracion() {
        int duracionMaxima = opcionSeleccionadaDuracion();
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getDuracion() <= duracionMaxima).
                collect(Collectors.toSet());
    }
    private int opcionSeleccionadaDuracion(){
        System.out.println("");
        System.out.println("Ingrese la duracion maxima desea para su vuelo en horas: ");
        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion >= 1 && seleccion <= 24) {
                    entradaValida = true;
                } else {
                    System.out.println("");
                    System.out.println("El numero ingresado no es valido. Por favor, ingrese como minimo 1 y como maximo 24 ");
                }
            } else {
                System.out.println("");
                System.out.println("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }

    //Filtrar por aerolinea

    private Set<Vuelo> filtrarPorAerolinea() {

        System.out.println("Seleccione la aerolinea deseada: ");
        for (Aerolinea aerolinea: this.listaAerolineas){
            System.out.println(this.listaAerolineas.indexOf(aerolinea) + "-" + aerolinea.getNombre());
        }
        int aerolineaIndex = inputAerolinea();
        Aerolinea aerolinea = listaAerolineas.get(aerolineaIndex);


        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getAerolinea().equals(aerolinea))
                .collect(Collectors.toSet());

    }

    private int inputAerolinea() {
        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion >= 0 && seleccion <= 2) {
                    entradaValida = true;
                } else {
                    System.out.println("");
                    System.out.println("El numero ingresado no es valido. Por favor, ingrese 0 como minimo y 2 como maximo");
                }
            } else {
                System.out.println("");
                System.out.println("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }

    //Filtrar por escalas

    private Set<Vuelo> filtrarPorEscalas() {
        int cantEscalas = opcionSeleccionadaEscalas();
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getEscalas() <= cantEscalas).
                collect(Collectors.toSet());
    }
    private int opcionSeleccionadaEscalas(){
        System.out.println("");
        System.out.println("Ingrese la cantidad maxima de escalas: ");
        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion >= 0 && seleccion <= 3) {
                    entradaValida = true;
                } else {
                    System.out.println("");
                    System.out.println("El numero ingresado no es valido. Por favor, ingrese 0 como minimo y 3 como maximo");
                }
            } else {
                System.out.println("");
                System.out.println("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }



    //-----------------------------------------Seccion reserva de pasaje--------------------------------------------------------------//

    //Orden del proceso:
    //Verifico el id recibido por parametro
    //Si existe, muestro el precio y realizo la comprobacion del pago
    //Si el pago se hizo correctamente imprimo el pasaje
    //Lo guardo en mi set de pasajes
    public void solicitarPasaje() { //aca pido la cantidad de pasajes que voy a querer, muestro el precio total y confirmo la compra

        String persona = inputPersona();


        MetodoDePago metodoSeleccionado = metodoDePagoElegido();
        Optional<Vuelo> optionalVuelo = Optional.empty();
        while(optionalVuelo.isEmpty()){
            int idRecibido = inputID();
            optionalVuelo = getVueloByID(idRecibido);
            if (optionalVuelo.isEmpty()){
                System.out.println("");
                System.out.println("El ID que ingreso no es valido. Ingrese uno valido por favor.");

            }
        }
        Vuelo vuelo = optionalVuelo.orElseThrow();

        int cantidadPasajes = inputCantPasajes();

        int precioAPagar = mostrarPrecios(vuelo,cantidadPasajes);
        boolean pagoConfirmado = verificarPago(precioAPagar,metodoSeleccionado);


        if (pagoConfirmado){
            Pasaje pasajeNuevo = generarPasaje(vuelo, persona, cantidadPasajes, metodoSeleccionado);
            pasajeNuevo.setNumeroDeReserva(generarNumeroReserva());
            pasajeNuevo.setMontoFinal(precioAPagar);
        } else {
            System.out.println("");
            System.out.println("El pago no fue realizado correctamente. Intentenlo nuevamente por favor.");
        }

    }

    private String inputPersona() {
        Scanner scanner = new Scanner(System.in);
        String nombre;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println("");
            System.out.println("Ingrese el nombre del titular del pasaje:");
            nombre = scanner.nextLine();

            if (esNombreValido(nombre)) {
                entradaValida = true;
                return nombre;
            } else {
                System.out.println("");
                System.out.println("El nombre ingresado no es válido. Por favor, intente nuevamente.");
            }
        }

        return null;
    }

    private boolean esNombreValido(String nombre) {
        // Verificar que el nombre no esté vacío y que contenga solo letras y espacios
        return nombre != null && nombre.matches("[a-zA-Z\\s]+");
    }
        //  La expresión regular "[a-zA-Z\\s]+" se descompone de la siguiente manera:
        //  [a-zA-Z]: Esto especifica que los caracteres permitidos son letras minúsculas (a-z) y letras mayúsculas (A-Z)
        //  \\s: Esto especifica que se permiten espacios en blanco (el carácter de espacio).
        //  +: Esto es un cuantificador que significa "uno o más" de los caracteres anteriores. Es decir, la cadena debe contener al menos uno de los caracteres especificados en [a-zA-Z\\s].
    private int inputID(){

        System.out.println("Ingrese el ID del vuelo que desea comprar: ");
        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion > 0) {
                    entradaValida = true;
                } else {
                    System.out.println("");
                    System.out.println("El numero ingresado no es valido. Por favor, ingrese 1 como minimo.");
                }
            } else {
                System.out.println("");
                System.out.println("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }
    private int inputCantPasajes(){

        System.out.println("Ingrese la cantidad de pasajes que desea comprar: ");
        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion > 0) {
                    entradaValida = true;
                } else {
                    System.out.println("");
                    System.out.println("El numero ingresado no es valido. Por favor, ingrese 1 como minimo.");
                }
            } else {
                System.out.println("");
                System.out.println("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }

    private MetodoDePago metodoDePagoElegido(){

        System.out.println("Seleccione el metodo de pago deseado para realizar su compra: ");
        for (MetodoDePago metodo: this.listaMetodosDePago){
            System.out.println(this.listaMetodosDePago.indexOf(metodo) + "-" + metodo.getNombre());
        }
        int metodoIndex = inputMetodoDePago();
        return  listaMetodosDePago.get(metodoIndex);
    }

    private int inputMetodoDePago(){

        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion >= 0) {
                    entradaValida = true;
                } else {
                    System.out.println("");
                    System.out.println("El numero ingresado no es valido. Por favor, ingrese 0 como minimo.");
                }
            } else {
                System.out.println("");
                System.out.println("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }
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
        System.out.println("Su precio final es de: " + precioFinal + " para " + cantPasajes + " pasajes con nivel de equipaje " + vueloSeleccionado.getEquipajeVuelo().getNombre() );
        System.out.println(" ");
        return precioFinal;


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

    private int opcionSeleccionada(){
        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

            while (!entradaValida) {
                if (scanner.hasNextInt()) {
                    seleccion = scanner.nextInt();
                    if (seleccion >= 1 && seleccion <= 3) {
                        entradaValida = true;
                    } else {
                        System.out.println("");
                        System.out.println("El numero ingresado no es valido. Por favor, ingrese 1, 2, o 3 ");
                    }
                } else {
                    System.out.println("");
                    System.out.println("La opcion ingresada no es valida. Por favor, ingrese un número.");
                    scanner.next();
                }
            }
            return seleccion;
        }


    private boolean verificarPago(int precio, MetodoDePago metodoDePago){ //Aca deberia ir el proceso de verificacion de compra
        boolean pagoConfirmado = false;
        try {
            System.out.println("Su pago de " + precio + " se procesará con su método de pago " + metodoDePago.getNombre());
            Thread.sleep(1000); // Pausa de 1 segundo
            System.out.println("Procesando su pago");
            Thread.sleep(1000); // Pausa de 1 segundo
            System.out.println(".");
            Thread.sleep(1000); // Pausa de 1 segundo
            System.out.println("..");
            Thread.sleep(1000); // Pausa de 1 segundo
            System.out.println("...");
            Thread.sleep(1000); // Pausa de 1 segundo
            System.out.println(".");
            Thread.sleep(1000); // Pausa de 1 segundo
            System.out.println("..");
            Thread.sleep(1000); // Pausa de 1 segundo
            System.out.println("...");
            Thread.sleep(1000); // Pausa de 1 segundo
            System.out.println("...");
            Thread.sleep(1000); // Pausa de 1 segundo
            System.out.println("Sigue procesando...");
            Thread.sleep(1000); // Pausa de 1 segundo
            System.out.println("Su pago se realizó correctamente :)");
            pagoConfirmado = true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("El hilo fue interrumpido");
        }
        return pagoConfirmado;
    }


    private Optional<Vuelo> getVueloByID(int id){
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

    private int generarNumeroReserva() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

}
