package fr.projetstage.models.monde.salle.patternSalle.fichiers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ChercheurFichiers{

    private final ArrayList<FileHandle> listeFichiers;

    /**
     * Constructeur de recherche de fichier dans le dossier donné
     */
    public ChercheurFichiers(){
        listeFichiers = new ArrayList<>();
    }

    /**
     * Cherche les fichiers dans le dossier donné
     * @param dossier dossier où se trouve les fichiers
     */
    public void trouverFichiersDansDossier(String dossier){
        FileHandle repertoire = Gdx.files.local("/android/assets/" + dossier);
        if(repertoire.isDirectory()){
            //On récupère le contenu du répertoire
            FileHandle[] contenuRep = repertoire.list();
            if(contenuRep != null){
                for(FileHandle fichier : contenuRep){ //Pour tout les fichiers contenu dans le répertoire si c'est pas un répertoire alors on l'ajoute à la liste
                    if(!fichier.isDirectory()){
                        listeFichiers.add(fichier);
                    }
                }
            }
        }
    }

    public FileHandle get(int id) {
        return listeFichiers.get(id%listeFichiers.size());
    }
}
