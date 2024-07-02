public class Visualizador { //Clase singleton
    private static Visualizador instanciaVisualizador;

    public Visualizador() {
    }

    public static Visualizador getInstancia(){
        if(instanciaVisualizador == null){
            instanciaVisualizador = new Visualizador();
        }
        return instanciaVisualizador;
    }

    public void visualizarMensaje(String mensaje){
        System.out.println(mensaje);
    }

    public void visualizarMensaje(int mensaje){
        System.out.println(mensaje);
    }

    public void saltoDeLinea(){
        System.out.println("");
    }










}
