package bookrecommender;

/*
PROGETTO REALIZZATO DA:
UDDIN SHAKIM AHMED - Matricola: 751180 - Sede: VA
KABUKA DAN MUMANGA - Matricola: 757708 - Sede: VA
LANDINI MATTEO - Matricola: 753593 - Sede: VA
*/

/**
 * La classe Libro rappresenta un libro con informazioni di base quali il titolo, gli autori, l'anno di pubblicazione,
 * l'editore e la categoria.
 *
 * @author UDDIN SHAKIM AHMED
 */
public class Libro {
    /**
     * Il titolo del libro.
     */
    String titolo;

    /**
     * Gli autori del libro, elencati in una stringa.
     */
    String autori;

    /**
     * L'anno di pubblicazione del libro.
     */
    String anno;

    /**
     * L'editore del libro.
     */
    String editore;

    /**
     * La categoria o genere del libro.
     */
    String categoria;

    /**
     * Costruisce un nuovo oggetto Libro con i dettagli specificati.
     *
     * @param titolo    Il titolo del libro.
     * @param autori    Gli autori del libro.
     * @param anno      L'anno di pubblicazione del libro.
     * @param editore   L'editore del libro.
     * @param categoria La categoria o genere del libro.
     * @author UDDIN SHAKIM AHMED
     */
    public Libro(String titolo, String autori, String anno, String editore, String categoria) {
        this.titolo = titolo;
        this.autori = autori;
        this.anno = anno;
        this.editore = editore;
        this.categoria = categoria;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Libro.
     *
     * @return Una stringa che contiene il titolo, gli autori, l'anno di pubblicazione, l'editore e la categoria del libro,
     * separati da punti e virgola.
     * @author UDDIN SHAKIM AHMED
     */
    @Override
    public String toString() {
        return titolo + ";" + autori + ";" + anno + ";" + editore + ";" + categoria;
    }
}
