import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Scanner;

public class Empresa {

    private String nombre;
    private HashSet<Vuelo> listaVuelos;
    private HashSet<Pasaje> listaPasajes;
    private ArrayList<Ciudad> listaCiudades;
    private ArrayList<Aerolinea> listaAerolineas;
    private ArrayList<MetodoDePago> listaMetodosDePago;

    public Empresa(String nombreEmpresa, ArrayList<Ciudad> listaCiudad, ArrayList<Aerolinea> listaAerolinea,ArrayList<MetodoDePago> listaMetodos) {
        nombre = nombreEmpresa;
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
        Visualizador.getInstancia().visualizarMensaje("Lista de vuelos completa: \n");
        for (Vuelo vuelo: this.listaVuelos) {
            Visualizador.getInstancia().visualizarMensaje(
                    ("Numero de ID: " + vuelo.getId() + "\n") +
                    ("Fecha: " + vuelo.getFecha() + "\n") +
                    ("Horario: " + vuelo.getHorario() + "\n") +
                    ("Ciudad de origen: " + vuelo.getOrigen() + "\n") +
                    ("Ciudad de destino: " + vuelo.getDestino() + "\n") +
                    ("Duracion total: " + vuelo.getDuracion() + "\n") +
                    ("Cantidad de escalas: " + vuelo.getEscalas() + "\n") +
                    ("Aerolinea: " + vuelo.getAerolinea() + "\n") +
                    ("Tipo de equipaje: " + vuelo.getEquipajeVuelo() + "\n"));

        }
    }


    public void mostrarPasajes(){
        Visualizador.getInstancia().visualizarMensaje("Lista de pasajes completa: \n");
        for (Pasaje pasaje: this.listaPasajes){
            Visualizador.getInstancia().visualizarMensaje(
            ("---------------------------------------------- \n") +
            ("Numero de reserva del pasaje: " + pasaje.getNumeroDeReserva() + "\n") +
            ("Nombre del titular: " + pasaje.getNombre() + "\n") + 
            ("ID del vuelo: " + pasaje.getVuelo().getId() + "\n") +
            ("Cantidad de asientos reservados: " + pasaje.getCantAsientosReservados() + "\n") +
            ("Monto final: " + pasaje.getMontoFinal() + "\n") +
            ("Metodo de pago: " + pasaje.getMetodoDePago().getNombre() + "\n") +
            ("---------------------------------------------- \n"));
            

        }

    }
    //--------------------------------------Menu Principal--------------------------------------------------------------//

