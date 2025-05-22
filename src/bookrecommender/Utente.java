package bookrecommender;

/*
PROGETTO REALIZZATO DA:
UDDIN SHAKIM AHMED - Matricola: 751180 - Sede: VA
KABUKA DAN MUMANGA - Matricola: 757708 - Sede: VA
LANDINI MATTEO - Matricola: 753593 - Sede: VA
*/

/**
 * La classe Utente rappresenta un utente registrato nel sistema.
 * Contiene informazioni personali e di autenticazione dell'utente.
 *
 * @author UDDIN SHAKIM AHMED
 */
public class Utente {
    
    /**
     * Il nome e cognome dell'utente.
     */
    String nomeCognome;
    
    /**
     * Il codice fiscale dell'utente.
     */
    String codiceFiscale;
    
    /**
     * L'indirizzo email dell'utente.
     */
    String email;
    
    /**
     * L'ID utente utilizzato per il login.
     */
    String userID;
    
    /**
     * La password dell'utente utilizzata per il login.
     */
    String password;

    /**
     * Costruttore della classe Utente. Inizializza i campi dell'utente con i valori forniti.
     *
     * @param nomeCognome    Il nome e cognome dell'utente.
     * @param codiceFiscale  Il codice fiscale dell'utente.
     * @param email          L'indirizzo email dell'utente.
     * @param userID         L'ID utente utilizzato per il login.
     * @param password       La password dell'utente utilizzata per il login.
     * @author UDDIN SHAKIM AHMED
     */
    public Utente(String nomeCognome, String codiceFiscale, String email, String userID, String password) {
        this.nomeCognome = nomeCognome;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.userID = userID;
        this.password = password;
    }
}
