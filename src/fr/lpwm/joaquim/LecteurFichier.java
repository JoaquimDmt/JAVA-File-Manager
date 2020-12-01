package fr.lpwm.joaquim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Interface LecteurFichier
 * 
 * Défini les méthodes d'affichage et comparaison
 * Implémentée dans les types de fichiers pouvant être affichés (texte mais pas html par exemple)
 */

public interface LecteurFichier {

    // public void compare(String file1, String file2) throws IOException;

    public static void read(File filesContainer, String file) throws FileNotFoundException, UnsupportedEncodingException {
        
        File fichier = new File(filesContainer, file);

        if (fichier.length() != 0) {
            FileInputStream fileInputStream = new FileInputStream(fichier);
        
            //specify encoding explicitly
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            try {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                
                while ((line = bufferedReader.readLine()) != null){
                    System.out.println(line);
                }
                bufferedReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("\033[41mFichier vide\033[0m");
        }
    }
    
}
