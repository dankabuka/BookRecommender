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
 * La classe Libreria rappresenta una libreria virtuale creata da un utente, contenente una lista di libri.
 * Ogni libreria è associata a un utente specifico e può contenere un numero illimitato di titoli di libri.
 *
 * @author UDDIN SHAKIM AHMED
 */
public class Libreria {
    /**
     * L'ID dell'utente che ha creato la libreria.
     */
    String userID;

    /**
     * Il nome assegnato alla libreria dall'utente.
     */
    String nomeLibreria;

    /**
     * La lista dei titoli dei libri associati alla libreria.
     */
    List<String> libri;

    /**
     * Costruisce un nuovo oggetto Libreria con un nome specificato, associato a un determinato utente.
     *
     * @param userID       L'ID dell'utente che crea la libreria.
     * @param nomeLibreria Il nome della libreria.
     * @author UDDIN SHAKIM AHMED
     */
    public Libreria(String userID, String nomeLibreria) {
        this.userID = userID;
        this.nomeLibreria = nomeLibreria;
        this.libri = new ArrayList<>();
    }

    /**
     * Aggiunge un nuovo libro alla libreria.
     *
     * @param titolo Il titolo del libro da aggiungere alla libreria.
     * @author UDDIN SHAKIM AHMED
     */
    public void aggiungiLibro(String titolo) {
        libri.add(titolo);
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Libreria.
     *
     * @return Una stringa che contiene l'ID dell'utente, il nome della libreria, e i titoli dei libri associati,
     * separati da punti e virgola e virgole.
     * @author UDDIN SHAKIM AHMED
     */
    @Override
    public String toString() {
        return userID + ";" + nomeLibreria + ";" + String.join(",", libri);
    }
}