    public void menuPrincipal(){
        boolean sigoEjecutando = true;
        int opcionSeleccionada = 9999;

        while(sigoEjecutando){
                Visualizador.getInstancia().visualizarMensaje(("Bienvenido a la app de " + this.nombre + " \n  \n") +
                ("Usted podra filtrar entre todos los vuelos de la compañia y reservar su vuelo \n") +
                ("Seleccione la opcion deseada: \n" ) +
                ("0) Terminar el programa \n") +
                ("1) Buscar y filtrar vuelos \n") +
                ("2) Reservar su pasaje \n"));
                opcionSeleccionada = inputMenu();
                sigoEjecutando = llamarMetodoMenu(opcionSeleccionada);

        }
    }
    private boolean llamarMetodoMenu(int opcion){

        Set<Vuelo> misVuelos = new HashSet<>();
        boolean sigoEjecutando = true;

        if(opcion == 1){
            misVuelos = menuFiltradoVuelos();
            this.mostrarIDVuelos(misVuelos);

        } else if(opcion == 2){
            solicitarPasaje();

        }  else {
            Visualizador.getInstancia().visualizarMensaje("Muchas gracias por elegirnos!");
            sigoEjecutando = false;
        }
        return sigoEjecutando;
    }
    private int inputMenu(){
        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion >= 0 && seleccion <= 2) {
                    entradaValida = true;
                } else {
                    Visualizador.getInstancia().saltoDeLinea();
                    Visualizador.getInstancia().visualizarMensaje("El numero ingresado no es valido. Por favor, ingrese 0 como minimo y 2 como maximo");
                }
            } else {
                Visualizador.getInstancia().saltoDeLinea();
                Visualizador.getInstancia().visualizarMensaje("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }

    //--------------------------------------Menu Filtrado---------------------------------------------------------------//

    private Set<Vuelo> menuFiltradoVuelos(){

        Visualizador.getInstancia().visualizarMensaje(("Seleccione la opcion deseada para filtrar su vuelo: \n") + 
        ("-------------------------------------------------------- \n") + 
        ("1) Filtrar por fecha \n") +
        ("2) Filtrar por origen y destino \n") +
        ("3) Filtrar por duracion \n") +
        ("4) Filtrar por aerolinea \n") + 
        ("5) Filtrar por cantidad de escalas maximas \n") +
        ("-------------------------------------------------------- \n"));
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
                    Visualizador.getInstancia().saltoDeLinea();
                    Visualizador.getInstancia().visualizarMensaje("El numero ingresado no es valido. Por favor, ingrese 1 como minimo y 5 como maximo");
                }
            } else {
                Visualizador.getInstancia().saltoDeLinea();
                Visualizador.getInstancia().visualizarMensaje("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }

    private void mostrarIDVuelos(Set<Vuelo> misVuelos){
        if (misVuelos.size() == 0){
            Visualizador.getInstancia().visualizarMensaje(("-------------------------------------------------------------------------------------\n " ) +
            ("No se encontraron vuelos disponibles con su criterio de filtrado. Intente nuevamente :) \n") +
            ("------------------------------------------------------------------------------------- \n ") +
            ("\n" ));
        } else{
            List<Vuelo> listaVuelos = new ArrayList<>(misVuelos);
            listaVuelos.sort(Comparator.comparingInt(Vuelo::getId));
            Visualizador.getInstancia().visualizarMensaje(("--------------------------------------------- \n") +
            (("        IDs de mis vuelos filtrados:      \n")));
            listaVuelos.forEach(vuelo -> Visualizador.getInstancia().visualizarMensaje(vuelo.getId()));
            Visualizador.getInstancia().visualizarMensaje("---------------------------------------------");

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
            Visualizador.getInstancia().visualizarMensaje("Ingrese una fecha en formato dd/MM/yyyy: \n");
            fechaIngresada = scanner.nextLine();

            if (esFormatoFechaValido(fechaIngresada)) {
                entradaValida = true;
                return fechaIngresada;
            } else {
                Visualizador.getInstancia().saltoDeLinea();
                Visualizador.getInstancia().visualizarMensaje("La fecha ingresada no tiene el formato correcto (dd/MM/yyyy). Por favor, intente nuevamente.");
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

        Visualizador.getInstancia().visualizarMensaje("Seleccione la ciudad de origen: ");
        for (Ciudad ciudad: this.listaCiudades){
            Visualizador.getInstancia().visualizarMensaje(this.listaCiudades.indexOf(ciudad) + "-" + ciudad.getNombre());
        }
        int origenIndex = inputCiudad();
        Ciudad origen = listaCiudades.get(origenIndex);

        Visualizador.getInstancia().visualizarMensaje("Seleccione la ciudad de destino: ");
        for (Ciudad ciudad: this.listaCiudades){
            Visualizador.getInstancia().visualizarMensaje(this.listaCiudades.indexOf(ciudad) + "-" + ciudad.getNombre());
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
                    Visualizador.getInstancia().saltoDeLinea();
                    Visualizador.getInstancia().visualizarMensaje("El numero ingresado no es valido. Por favor, ingrese 0 como minimo y 6 como maximo");
                }
            } else {
                Visualizador.getInstancia().saltoDeLinea();
                Visualizador.getInstancia().visualizarMensaje("La opcion ingresada no es valida. Por favor, ingrese un número.");
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
        Visualizador.getInstancia().saltoDeLinea();
        Visualizador.getInstancia().visualizarMensaje("Ingrese la duracion maxima desea para su vuelo en horas: ");
        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion >= 1 && seleccion <= 24) {
                    entradaValida = true;
                } else {
                    Visualizador.getInstancia().saltoDeLinea();
                    Visualizador.getInstancia().visualizarMensaje("El numero ingresado no es valido. Por favor, ingrese como minimo 1 y como maximo 24 ");
                }
            } else {
                Visualizador.getInstancia().saltoDeLinea();
                Visualizador.getInstancia().visualizarMensaje("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }

    //Filtrar por aerolinea

    private Set<Vuelo> filtrarPorAerolinea() {

        Visualizador.getInstancia().saltoDeLinea();
        Visualizador.getInstancia().visualizarMensaje("Seleccione la aerolinea deseada: ");
        for (Aerolinea aerolinea: this.listaAerolineas){
            Visualizador.getInstancia().visualizarMensaje(this.listaAerolineas.indexOf(aerolinea) + "-" + aerolinea.getNombre());
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
                    Visualizador.getInstancia().saltoDeLinea();
                    Visualizador.getInstancia().visualizarMensaje("El numero ingresado no es valido. Por favor, ingrese 0 como minimo y 2 como maximo");
                }
            } else {
                Visualizador.getInstancia().saltoDeLinea();
                Visualizador.getInstancia().visualizarMensaje("La opcion ingresada no es valida. Por favor, ingrese un número.");
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
        Visualizador.getInstancia().saltoDeLinea();
        Visualizador.getInstancia().visualizarMensaje("Ingrese la cantidad maxima de escalas: ");
        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion >= 0 && seleccion <= 3) {
                    entradaValida = true;
                } else {
                    Visualizador.getInstancia().saltoDeLinea();
                    Visualizador.getInstancia().visualizarMensaje("El numero ingresado no es valido. Por favor, ingrese 0 como minimo y 3 como maximo");
                }
            } else {
                Visualizador.getInstancia().saltoDeLinea();
                Visualizador.getInstancia().visualizarMensaje("La opcion ingresada no es valida. Por favor, ingrese un número.");
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
    private void solicitarPasaje() {

        String persona = inputPersona();


        MetodoDePago metodoSeleccionado = metodoDePagoElegido();
        Optional<Vuelo> optionalVuelo = Optional.empty();
        while(optionalVuelo.isEmpty()){
            int idRecibido = inputID();
            optionalVuelo = getVueloByID(idRecibido);
            if (optionalVuelo.isEmpty()){
                Visualizador.getInstancia().saltoDeLinea();
                Visualizador.getInstancia().visualizarMensaje("El ID que ingreso no es valido. Ingrese uno valido por favor.");

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
            Visualizador.getInstancia().saltoDeLinea();
            Visualizador.getInstancia().visualizarMensaje("El pago no fue realizado correctamente. Intentenlo nuevamente por favor.");
        }
    }

    private String inputPersona() {
        Scanner scanner = new Scanner(System.in);
        String nombre;
        boolean entradaValida = false;

        while (!entradaValida) {
            Visualizador.getInstancia().saltoDeLinea();
            Visualizador.getInstancia().visualizarMensaje("Ingrese el nombre del titular del pasaje:");
            nombre = scanner.nextLine();

            if (esNombreValido(nombre)) {
                entradaValida = true;
                return nombre;
            } else {
                Visualizador.getInstancia().saltoDeLinea();
                Visualizador.getInstancia().visualizarMensaje("El nombre ingresado no es válido. Por favor, intente nuevamente.");
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

        Visualizador.getInstancia().visualizarMensaje("Ingrese el ID del vuelo que desea comprar: ");
        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion > 0) {
                    entradaValida = true;
                } else {
                    Visualizador.getInstancia().saltoDeLinea();
                    Visualizador.getInstancia().visualizarMensaje("El numero ingresado no es valido. Por favor, ingrese 1 como minimo.");
                }
            } else {
                Visualizador.getInstancia().saltoDeLinea();
                Visualizador.getInstancia().visualizarMensaje("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }
    private int inputCantPasajes(){

        Visualizador.getInstancia().visualizarMensaje("Ingrese la cantidad de pasajes que desea comprar: ");
        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion > 0) {
                    entradaValida = true;
                } else {
                    Visualizador.getInstancia().saltoDeLinea();
                    Visualizador.getInstancia().visualizarMensaje("El numero ingresado no es valido. Por favor, ingrese 1 como minimo.");
                }
            } else {
                Visualizador.getInstancia().saltoDeLinea();
                Visualizador.getInstancia().visualizarMensaje("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }

    private MetodoDePago metodoDePagoElegido(){

        Visualizador.getInstancia().visualizarMensaje("Seleccione el metodo de pago deseado para realizar su compra: ");
        for (MetodoDePago metodo: this.listaMetodosDePago){
            Visualizador.getInstancia().visualizarMensaje(this.listaMetodosDePago.indexOf(metodo) + "-" + metodo.getNombre());
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
                    Visualizador.getInstancia().saltoDeLinea();
                    Visualizador.getInstancia().visualizarMensaje("El numero ingresado no es valido. Por favor, ingrese 0 como minimo.");
                }
            } else {
                Visualizador.getInstancia().saltoDeLinea();
                Visualizador.getInstancia().visualizarMensaje("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }
    private int mostrarPrecios(Vuelo vueloSeleccionado, int cantPasajes) { //Voy a mostrar los precios disponibles y espero recibir un 1 2 o 3 dependiendo del asiento deseado

        Visualizador.getInstancia().visualizarMensaje(("Los precios de asientos para su vuelo seleccionado son: \n") +
        ("1) Precio Economy: " + vueloSeleccionado.getPrecioEconomy() + "\n") +
        ("2) Precio Super: " + vueloSeleccionado.getPrecioSuper() + "\n") +
        ("3) Precio Primera: " + vueloSeleccionado.getPrecioPrimera() + "\n") +
        ("-----------------------------------------------------------"));

        Visualizador.getInstancia().visualizarMensaje("Seleccione el tipo de asiento (1, 2, o 3): \n");

        int precioFinal = devolverPrecio(vueloSeleccionado,cantPasajes);

        Visualizador.getInstancia().visualizarMensaje("Su precio final es de: " + precioFinal + " para " + cantPasajes + " pasajes con nivel de equipaje " + vueloSeleccionado.getEquipajeVuelo().getNombre() + "\n");
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
                        Visualizador.getInstancia().saltoDeLinea();
                        Visualizador.getInstancia().visualizarMensaje("El numero ingresado no es valido. Por favor, ingrese 1, 2, o 3 ");
                    }
                } else {
                    Visualizador.getInstancia().saltoDeLinea();
                    Visualizador.getInstancia().visualizarMensaje("La opcion ingresada no es valida. Por favor, ingrese un número.");
                    scanner.next();
                }
            }
            return seleccion;
        }


    private boolean verificarPago(int precio, MetodoDePago metodoDePago){ //Aca deberia ir el proceso de verificacion de compra
        boolean pagoConfirmado = false;
        try {
            Visualizador.getInstancia().visualizarMensaje("Su pago de " + precio + " se procesará con su método de pago " + metodoDePago.getNombre());
            Thread.sleep(1000); // Pausa de 1 segundo
            Visualizador.getInstancia().visualizarMensaje("Procesando su pago");
            Thread.sleep(1000); // Pausa de 1 segundo
            Visualizador.getInstancia().visualizarMensaje(".");
            Thread.sleep(1000); // Pausa de 1 segundo
            Visualizador.getInstancia().visualizarMensaje("..");
            Thread.sleep(1000); // Pausa de 1 segundo
            Visualizador.getInstancia().visualizarMensaje("...");
            Thread.sleep(1000); // Pausa de 1 segundo
            Visualizador.getInstancia().visualizarMensaje(".");
            Thread.sleep(1000); // Pausa de 1 segundo
            Visualizador.getInstancia().visualizarMensaje("..");
            Thread.sleep(1000); // Pausa de 1 segundo
            Visualizador.getInstancia().visualizarMensaje("...");
            Thread.sleep(1000); // Pausa de 1 segundo
            Visualizador.getInstancia().visualizarMensaje("...");
            Thread.sleep(1000); // Pausa de 1 segundo
            Visualizador.getInstancia().visualizarMensaje("Sigue procesando...");
            Thread.sleep(1000); // Pausa de 1 segundo
            Visualizador.getInstancia().visualizarMensaje("Su pago se realizó correctamente :)");
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



    private Pasaje generarPasaje(Vuelo vuelo, String persona, int cantAsientos, MetodoDePago metodoDePago){ // puedo hacer que esta funcion solo lo genere recibiendo un booleano de pago confirmado

        Pasaje pasaje = new Pasaje(persona, vuelo, cantAsientos, metodoDePago);
        listaPasajes.add(pasaje);
        return pasaje;

    }

    private int generarNumeroReserva() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }


}
