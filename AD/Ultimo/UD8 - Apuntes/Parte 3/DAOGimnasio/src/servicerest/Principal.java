
package servicerest;

import clases.Clientes;
import com.google.gson.Gson;
import dao.DAOMongoGimnasio;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class Principal {


    public static void main(String[] args) throws JAXBException {
        // Probar la visualización de ArrayList con getALL
        System.out.println();
        System.out.println("GETALL");
        System.out.println("======");
        System.out.println(DAOMongoGimnasio.clientesGetAll());
        
        
        // Probar PUT
        System.out.println();
        System.out.println("PUT");
        System.out.println("===");
        
        Clientes cli;
        
        cli = DAOMongoGimnasio.clientesGet("21222333");
        System.out.println(cli.toString());
        
        if (cli.getTelefono().equals("666111000")) {
            cli.setTelefono("666777888");
        } else {
            cli.setTelefono("666111000");
        }
       
        DAOMongoGimnasio.clientesPutTelefono("21222333",cli);
        
        cli = DAOMongoGimnasio.clientesGet("21222333");
        System.out.println(cli.toString());
        

        // Probar la visualización JSON
        System.out.println();
        System.out.println("JSON");
        System.out.println("====");
        
        System.out.println(new Gson().toJson(cli));
        
        // Probar la visualización JAXB
        System.out.println();
        System.out.println("XML");
        System.out.println("===");
        
        JAXBContext context = JAXBContext.newInstance(Clientes.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(cli, System.out);
        
    }
    
}
