import java.util.HashSet;

public class Empresa {

    private HashSet <Vuelo> listaVuelos;

    public Empresa() {
        listaVuelos = new HashSet<>();
    }

    public void guardarVuelo(Vuelo vuelo){
        listaVuelos.add(vuelo);
    }

    public void mostrarVuelo(){
        System.out.println(listaVuelos);
    }

}
