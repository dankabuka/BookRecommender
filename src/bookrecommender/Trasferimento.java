package bookrecommender;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
PROGETTO REALIZZATO DA:
UDDIN SHAKIM AHMED - Matricola: 751180 - Sede: VA
KABUKA DAN MUMANGA - Matricola: 757708 - Sede: VA
LANDINI MATTEO - Matricola: 753593 - Sede: VA
*/

/** 
 * La classe Trasferimento legge i dati da un file CSV contenente informazioni sui libri e li scrive
 * in un file di output formattato.
 * 
 * @author UDDIN SHAKIM AHMED
 */
public class Trasferimento {

    /**
     * Il percorso del file CSV di input contenente i dati sui libri.
     */
    private static final String INPUT_CSV_FILE = "C:\\Users\\Clarabella\\Documents\\BookRecommender\\data\\BooksDatasetClean.csv";
    
    /**
     * Il percorso del file di output in cui verranno scritti i dati formattati.
     */
    private static final String OUTPUT_FILE = "C:\\Users\\Clarabella\\Documents\\BookRecommender\\data\\Libri.dati";

    /**
     * Il metodo main legge i dati dal file CSV di input, li formatta e li scrive nel file di output.
     *
     * @param args Argomenti della riga di comando (non utilizzati).
     * @author UDDIN SHAKIM AHMED
     */
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_CSV_FILE));
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {

            String line;
            // Salta la riga di intestazione
            br.readLine();

            // Legge e processa ogni riga del file CSV
            while ((line = br.readLine()) != null) {
                // Suddivide la riga usando la virgola come delimitatore, tenendo conto delle virgolette
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (values.length < 8) {
                    // Salta le righe con colonne insufficienti
                    continue;
                }

                // Estrae i campi necessari
                String title = values[0].trim();
                String authors = values[1].trim();
                String description = values[2].trim();
                String category = values[3].trim();
                String publisher = values[4].trim();
                String price = values[5].trim();
                String publishMonth = values[6].trim();
                String publishYear = values[7].trim();

                // Format the output line
                String outputLine = String.join(",",
                        title,
                        authors,
                        description,
                        category,
                        publisher,
                        price,
                        publishMonth,
                        publishYear
                );

                // Scrive la riga formattata nel file di output
                bw.write(outputLine);
                bw.newLine(); // Scrive una nuova riga
            }

            // Mostra solo "I dati sono stati trasferiti su Libri.dati"
            System.out.println("I dati sono stati trasferiti su Libri.dati");
            
            //System.out.println("I dati sono stati trasferiti su " + OUTPUT_FILE); // Mostra "I dati sono stati trasferiti su + percorso\\Libri.dati"

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
