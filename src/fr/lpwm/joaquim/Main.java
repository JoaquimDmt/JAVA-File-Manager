package fr.lpwm.joaquim;

import java.io.File;
import java.io.FileNotFoundException;
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
        // Fichier.directoryFiles(filesContainer);
        menu(filesContainer, file);
    }

    public static void menu(File filesContainer, String file) {
        System.out.println("\n\033[1;35mQue souhaitez vous faire avec " + file + " ?\033[0m");
        System.out
                .println("1. Lire ce fichier" + "\n2. Lire ce fichier à l'envers" + "\n3. Lire le palindrome du fichier"
                        + "\n4. Comparer à un autre fichier" + "\n5. Changer de fichier" + "\n6. Quitter");
        int choix = input.nextInt();
        switch (choix) {
            case 1:
                // Fichier.read(file);
                break;
            case 2:
                // Fichier.readReverse(file);
                break;
            case 3:
                // Fichier.readPalindrome(file);
                break;
            case 4:
                // Fichier.compare(file);
                break;
            case 5:
                // Fichier.write(file);
                break;
            case 6:
                try {
                    Fichier.directoryFiles(filesContainer);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        
            default:
                break;
        }
    }

	// private static void getFileExtension(File file) {
	// }
}