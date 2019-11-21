package csvejemplo;

import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvEjemplo {

    public static void main(String[] args) {
        String outputFile = "./datos/users.csv";
// Antes de abrir el fichero comprobamos si existe
        boolean alreadyExists = new File(outputFile).exists();
        try {
            CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), '|');
            csvOutput.setDelimiter('|'); // No sería necesario porque ya se le ha indicado
            csvOutput.setRecordDelimiter('\n'); // Es el valor por defecto
// Si ya existe el fichero no se necesita escrbir las cabeceras
            if (!alreadyExists) {
                csvOutput.write("id");
                csvOutput.write("name");
                csvOutput.endRecord();
            }
// ELSE asume que como ya existe tiene las cabeceras
// Escribe unos registros
            csvOutput.write("1");
            csvOutput.write("Juan");
            csvOutput.endRecord();
            csvOutput.write("2");
            csvOutput.write("Pedro");
            csvOutput.endRecord();
            csvOutput.write("3");
            csvOutput.write("Inés");
            csvOutput.endRecord();
            csvOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
