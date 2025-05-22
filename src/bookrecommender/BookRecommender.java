package bookrecommender;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Sistema di raccomandazione di libri che permette agli utenti di cercare,
 * visualizzare,
 * registrarsi, effettuare login, creare librerie personali, aggiungere
 * valutazioni e fornire consigli sui libri.
 *
 * PROGETTO REALIZZATO DA:
 * UDDIN SHAKIM AHMED - Matricola: 751180 - Sede: VA
 * KABUKA DAN MUMANGA - Matricola: 757708 - Sede: VA
 * LANDINI MATTEO - Matricola: 753593 - Sede: VA
 */
public class BookRecommender {

    private static final String LIBRI_FILE = "C:\\Users\\Clarabella\\Documents\\BookRecommender\\data/Libri.dati";     // "data/Libri.dati";
    private static final String UTENTI_FILE = "C:\\Users\\Clarabella\\Documents\\BookRecommender\\data/UtentiRegistrati.dati"; // "data/UtentiRegistrati.dati";
    private static final String LIBRERIE_FILE = "C:\\Users\\Clarabella\\Documents\\BookRecommender\\data/Librerie.dati"; // "data/Librerie.dati";
    private static final String VALUTAZIONI_FILE = "C:\\Users\\Clarabella\\Documents\\BookRecommender\\data/ValutazioniLibri.dati"; // "data/ValutazioniLibri.dati";
    private static final String CONSIGLI_FILE = "C:\\Users\\Clarabella\\Documents\\BookRecommender\\data/ConsigliLibri.dati"; // "data/ConsigliLibri.dati";

    private static Utente utenteLoggato = null;

