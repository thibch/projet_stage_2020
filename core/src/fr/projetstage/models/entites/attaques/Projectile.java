package fr.projetstage.models.entites.attaques;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import fr.projetstage.models.entites.EntiteMouvante;
import fr.projetstage.models.Orientation;
import fr.projetstage.models.entites.Type;
import fr.projetstage.models.entites.TypeEntite;
import fr.projetstage.models.monde.GameWorld;

public abstract class Projectile extends EntiteMouvante {

    private double dureeVie; // A détermnier en fonction de la portée et vitesse d'attaque (Classe Attaque)
    protected Body body;
    protected boolean estLancee;

    public Projectile(GameWorld world, Vector2 position) {
        super(world, position);
    }

    public abstract void update(Vector2 position, Orientation direction);

    public void launch(Orientation direction, float speed, int id){
        generateBody();
        if(body != null){
            Vector2 vecteurDirection;

            switch (direction){
                case BAS:
                    vecteurDirection = new Vector2(0,-speed);
                    break;
                case DROITE:
                    vecteurDirection = new Vector2(speed,0);
                    break;
                case HAUT:
                    vecteurDirection = new Vector2(0,speed);
                    break;
                default:
                    vecteurDirection = new Vector2(-speed,0);
                    break;
            }
            estLancee = true;

            body.setLinearVelocity(vecteurDirection);
            body.setUserData(new Type(TypeEntite.DISTANCE, id));
        }
    }

    public void launch(Vector2 direction, float speed, int id){
        generateBody();
        if(body != null){
            estLancee = true;
            body.setLinearVelocity(new Vector2((float) ((direction.x/(Math.pow(direction.x,2)+Math.pow(direction.y,2)))*speed),(float) ((direction.y/(Math.pow(direction.x,2)+Math.pow(direction.y,2)))*speed)));
            body.setUserData(new Type(TypeEntite.DISTANCE_EN, id));
        }
    }

    /**
     * Getter si la flèche est lancée dans le monde ou non
     * @return vrai si la flèche est lancée
     */
    public boolean isLaunched(){
        return estLancee;
    }

    @Override
    public Vector2 getPosition(){
        if(body != null) return body.getPosition();
        return position;
    }

    @Override
    public void destroyBody(){
        world.getWorld().destroyBody(body);
    }

}
