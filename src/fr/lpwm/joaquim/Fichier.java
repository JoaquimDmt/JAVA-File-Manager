package fr.lpwm.joaquim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Fichier
 * 
 * test d'existence créer et/ou écrire dedant fichier vide ecrire dedant fichier
 * non vide demander si on veut ecraser ou ecrire après
 * 
 * ouvrir et lire contenu (lecteurfichier) distinction fichier/répertoire
 * copie/effacement (exeptions à gérer) fermer fichiers ouverts ?
 */

public abstract class Fichier implements LecteurFichier {

    static Scanner input = new Scanner(System.in);

    public static void create(File filesContainer, String filename) throws IOException {

        File fichier = new File(filesContainer, filename);
        try {

            if (!fichier.exists()) {

                System.out.println("\033[0m\nCréation du nouveau fichier...");
                fichier.createNewFile();
                System.out.println("\033[1;37mNouveau fichier \"\033[1;33m" + fichier.getName()
                        + "\033[1;37m\" stocké dans : \033[0;32m" + fichier.getPath());

            } else {
                System.out.println("\033[0m\nCe fichier existe déjà.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // indiquer l'emplacement du fichier créé ou déjà existant

            System.out.println("\033[4;37mChemin absolu:\033[0m " + fichier.getAbsolutePath() + "\n");
            // getAbsoluteBath similaire à
            // Path path = Paths.get("fichiers/fichier1.txt");
            // System.out.println(path.toRealPath(LinkOption.NOFOLLOW_LINKS));
        }
    }

    public static void write(File filesContainer, String file) throws IOException {
        FileInputStream fileInputStream = null;
        File fichier = new File(filesContainer, file);
        boolean writing = true;

        try {
            fileInputStream = new FileInputStream(fichier);
            FileWriter fileWriter = new FileWriter(fichier);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            System.out.println("\033[1;36mVous pouvez commencer à écrire dans \033[0;33m"+file+"\033[1;36m :\033[0m");
            while (writing == true) {
                String text = input.next();
                bw.write(text);
                System.out.println("\033[1;36mNouvelle ligne ?\033[0m");
                // boolean newLine = input.nextBoolean();
                // if(newLine == true) {
                //     writing = true;
                //     bw.newLine();
                // } else if (newLine == false){
                //     writing = false;
                // }
                writing=false;
            }
            bw.write("Salut mec!");
            bw.newLine();
            bw.write("Cool.");
            bw.close();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }

    public static void read(File filesContainer, String file) throws FileNotFoundException, UnsupportedEncodingException {
        
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
                    System.out.println(line);
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
    
}