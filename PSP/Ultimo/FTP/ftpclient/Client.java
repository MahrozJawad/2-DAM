package ftpclient;

import Utils.Getter;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import java.io.IOException;
import java.net.SocketException;

public class Client
{

    protected static FTPClient client = null;

    public static void main(String[] args) throws IOException
    {
        String serverURL = "localhost";
        String user = "anonymous";
        String password = "1234";

        try
        {
            int reply;
            client = new FTPClient();
            client.connect(serverURL);
            reply = client.getReplyCode();

            if (FTPReply.isPositiveCompletion(reply))
            {
                client.login(user, password);

                if (client.isConnected())
                    System.out.println("Connected");

                boolean exit = false;

                String[] menuSentences = new String[]
                {
                    "Show directory",
                    "Rename file/directory",
                    "Download file",
                    "Upload file",
                    "Delete file"
                };
                
                while (!exit)
                {
                    switch (Getter.GetMenu(menuSentences))
                    {
                        case 1:
                            Actions.show(Getter.GetString("Folder to show (leave empty to see all):"), 0);
                            break;
                        case 2:
                            Actions.rename(Getter.GetString("Folder/directory to rename:"));
                            break;
                        case 3:
                            Actions.download(Getter.GetString("File to download:"), Getter.GetString("Folder to be saved:"));
                            break;
                        case 4:
                            Actions.upload(Getter.GetString("File to save:"), Getter.GetString("Save location path:"));     // C:/Users/boqui/Desktop/Nueva carpeta (3)/Grid1/test.txt
                            break;
                        case 5:
                            Actions.delete(Getter.GetString("File to delete:"));
                            break;
                        case 0:
                            exit = true;
                            break;
                        default:
                            break;
                    }

                }
            }
        } catch (SocketException ex)
        {
            System.out.println(ex.toString());
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            if (client != null)
                client.disconnect();
        }
    }

}
