package fr.projetstage.models.monde.salle.patternSalle.fichiers;

import com.badlogic.gdx.files.FileHandle;
import fr.projetstage.models.monde.salle.Salle;

import java.io.*;

/**
 * Lecteur de fichier
 * @author Thibault Choné
 */
public class Lecteur{

    private GenerateurSalle generateurSalle;

    /**
     * Constructeur du lecteru de fichier
     */
    public Lecteur(GenerateurSalle generateurSalle){
        this.generateurSalle = generateurSalle;
    }

    /**
     * Explorartion du fichier donné
     * @param salle
     * @param fichier le nom du fichier
     */
    public void explorerFichier(Salle salle, FileHandle fichier){

        InputStream lecteurAvecBuffer;

        try{
            lecteurAvecBuffer = fichier.read();
            int elem;
            // On lit chaque ligne
            while ((elem = lecteurAvecBuffer.read()) != -1){
                generateurSalle.carac(salle, (char)(elem));
            }

            lecteurAvecBuffer.close();
        }catch(FileNotFoundException exc){
            System.out.println("Erreur d'ouverture");
        } catch (IOException e) {
            System.out.println("Erreur lecture");
        }
    }
}
