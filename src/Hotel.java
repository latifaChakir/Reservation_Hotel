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
        this.chambres.add(new Chambre(1,"simple", true));  // Chambre 1, disponible
        this.chambres.add(new Chambre(2,"simple",  true));  // Chambre 2, disponible
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

    public void annulerReservation(int chambreNumero) {
        for (Reservation reservation : reservations) {
            if (reservation.getChambre().getNumero() == chambreNumero) {
                reservation.getChambre().liberer();
                reservations.remove(reservation);
                System.out.println("Réservation annulée avec succès pour la chambre " + chambreNumero);
                return;
            }
        }
        System.out.println("Aucune réservation trouvée pour la chambre " + chambreNumero);
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

    public void afficherToutesLesReservations() {
        if (reservations.isEmpty()) {
            System.out.println("|-------------------------------------------------------------------------------------|");
            System.out.println("|     Chambre     |        Client        |         Debut        |          Fin        |");
            System.out.println("|-------------------------------------------------------------------------------------|");
            System.out.println("|                          Aucune réservation n'a été trouvée.                        |");
            System.out.println("|-------------------------------------------------------------------------------------|");

        } else {
            for (Reservation reservation : reservations) {
                System.out.println("|-------------------------------------------------------------------------------------|");
                System.out.println("|     Chambre     |        Client        |         Debut        |          Fin        |");
                System.out.println("|-------------------------------------------------------------------------------------|");
                System.out.println("|        " + reservation.getChambre().getNumero() +"        |       "
                        + reservation.getClient().getName() + "    |      "
                        + reservation.getDateDebut() + "      |       "
                        + reservation.getDateFin() + "    |     ");
                System.out.println("|-------------------------------------------------------------------------------------|");

            }
        }
    }
    public void afficherReservationsClient(String nomClient) {
        boolean found = false;
        for (Reservation reservation : reservations) {
            if (reservation.getClient().getName().equalsIgnoreCase(nomClient)) {

                System.out.println("|------------------------------------------------------------------------------------|");
                System.out.println("|     Chambre     |        Type         |         Debut        |          Fin        |");
                System.out.println("|------------------------------------------------------------------------------------|");
                System.out.println("|        " + reservation.getChambre().getNumero() +"        |       "
                        + reservation.getChambre().getType() + "        |      "
                        + reservation.getDateDebut() + "      |       "
                        + reservation.getDateFin() + "    |     ");
                System.out.println("|------------------------------------------------------------------------------------|");


                found = true;
            }
        }
        if (!found) {
            System.out.println("|-------------------------------------------------------------------------------------|");
            System.out.println("|     Chambre     |        Type        |         Debut        |          Fin        |");
            System.out.println("|-------------------------------------------------------------------------------------|");
            System.out.println("|             Aucune réservation trouvée pour le client" + nomClient+"                |");
            System.out.println("|-------------------------------------------------------------------------------------|");

        }
    }


}
