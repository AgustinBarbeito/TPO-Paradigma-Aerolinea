import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Equipaje mochila = new Equipaje("Mochila");
        Equipaje equipajeDeMano = new Equipaje("Equipaje de mano");
        Equipaje equipajeParaDespachar = new Equipaje("Equipaje para despachar");

        ArrayList<Aerolinea> listaAerolineas = new ArrayList<>();

        Aerolinea aar =  new Aerolinea("Aerolineas Argentinas", 400);
        Aerolinea aa = new Aerolinea("American Airlines", 600);
        Aerolinea fly = new Aerolinea("FlyBondi", 200);

        listaAerolineas.add(aar);
        listaAerolineas.add(aa);
        listaAerolineas.add(fly);


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

        ArrayList<MetodoDePago> listaMetodoDePago = new ArrayList<>();
        MetodoDePago visa = new MetodoDePago("Visa");
        MetodoDePago amex = new MetodoDePago("American Express");
        MetodoDePago master = new MetodoDePago("Mastercard");

        listaMetodoDePago.add(visa);
        listaMetodoDePago.add(amex);
        listaMetodoDePago.add(master);

        Empresa vuelosUADE = new Empresa(listaCiudades, listaAerolineas,listaMetodoDePago);

        Vuelo vuelo1 = new Vuelo(1,"10/02/2025","15:00",buenosAires,miami,8,2,aar,equipajeParaDespachar);
        Vuelo vuelo2 = new Vuelo(2,"10/02/2025","15:00",buenosAires,miami,7,2,aar,equipajeParaDespachar);
        Vuelo vuelo8 = new Vuelo(8,"10/02/2025","15:00",buenosAires,miami,6,1,aa,equipajeParaDespachar);






        vuelo1.mostrarPrecios();

        vuelosUADE.guardarVuelo(vuelo1);
        vuelosUADE.guardarVuelo(vuelo2);
        vuelosUADE.guardarVuelo(vuelo8);
        vuelosUADE.mostrarVuelo();

        Set<Vuelo> vuelosFiltrados = vuelosUADE.menuFiltradoVuelos();
        vuelosUADE.mostrarIDVuelos(vuelosFiltrados);


        vuelosUADE.solicitarPasaje();

        vuelosUADE.mostrarPasajes();



    }
}