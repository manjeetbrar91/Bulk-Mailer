
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MANJEET BRAR
 */
public class TEst {

    public static void main(String[] args) {
        
        
        String p = "D:\\_Projects\\NodeJS\\efuel-latest\\assets";

        List<File> fileList  = new TEst().getFiles(new ArrayList(), new File(p));
         File array[]= new File[fileList .size()];
         for (int i = 0; i < fileList .size(); i++) {
            File f = fileList .get(i);
            array[i] = f;
        }
    }

    public List<File> getFiles(List<File> al, File baseFolder) {
        File[] listFiles = baseFolder.listFiles();
        if (listFiles.length == 0) {
            return al;
        }
        File lastModifiedFile = listFiles[0];

        for (int i = 0; i < listFiles.length; i++) {
            File file = listFiles[i];
            if (file.isFile()) {
                if (lastModifiedFile.lastModified() < file.lastModified()) {
                    lastModifiedFile = file;
                }
            } else {
                getFiles(al, file);
            }
        }
        al.add(lastModifiedFile);
        return al;
    }
}
