public class Chambre {
    private  int numero;
    private  String type;
    private boolean isDisponible;
    public Chambre(int numero, String type, boolean isDisponible) {
        this.numero = numero;
        this.type=type;
        this.isDisponible=isDisponible;
    }
    public int getNumero() {
        return  numero;
    }
    public String getType() {
        return  type;
    }
    public boolean isDisponible() {
        return isDisponible;
    }
    public void reserver(){
        this.isDisponible=false;
    }
    public void liberer(){
        this.isDisponible=true;
    }

    public String toString(){
        return  "Chambre {"
                + "numero : "+numero+ "," +
                " type : "+type+ ", " +
                "isDisponible : "+isDisponible+'}';
    }
}
