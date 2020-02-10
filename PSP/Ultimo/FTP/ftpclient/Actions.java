package ftpclient;

import Utils.Getter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.net.ftp.FTPFile;

public class Actions
{

    public static void show(String parent, int depth) throws IOException
    {
        FTPFile[] subFiles = Client.client.listFiles(parent);

        for (FTPFile fileName : subFiles)
        {
            for (int i = 0; i < depth; i++)
                System.out.print("\t");

            System.out.println(fileName.getName() + "\t\t\t\t\t\t" + parent + "/" + fileName.getName());
            if (fileName.isDirectory())
                show(parent + "/" + fileName.getName(), depth + 1);
        }
    }

    public static void rename(String name) throws IOException
    {
        int aux = name.lastIndexOf("/");

        String fileName;
        String parent;

        if (aux > 0)
        {
            parent = name.substring(0, aux);
            fileName = name.substring(aux);

        } else
        {
            fileName = name;
            parent = "";
        }

        Client.client.rename(name, parent + "/" + Getter.GetString("Change " + fileName + " to:"));
    }

    public static void download(String toSaveFile, String savePath) throws IOException
    {
        int aux = toSaveFile.lastIndexOf("/");
        if (aux > 0)
            Files.createDirectories(Paths.get(savePath + toSaveFile.substring(0, aux)));
        else
            Files.createDirectories(Paths.get(savePath + toSaveFile));

        File downloadFile = new File(savePath + toSaveFile);

        boolean success;
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile)))
        {
            success = Client.client.retrieveFile(toSaveFile, outputStream);
        }

        if (success)
            System.out.println("File has been downloaded successfully.");
    }

    public static void upload(String localFile, String saveLocation) throws IOException
    {
        boolean success;
        try (FileInputStream inputStream = new FileInputStream(localFile))
        {
            success = Client.client.storeFile(saveLocation + "/" + Paths.get(localFile).getFileName(), inputStream);
        }
        if (success)
            System.out.println("File has been uploaded successfully.");
        else
            System.out.println("Something went wrong.");
    }

    public static void delete(String deleteFile) throws IOException
    {
        Client.client.deleteFile(deleteFile);
    }

}
