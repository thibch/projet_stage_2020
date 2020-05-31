package fr.projetstage.models.monde.salle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.projetstage.dataFactories.TextureFactory;
import fr.projetstage.models.Entite;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.entites.ennemis.*;
import fr.projetstage.models.entites.objets.Coffre;
import fr.projetstage.models.entites.objets.ObjetsTousTypes;
import fr.projetstage.models.entites.objets.objetsAuSol.Consommable;
import fr.projetstage.models.entites.objets.objetsAuSol.PackDeFleches;
import fr.projetstage.models.entites.objets.objetsAuSol.PotionVieRouge;
import fr.projetstage.models.entites.objets.objetsCoffre.*;
import fr.projetstage.models.monde.GameWorld;
import fr.projetstage.models.monde.TypeSalle;
import fr.projetstage.models.monde.salle.solEtMurs.*;
import fr.projetstage.models.monde.salle.solEtMurs.meubles.Obstacle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class Salle {

    protected final int largeur;
    protected final int hauteur;
    protected final int idEtage;

    protected ArrayList<Entite> tileMap;
    protected ArrayList<Prop> props;
    protected ArrayList<Entite> meubles;

    protected ArrayList<Porte> portes;

    protected int nbPieges = 0;
    protected int nbObjetAuSols = 0;

    protected HashMap<Integer, Ennemi> pieges;
    protected HashMap<Integer, Ennemi> ennemis;
    protected HashMap<Integer, Ennemi> invocationWaitList;
    protected int nbEnnemis;
    protected HashMap<Integer, ObjetsTousTypes> objets;

    protected EtatSalle etat;
    protected final GameWorld world;

    /**
     * Salle généré avec un body Static et des portes pour sortir
     * Les salles sont forcement rectangulaire
     * @param world le monde dans lequel la salle est générée
     * @param idEtage
     * @param largeur largeur en nombre de case de la salle
     * @param hauteur hauteur en nombre de case de la salle
     */
    public Salle(GameWorld world, int idEtage, int largeur, int hauteur){
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.world = world;
        this.idEtage = idEtage;

        tileMap = new ArrayList<>();
        props = new ArrayList<>();
        meubles = new ArrayList<>();
        portes = new ArrayList<>();
        pieges = new HashMap<>();
        ennemis = new HashMap<>();
        invocationWaitList = new HashMap<>();
        objets = new HashMap<>();
        etat = EtatSalle.NON_VISITE;
        nbEnnemis = 0;
    }

    public void ajouterMeuble(Obstacle obstacle){
        meubles.add(obstacle);
    }

    public void ajouterPièges(float x, float y){
        pieges.put(nbPieges, new Piege(world, new Vector2(x, y), new Type(TypeEntite.PIEGE, nbPieges)));
        nbPieges++;
    }

    public void ajouterNouvelEnnemi(float x, float y){
        ennemis.put(nbEnnemis, getRandomEnnemi(x, y));
        nbEnnemis++;
    }

    private Ennemi getRandomEnnemi(float x, float y){
        int rand = Math.abs(world.getNextRandom()%100);
        if(idEtage == 0){
            if(rand <= 33){
                return new ChauveSouris(world, new Vector2(x, y), new Type(TypeEntite.ENNEMI, nbEnnemis));
            }else if(rand <= 66){
                return new Goblin(world, new Vector2(x, y), new Type(TypeEntite.ENNEMI, nbEnnemis));
            }else{
                return new Slime(world, new Vector2(x, y), new Type(TypeEntite.ENNEMI, nbEnnemis));
            }
        }else if (idEtage == 1){
            if(rand <= 33){
                return new OrcGuerrier(world, new Vector2(x, y), new Type(TypeEntite.ENNEMI, nbEnnemis));
            }else if(rand <= 66){
                return new OrcMasque(world, new Vector2(x, y), new Type(TypeEntite.ENNEMI, nbEnnemis));
            }else{
                return new Slime(world, new Vector2(x, y), new Type(TypeEntite.ENNEMI, nbEnnemis));
            }
        }else{
            if(rand <= 33){
                return new Chort(world, new Vector2(x, y), new Type(TypeEntite.ENNEMI, nbEnnemis));
            }else if(rand <= 66){
                return new Wogol(world, new Vector2(x, y), new Type(TypeEntite.ENNEMI, nbEnnemis));
            }else{
                return new Slime(world, new Vector2(x, y), new Type(TypeEntite.ENNEMI, nbEnnemis));
            }

        }
    }

    public void ajouterNouveauConsommable(float x, float y){
        objets.put(nbObjetAuSols, getRandomConsommable(x, y));
        nbObjetAuSols++;
    }

    private Consommable getRandomConsommable(float x, float y){
        int rand = Math.abs(world.getNextRandom()%100);
        if(rand <= 20){
            return new PackDeFleches(world, new Vector2(x, y), nbObjetAuSols);
        }else if(rand <= 40){
            return new PackDeFleches(world, new Vector2(x, y), nbObjetAuSols);
        }else if (rand <= 60){
            return new PotionVieRouge(world, new Vector2(x, y), nbObjetAuSols);
        }else{
            return new PotionVieRouge(world, new Vector2(x, y), nbObjetAuSols);
        }
    }

    public Coffre ajouterNouveauCoffre(float x, float y){
        Coffre coffre = getRandomCoffre(x, y);
        objets.put(nbObjetAuSols, coffre);
        nbObjetAuSols++;
        return coffre;
    }

    private Coffre getRandomCoffre(float x, float y){
        int rand = Math.abs(world.getNextRandom()%100);
        if(rand <= 20){
            return new Coffre(world, new Vector2(x,y), new Coeur(world), nbObjetAuSols);
        }else if(rand <= 40){
            return new Coffre(world, new Vector2(x,y), new Crane(world), nbObjetAuSols);
        }else if (rand <= 60){
            return new Coffre(world, new Vector2(x,y), new PotionAttaque(world), nbObjetAuSols);
        }else if(rand <= 80) {
            return new Coffre(world, new Vector2(x,y), new Sunglasses(world), nbObjetAuSols);
        }else{
            return new Coffre(world, new Vector2(x,y), new PotionVitesse(world), nbObjetAuSols);
        }
    }

    public void ajouterPorte(Orientation orientationPorte){
        switch (orientationPorte){
            case BAS:
                portes.add(new Porte(world, new Vector2((float)(largeur/2)-1, -2), 2, 2, Orientation.BAS));
                break;
            case HAUT:
                portes.add(new Porte(world, new Vector2((float)(largeur/2)-1, hauteur), 2, 2, Orientation.HAUT));
                break;
            case GAUCHE:
                portes.add(new Porte(world, new Vector2(-2, (float)(hauteur/2)-1), 2, 2, Orientation.GAUCHE));
                break;
            case DROITE:
                portes.add(new Porte(world, new Vector2(largeur, (float)(hauteur/2)-1), 2, 2, Orientation.DROITE));
                break;
        }
    }

    public void ajouterTorchesAuxPortes(Orientation orientationTorches) {
        switch (orientationTorches){
            case BAS:
                props.add(0, new Torche(new Vector2((float)(largeur/2)-2, -1), Orientation.BAS));
                props.add(0, new Torche(new Vector2((float)(largeur/2)+1, -1), Orientation.BAS));
                break;
            case HAUT:
                props.add(0, new Torche(new Vector2((float)(largeur/2)-2, hauteur), Orientation.HAUT));
                props.add(0, new Torche(new Vector2((float)(largeur/2)+1, hauteur), Orientation.HAUT));
                break;
            case GAUCHE:
                props.add(0, new Torche(new Vector2(-1, (float)(hauteur/2)-2), Orientation.GAUCHE));
                props.add(0, new Torche(new Vector2(-1, (float)(hauteur/2)+1), Orientation.GAUCHE));
                break;
            case DROITE:
                props.add(0, new Torche(new Vector2(largeur, (float)(hauteur/2)-2), Orientation.DROITE));
                props.add(0, new Torche(new Vector2(largeur, (float)(hauteur/2)+1), Orientation.DROITE));
                break;
        }
    }

    public void update(){
        boolean isVictorious;

        if(invocationWaitList.size() > 0){
            ennemis.putAll(invocationWaitList);
            invocationWaitList.clear();
        }
        Iterator<Integer> it;
        int courant;
        it = ennemis.keySet().iterator();
        while(it.hasNext()){
            courant = it.next();

            //update des ennemis
            ennemis.get(courant).update();

            if(ennemis.get(courant).estMort()){
                world.getWorld().destroyBody(ennemis.get(courant).getBody());
                it.remove();
                ennemis.remove(courant);
            }
        }

        it = pieges.keySet().iterator();
        while(it.hasNext()){
            courant = it.next();

            //update des pièges
            pieges.get(courant).update();
        }


        isVictorious = ennemis.size() <= 0;

        it = objets.keySet().iterator();
        while(it.hasNext()){
            courant = it.next();

            objets.get(courant).update();

            if(objets.get(courant).estDetruit()){
                world.getWorld().destroyBody(objets.get(courant).getBody());
                it.remove();
                objets.remove(courant);
            }
        }

        for(Prop prop : props){
            prop.update();
        }

        if(isVictorious){
            for(Porte porte : portes){
                porte.ouverturePorte();
            }
        }
    }

    public void draw(SpriteBatch listeAffImg, float x, float y) {

        Texture tmpWallCorner = TextureFactory.getInstance().getMurAngle();
        Texture tmpWallBorder = TextureFactory.getInstance().getBordureMur();
        Texture tmpWallBorderCorner = TextureFactory.getInstance().getBordureMurAngle();

        // dessine mur et sol
        for(Entite tile : tileMap){
            tile.draw(listeAffImg, x, y);
        }
        // objets sur les murs
        for(Prop prop : props){
            prop.draw(listeAffImg, x, y);
        }

        for(Entite meuble : meubles){
            meuble.draw(listeAffImg, x, y);
        }

        for(Entite piege : pieges.values()){
            piege.draw(listeAffImg, x, y);
        }

        for(Entite monstre : ennemis.values()){
            monstre.draw(listeAffImg, x, y);
        }

        for(Entite obj : objets.values()){
            obj.draw(listeAffImg, x, y);
        }

        for(int i = 0; i < hauteur; i++){
            // genere le mur de gauche (sur x == 1)
            listeAffImg.draw(tmpWallBorder, x-1, y + i, 0, 0, 1, 1, 1, 1, 90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);

            // genere le mur de droite (sur x == largeur - 2)
            listeAffImg.draw(tmpWallBorder,x + largeur+1, y + (1 + i), 0, 0, 1, 1, 1, 1, -90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        }

        for (int i = 0; i < largeur; i++) {
            // genere le mur du haut (sur y == hauteur-2)
            listeAffImg.draw(tmpWallBorder, x + i,y + hauteur+1, 1, 1);

            // genere le mur du bas (sur y == 1)
            listeAffImg.draw(tmpWallBorder, x + (1+i), y - 1,0,0, 1, 1,1,1,180, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        }

        for(Entite porte : portes){
            porte.draw(listeAffImg, x, y);
        }

        // coin haut gauche
        listeAffImg.draw(tmpWallBorderCorner, x - 2, y + (hauteur+1), 1, 1);
        listeAffImg.draw(tmpWallBorder, x - 1, y + hauteur, 0, 0, 1, 1, 1, 1, 90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, x - 1, y + (hauteur+1), 1, 1);
        listeAffImg.draw(tmpWallCorner, x - 1, y + hauteur, 1, 1);

        // coin haut droite
        listeAffImg.draw(tmpWallBorderCorner, x + (largeur+1), y + hauteur+2, 0, 0, 1, 1, 1, 1, -90, 0, 0, tmpWallBorderCorner.getWidth(), tmpWallBorderCorner.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, x + (largeur+1), y + (hauteur+1), 0, 0, 1, 1, 1, 1, -90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, x + largeur, y + (hauteur+1), 1, 1);
        listeAffImg.draw(tmpWallCorner, x + largeur, y + (hauteur+1), 0, 0, 1, 1, 1, 1, -90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);

        // coin bas droite
        listeAffImg.draw(tmpWallBorderCorner, x + largeur+2, y-1, 0, 0, 1, 1, 1, 1, 180, 0, 0, tmpWallBorderCorner.getWidth(), tmpWallBorderCorner.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder,x +  (largeur+1), y, 0, 0, 1, 1, 1, 1, -90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder,x +  (largeur+1), y-1, 0, 0, 1, 1, 1, 1, 180, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallCorner,x +  (largeur+1), y, 0, 0, 1, 1, 1, 1, 180, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);

        // coin bas gauche
        listeAffImg.draw(tmpWallBorderCorner, x-1, y-2, 0, 0, 1, 1, 1, 1, 90, 0, 0, tmpWallBorderCorner.getWidth(), tmpWallBorderCorner.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, x-1, y-1, 0, 0, 1, 1, 1, 1, 90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallBorder, x, y-1, 0, 0, 1, 1, 1, 1, 180, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);
        listeAffImg.draw(tmpWallCorner, x, y-1, 0, 0, 1, 1, 1, 1, 90, 0, 0, tmpWallBorder.getWidth(), tmpWallBorder.getHeight(), false, false);

    }

    public void generateBodies(){
        for(Entite tile : tileMap){
            tile.generateBody();
        }
        // objets sur les murs
        for(Prop prop : props){
            prop.generateBody();
        }

        for(Entite meuble : meubles){
            meuble.generateBody();
        }

        for(Entite piege : pieges.values()){
            piege.generateBody();
        }

        for(Entite monstre : ennemis.values()){
            monstre.generateBody();
        }

        for(Entite obj : objets.values()){
            obj.generateBody();
        }

        for (Entite porte : portes) {
            porte.generateBody();
        }
    }

    public void destroyBodies() {

        for (Entite tile : tileMap) {
            tile.destroyBody();
        }
        // objets sur les murs
        for (Prop prop : props) {
            prop.destroyBody();
        }

        for (Entite meuble : meubles) {
            meuble.destroyBody();
        }

        for(Entite piege : pieges.values()){
            piege.destroyBody();
        }

        for (Entite monstre : ennemis.values()) {
            monstre.destroyBody();
        }

        for (Entite obj : objets.values()) {
            obj.destroyBody();
        }

        for (Entite porte : portes) {
            porte.destroyBody();
        }
    }

    public abstract void genererSalle();

    protected void genererSolsEtMurs(){

        ArrayList<Orientation> mursARajouter = new ArrayList<>();
        mursARajouter.add(Orientation.HAUT);
        mursARajouter.add(Orientation.BAS);
        mursARajouter.add(Orientation.GAUCHE);
        mursARajouter.add(Orientation.DROITE);

        for (Porte porte : portes){
            mursARajouter.remove(porte.getOrientation());
        }

        // Mur Gauche et Droite
        for(int y = 0; y < hauteur/2 - 1;y++){
            tileMap.add(new Mur(world, new Vector2(-1, y), Orientation.GAUCHE,getRandomWall()));
            tileMap.add(new Mur(world, new Vector2(largeur, y), Orientation.DROITE,getRandomWall()));
        }

        if(mursARajouter.contains(Orientation.GAUCHE)){
            tileMap.add(new Mur(world, new Vector2(-1, (float)(hauteur/2) - 1), Orientation.GAUCHE,getRandomWall()));
            tileMap.add(new Mur(world, new Vector2(-1, (float)(hauteur/2)), Orientation.GAUCHE,getRandomWall()));
        }

        if(mursARajouter.contains(Orientation.DROITE)){
            tileMap.add(new Mur(world, new Vector2(largeur, (float)(hauteur/2) - 1), Orientation.DROITE,getRandomWall()));
            tileMap.add(new Mur(world, new Vector2(largeur, (float)(hauteur/2)), Orientation.DROITE,getRandomWall()));
        }

        for(int y = hauteur/2 + 1; y < hauteur;y++){
            tileMap.add(new Mur(world, new Vector2(-1, y), Orientation.GAUCHE,getRandomWall()));
            tileMap.add(new Mur(world, new Vector2(largeur, y), Orientation.DROITE,getRandomWall()));
        }

        // Mur Haut et Bas
        for (int x = 0; x < largeur/2-1; x++) {
            tileMap.add(new Mur(world, new Vector2(x, hauteur), Orientation.HAUT, getRandomWall()));
            tileMap.add(new Mur(world, new Vector2(x, -1), Orientation.BAS, getRandomWall()));
        }

        if(mursARajouter.contains(Orientation.HAUT)){
            tileMap.add(new Mur(world, new Vector2((float)(largeur/2)-1, hauteur), Orientation.HAUT, getRandomWall()));
            tileMap.add(new Mur(world, new Vector2((float)(largeur/2), hauteur), Orientation.HAUT, getRandomWall()));
        }

        if(mursARajouter.contains(Orientation.BAS)){
            tileMap.add(new Mur(world, new Vector2((float)(largeur/2)-1, -1), Orientation.BAS, getRandomWall()));
            tileMap.add(new Mur(world, new Vector2((float)(largeur/2), -1), Orientation.BAS, getRandomWall()));
        }

        for (int x = largeur/2+1; x < largeur; x++) {
            tileMap.add(new Mur(world, new Vector2(x, hauteur), Orientation.HAUT, getRandomWall()));
            tileMap.add(new Mur(world, new Vector2(x, -1), Orientation.BAS, getRandomWall()));
        }



        // parcours tout les murs et ajoute aléatoirement des props si le mur est de type 1
        Mur tmp;
        TypeProp tmpAlea;
        for(Entite mur : tileMap){
            tmp = (Mur) mur;
            if(tmp.getNumMur() == TypeMur.MUR1){
                tmpAlea = getRandomProps();
                if(tmpAlea != null){
                    props.add(new Prop(tmp.getPosition(),tmp.getOrientation(),tmpAlea));
                }
            }
        }


        // le sol
        for(int x = 0; x < largeur; x++){
            for(int y = 0; y < hauteur; y++){
                tileMap.add(new Sol(new Vector2(x,y),getRandomGround()));
            }
        }

    }

    public int getLargeur() {
        return largeur;
    }
    public int getHauteur() {
        return hauteur;
    }

    /**
     * renvoie un numero de mur aleatoire basé avec des probabilités
     * @return un entier entre 1 et 4
     */
    public TypeMur getRandomWall(){
        int rand = Math.abs(world.getNextRandom()%100);
        TypeMur tmp;
        if(rand <= 70){
            tmp = TypeMur.MUR1;
        }
        else if(rand <= 90){
            tmp = TypeMur.MUR2;
        }
        else if(rand <= 94){
            tmp = TypeMur.MUR3;
        }
        else{
            tmp = TypeMur.MUR4;
        }
        return tmp;
    }

    /**
     * renvoie un numero de props aleatoire basé avec des probabilités
     * @return un entier entre 0 et 3
     */
    public TypeProp getRandomProps(){
        int rand = Math.abs(world.getNextRandom()%100);
        TypeProp tmp;
        if(rand <= 85){
            tmp = null;
        }
        else if(rand <= 90){
            tmp = TypeProp.PROP_DRAPEAU_ROUGE;
        }
        else if(rand <= 95){
            tmp = TypeProp.PROP_DRAPEAU_VERT;
        }
        else{
            tmp = TypeProp.PROP_PRISONNIER;
        }
        return tmp;
    }

    /**
     * renvoie un numero de sol aleatoire basé avec des probabilités
     * @return un entier entre 1 et 10
     */
    public TypeSol getRandomGround(){
        int rand = Math.abs(world.getNextRandom()%100);
        TypeSol tmp;
        if(rand <= 55){
            tmp = TypeSol.SOL2;
        }
        else if(rand <= 60){
            tmp = TypeSol.SOL1;
        }
        else if(rand <= 65){
            tmp = TypeSol.SOL3;
        }
        else if(rand <= 70){
            tmp = TypeSol.SOL4;
        }
        else if(rand <= 75){
            tmp = TypeSol.SOL5;
        }
        else if(rand <= 80){
            tmp = TypeSol.SOL6;
        }
        else if(rand <= 85){
            tmp = TypeSol.SOL7;
        }
        else if(rand <= 90){
            tmp = TypeSol.SOL8;
        }
        else if(rand <= 95){
            tmp = TypeSol.SOL9;
        }
        else{
            tmp = TypeSol.SOL10;
        }
        return tmp;
    }

    public Ennemi getEnnemi(int id){
        return ennemis.get(id);
    }

    public void setEnnemiTouche(int id, Entite source) {
        ennemis.get(id).setTouche(source);
    }

    public void setPickUpTaken(int id) {
        ObjetsTousTypes obj = objets.get(id);
        if(obj != null){
            obj.setTouche(true);
        }
    }

    /**
     * Methode permettant de récupérer l'état d'une salle
     * @return un Etat de salle
     */
    public EtatSalle getEtat() {
        return etat;
    }

    public abstract TypeSalle getType();

    /**
     * Methode permettant de définir l'état d'une salle
     * @param etat le nouvel état de la salle
     */
    public void setEtat(EtatSalle etat){
        this.etat = etat;
    }

    /**
     * Methode permettant d'ajouter des monstres sur le tas
     * @param ennemi le monstre à ajouter
     */
    public void ajouterMonstre(Ennemi ennemi){
        invocationWaitList.put(nbEnnemis, ennemi);
        invocationWaitList.get(nbEnnemis).generateBody();
        nbEnnemis++;
    }

    /**
     * Methode permettant de savoir ou en est le compteur
     * @return un entier du nombre d'ennemis.
     */
    public int getNbEnnemis(){
        return nbEnnemis;
    }

    public Ennemi getPiege(int id) {
        return pieges.get(id);
    }

    @Override
    public String toString() {
        return "Salle{" +
                "largeur=" + largeur +
                ", hauteur=" + hauteur +
                ", tileMap=" + tileMap +
                ", props=" + props +
                ", meubles=" + meubles +
                ", portes=" + portes +
                ", nbPieges=" + nbPieges +
                ", nbObjetAuSols=" + nbObjetAuSols +
                ", pieges=" + pieges +
                ", ennemis=" + ennemis +
                ", invocationWaitList=" + invocationWaitList +
                ", nbEnnemis=" + nbEnnemis +
                ", objets=" + objets +
                ", etat=" + etat +
                '}';
    }
}
