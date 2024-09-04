import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private List<Chambre> chambres;
    private List<Reservation> reservations;
    private static int currentReservationId = 1;

    public Hotel() {
        this.chambres = new ArrayList<Chambre>();
        this.reservations = new ArrayList<Reservation>();
        this.chambres.add(new Chambre(1,"simple", true));
        this.chambres.add(new Chambre(2,"simple",  true));
        this.chambres.add(new Chambre(3,"simple",  false));

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

    public boolean creerReservation(Client client, int numeroChambre, LocalDate debut, LocalDate fin) {
        Chambre chambre = trouverChambre(numeroChambre, debut, fin);
        if (chambre != null) {
            int reservationId = currentReservationId++;
            Reservation newReservation = new Reservation(reservationId,client, chambre, debut, fin);
            reservations.add(newReservation);
            chambre.reserver();
            return true;
        }
        return false;
    }

    public Chambre trouverChambre(int numeroChambre, LocalDate dateDebut, LocalDate dateFin) {
        for (Chambre chambre : chambres) {
            if (chambre.getNumero() == numeroChambre) {
                boolean isAvailable = true;

                for (Reservation reservation : reservations) {
                    if (reservation.getChambre().getNumero() == numeroChambre) {
                        if (reservation.getDateFin().isBefore(LocalDate.now())) {
                            reservation.getChambre().liberer();
                            reservations.remove(reservation);
                        } else {
                            if (!(dateFin.isBefore(reservation.getDateDebut()) || dateDebut.isAfter(reservation.getDateFin()))) {
                                isAvailable = false;
                                break;
                            }
                        }
                    }
                }
                return isAvailable ? chambre : null;
            }
        }
        return null;
    }



    public void annulerReservation(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                reservation.getChambre().liberer();
                reservations.remove(reservation);
                System.out.println("Réservation annulée avec succès pour l'ID de réservation " + reservationId);
                return;
            }
        }
        System.out.println("Aucune réservation trouvée avec l'ID " + reservationId);
    }

    public boolean modifierReservation(int reservationId, Client client, LocalDate debut, LocalDate fin, int numeroChambre) {
        // Rechercher la réservation à modifier
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                // Trouver une chambre disponible pour les nouvelles dates
                Chambre nouvelleChambre = trouverChambre(numeroChambre, debut, fin);

                // Vérifier si la chambre est disponible
                if (nouvelleChambre == null) {
                    System.out.println("La chambre avec le numéro spécifié n'est pas disponible pour les dates données.");
                    return false;
                }

                // Mettre à jour les détails de la réservation
                reservation.setClient(client);
                reservation.setDateDebut(debut);
                reservation.setDateFin(fin);
                reservation.setChambre(nouvelleChambre);
                return true;
            }
        }
        System.out.println("Aucune réservation trouvée avec l'ID " + reservationId);
        return false;
    }

    public Chambre getChambreByNumero(int numeroChambre) {
        for (Chambre chambre : chambres) {
            if (chambre.getNumero() == numeroChambre) {
                return chambre;
            }
        }
        return null;
    }


    public void afficherToutesLesReservations() {
        if (reservations.isEmpty()) {
            System.out.println("|-----------------------------------------------------------------------------------------------|");
            System.out.println("|    id    |    Chambre     |        Client        |         Debut        |          Fin        |");
            System.out.println("|-----------------------------------------------------------------------------------------------|");
            System.out.println("|                          Aucune réservation n'a été trouvée.                                  |");
            System.out.println("|-----------------------------------------------------------------------------------------------|");

        } else {
            System.out.println("|-----------------------------------------------------------------------------------------------|");
            System.out.println("|    id    |    Chambre     |        Client        |         Debut        |          Fin        |");
            System.out.println("|-----------------------------------------------------------------------------------------------|");
            for (Reservation reservation : reservations) {
                System.out.println("|      "+reservation.getId()+ "   |       " + reservation.getChambre().getNumero() +"        |         "
                        + reservation.getClient().getName() + "          |      "
                        + reservation.getDateDebut() + "      |       "
                        + reservation.getDateFin() + "    |     ");
            System.out.println("|-----------------------------------------------------------------------------------------------|");

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
