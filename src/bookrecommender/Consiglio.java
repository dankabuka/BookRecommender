package bookrecommender;

import java.util.ArrayList;
import java.util.List;

/*
PROGETTO REALIZZATO DA:
UDDIN SHAKIM AHMED - Matricola: 751180 - Sede: VA
KABUKA DAN MUMANGA - Matricola: 757708 - Sede: VA
LANDINI MATTEO - Matricola: 753593 - Sede: VA
*/

/**
 * La classe Consiglio rappresenta i consigli di lettura forniti da un utente per un determinato libro.
 * Ogni consiglio può contenere fino a un massimo di 3 libri consigliati.
 *
 * @author UDDIN SHAKIM AHMED
 */
public class Consiglio {
    /**
     * L'ID dell'utente che ha fornito i consigli.
     */
    String userID;

    /**
     * Il titolo del libro per cui vengono forniti i consigli.
     */
    String titoloLibro;

    /**
     * La lista dei titoli dei libri consigliati dall'utente.
     * Può contenere al massimo 3 titoli.
     */
    List<String> consigliati;

    /**
     * Costruisce un nuovo oggetto Consiglio per un determinato libro e utente.
     *
     * @param userID      L'ID dell'utente che fornisce il consiglio.
     * @param titoloLibro Il titolo del libro per cui viene fornito il consiglio.
     * @author UDDIN SHAKIM AHMED
     */
    public Consiglio(String userID, String titoloLibro) {
        this.userID = userID;
        this.titoloLibro = titoloLibro;
        this.consigliati = new ArrayList<>();
    }

    /**
     * Aggiunge un nuovo libro alla lista dei consigliati, se non è già stato raggiunto il limite di 3 libri.
     *
     * @param titoloConsigliato Il titolo del libro da aggiungere alla lista dei consigli.
     * @author UDDIN SHAKIM AHMED
     */
    public void inserisciSuggerimentoLibro(String titoloConsigliato) {
        if (consigliati.size() < 3) {
            consigliati.add(titoloConsigliato);
        } else {
            System.out.println("Puoi aggiungere solo fino a 3 consigli.");
        }
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Consiglio.
     *
     * @return Una stringa che contiene l'ID dell'utente, il titolo del libro, e i titoli dei libri consigliati,
     * separati da punti e virgola e virgole.
     * @author UDDIN SHAKIM AHMED
     */
    @Override
    public String toString() {
        return userID + ";" + titoloLibro + ";" + String.join(",", consigliati);
    }
}
