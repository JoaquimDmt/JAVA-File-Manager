package fr.lpwm.joaquim;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
// import java.nio.file.LinkOption;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.util.ArrayList;
import java.io.FileNotFoundException;
// import java.util.Collections;



/**
 * Fichier
 * 
 * test d'existence
 * créer et/ou écrire dedant
 * fichier vide ecrire dedant
 * fichier non vide demander si on veut ecraser ou ecrire après
 * 
 * ouvrir et lire contenu (lecteurfichier)
 * distinction fichier/répertoire
 * copie/effacement
 * (exeptions à gérer)
 * fermer fichiers ouverts ?
 */

public abstract class Fichier implements LecteurFichier {

    // File fichier = new File(filesContainer, "fichier1.txt");
    
    public static void create(File filesContainer, String filename) throws IOException {

        File fichier = new File(filesContainer, filename);
        try {

            if(!fichier.exists()){

                System.out.println("\033[0m\nCreation du nouveau fichier");
                fichier.createNewFile();
                System.out.println("\033[1;37mNouveau fichier \"\033[1;33m"+fichier.getName()+"\033[1;37m\" stocké dans : \033[0;32m"+fichier.getPath());

            } else {
                System.out.println("\033[0m\nCe fichier existe déjà ...");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // indiquer l'emplacement du fichier créé ou déjà existant
            
            System.out.println("\033[4;37mChemin absolu:\033[0m "+fichier.getAbsolutePath()+"\n");
            //getAbsoluteBath similaire à
            // Path path = Paths.get("fichiers/fichier1.txt");
            // System.out.println(path.toRealPath(LinkOption.NOFOLLOW_LINKS));
        }
    }

    /*
    * Fonction Liste Fichiers (et dossiers)
    */
    public static void directoryFiles(File filesContainer) throws FileNotFoundException {
        
        if (filesContainer.isDirectory() ) {
            File[] fichiers = filesContainer.listFiles();
            System.out.println("\033[4;36mListe des fichiers:\033[0m");
            
            //affichage liste des fichiers/dossiers (par défaut dans l'ordre alphabétique)
            for(File path : fichiers) {
                if (!path.isDirectory()) 
                    System.out.println("\033[0;33m"+path.getName()+"\033[0m");
                else
                    System.out.println("\033[0;32m"+path.getName()+"/\033[0m");
                    subDirectoryFiles(path.getName(), path);    
            }
        } else {
            System.out.println("\033[1;31mLe paramètre en entrée ne correspond pas à un dossier existant !\033[0m");
            throw new FileNotFoundException();
        }
    }

    //affiche la liste des fichiers/dossiers contenus dans les sous-dossiers du dossier "fichiers"
    public static void subDirectoryFiles(String folder, File fichier) {

        if (fichier.isDirectory() ) {
            
            File[] paths = fichier.listFiles();
            for(File path : paths) {
                if (path.isDirectory()) {
                    System.out.println("\033[0;32m"+folder+"/\033[0m");
                    subDirectoryFiles(path.getName(), path);      	
                }else{
                  System.out.println("\033[0;32m"+folder+"/\033[0;33m"+path.getName()+"\033[0m");
                }
            }
        }  
    }

   
//     if(!file.exists()){
//         try {
//             System.out.println("creation fichier");
//             file.createNewFile();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//         FileInputStream in = null;
//         try {
//             FileWriter writer = new FileWriter(file);
//             BufferedWriter bw = new BufferedWriter(writer);
//             in = new FileInputStream(f);
//             bw.write("Salut mec!");
//             bw.newLine();
//             bw.write("Cool.");
//             bw.close();
//             writer.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         } finally {
//             if (in != null){
//                 in.close();
//             }
//         }
//     } else {
//         System.out.println("fichier existe déjà");
//         getFileExtension(file);
//         FileInputStream fileInputStream = new FileInputStream(file);

//         //specify encoding explicitly
//         InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
//         try {
//             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//             String line;
//             ArrayList<String> lineslist = new ArrayList<>();
//             while ((line = bufferedReader.readLine()) != null){
//                 System.out.println(line);
//                 lineslist.add(line);
//             }
//             bufferedReader.close();
//             Collections.reverse(lineslist);

//             for (int i = 0; i < lineslist.size(); i++) {
//                 System.out.println(lineslist.get(i));
//             }
//             bufferedReader.close();

//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         finally {
//             // bufferedReader.close();
//         }
//     }
}