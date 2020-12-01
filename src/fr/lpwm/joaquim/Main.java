package fr.lpwm.joaquim;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

/**
 * @author Joaquim Dimitrov
 * 
 * Main
 * 
 * Demande le nom d'un fichier et propose toutes les options en lien avec la gestion de ce fichier. Appuyez sur les touches correspondantes au menu pour lancer les fonctions souhaitées :
 * 
 * 1. Ecrire dans ce fichier
 * 2. Lire ce fichier
 * 3. Lire ce fichier à l'envers
 * 4. Comparer à un autre fichier
 * 5. Informations sur le fichier
 * 6. Liste des fichiers
 * 7. Changer de fichier
 * 8. Crer un nouveau fichier
 * 9. Supprimer le fichier
 * 0. Quitter
 */

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        File filesContainer = new File("fichiers/");

        // S'il n'existe pas, créé le dossier permettant de stocker tous les fichiers
        // que nous allons générer ("fichiers" de base)
        if (!filesContainer.exists()) {
            filesContainer.mkdir();
        }

        System.out.println("\nEcrire le nom de fichier avec l'extension :\033[0;94m");
        String file = input.next();

        String extension = getFileExtension(file);


        if (!extension.equalsIgnoreCase("html")) {

            //Programme Principal
            Fichier.create(filesContainer, file);
            menu(filesContainer, file);

        } else {

            //Programme secondaire pour fichiers type HTML
            File htmlFilesContainer = new File("fichiers/html/");
            if (!htmlFilesContainer.exists()) {
                htmlFilesContainer.mkdir();
            }
            Fichier.create(htmlFilesContainer, file);
            menuHtml(htmlFilesContainer, file);
        }

    }

    /**
     * Fonction menu()
     * 
     * Affiche les différentes options et lance les programmes correspondants...
     */
    
    public static void menu(File filesContainer, String file) throws IOException {
        System.out.println("\n\033[1;35mQue souhaitez vous faire avec \033[0;33m" + file + "\033[1;35m ?\033[0m");
        System.out.println(
            "1. Ecrire dans ce fichier" +
            "\n2. Lire ce fichier" +
            "\n3. Lire ce fichier à l'envers" +
            "\n4. Comparer à un autre fichier" +
            "\n5. Informations sur le fichier" +
            "\n6. Liste des fichiers" +
            "\n7. Changer de fichier" +
            "\n8. Créer un nouveau fichier" +
            "\n9. \033[0;31mSupprimer le fichier\033[0m" +
            "\n0. Quitter\033[0;94m"
        );
        try {
            int choix = input.nextInt();
            System.out.println("\033[0m");

            switch (choix) {
                case 1:
                    Fichier.write(filesContainer, file);
                    break;
                case 2:
                    Fichier.read(filesContainer, file);
                    break;
                case 3:
                    Fichier.readReverse(filesContainer, file);
                    break;
                case 4:
                    System.out.println("Ecrire le nom du fichier avec lequel vous souhaitez comparer\033[0;33m "+file+" \033[0m:\033[0;94m");
                    String fileName2 = input.next();
                    File file2 = new File(filesContainer, fileName2);
                    if (!file2.exists()) {
                        System.out.println("\033[0;31mAucun fichier n'existe sous ce nom, vous pouvez vérifier la liste des fichiers à l'aide de l'\033[4;31moption 6 du menu\033[0m");
                        break;
                    }
                    Fichier.compare(filesContainer, file, fileName2);
                    break;
                case 5:
                    Fichier.getInfo(filesContainer, file);
                    break;
                case 6:
                    try {
                        Fichier.directoryFiles(filesContainer);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    //Accéder au menu d'un autre fichier existant
                    System.out.println("Ecrire le nom de fichier avec l'extension :\033[0;94m");
                    String otherfile = input.next();
                    File fichier = new File(filesContainer, otherfile);
                    if (fichier.exists()) {
                        menu(filesContainer, otherfile);
                    }
                    else {
                        System.out.println("\033[0;31mAucun fichier n'existe sous ce nom, vous pouvez vérifier la liste des fichiers à l'aide de l'\033[4;31moption 6 du menu\033[0m");
                    }
                    break;
                case 8:
                    System.out.println("Ecrire le nom du nouveau fichier avec l'extension :\033[0;94m");
                    String newfile = input.next();
                    Fichier.create(filesContainer, newfile);
                    break;
                case 9:
                    Fichier.delete(filesContainer, file);
                    break;
                case 0:
                    System.exit(0); //un simple return n'est pas bon car menus imbriqués
                default:
                    break;
            }

        } catch (InputMismatchException e) {
            System.out.println("\n\033[0mVous deviez rentrer une valeur numérique !");
            e.printStackTrace();
            System.exit(1); //error force program kill
        }

        menu(filesContainer, file);
    }

    /**
     * Feature menuHtml() -> programme secondaire
     * 
     * cf. FichierHtml.java
     */

    public static void menuHtml(File filesContainer, String file) throws IOException {
        System.out.println("\n\033[1;35mQue souhaitez vous faire avec \033[0;33m" + file + "\033[1;35m ?\033[0m");
        System.out.println(
            "1. Ecrire dans ce fichier [SOON]" +
            "\n2. Ouvrir ce fichier dans le navigateur [SOON]" +
            "\n3. Informations sur le fichier" +
            "\n4. Liste des fichiers de type HTML" +
            "\n5. \033[0;31mSupprimer le fichier\033[0m" +
            "\n6. Quitter\033[0;94m"
        );
        try {
            int choix = input.nextInt();
            System.out.println("\033[0m");

            switch (choix) {
                case 1:
                    // Fichier.write(filesContainer, file);
                    break;
                case 2:
                    // Fichier.openInBrowser(filesContainer, file);
                    break;
                case 3:
                    Fichier.getInfo(filesContainer, file);
                    break;
                case 4:
                    try {
                        Fichier.directoryFiles(filesContainer);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    Fichier.delete(filesContainer, file);
                    break;
                case 6:
                    System.exit(0); //un simple return n'est pas bon car menus imbriqués
                    break;
                default:
                    break;
            }

        } catch (InputMismatchException e) {
            System.out.println("\n\033[0mVous deviez rentrer une valeur numérique !");
            e.printStackTrace();
            System.exit(1); //error force program kill
        }

        menuHtml(filesContainer, file);
    }


    /**
     * Fonction pour obtenir l'extension d'un fichier
     * 
     * @param file //file name as String
     */

	private static String getFileExtension(String file) {
        String extension = Optional
        .of(file)
        .filter(f -> f.contains("."))
        .map(f -> f.substring(file.lastIndexOf(".") + 1)).orElse("");
        return extension;
	}
}
