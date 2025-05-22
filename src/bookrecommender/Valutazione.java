package bookrecommender;

/*
PROGETTO REALIZZATO DA:
UDDIN SHAKIM AHMED - Matricola: 751180 - Sede: VA
KABUKA DAN MUMANGA - Matricola: 757708 - Sede: VA
LANDINI MATTEO - Matricola: 753593 - Sede: VA
*/

/**
 * La classe Valutazione rappresenta una valutazione di un libro da parte di un utente.
 * Ogni valutazione include punteggi su vari aspetti del libro e un voto finale calcolato come media di questi punteggi.
 *
 * @author UDDIN SHAKIM AHMED
 */
public class Valutazione {

    /**
     * L'ID dell'utente che ha effettuato la valutazione.
     */
    String userID;

    /**
     * Il titolo del libro valutato.
     */
    String titoloLibro;

    /**
     * Il punteggio assegnato per lo stile del libro (da 1 a 5).
     */
    int stile;

    /**
     * Il punteggio assegnato per il contenuto del libro (da 1 a 5).
     */
    int contenuto;

    /**
     * Il punteggio assegnato per la gradevolezza del libro (da 1 a 5).
     */
    int gradevolezza;

    /**
     * Il punteggio assegnato per l'originalita del libro (da 1 a 5).
     */
    int originalita
;

    /**
     * Il punteggio assegnato per l'edizione del libro (da 1 a 5).
     */
    int edizione;

    /**
     * Il voto finale calcolato come media dei punteggi per stile, contenuto, gradevolezza, originalita ed edizione.
     */
    int votoFinale;

    /**
     * Costruttore della classe Valutazione. Inizializza i campi con i valori forniti
     * e calcola automaticamente il voto finale come media dei punteggi.
     *
     * @param userID       L'ID dell'utente che ha effettuato la valutazione.
     * @param titoloLibro  Il titolo del libro valutato.
     * @param stile        Il punteggio assegnato per lo stile del libro (da 1 a 5).
     * @param contenuto    Il punteggio assegnato per il contenuto del libro (da 1 a 5).
     * @param gradevolezza Il punteggio assegnato per la gradevolezza del libro (da 1 a 5).
     * @param originalita  Il punteggio assegnato per l'originalita del libro (da 1 a 5).
     * @param edizione     Il punteggio assegnato per l'edizione del libro (da 1 a 5).
     */
    public Valutazione(String userID, String titoloLibro, int stile, int contenuto, int gradevolezza, int originalita
, int edizione) {
        this.userID = userID;
        this.titoloLibro = titoloLibro;
        this.stile = stile;
        this.contenuto = contenuto;
        this.gradevolezza = gradevolezza;
        this.originalita = originalita
;
        this.edizione = edizione;
        this.votoFinale = calcolaVotoFinale();
    }

    /**
     * Calcola il voto finale come la media dei punteggi assegnati per stile, contenuto, gradevolezza, originalita ed edizione.
     *
     * @return Il voto finale calcolato.
     */
    private int calcolaVotoFinale() {
        return (stile + contenuto + gradevolezza + originalita + edizione) / 5;
    }

    /**
     * Restituisce una rappresentazione in formato stringa della valutazione.
     *
     * @return Una stringa contenente i dettagli della valutazione, formattata come
     *         "userID;titoloLibro;stile;contenuto;gradevolezza;originalita
;edizione;votoFinale".
     */
    @Override
    public String toString() {
        // Usa il formato separato da punto e virgola per salvarlo nel file
        return userID + ";" +
               titoloLibro + ";" +
               stile + ";" +
               contenuto + ";" +
               gradevolezza + ";" +
               originalita + ";" +
               edizione + ";" +
               votoFinale;
    }
}
