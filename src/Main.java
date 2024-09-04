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
        LocalDate now = LocalDate.now();

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
                    while(clientName.isEmpty()){
                        System.out.print("Client name: ");
                        clientName = scanner.nextLine();
                    }

                    System.out.print("Client age: ");
                    int age = 0;
                    while (age <= 0) {
                        try {
                            age = Integer.parseInt(scanner.nextLine());
                            if (age <= 0) {
                                System.out.println("Age must be a positive number. Please enter a valid age.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid age. Please enter a number.");
                        }
                    }

                    System.out.print("Client address: ");
                    String address = scanner.nextLine();
                    while(address.isEmpty()){
                        System.out.print("Client address: ");
                        address = scanner.nextLine();
                    }

                    System.out.print("Client phone: ");
                    String phone = scanner.nextLine();
                    while(phone.isEmpty()){
                        System.out.print("Client phone: ");
                        phone = scanner.nextLine();
                    }

                    System.out.print("Numero de chambre: ");
                    int chambreNumero = -1;
                    while (chambreNumero <= 0) {
                        try {
                            chambreNumero = Integer.parseInt(scanner.nextLine());
                            if (chambreNumero <= 0) {
                                System.out.println("Room number must be a positive number. Please enter a valid room number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid room number. Please enter a number.");
                        }
                    }


                    System.out.print("Date Debut (yyyy-MM-dd): ");
                    LocalDate dateDebut;
                    while (true) {
                        try {
                            dateDebut = LocalDate.parse(scanner.nextLine(), formatter);
                            if (dateDebut.isBefore(now)) {
                                System.out.println("Start date must be today or later. Please enter a valid start date.");
                            } else {
                                break;
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid start date. Please enter the date in the format yyyy-MM-dd.");
                        }
                    }

                    System.out.print("Date Fin (yyyy-MM-dd): ");
                    LocalDate dateFin;
                    while (true) {
                        try {
                            dateFin = LocalDate.parse(scanner.nextLine(), formatter);
                            if (dateFin.isBefore(dateDebut)) {
                                System.out.println("End date cannot be before the start date. Please enter a valid end date.");
                            } else {
                                break;
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid end date. Please enter the date in the format yyyy-MM-dd.");
                        }
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
                    System.out.print("id de la reservation à modifier: ");
                    int ReservIdModif = -1;
                    while (ReservIdModif <= 0) {
                        try {
                            ReservIdModif = Integer.parseInt(scanner.nextLine());
                            if (ReservIdModif <= 0) {
                                System.out.println("Room number must be a positive number. Please enter a valid room number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid room number. Please enter a number.");
                        }
                    }

                    System.out.print("Nouveau nom du client: ");
                    String newClientName = scanner.nextLine();

                    System.out.print("Nouvel âge du client: ");
                    int newAge = 0;
                    while (newAge <= 0) {
                        try {
                            newAge = Integer.parseInt(scanner.nextLine());
                            if (newAge <= 0) {
                                System.out.println("Age must be a positive number. Please enter a valid age.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid age. Please enter a number.");
                        }
                    }

                    System.out.print("Nouvelle adresse du client: ");
                    String newAddress = scanner.nextLine();

                    System.out.print("Nouveau numéro de téléphone du client: ");
                    String newPhone = scanner.nextLine();

                    System.out.print("Nouvelle date de début (yyyy-MM-dd): ");
                    LocalDate newDateDebut;
                    while (true) {
                        try {
                            newDateDebut = LocalDate.parse(scanner.nextLine(), formatter);
                            if (newDateDebut.isBefore(now)) {
                                System.out.println("Start date must be today or later. Please enter a valid start date.");
                            } else {
                                break;
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid start date. Please enter the date in the format yyyy-MM-dd.");
                        }
                    }


                    System.out.print("Nouvelle date de fin (yyyy-MM-dd): ");
                    LocalDate newDateFin;
                    while (true) {
                        try {
                            newDateFin = LocalDate.parse(scanner.nextLine(), formatter);
                            if (newDateFin.isBefore(newDateDebut)) {
                                System.out.println("End date cannot be before the start date. Please enter a valid end date.");
                            } else {
                                break;
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid end date. Please enter the date in the format yyyy-MM-dd.");
                        }
                    }

                    Client newClient = new Client(newClientName, newAge, newAddress, newPhone);
                    boolean modifSuccess = hotel.modifierReservation(ReservIdModif, newClient, newDateDebut, newDateFin);
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
                    System.out.print("Entrez id de la réservation à annuler: ");
                    int RervationId;
                    try {
                        RervationId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("id invalide. Veuillez entrer id de reservation .");
                        break;
                    }
                    hotel.annulerReservation(RervationId);
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
