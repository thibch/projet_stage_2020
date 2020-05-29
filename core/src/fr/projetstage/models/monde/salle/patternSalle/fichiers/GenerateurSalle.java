package fr.projetstage.models.monde.salle.patternSalle.fichiers;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.TypeSalle;
import fr.projetstage.models.monde.salle.Salle;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.Biblio;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.GrandeTable;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.PetiteTable;


public class GenerateurSalle {

    private boolean waitForT = false;
    private int x = 0;
    private int y = 0;
    private final GameWorld world;
    private final Lecteur lecteur;
    private final ChercheurFichiers chercheurSansCoffre;
    private final ChercheurFichiers chercheurAvecCoffre;

    public GenerateurSalle(GameWorld world){
        this.world = world;
        lecteur = new Lecteur(this);
        chercheurAvecCoffre = new ChercheurFichiers();
        chercheurAvecCoffre.trouverFichiersDansDossier("paternesDeSalles/avecCoffre");
        chercheurSansCoffre = new ChercheurFichiers();
        chercheurSansCoffre.trouverFichiersDansDossier("paternesDeSalles/sansCoffre");
    }

    public void carac(Salle salle, Character character){
        switch (character){
            case 'V':
            case 'S':
                if(waitForT){
                    salle.ajouterMeuble(new GrandeTable(world, new Vector2(x, y)));
                    waitForT = false;
                }
                break;
            case '\n':
                x = -1;
                y++;
                break;
            case 'B':
                salle.ajouterMeuble(new Biblio(world, new Vector2(x, y)));
                break;
            case 'T':
                if(waitForT){
                    salle.ajouterMeuble(new GrandeTable(world, new Vector2(x, y)));
                    waitForT = false;
                }else{
                    salle.ajouterMeuble(new PetiteTable(world, new Vector2(x, y)));
                }
                break;
            case 'G':
                waitForT = true;
                break;
            case 'E':
                salle.ajouterNouvelEnnemi(x, y);
                break;
            case 'O':
                salle.ajouterNouveauConsommable(x, y);
                break;
            case 'P':
                salle.ajouterPièges(x, y);
                break;
            case 'C':
                salle.ajouterNouveauCoffre(x, y);
                break;
            default:
                x--;
                break;
        }
        x++;
        if(waitForT){
            waitForT = false;
        }
    }

    public Salle genererSalle(boolean salleAvecCoffre){
        FileHandle fichier;
        if(salleAvecCoffre){
            fichier = chercheurAvecCoffre.get(world.getNextRandom());
            return new Salle(world, 16, 10) {
                @Override
                public void genererSalle() {
                    x = 0;
                    y = 0;
                    lecteur.explorerFichier(this, fichier);
                    this.genererSolsEtMurs();
                }

                @Override
                public TypeSalle getType() {
                    return TypeSalle.SALLE_COFFRE;
                }
            };
        }else{
            fichier = chercheurSansCoffre.get(world.getNextRandom());
            return new Salle(world, 16, 10) {
                @Override
                public void genererSalle() {
                    x = 0;
                    y = 0;
                    lecteur.explorerFichier(this, fichier);
                    this.genererSolsEtMurs();
                }

                @Override
                public TypeSalle getType() {
                    return TypeSalle.NO_TYPE;
                }
            };
        }


    }

}
