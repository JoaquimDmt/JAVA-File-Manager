package fr.lpwm.joaquim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * LecteurReverse
 * 
 * Affiche fichier à l'envers sur l'écran (en terme de lignes)
 * Interface implémentée sur les fichiers texte pouvants être affichés à l'envers
 */

public interface LecteurReverse extends LecteurFichier{

    public static void readReverse(File filesContainer, String file) throws FileNotFoundException, UnsupportedEncodingException {
        
        File fichier = new File(filesContainer, file);

        if (fichier.length() != 0) {
            FileInputStream fileInputStream = new FileInputStream(fichier);
        
            //specify encoding explicitly
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            try {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                ArrayList<String> lineslist = new ArrayList<>();
                
                while ((line = bufferedReader.readLine()) != null){
                    lineslist.add(line);
                }
                bufferedReader.close();

                Collections.reverse(lineslist);

                for (int i = 0; i < lineslist.size(); i++) {
                    System.out.println(lineslist.get(i));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("\033[41mFichier vide\033[0m");
        }
    }
}

