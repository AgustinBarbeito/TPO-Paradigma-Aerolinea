import java.util.*;
import java.util.stream.Collectors;
import java.util.Scanner;

public class Empresa {

    private HashSet<Vuelo> listaVuelos;
    private HashSet<Pasaje> listaPasajes;
    private ArrayList<Ciudad> listaCiudades;

    public Empresa(ArrayList<Ciudad> listaCiudad) {
        listaVuelos = new HashSet<>();
        listaPasajes = new HashSet<>();
        listaCiudades = listaCiudad;
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

    //--------------------------------------Menu Filtrado---------------------------------------------------------------//

    public void menuFiltradoVuelos(){
        System.out.println("Seleccione la opcion deseada para filtrar su vuelo: ");
        System.out.println("");
        System.out.println("1) Filtrar por fecha");
        System.out.println("2) Filtrar por origen y destino");
        System.out.println("3) Filtrar por duracion");
        System.out.println("4) Filtrar por aerolinea");
        System.out.println("5) Filtrar por cantidad de escalas maximas");
        System.out.println("");
        int opcionSeleccionada = opcionSeleccionadaFiltrar();





    }

    public void llamarMetodo(int opcion){
        if (opcion == 1){
            filtrarFecha();
        } else if (opcion == 2) {
            filtrarOrigenDestino();
        } else if (opcion == 3){
            filtrarPorDuracion();
        }
    }

    public int opcionSeleccionadaFiltrar(){
        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion >= 1 && seleccion <= 5) {
                    entradaValida = true;
                } else {
                    System.out.println("El numero ingresado no es valido. Por favor, ingrese 1 como minimo y 5 como maximo");
                }
            } else {
                System.out.println("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }


    //--------------------------------------Seccion filtrado de vuelos--------------------------------------------------//

    //Filtrar por fecha---
    public Set<Vuelo> filtrarFecha() {
        String fecha = inputFecha();
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getFecha().equals(fecha))
                .collect(Collectors.toSet());
    }
    public String inputFecha() {
        Scanner scanner = new Scanner(System.in);
        String fechaIngresada;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println("Ingrese una fecha en formato dd/MM/yyyy:");
            fechaIngresada = scanner.nextLine();

            if (esFormatoFechaValido(fechaIngresada)) {
                entradaValida = true;
                return fechaIngresada;
            } else {
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
    public Set<Vuelo> filtrarOrigenDestino() {
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

    public int inputCiudad() {
        Scanner scanner = new Scanner(System.in);
        int seleccion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion >= 0 && seleccion <= 6) {
                    entradaValida = true;
                } else {
                    System.out.println("El numero ingresado no es valido. Por favor, ingrese 0 como minimo y 6 como maximo");
                }
            } else {
                System.out.println("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }

    public Set<Vuelo> filtrarPorDuracion() {
        int duracionMaxima = opcionSeleccionadaDuracion();
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getDuracion() <= duracionMaxima).
                collect(Collectors.toSet());
    }
    public int opcionSeleccionadaDuracion(){
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
                    System.out.println("El numero ingresado no es valido. Por favor, ingrese como minimo 1 y como maximo 24 ");
                }
            } else {
                System.out.println("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }

    //Filtrar por aerolinea
    public Set<Vuelo> filtrarPorAerolinea(Aerolinea aerolinea) {
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getAerolinea().equals(aerolinea))
                .collect(Collectors.toSet());

    }

    //Filtrar por escalas
    public Set<Vuelo> filtrarPorEscalas(int cantEscalas) {
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getEscalas() <= cantEscalas).
                collect(Collectors.toSet());
    }
    public int opcionSeleccionadaEscalas(){
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
                    System.out.println("El numero ingresado no es valido. Por favor, ingrese 0 como minimo y 3 como maximo");
                }
            } else {
                System.out.println("La opcion ingresada no es valida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return seleccion;
    }



    //-----------------------------------------Seccion reserva de pasaje--------------------------------------------------------------//

    public void solicitarPasaje(int idVuelo, String persona, int cantidadPasajes, MetodoDePago metodoSeleccionado) { //aca pido la cantidad de pasajes que voy a querer, muestro el precio total y confirmo la compra

        Optional<Vuelo> optionalVuelo = getVueloByID(idVuelo);
        Vuelo vuelo = optionalVuelo.orElseThrow();
        int precioAPagar = mostrarPrecios(vuelo,cantidadPasajes);
        boolean pagoConfirmado = verificarPago(precioAPagar,metodoSeleccionado);

        if (pagoConfirmado){
            Pasaje pasajeNuevo = generarPasaje(vuelo, persona, cantidadPasajes, metodoSeleccionado);
            pasajeNuevo.setNumeroDeReserva(generarNumeroReserva());
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
        try {
            System.out.println("Su pago de " + precio + " se procesará con su método de pago " + metodoDePago);
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

    private int generarNumeroReserva() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
    //Combinaciones entre estos metodos en un nuevo metodo --> Ojo con las listas que me quedan entre metodos.
    //Mostrar ID en la lista del metodo para luego poder reservar con ese ID
}
