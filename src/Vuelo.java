import java.util.HashSet;

public class Vuelo {

    private int id;
    private String fecha; //Formato DD/MM/AAAA
    private String horario;
    private Ciudad origen;
    private Ciudad destino;
    private int duracion;
    private int escalas;
    private Aerolinea aerolinea;
    private Equipaje equipajeVuelo;
    private int precioEconomy;
    private int precioSuper;
    private int precioPrimera;

    public Vuelo(int id, String fecha, String horario, Ciudad origen, Ciudad destino, int duracion, int escalas, Aerolinea aerolinea, Equipaje equipajeVuelo) {
        this.id = id;
        this.fecha = fecha;
        this.horario = horario;
        this.origen = origen;
        this.destino = destino;
        this.duracion = duracion;
        this.escalas = escalas;
        this.aerolinea = aerolinea;
        this.equipajeVuelo = equipajeVuelo;

    }

    public int getDuracion() {
        return duracion;
    }

    public int getEscalas() {
        return escalas;
    }


    private int calcularPrecioDuracion(){
         int precioBase = aerolinea.getPrecioBase();

        if(duracion <= 2) {
            precioBase = (int) (precioBase * 1.15);

        } else if (duracion > 2 && duracion < 5) {
            precioBase = (int) (precioBase * 1.3);

        }else if (duracion > 4 && duracion < 7) {
            precioBase = (int) (precioBase * 1.45);

        }else if (duracion > 6 && duracion < 9) {
            precioBase = (int) (precioBase * 1.60);

        } else if (duracion > 8) {
            precioBase = (int) (precioBase * 1.75);

        }
        return precioBase;
    }

    private int calcularPrecioEscala(){
        int precioNuevo = calcularPrecioDuracion();

        if(escalas == 1){
            precioNuevo = (int) (precioNuevo * 0.90);
        }
        if(escalas == 2){
            precioNuevo = (int) (precioNuevo * 0.80);
        }
        if(escalas >= 3){
            precioNuevo = (int) (precioNuevo * 0.70);
        }

        return precioNuevo;
    }

    public void mostrarPrecios(){

        int precioEconomy = getPrecioEconomy();
        int precioSuper = getPrecioSuper();
        int precioPrimera = getPrecioPrimera();
        System.out.println("Estos son los valores para los asientos del vuelo con ID = " + this.id);
        System.out.println("----------------------------------------------------");
        System.out.println(" ");
        System.out.println("Precio Economy: " + precioEconomy);
        System.out.println("Precio Super: " + precioSuper);
        System.out.println("Precio Primera: " + precioPrimera);
    }

    public int getPrecioEconomy(){
        int precioEconomy = calcularPrecioEscala();
        this.setPrecioEconomy(precioEconomy);
        return precioEconomy;
    }

    public int getPrecioSuper(){
        int precioSuper = getPrecioEconomy();
        precioSuper = (int) (precioSuper * 1.5);
        this.setPrecioSuper(precioSuper);
        return precioSuper;
    }

    public int getPrecioPrimera(){
        int precioPrimera = getPrecioEconomy();
        precioPrimera = (int) (precioPrimera * 2);
        this.setPrecioPrimera(precioPrimera);
        return precioPrimera;
    }


    public String getFecha() {
        return fecha;
    }

    public String getHorario() {
        return horario;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public int getId() {
        return id;
    }

    public void setPrecioEconomy(int precioEconomy) {
        this.precioEconomy = precioEconomy;
    }

    public void setPrecioSuper(int precioSuper) {
        this.precioSuper = precioSuper;
    }

    public void setPrecioPrimera(int precioPrimera) {
        this.precioPrimera = precioPrimera;
    }

    public Equipaje getEquipajeVuelo() {
        return equipajeVuelo;
    }

}
