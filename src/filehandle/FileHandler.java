package filehandle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RAM;


public class FileHandler {
    /**
     * DuyDo
     * Handling file class separate from RAMManagement for further development (database, text)
     */

    private String pathFile;

    public FileHandler(String pathFile) {
        this.pathFile = pathFile;
    }

    public void saveToFile(List<RAM> ramList) {
        try {
            File fo = new File(pathFile);
            
            FileOutputStream fos = new FileOutputStream(fo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            for (RAM r:ramList)
            oos.writeObject(r);

            oos.close();
            fos.close();
            System.out.println("Save at: "+fo.getPath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, "File not found " );
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, "Error saving to file");
        }
    }

    public List<RAM> loadFromFile() {
        ArrayList <RAM> ramL=new ArrayList();
        File file = new File(pathFile);
        if (!file.exists()) {
            System.out.println("FIle not found.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            RAM r;
            while((r=(RAM) ois.readObject())!=null)
                ramL.add(r);
            
            return ramL;
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data " );
            return null;
        }
    }
}
