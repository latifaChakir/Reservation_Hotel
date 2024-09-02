import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("    Application de Gestion de Réservation d'Hôtel \n");
        System.out.print(" ==================================================");
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.println("\n1. Créer une réservation");
            System.out.println("2. Modifier une réservation");
            System.out.println("3. Afficher toutes les réservations");
            System.out.println("4. Afficher les réservations d'un client");
            System.out.println("5. Annuler une réservation");
            System.out.println("6. Quitter");
            System.out.print("Choisissez une option: ");


            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Client name: ");
                    String clientName = scanner.nextLine();

                    System.out.print("Client age: ");
                    int age;
                    try {
                        age = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid age. Please enter a number.");
                        break;
                    }

                    System.out.print("Client address: ");
                    String address = scanner.nextLine();

                    System.out.print("Client phone: ");
                    String phone = scanner.nextLine();

                    System.out.print("Numero de chambre : ");
                    int chambreNumero;
                    try {
                        chambreNumero = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid room number. Please enter a number.");
                        break;
                    }

                    System.out.print("Date Debut (yyyy-MM-dd): ");
                    LocalDate dateDebut;
                    try {
                        dateDebut = LocalDate.parse(scanner.nextLine(), formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid start date. Please enter the date in the format yyyy-MM-dd.");
                        break;
                    }

                    System.out.print("Date Fin (yyyy-MM-dd): ");
                    LocalDate dateFin;
                    try {
                        dateFin = LocalDate.parse(scanner.nextLine(), formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid end date. Please enter the date in the format yyyy-MM-dd.");
                        break;
                    }

                    Client client = new Client(clientName, age, address, phone);
                    boolean success = hotel.creerReservation(client, chambreNumero, dateDebut, dateFin);
                    if (success) {
                        System.out.println("Reservation created successfully!");
                    } else {
                        System.out.println("Failed to create reservation. Room might be unavailable.");
                    }
                    break;

                case 2:
                    System.out.print("Numero de chambre de la reservation à modifier: ");
                    int chambreNumeroModif;
                    try {
                        chambreNumeroModif = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid room number. Please enter a number.");
                        break;
                    }

                    System.out.print("Nouveau nom du client: ");
                    String newClientName = scanner.nextLine();

                    System.out.print("Nouvel âge du client: ");
                    int newAge;
                    try {
                        newAge = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid age. Please enter a number.");
                        break;
                    }

                    System.out.print("Nouvelle adresse du client: ");
                    String newAddress = scanner.nextLine();

                    System.out.print("Nouveau numéro de téléphone du client: ");
                    String newPhone = scanner.nextLine();

                    System.out.print("Nouvelle date de début (yyyy-MM-dd): ");
                    LocalDate newDateDebut;
                    try {
                        newDateDebut = LocalDate.parse(scanner.nextLine(), formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid start date. Please enter the date in the format yyyy-MM-dd.");
                        break;
                    }

                    System.out.print("Nouvelle date de fin (yyyy-MM-dd): ");
                    LocalDate newDateFin;
                    try {
                        newDateFin = LocalDate.parse(scanner.nextLine(), formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid end date. Please enter the date in the format yyyy-MM-dd.");
                        break;
                    }

                    Client newClient = new Client(newClientName, newAge, newAddress, newPhone);
                    boolean modifSuccess = hotel.modifierReservation(chambreNumeroModif, newClient, newDateDebut, newDateFin);
                    if (modifSuccess) {
                        System.out.println("Reservation modified successfully!");
                    } else {
                        System.out.println("Failed to modify reservation. Room might not be booked yet or some error occurred.");
                    }
                    break;

                case 3:
                    hotel.afficherToutesLesReservations();
                    break;

                case 4:
                    System.out.print("Entrez le nom du client: ");
                    String nomClient = scanner.nextLine();
                    hotel.afficherReservationsClient(nomClient);
                    break;

                case 5:
                    System.out.print("Entrez le numéro de la chambre de la réservation à annuler: ");
                    int chambreNumeroAnnuler;
                    try {
                        chambreNumeroAnnuler = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Numéro de chambre invalide. Veuillez entrer un nombre.");
                        break;
                    }
                    hotel.annulerReservation(chambreNumeroAnnuler);
                    break;

                case 6:
                    System.out.println("Au revoir !");
                    return;

                default:
                    System.out.println("Option invalide.");
            }
        }
    }
}
