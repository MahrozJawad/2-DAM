package swingdirchooserapplication;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class SwingDirChooserApplication {

    public static void main(String[] args) {
        System.out.println("App User Directory :"
                + System.getProperty("user.dir"));
        System.out.println("Home User Directory :"
                + System.getProperty("user.home"));
        System.out.println("Home Directory :"
                + FileSystemView.getFileSystemView().getHomeDirectory());
        System.out.println("Default Directory :"
                + FileSystemView.getFileSystemView().getDefaultDirectory());
        System.out.println("Actual Directory :"
                + (new File(".").getAbsolutePath()));
        JFileChooser jfc = new JFileChooser(new File(".").getAbsolutePath());
        jfc.setDialogTitle("Elegir una carpeta: ");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (jfc.getSelectedFile().isDirectory()) {
                System.out.println("Ha seleccionado la carpeta: "
                        + jfc.getSelectedFile());
            }
        }
    }
}
