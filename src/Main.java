import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Equipaje mochila = new Equipaje();
        Equipaje equipajeDeMano = new Equipaje();
        Equipaje equipajeParaDespachar = new Equipaje();

        Aerolinea aar =  new Aerolinea("Aerolineas Argentinas", 400);
        Aerolinea aa = new Aerolinea("American Airlines", 600);
        Aerolinea fly = new Aerolinea("FlyBondi", 200);

        Ciudad buenosAires = new Ciudad();
        Ciudad roma = new Ciudad();
        Ciudad rio = new Ciudad();
        Ciudad miami = new Ciudad();
        Ciudad santiago = new Ciudad();
        Ciudad madrid = new Ciudad();
        Ciudad tokyo = new Ciudad();

        Empresa vuelosUADE = new Empresa();

        Vuelo vuelo1 = new Vuelo(1,"10/02/2025","15:00",buenosAires,miami,8,2,aar,equipajeParaDespachar);

        vuelo1.mostrarPrecios();

        vuelosUADE.guardarVuelo(vuelo1);
        vuelosUADE.mostrarVuelo();
        Set<Vuelo> vuelosFiltradoPorOrigenDestino = vuelosUADE.filtrarOrigenDestino(buenosAires,miami);
        System.out.println("IDs de mis vuelos filtrados: ");
        vuelosFiltradoPorOrigenDestino.forEach(vuelo -> System.out.println(vuelo.getId()));



    }
}