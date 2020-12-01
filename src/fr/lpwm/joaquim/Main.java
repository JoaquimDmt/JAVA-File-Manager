package fr.lpwm.joaquim;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
// import java.nio.file.LinkOption;
// import java.nio.file.Path;
// import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Main
 * 
 * Interface de lecteur de fichiers Plusieurs sous-classes pour différents types
 * de fichiers (pour choix type html, txt) Classe abstraite pour définir
 * méthodes fixes Classe affiche fichier à l'envers (lignes) Classe affiche
 * fichier de manière palindromique ??? Comparateur de fichiers "diff" ??
 * 
 * 
 * si le dossier fichiers existe pas le créé demander nom fichier fichier vide
 * ecrire dedant fichier non vide demander si on veut ecraser ou ecrire après
 * 
 * 
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

        Fichier.create(filesContainer, file);

        menu(filesContainer, file);
    }

    public static void menu(File filesContainer, String file) throws IOException {
        System.out.println("\n\033[1;35mQue souhaitez vous faire avec \033[0;33m" + file + "\033[1;35m ?\033[0m");
        System.out
            .println(
                "1. Ecrire dans ce fichier" +
                "\n2. Lire ce fichier" +
                "\n3. Lire ce fichier à l'envers" +
                "\n4. Comparer à un autre fichier" +
                "\n5. Informations sur le fichier" +
                "\n6. Liste des fichiers" +
                "\n7. Changer de fichier" +
                "\n8. Créer un nouveau fichier" +
                "\n9. Quitter\033[0;94m"
            );
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
                // Fichier.readReverse(file);
                break;
            case 4:
                System.out.println("Ecrire le nom du fichier avec lequel vous souhaitez comparer\033[0;33m "+file+" \033[0m:\033[0;94m");
                String fileName2 = input.next();
                File file2 = new File(filesContainer, fileName2);
                if (!file2.exists()) {
                    System.out.println("\033[0;31mAucun fichier n'existe sous ce nom, vous pouvez vérifier la liste des fichiers à l'aide de l'\033[4;31moption 6 du menu\033[0m");
                    break;
                }
                // Fichier.compare(file, fileName2);
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
                System.exit(0); //un simple return n'est pas bon car menus imbriqués
            default:
                break;
        }
        menu(filesContainer, file);
    }

	// private static void getFileExtension(File file) {
	// }
}
