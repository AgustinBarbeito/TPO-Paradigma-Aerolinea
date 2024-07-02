import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Aerolinea aar =  new Aerolinea("Aerolineas Argentinas", 400);
        Aerolinea aa = new Aerolinea("American Airlines", 600);
        Aerolinea fly = new Aerolinea("FlyBondi", 200);
        Aerolinea aca = new Aerolinea("Air Canada", 500);
        Aerolinea delta = new Aerolinea("Delta", 800);
        Aerolinea lan = new Aerolinea("LAN", 300);

        ArrayList<Aerolinea> listaAerolineas = new ArrayList<>();

        listaAerolineas.add(aar);
        listaAerolineas.add(aa);
        listaAerolineas.add(fly);
        listaAerolineas.add(aca);
        listaAerolineas.add(delta);
        listaAerolineas.add(lan);

        Equipaje mochila = new Equipaje("Mochila");
        Equipaje equipajeDeMano = new Equipaje("Equipaje de mano");
        Equipaje equipajeParaDespachar = new Equipaje("Equipaje para despachar");



        Ciudad buenosAires = new Ciudad("Buenos Aires");
        Ciudad roma = new Ciudad("Roma");
        Ciudad rio = new Ciudad("Rio");
        Ciudad miami = new Ciudad("Miami");
        Ciudad santiago = new Ciudad("Santiago");
        Ciudad madrid = new Ciudad("Madrid");
        Ciudad tokyo = new Ciudad("Tokyo");

        ArrayList<Ciudad> listaCiudades = new ArrayList<>();

        listaCiudades.add(buenosAires);
        listaCiudades.add(roma);
        listaCiudades.add(rio);
        listaCiudades.add(miami);
        listaCiudades.add(santiago);
        listaCiudades.add(madrid);
        listaCiudades.add(tokyo);


        MetodoDePago visa = new MetodoDePago("Visa");
        MetodoDePago amex = new MetodoDePago("American Express");
        MetodoDePago master = new MetodoDePago("Mastercard");

        ArrayList<MetodoDePago> listaMetodoDePago = new ArrayList<>();

        listaMetodoDePago.add(visa);
        listaMetodoDePago.add(amex);
        listaMetodoDePago.add(master);

        Empresa vuelosUADE = new Empresa("VuelosUade",listaCiudades, listaAerolineas,listaMetodoDePago);


        Vuelo vuelo1 = new Vuelo(1,"10/02/2025","15:00",buenosAires,miami,8,2,aar,equipajeParaDespachar);
        Vuelo vuelo2 = new Vuelo(2,"10/02/2025","15:00",buenosAires,miami,7,2,aar,equipajeParaDespachar);
        Vuelo vuelo3 = new Vuelo(3,"11/02/2025","16:00",roma,rio,5,3,fly,equipajeDeMano);
        Vuelo vuelo4 = new Vuelo(4,"12/02/2025","17:00",miami,santiago,4,2,aca,mochila);
        Vuelo vuelo5 = new Vuelo(5,"13/02/2025","18:00",santiago,madrid,3,1,delta,equipajeParaDespachar);
        Vuelo vuelo6 = new Vuelo(6,"14/02/2025","19:00",madrid,tokyo,2,3,lan,equipajeDeMano);
        Vuelo vuelo7 = new Vuelo(7,"15/02/2025","20:00",tokyo,buenosAires,1,2,aar,mochila);
        Vuelo vuelo8 = new Vuelo(8,"10/02/2025","15:00",buenosAires,miami,6,1,aa,equipajeParaDespachar);
        Vuelo vuelo9 = new Vuelo(9,"16/02/2025","21:00",rio,miami,3,1,aa,equipajeDeMano);
        Vuelo vuelo10 = new Vuelo(10,"17/02/2025","22:00",roma,santiago,5,2,fly,equipajeParaDespachar);
        Vuelo vuelo11 = new Vuelo(11,"18/02/2025","23:00",miami,madrid,4,3,aca,mochila);
        Vuelo vuelo12 = new Vuelo(12,"19/02/2025","00:00",santiago,tokyo,6,1,delta,equipajeParaDespachar);
        Vuelo vuelo13 = new Vuelo(13,"20/02/2025","01:00",madrid,buenosAires,7,2,lan,equipajeDeMano);
        Vuelo vuelo14 = new Vuelo(14,"21/02/2025","02:00",tokyo,roma,8,3,aar,mochila);
        Vuelo vuelo15 = new Vuelo(15,"22/02/2025","03:00",buenosAires,rio,2,1,aa,equipajeParaDespachar);
        Vuelo vuelo16 = new Vuelo(16,"23/02/2025","04:00",rio,santiago,3,2,fly,equipajeDeMano);
        Vuelo vuelo17 = new Vuelo(17,"24/02/2025","05:00",roma,madrid,5,3,aca,mochila);
        Vuelo vuelo18 = new Vuelo(18,"25/02/2025","06:00",miami,tokyo,4,1,delta,equipajeParaDespachar);
        Vuelo vuelo19 = new Vuelo(19,"26/02/2025","07:00",santiago,buenosAires,6,2,lan,equipajeDeMano);
        Vuelo vuelo20 = new Vuelo(20,"27/02/2025","08:00",madrid,miami,7,3,aar,mochila);
        Vuelo vuelo21 = new Vuelo(21,"28/02/2025","09:00",tokyo,rio,8,1,aa,equipajeParaDespachar);
        Vuelo vuelo22 = new Vuelo(22,"01/03/2025","10:00",buenosAires,roma,2,2,fly,equipajeDeMano);
        Vuelo vuelo23 = new Vuelo(23,"02/03/2025","11:00",rio,madrid,3,3,aca,mochila);
        Vuelo vuelo24 = new Vuelo(24,"03/03/2025","12:00",roma,tokyo,4,1,delta,equipajeParaDespachar);
        Vuelo vuelo25 = new Vuelo(25,"04/03/2025","13:00",miami,buenosAires,6,2,lan,equipajeDeMano);
        Vuelo vuelo26 = new Vuelo(26,"05/03/2025","14:00",santiago,roma,7,3,aar,mochila);
        Vuelo vuelo27 = new Vuelo(27,"06/03/2025","15:00",madrid,rio,8,1,aa,equipajeParaDespachar);
        Vuelo vuelo28 = new Vuelo(28,"07/03/2025","16:00",tokyo,miami,2,2,fly,equipajeDeMano);
        Vuelo vuelo29 = new Vuelo(29,"08/03/2025","17:00",buenosAires,santiago,3,3,aca,mochila);
        Vuelo vuelo30 = new Vuelo(30,"09/03/2025","18:00",rio,madrid,4,1,delta,equipajeParaDespachar);
        Vuelo vuelo31 = new Vuelo(31,"10/03/2025","19:00",roma,tokyo,6,2,lan,equipajeDeMano);
        Vuelo vuelo32 = new Vuelo(32,"11/03/2025","20:00",miami,buenosAires,7,3,aar,mochila);
        Vuelo vuelo33 = new Vuelo(33,"12/03/2025","21:00",santiago,rio,8,1,aa,equipajeParaDespachar);
        Vuelo vuelo34 = new Vuelo(34,"13/03/2025","22:00",madrid,miami,2,2,fly,equipajeDeMano);
        Vuelo vuelo35 = new Vuelo(35,"14/03/2025","23:00",tokyo,buenosAires,3,3,aca,mochila);
        Vuelo vuelo36 = new Vuelo(36,"15/03/2025","00:00",buenosAires,roma,4,1,delta,equipajeParaDespachar);
        Vuelo vuelo37 = new Vuelo(37,"16/03/2025","01:00",rio,tokyo,6,2,lan,equipajeDeMano);
        Vuelo vuelo38 = new Vuelo(38,"17/03/2025","02:00",roma,miami,7,3,aar,mochila);
        Vuelo vuelo39 = new Vuelo(39,"18/03/2025","03:00",miami,rio,8,1,aa,equipajeParaDespachar);
        Vuelo vuelo40 = new Vuelo(40,"19/03/2025","04:00",santiago,madrid,2,2,fly,equipajeDeMano);
        Vuelo vuelo41 = new Vuelo(41,"20/03/2025","05:00",madrid,roma,3,3,aca,mochila);
        Vuelo vuelo42 = new Vuelo(42,"21/03/2025","06:00",tokyo,santiago,4,1,delta,equipajeParaDespachar);
        Vuelo vuelo43 = new Vuelo(43,"22/03/2025","07:00",buenosAires,miami,6,2,lan,equipajeDeMano);
        Vuelo vuelo44 = new Vuelo(44,"23/03/2025","08:00",rio,buenosAires,7,3,aar,mochila);
        Vuelo vuelo45 = new Vuelo(45,"24/03/2025","09:00",roma,rio,8,1,aa,equipajeParaDespachar);
        Vuelo vuelo46 = new Vuelo(46,"25/03/2025","10:00",miami,santiago,2,2,fly,equipajeDeMano);
        Vuelo vuelo47 = new Vuelo(47,"26/03/2025","11:00",santiago,miami,3,3,aca,mochila);
        Vuelo vuelo48 = new Vuelo(48,"27/03/2025","12:00",madrid,buenosAires,4,1,delta,equipajeParaDespachar);
        Vuelo vuelo49 = new Vuelo(49,"28/03/2025","13:00",tokyo,roma,6,2,lan,equipajeDeMano);
        Vuelo vuelo50 = new Vuelo(50,"29/03/2025","14:00",buenosAires,rio,7,3,aar,mochila);

        vuelosUADE.guardarVuelo(vuelo1);
        vuelosUADE.guardarVuelo(vuelo2);
        vuelosUADE.guardarVuelo(vuelo3);
        vuelosUADE.guardarVuelo(vuelo4);
        vuelosUADE.guardarVuelo(vuelo5);
        vuelosUADE.guardarVuelo(vuelo6);
        vuelosUADE.guardarVuelo(vuelo7);
        vuelosUADE.guardarVuelo(vuelo8);
        vuelosUADE.guardarVuelo(vuelo9);
        vuelosUADE.guardarVuelo(vuelo10);
        vuelosUADE.guardarVuelo(vuelo11);
        vuelosUADE.guardarVuelo(vuelo12);
        vuelosUADE.guardarVuelo(vuelo13);
        vuelosUADE.guardarVuelo(vuelo14);
        vuelosUADE.guardarVuelo(vuelo15);
        vuelosUADE.guardarVuelo(vuelo16);
        vuelosUADE.guardarVuelo(vuelo17);
        vuelosUADE.guardarVuelo(vuelo18);
        vuelosUADE.guardarVuelo(vuelo19);
        vuelosUADE.guardarVuelo(vuelo20);
        vuelosUADE.guardarVuelo(vuelo21);
        vuelosUADE.guardarVuelo(vuelo22);
        vuelosUADE.guardarVuelo(vuelo23);
        vuelosUADE.guardarVuelo(vuelo24);
        vuelosUADE.guardarVuelo(vuelo25);
        vuelosUADE.guardarVuelo(vuelo26);
        vuelosUADE.guardarVuelo(vuelo27);
        vuelosUADE.guardarVuelo(vuelo28);
        vuelosUADE.guardarVuelo(vuelo29);
        vuelosUADE.guardarVuelo(vuelo30);
        vuelosUADE.guardarVuelo(vuelo31);
        vuelosUADE.guardarVuelo(vuelo32);
        vuelosUADE.guardarVuelo(vuelo33);
        vuelosUADE.guardarVuelo(vuelo34);
        vuelosUADE.guardarVuelo(vuelo35);
        vuelosUADE.guardarVuelo(vuelo36);
        vuelosUADE.guardarVuelo(vuelo37);
        vuelosUADE.guardarVuelo(vuelo38);
        vuelosUADE.guardarVuelo(vuelo39);
        vuelosUADE.guardarVuelo(vuelo40);
        vuelosUADE.guardarVuelo(vuelo41);
        vuelosUADE.guardarVuelo(vuelo42);
        vuelosUADE.guardarVuelo(vuelo43);
        vuelosUADE.guardarVuelo(vuelo44);
        vuelosUADE.guardarVuelo(vuelo45);
        vuelosUADE.guardarVuelo(vuelo46);
        vuelosUADE.guardarVuelo(vuelo47);
        vuelosUADE.guardarVuelo(vuelo48);
        vuelosUADE.guardarVuelo(vuelo49);
        vuelosUADE.guardarVuelo(vuelo50);



        vuelosUADE.menuPrincipal();


        //vuelo1.mostrarPrecios();
        //vuelosUADE.mostrarVuelo();
        //vuelosUADE.mostrarPasajes();



    }
}