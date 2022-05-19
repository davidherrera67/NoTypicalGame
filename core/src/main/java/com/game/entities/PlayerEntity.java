package com.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.game.Constants;

public class PlayerEntity extends Actor {

    private Texture texture;

    private World world;

    private Body body;

    private Fixture fixture;

    private boolean alive = true;
    private boolean jumping = false;
    private boolean mustJump = false;

    public PlayerEntity(World world, Texture texture, Vector2 position) {
        this.world = world;
        this.texture = texture;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        box.setAsBox(0.5f, 0.5f);
        fixture = body.createFixture(box, 3);
        fixture.setUserData("player");
        box.dispose();

        setSize(Constants.PIXELS_IN_METERS, Constants.PIXELS_IN_METERS);
    }

//    Getters && Setters ***************************************************************************

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isMustJump() {
        return mustJump;
    }

    public void setMustJump(boolean mustJump) {
        this.mustJump = mustJump;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(body.getPosition().x * Constants.PIXELS_IN_METERS,
                body.getPosition().y * Constants.PIXELS_IN_METERS);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        //Iniciar un salto si hemos tocado la pantalla.

        if (Gdx.input.justTouched()) {
            jump();
        }

        // Hacer avanzar al jugador si esta vivo.

        if(alive){
            float velocityY = body.getLinearVelocity().y;
            body.setLinearVelocity(8, velocityY);
        }
    }

    private void jump() {
        if (!jumping && alive) {
            jumping = true;

            Vector2 position = body.getPosition();
            body.applyLinearImpulse(0, 20, position.x, position.y, true);
        }
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
