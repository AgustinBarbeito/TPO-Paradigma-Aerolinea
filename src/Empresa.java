import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Empresa {

    private HashSet <Vuelo> listaVuelos;
    private HashSet <Pasaje> listaPasajes;

    public Empresa() {
        listaVuelos = new HashSet<>();
    }

    public void guardarVuelo(Vuelo vuelo){
        listaVuelos.add(vuelo);
    }

    public void mostrarVuelo(){
        System.out.println(listaVuelos);
    }


    public Set<Vuelo> filtrarPorFecha(String fecha){
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getFecha().equals(fecha) )
                .collect(Collectors.toSet()) ;
    }
    public Set<Vuelo> filtrarOrigenDestino(Ciudad origen, Ciudad destino){
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getOrigen().equals(origen) && vuelo.getDestino().equals(destino))
                .collect(Collectors.toSet()) ;
    }

    public Set<Vuelo> filtrarPorDuracion(int duracionMaxima){
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getDuracion() <= duracionMaxima ).
                        collect(Collectors.toSet());
    }


    public Set<Vuelo> filtrarPorAerolinea(Aerolinea aerolinea){
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getAerolinea().equals(aerolinea) )
                .collect(Collectors.toSet()) ;

    }

    public Set<Vuelo> filtrarPorEscalas(int cantEscalas){
        return listaVuelos.stream()
                .filter(vuelo -> vuelo.getEscalas() <= cantEscalas ).
                collect(Collectors.toSet());
    }


    public Optional<Vuelo> getVueloByID(int id){
        Optional<Vuelo> optionalVuelo = listaVuelos
                .stream()
                .filter(vuelo -> vuelo.getId() == id)
                .findFirst();

            return optionalVuelo;

        }

    public Pasaje generarPasaje(int idVuelo, String persona){
        Optional<Vuelo> cajaDelVuelo = getVueloByID(idVuelo);

        Vuelo vuelo = cajaDelVuelo.orElseThrow();
        vuelo.decrementarCantidad();
        Pasaje pasaje = new Pasaje(persona, vuelo);
        listaPasajes.add(pasaje);

        return pasaje;

    }
    //Combinaciones entre estos metodos en un nuevo metodo --> Ojo con las listas que me quedan entre metodos.
    //Mostrar ID en la lista del metodo para luego poder reservar con ese ID
}
