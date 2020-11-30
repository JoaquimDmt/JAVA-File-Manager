package fr.lpwm.joaquim;

/**
 * LecteurReverse
 * 
 * affiche le fichier à l'envers sur l'écran : lignes /
 */

public interface LecteurReverse extends LecteurFichier{

    @Override
    void affiche(String fileName);
    
}

