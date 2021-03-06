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
 * Méthode create() -> test existence + création fichier
 * Méthode write() -> écrire contenu texte dans fichier
 * Méthode getInfo() -> obtenir nom fichier, path, size, ...
 * Méthode directoryFiles() -> liste fichiers & dossiers
 * Méthode delete() -> suppression fichier
 */

public abstract class Fichier {

    static Scanner input = new Scanner(System.in);

    /**
     * Fonction Créer Fichier
     *
     */

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
            // Path path = Paths.get("fichiers/fichier1.txt");
            // System.out.println(path.toRealPath(LinkOption.NOFOLLOW_LINKS));
        }
    }


    /**
     * Fonction Ecrire dans Fichier
     *
     * Permet d'écrire dans un fichier texte (overriden in FichierHtml)
     */

    protected static void write(File filesContainer, String file) throws IOException {
        FileInputStream fileInputStream = null;
        File fichier = new File(filesContainer, file);
        boolean writing = true;

        if (fichier.length() != 0) {
            System.out.println("Ce fichier contient déjà quelque-chose, voulez-vous vraiment réécrire par dessus ? (O/N)");
            String overwrite = input.next();
            if (overwrite.equalsIgnoreCase("N")){
                return;
            }
        }

        try {
            fileInputStream = new FileInputStream(fichier);
            FileWriter fileWriter = new FileWriter(fichier);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            System.out.println("\033[1;36mVous pouvez commencer à écrire dans \033[0;33m"+file);
            while (writing == true) {
                System.out.println("\033[1;36mEntrez votre texte :\033[0m");
                String text = input.next();
                bw.write(text);

                System.out.println("\033[1;36mNouvelle ligne ?\033[0m");
                String newLine = input.next();

                if (newLine.equalsIgnoreCase("oui") || newLine.equalsIgnoreCase("yes") || newLine.equalsIgnoreCase("O") || newLine.equalsIgnoreCase("Y")) {
                    bw.newLine();//revient à la ligne dans le fichier
                } else if (newLine.equalsIgnoreCase("non") || newLine.equalsIgnoreCase("no") || newLine.equalsIgnoreCase("N")){
                    writing = false;
                } else {
                    while (!newLine.equalsIgnoreCase("oui") && !newLine.equalsIgnoreCase("yes") && !newLine.equalsIgnoreCase("O") && !newLine.equalsIgnoreCase("Y") && !newLine.equalsIgnoreCase("non") && !newLine.equalsIgnoreCase("no") && !newLine.equalsIgnoreCase("N")){
                        System.out.println("\033[0;36mVeuillez entrer une réponse correcte : (oui/non, yes/no, O/N, Y/N)\033[0m");
                        newLine = input.next();
                    }
                    try {
                        if (newLine.equalsIgnoreCase("oui") || newLine.equalsIgnoreCase("yes") || newLine.equalsIgnoreCase("O") || newLine.equalsIgnoreCase("Y")) {
                            bw.newLine();//revient à la ligne dans le fichier
                        } else if (newLine.equalsIgnoreCase("non") || newLine.equalsIgnoreCase("no") || newLine.equalsIgnoreCase("N")){
                            writing = false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
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

    /**
    * Fonction Lire contenu Fichier
    *
    * TODO: need to be removed -> only in sub classes implementing LecteurFichier
    */

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

    /**
    * Fonction Lire Fichier à l'envers (en terme de lignes)
    *
    * TODO: need to be removed -> only in sub classes implementing LecteurReverse
    */
    
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


    /**
     * Fonction Informations fichier
     * 
     * @throws FileNotFoundException
     *
     */
    public static void getInfo(File filesContainer, String fileName) throws FileNotFoundException {
        File fichier = new File(filesContainer, fileName);
        if (fichier.exists()) {
            System.out.println("\033[4;37mFile name:\033[0;33m " + fichier.getName());
            System.out.println("\033[4;37mPath:\033[0;32m " + fichier.getPath());
            System.out.println("\033[4;37mAbsolute path:\033[0;32m " + fichier.getAbsolutePath());
            System.out.println("\033[4;37mWriteable:\033[0m " + fichier.canWrite());
            System.out.println("\033[4;37mReadable:\033[0m " + fichier.canRead());
            System.out.println("\033[4;37mFile size in bytes:\033[0m " + fichier.length());
        } else {
            System.out.println("\033[0;31mThe file does not exist.\033[0m");
            throw new FileNotFoundException();
        }
    }


    /**
    * Fonction Listage Fichiers (et dossiers)
    *
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


    /**
    * Fonction Comparaison Fichier avec un autre
    *
    * TODO: need to be removed -> only in sub classes implementing LecteurReverse
    *
    * @param fileName1
    * @param fileName2
    */

    public static void compare(File filesContainer, String fileName1, String fileName2) throws IOException {
        File fichier1 = new File(filesContainer, fileName1);
        File fichier2 = new File(filesContainer, fileName2);
        
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filesContainer, fileName1)), "UTF-8"));
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filesContainer, fileName2)), "UTF-8"));
         
        String line1 = reader1.readLine();
        String line2 = reader2.readLine();

        boolean areEqual = true;
        int lineNum = 1;
         
        while (line1 != null || line2 != null) {

            if(line1 == null || line2 == null) {
                areEqual = false;
                break;
            } 
            else if(! line1.equalsIgnoreCase(line2)) {
                areEqual = false; 
                break;
            }
             
            line1 = reader1.readLine(); 
            line2 = reader2.readLine();
            lineNum++;
        }
         
        if(areEqual) {
            System.out.println("\033[0mLes deux fichiers ont le même contenu.");
            
            if (fichier1.length() == 0 || fichier2.length() == 0){
                System.out.println("Ils sont tous les deux vides.");
            }
        }
        else {
            System.out.println("\033[0mLes deux fichiers n'ont pas le même contenu. Ils diffèrent à la ligne "+lineNum+".");
            System.out.println("\033[0;33m"+fileName1+"\033[0m contient \""+line1+"\" tandis que \033[0;33m"+fileName2+"\033[0m contient \""+line2+"\" à la ligne "+lineNum);

            if (fichier1.length() == fichier2.length()){
                System.out.println("\033[0;33m"+fileName1+"\033[0m pèse le même poids que \033[0;33m"+fileName2+"\033[0m : "+fichier1.length()+" bytes");

            } else if (fichier1.length() > fichier2.length()){
                System.out.println("\033[0;33m"+fileName1+"\033[0m contient plus de texte que \033[0;33m"+fileName2);

            } else if (fichier1.length() < fichier2.length()){
                System.out.println("\033[0;33m"+fileName1+"\033[0m contient moins de texte que \033[0;33m"+fileName2);

            } else {
                throw new IOException();
            }
        }
         
        reader1.close();
        reader2.close();
    }


    /**
    * Fonction Suppression Fichier
    *
    */

    public static void delete(File filesContainer, String file) {
        File fichier = new File(filesContainer, file);

        System.out.println("\033[0;91mEtes-vous bien sûr de vouloir supprimer le fichier \033[0;33m"+ fichier.getName()+"\033[0;91m ? (O/N)\033[0;94m");

        String confirmation = input.next();

        if (confirmation.equalsIgnoreCase("N")){
            System.out.println("\033[0mLe fichier n'a pas été supprimé, retour au menu.");
            return;
        }
        
        if (fichier.delete()) { 
            System.out.println("\033[0;31mFichier \033[0;33m" + fichier.getName()+"\033[0;31m supprimé ! Fin du programme.\033[0m");
            System.exit(0);
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
}