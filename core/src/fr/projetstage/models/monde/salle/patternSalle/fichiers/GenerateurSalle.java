package fr.projetstage.models.monde.salle.patternSalle.fichiers;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.Monde;
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
        chercheurAvecCoffre.trouverFichiersDansDossier("avecCoffre");
        chercheurSansCoffre = new ChercheurFichiers();
        chercheurSansCoffre.trouverFichiersDansDossier("sansCoffre");
    }

    public void carac(Salle salle, Character character){
        switch (character){
            case 'V':
            case 'S':
            case 'R':
            case 'r':
            case 'v':
            case 's':
                if(waitForT){
                    salle.ajouterMeuble(new GrandeTable(world, new Vector2(x-1, salle.getHauteur() - y-1)));
                    waitForT = false;
                }
                break;
            case 'B':
            case 'b':
                salle.ajouterMeuble(new Biblio(world, new Vector2(x, salle.getHauteur() - y-1)));
                waitForT = false;
                break;
            case 'T':
            case 't':
                if(waitForT){
                    salle.ajouterMeuble(new GrandeTable(world, new Vector2(x-1, salle.getHauteur() - y-1)));
                    waitForT = false;
                }else{
                    salle.ajouterMeuble(new PetiteTable(world, new Vector2(x, salle.getHauteur() - y-1)));
                }
                break;
            case 'G':
            case 'g':
                waitForT = true;
                break;
            case 'E':
            case 'e':
                salle.ajouterNouvelEnnemi(x, salle.getHauteur() - y-1);
                waitForT = false;
                break;
            case 'O':
            case 'o':
                salle.ajouterNouveauConsommable(x, salle.getHauteur() - y-1);
                waitForT = false;
                break;
            case 'P':
            case 'p':
                salle.ajouterPièges(x, salle.getHauteur() - y-1);
                waitForT = false;
                break;
            case 'C':
            case 'c':
                salle.ajouterNouveauCoffre(x,salle.getHauteur() - y-1);
                waitForT = false;
                break;
            case '\n':
                x = -1;
                y++;
                break;
            default:
                x--;
                break;
        }
        x++;
    }

    public Salle genererSalle(int idEtage, Monde monde, boolean salleAvecCoffre){
        FileHandle fichier;
        if(salleAvecCoffre){
            fichier = chercheurAvecCoffre.get(world.getNextRandom());
            return new Salle(world, monde,idEtage, 16, 10) {
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
            return new Salle(world, monde, idEtage, 16, 10) {
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
