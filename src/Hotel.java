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
        this.reservations = new ArrayList<Reservation>();
    }

    // Getters and Setters
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

    // Méthode pour créer une réservation
    public boolean creerReservation(Client client, int numeroChambre, LocalDate debut, LocalDate fin) {
        Chambre chambre = trouverChambre(numeroChambre);
        if (chambre != null && chambre.isDisponible()) {
            Reservation reservation = new Reservation(client, chambre, debut, fin);
            reservations.add(reservation);
            chambre.reserver();
            return true;
        }
        return false;
    }

    // Méthode pour trouver une chambre par son numéro
    public Chambre trouverChambre(int numeroChambre) {
        for (Chambre chambre : chambres) {
            if (chambre.getNumero() == numeroChambre) {
                return chambre;
            }
        }
        return null;
    }

    public void annulerReservation(Reservation reservation) {
        reservation.getChambre().liberer();
        reservations.remove(reservation);
    }


    public boolean modifierReservation(int numeroChambre, Client client, LocalDate debut, LocalDate fin) {
        for (Reservation reservation : reservations) {
            if (reservation.getChambre().getNumero() == numeroChambre) {
                reservation.setClient(client);
                reservation.setDateDebut(debut);
                reservation.setDateFin(fin);
                return true;
            }
        }
        return false;
    }
}
