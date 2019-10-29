package csvejemplo;


import com.csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CsvEjemplo {

    public static void main(String[] args) {
        try {
            CsvReader products = new CsvReader("./datos/productos.csv");
            products.setDelimiter('|');
            products.setRecordDelimiter('\n');
            products.readHeaders();
            while (products.readRecord()) {
                String proID = products.get("idpro"); //products.get(1)
                String proDescrip = products.get("descrip"); //products.get(2)
                String proPrecio = products.get("precio"); //products.get(3)
                System.out.println(proID + "|" + proDescrip + "|" + proPrecio);
            }
            products.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
