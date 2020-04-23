package fr.projetstage.models.entites.ennemis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Comportement implements Steerable<Vector2> {

    private Body body;
    private boolean tagged;
    private float boundingRadius;
    private float maxLinearSpeed, maxLinearAcceleration;
    private float maxAngularSpeed, maxAngularAcceleration;

    private SteeringBehavior<Vector2> behavior;
    private SteeringAcceleration<Vector2> steeringOutput;

    /**
     * @param body Le body concerné par le comportement
     * @param boundingRadius La distance entre le centre du body et du body "poursuivit"
     */
    public Comportement(Body body, float boundingRadius){
        this.body = body;
        this.boundingRadius = boundingRadius;

        this.maxLinearSpeed = 100;
        this.maxLinearAcceleration = 500;
        this.maxAngularSpeed = 30;
        this.maxAngularAcceleration = 5;

        this.tagged = false;

        this.steeringOutput = new SteeringAcceleration<>(new Vector2());
    }

    /**
     * Met à jour les forces à appliquer pour suivre le point
     */
    public void update(){
        if(behavior != null){
            behavior.calculateSteering(steeringOutput);
            applySteering(Gdx.graphics.getDeltaTime());
        }
    }

    private void applySteering(float deltaTime) {
        boolean anyAccelerations = false;

        if(!steeringOutput.linear.isZero()){
            Vector2 force = steeringOutput.linear.scl(deltaTime);
            body.applyForceToCenter(force, true);
            anyAccelerations = true;
        }

        // Si l'hitbox doit tourner
        /*if(steeringOutput.angular != 0){
            body.applyTorque(steeringOutput.angular * deltaTime, true);
            anyAccelerations = true;
        }*/

        if(anyAccelerations){
            // Linear Capping
            Vector2 velocity = body .getLinearVelocity();
            float currentSpeedSquare = velocity.len2();
            if(currentSpeedSquare > maxLinearSpeed * maxLinearSpeed){
                body.setLinearVelocity(velocity.scl(maxLinearSpeed / (float) Math.sqrt(currentSpeedSquare)));
            }

            // Angular capping
            if(body.getAngularVelocity() > maxAngularSpeed){
                body.setAngularVelocity(maxAngularSpeed);
            }
        }
    }


    @Override
    public Vector2 getLinearVelocity() {
        if(body != null) return body.getLinearVelocity();
        return new Vector2();
    }

    @Override
    public float getAngularVelocity() {
        return body.getAngularVelocity();
    }

    @Override
    public float getBoundingRadius() {
        return boundingRadius;
    }

    @Override
    public boolean isTagged() {
        return tagged;
    }

    @Override
    public void setTagged(boolean tagged) {
        this.tagged = tagged;
    }

    @Override
    public float getZeroLinearSpeedThreshold() {
        return 0;
    }

    @Override
    public void setZeroLinearSpeedThreshold(float value) {

    }

    @Override
    public float getMaxLinearSpeed() {
        return maxLinearSpeed;
    }

    @Override
    public void setMaxLinearSpeed(float maxLinearSpeed) {
        this.maxLinearSpeed = maxLinearSpeed;
    }

    @Override
    public float getMaxLinearAcceleration() {
        return maxLinearAcceleration;
    }

    @Override
    public void setMaxLinearAcceleration(float maxLinearAcceleration) {
        this.maxLinearAcceleration = maxLinearAcceleration;
    }

    @Override
    public float getMaxAngularSpeed() {
        return maxAngularSpeed;
    }

    @Override
    public void setMaxAngularSpeed(float maxAngularSpeed) {
        this.maxAngularSpeed = maxAngularSpeed;
    }

    @Override
    public float getMaxAngularAcceleration() {
        return maxAngularAcceleration;
    }

    @Override
    public void setMaxAngularAcceleration(float maxAngularAcceleration) {
        this.maxAngularAcceleration = maxAngularAcceleration;
    }

    @Override
    public Vector2 getPosition() {
        if(body != null) return body.getPosition();
        return new Vector2();
    }

    @Override
    public float getOrientation() {
        return body.getAngle();
    }

    @Override
    public void setOrientation(float orientation) {

    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return (float) Math.atan2(-vector.x, vector.y);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        outVector.x = -(float) Math.sin(angle);
        outVector.y = (float) Math.cos(angle);
        return outVector;
    }

    @Override
    public Location<Vector2> newLocation() {
        return null;
    }

    /**
     * Getter sur le body qui est effecté par le comportement
     * @return le body qui est effecté par le comportement
     */
    public Body getBody(){
        return body;
    }

    /**
     * Définit le comportement
     * @param behavior classe qui contient le comporement
     */
    public void setBehavior(SteeringBehavior<Vector2> behavior){
        this.behavior = behavior;

    }

    /**
     * Getter sur le comportement
     * @return le comportement
     */
    public SteeringBehavior<Vector2> getBehavior(){
       return behavior;
    }
}
