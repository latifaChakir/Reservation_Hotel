import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private List<Chambre> chambres;
    private List<Reservation> reservations;

    public Hotel() {
        this.chambres = new ArrayList<Chambre>();
        this.reservations=new ArrayList<Reservation>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean creerReservation(Client client, int numerochambre, LocalDate debut, LocalDate fin) {
        Chambre chambre=trouverChambre(numerochambre);
        if(chambre!=null && chambre.isDisponible()) {
            Reservation reservation= new Reservation(id,client, chambre, debut,fin);
            reservations.add(reservation);
            chambre.reserver();
            return true;

        }
        return false;
    }
    public Chambre trouverChambre(int numeroChambre) {
        for (Chambre chambre : chambres) {
            if (chambre.getNumero() == numeroChambre) {
                return chambre;
            }
        }
        return null;
    }
    public void annulerRervation(Reservation reservation) {
        reservation.getChambre().liberer();
        reservations.remove(reservation);
    }

    public boolean modifierReservation(int numerochambre,Client client,LocalDate debut, LocalDate fin) {
        Chambre chambre=trouverChambre(numerochambre);
        if(chambre!=null && chambre.isDisponible()) {
            Reservation reservation=reservations.get(chambre.getNumero());
            reservation.setChambre(chambre);
            reservation.setClient(client);
            reservation.setDateDebut(debut);
            reservation.setDateFin(fin);

        }else{
            return false;
        }
        return true;
    }



}