    /**
     * Metodo principale che avvia il programma e gestisce il menu principale del
     * sistema di raccomandazione.
     *
     * @param args Argomenti da linea di comando (non utilizzati).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n Menu Principale:");
            System.out.println("1. Cerca libro");
            System.out.println("2. Visualizza libro");
            System.out.println("3. Registrati");
            System.out.println("4. Login");
            System.out.println("5. Crea libreria");
            System.out.println("6. Aggiungi valutazione libro");
            System.out.println("7. Aggiungi consiglio libro");
            System.out.println("8. Esci");
            System.out.print("Scegli un'opzione: ");
            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma la newline

            switch (scelta) {
                case 1:
                    System.out.print("Inserisci il titolo, autore, o autore + anno: ");
                    String query = scanner.nextLine();
                    cercaLibro(query);
                    break;
                case 2:
                    System.out.print("Inserisci il titolo del libro da visualizzare: ");
                    String titolo = scanner.nextLine();
                    visualizzaLibro(titolo);
                    break;
                case 3:
                    registrazione(scanner);
                    break;
                case 4:
                    login(scanner);
                    break;
                case 5:
                    if (utenteLoggato != null) {
                        registraLibreria(scanner);
                    } else {
                        System.out.println("Devi effettuare il login prima di creare una libreria.");
                    }
                    break;
                case 6:
                    if (utenteLoggato != null) {
                        inserisciValutazioneLibro(scanner);
                    } else {
                        System.out.println("Devi effettuare il login prima di aggiungere una valutazione.");
                    }
                    break;
                case 7:
                    if (utenteLoggato != null) {
                        inserisciSuggerimentoLibro(scanner);
                    } else {
                        System.out.println("Devi effettuare il login prima di aggiungere un consiglio.");
                    }
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Opzione non valida.");
            }
        }

        scanner.close();
    }

    /**
     * Cerca un libro nel file dei libri basandosi sulla query fornita dall'utente.
     * La query può essere il titolo, autore o una combinazione di autore e anno.
     *
     * @param query Il titolo, autore o autore + anno del libro da cercare.
     */
    private static void cercaLibro(String query) {
        try (BufferedReader br = new BufferedReader(new FileReader(LIBRI_FILE))) {
            String line;
            boolean found = false;

            // Se la query contiene sia autori che anno (es. "Colton, Larry 1993")
            String[] parts = query.split(" ");
            String possibleYear = parts[parts.length - 1]; // L'ultimo elemento potrebbe essere l'anno
            String authorQuery = query.replace(possibleYear, "").trim(); // Rimuovi l'anno dalla query

            while ((line = br.readLine()) != null) {
                // Ricerca per titolo, autori, o autori + anno
                if (line.toLowerCase().contains(query.toLowerCase()) ||
                        (line.toLowerCase().contains(authorQuery.toLowerCase()) && line.contains(possibleYear))) {
                    System.out.println(line);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Nessun libro trovato.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Visualizza i dettagli di un libro specificato dall'utente, incluse le
     * valutazioni e i consigli disponibili.
     * 
     * @param titolo Il titolo del libro da visualizzare.
     */
    private static void visualizzaLibro(String titolo) {
        try (BufferedReader br = new BufferedReader(new FileReader(LIBRI_FILE))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().startsWith(titolo.toLowerCase())) {
                    System.out.println("Dettagli del libro:");
                    System.out.println(line);
                    found = true;
                    mostraValutazioniEConsigli(titolo);
                }
            }
            if (!found) {
                System.out.println("Libro non trovato.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mostra le valutazioni e i consigli di lettura aggregati per un libro
     * specifico.
     *
     * @param titolo Il titolo del libro per cui mostrare valutazioni e consigli.
     */
    private static void mostraValutazioniEConsigli(String titolo) {
        System.out.println("Valutazioni del libro:");
        int totalStile = 0, totalContenuto = 0, totalGradevolezza = 0, totalOriginalita = 0, totalEdizione = 0;
        int countValutazioni = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(VALUTAZIONI_FILE))) {
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                String[] valutazioneData = line.split(";");

                if (valutazioneData.length >= 7 && valutazioneData[1].equalsIgnoreCase(titolo)) {
                    int stile = Integer.parseInt(valutazioneData[2]);
                    int contenuto = Integer.parseInt(valutazioneData[3]);
                    int gradevolezza = Integer.parseInt(valutazioneData[4]);
                    int originalita = Integer.parseInt(valutazioneData[5]);
                    int edizione = Integer.parseInt(valutazioneData[6]);

                    totalStile += stile;
                    totalContenuto += contenuto;
                    totalGradevolezza += gradevolezza;
                    totalOriginalita += originalita;
                    totalEdizione += edizione;
                    countValutazioni++;

                    found = true;
                }
            }

            if (found) {
                System.out.println("Numero di valutazioni: " + countValutazioni);
                System.out.println("Media Stile: " + (totalStile / (double) countValutazioni));
                System.out.println("Media Contenuto: " + (totalContenuto / (double) countValutazioni));
                System.out.println("Media Gradevolezza: " + (totalGradevolezza / (double) countValutazioni));
                System.out.println("Media Originalità: " + (totalOriginalita / (double) countValutazioni));
                System.out.println("Media Edizione: " + (totalEdizione / (double) countValutazioni));
            } else {
                System.out.println("Nessuna valutazione trovata.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nConsigli di lettura per il libro:");
        Map<String, Integer> consigliAggregati = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CONSIGLI_FILE))) {
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                String[] consiglioData = line.split(";");

                if (consiglioData.length >= 2 && consiglioData[1].equalsIgnoreCase(titolo)) {
                    found = true;

                    for (int i = 2; i < consiglioData.length; i++) {
                        String libroConsigliato = consiglioData[i].trim();
                        consigliAggregati.put(libroConsigliato,
                                consigliAggregati.getOrDefault(libroConsigliato, 0) + 1);
                    }
                }
            }

            if (found) {
                for (Map.Entry<String, Integer> entry : consigliAggregati.entrySet()) {
                    System.out.println(entry.getKey() + " consigliato " + entry.getValue() + " volte");
                }
            } else {
                System.out.println("Nessun consiglio trovato.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Registra un nuovo utente nel sistema, richiedendo le informazioni necessarie
     * e salvandole nel file degli utenti.
     *
     * @param scanner Lo scanner utilizzato per leggere l'input dell'utente.
     * @author UDDIN SHAKIM AHMED
     */
    private static void registrazione(Scanner scanner) {
        System.out.print("Nome e Cognome: ");
        String nomeCognome = scanner.nextLine();
        System.out.print("Codice Fiscale: ");
        String codiceFiscale = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("UserID: ");
        String userID = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(UTENTI_FILE, true))) {
            Utente nuovoUtente = new Utente(nomeCognome, codiceFiscale, email, userID, password);
            bw.write(nuovoUtente.nomeCognome + ";" + nuovoUtente.codiceFiscale + ";" + nuovoUtente.email + ";"
                    + nuovoUtente.userID + ";" + nuovoUtente.password);
            bw.newLine();
            System.out.println("Registrazione completata con successo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permette a un utente di effettuare il login nel sistema verificando le
     * credenziali fornite.
     *
     * @param scanner Lo scanner utilizzato per leggere l'input dell'utente.
     * @author UDDIN SHAKIM AHMED
     */
    private static void login(Scanner scanner) {
        System.out.print("UserID: ");
        String userID = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        boolean loggedIn = false;

        try (BufferedReader br = new BufferedReader(new FileReader(UTENTI_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields[3].equals(userID) && fields[4].equals(password)) {
                    utenteLoggato = new Utente(fields[0], fields[1], fields[2], fields[3], fields[4]);
                    loggedIn = true;
                    System.out.println("Login effettuato con successo.");
                    break;
                }
            }

            if (!loggedIn) {
                System.out.println("UserID o Password errati.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permette all'utente loggato di creare una nuova libreria e aggiungere libri a
     * essa.
     *
     * @param scanner Lo scanner utilizzato per leggere l'input dell'utente.
     * @author UDDIN SHAKIM AHMED
     */
    private static void registraLibreria(Scanner scanner) {
        System.out.print("Nome della libreria: ");
        String nomeLibreria = scanner.nextLine();
        Libreria nuovaLibreria = new Libreria(utenteLoggato.userID, nomeLibreria);

        System.out.println("Aggiungi libri alla libreria (digita 'fine' per terminare):");
        while (true) {
            String titoloLibro = scanner.nextLine();
            if (titoloLibro.equalsIgnoreCase("fine")) {
                break;
            }
            nuovaLibreria.aggiungiLibro(titoloLibro);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LIBRERIE_FILE, true))) {
            bw.write(nuovaLibreria.toString());
            bw.newLine();
            System.out.println("Libreria creata con successo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permette all'utente loggato di aggiungere una valutazione a un libro
     * specificato, calcolando automaticamente
     * la media dei voti inseriti.
     *
     * @param scanner Lo scanner utilizzato per leggere l'input dell'utente.
     * @author UDDIN SHAKIM AHMED
     */
    private static void inserisciValutazioneLibro(Scanner scanner) {
        System.out.print("Titolo del libro da valutare: ");
        String titoloLibro = scanner.nextLine();
        System.out.print("Valuta il libro per Stile (1-5): ");
        int stile = scanner.nextInt();
        System.out.print("Valuta il libro per Contenuto (1-5): ");
        int contenuto = scanner.nextInt();
        System.out.print("Valuta il libro per Gradevolezza (1-5): ");
        int gradevolezza = scanner.nextInt();
        System.out.print("Valuta il libro per Originalita' (1-5): ");
        int originalita = scanner.nextInt();
        System.out.print("Valuta il libro per Edizione (1-5): ");
        int edizione = scanner.nextInt();
        scanner.nextLine(); // Consuma la newline

        Valutazione nuovaValutazione = new Valutazione(utenteLoggato.userID, titoloLibro, stile, contenuto,
                gradevolezza, originalita, edizione);

        System.out.println(
                "Il voto finale calcolato (media) per \"" + titoloLibro + "\" e' " + nuovaValutazione.votoFinale);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(VALUTAZIONI_FILE, true))) {
            bw.write(nuovaValutazione.toString());
            bw.newLine();
            System.out.println("Valutazione aggiunta con successo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permette all'utente loggato di aggiungere consigli di lettura per un libro
     * specificato.
     *
     * @param scanner Lo scanner utilizzato per leggere l'input dell'utente.
     * @author UDDIN SHAKIM AHMED
     */
    private static void inserisciSuggerimentoLibro(Scanner scanner) {
        System.out.print("Titolo del libro per cui dare consigli: ");
        String titoloLibro = scanner.nextLine();
        Consiglio nuovoConsiglio = new Consiglio(utenteLoggato.userID, titoloLibro);

        System.out.println("Aggiungi libri consigliati (massimo 3, digita 'fine' per terminare):");
        while (nuovoConsiglio.consigliati.size() < 3) {
            String titoloConsigliato = scanner.nextLine();
            if (titoloConsigliato.equalsIgnoreCase("fine")) {
                break;
            }
            nuovoConsiglio.inserisciSuggerimentoLibro(titoloConsigliato);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CONSIGLI_FILE, true))) {
            bw.write(nuovoConsiglio.toString());
            bw.newLine();
            System.out.println("Consigli aggiunti con successo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
