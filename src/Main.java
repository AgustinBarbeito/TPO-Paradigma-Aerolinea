import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Equipaje mochila = new Equipaje();
        Equipaje equipajeDeMano = new Equipaje();
        Equipaje equipajeParaDespachar = new Equipaje();

        Aerolinea aar =  new Aerolinea("Aerolineas Argentinas", 400);
        Aerolinea aa = new Aerolinea("American Airlines", 600);
        Aerolinea fly = new Aerolinea("FlyBondi", 200);

        ArrayList<Ciudad> listaCiudades = new ArrayList<>();

        Ciudad buenosAires = new Ciudad("Buenos Aires");
        Ciudad roma = new Ciudad("Roma");
        Ciudad rio = new Ciudad("Rio");
        Ciudad miami = new Ciudad("Miami");
        Ciudad santiago = new Ciudad("Santiago");
        Ciudad madrid = new Ciudad("Madrid");
        Ciudad tokyo = new Ciudad("Tokyo");


        listaCiudades.add(buenosAires);
        listaCiudades.add(roma);
        listaCiudades.add(rio);
        listaCiudades.add(miami);
        listaCiudades.add(santiago);
        listaCiudades.add(madrid);
        listaCiudades.add(tokyo);


        Empresa vuelosUADE = new Empresa(listaCiudades);

        Vuelo vuelo1 = new Vuelo(1,"10/02/2025","15:00",buenosAires,miami,8,2,aar,equipajeParaDespachar);
        Vuelo vuelo2 = new Vuelo(2,"10/02/2025","15:00",buenosAires,miami,7,2,aar,equipajeParaDespachar);
        Vuelo vuelo8 = new Vuelo(8,"10/02/2025","15:00",buenosAires,miami,6,1,aa,equipajeParaDespachar);

        MetodoDePago visa = new MetodoDePago();
        MetodoDePago amex = new MetodoDePago();
        MetodoDePago master = new MetodoDePago();
        vuelo1.mostrarPrecios();

        vuelosUADE.guardarVuelo(vuelo1);
        vuelosUADE.guardarVuelo(vuelo2);
        vuelosUADE.guardarVuelo(vuelo8);
        vuelosUADE.mostrarVuelo();

        Set<Vuelo> vuelosFiltradoPorOrigenDestino = vuelosUADE.filtrarOrigenDestino();
        System.out.println("IDs de mis vuelos filtrados: ");
        vuelosFiltradoPorOrigenDestino.forEach(vuelo -> System.out.println(vuelo.getId()));
        Set<Vuelo> vuelosFiltradoPorFecha = vuelosUADE.filtrarFecha();
        System.out.println(("IDs de mis vuelos filtrados: "));
        vuelosFiltradoPorFecha.forEach(vuelo -> System.out.println(vuelo.getId()));


        vuelosUADE.solicitarPasaje(1,"Azul", 2,visa);

        vuelosUADE.mostrarPasajes();



    }
}