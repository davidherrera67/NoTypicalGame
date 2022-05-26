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

    private final Texture texture;

    private final World world;

    private final Body body;

    private final Fixture fixture;

    private boolean alive = true;

    private boolean jumping = false;

    private boolean mustJump = false;

    public PlayerEntity(World world, Texture texture, Vector2 position) {
        this.world = world;
        this.texture = texture;

        /**
         * Creamos y posicionamos en el espacio la entidad del actor principal.
         */

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);

        /**
         *
         * Definimos la forma y el tamaño que va a tener nuestro actor principal.
         */

        PolygonShape box = new PolygonShape();
        box.setAsBox(0.5f, 0.5f);
        fixture = body.createFixture(box, 3);
        fixture.setUserData("player");
        box.dispose();

        setSize(Constants.PIXELS_IN_METERS, Constants.PIXELS_IN_METERS);
    }

    /**
     * Getters && Setters ******************************************************************************
     */
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

    public void setMustJump(boolean mustJump) {
        this.mustJump = mustJump;
    }


    /**
     * Dibujamos el suelo en nuestra pantalla.
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(body.getPosition().x * Constants.PIXELS_IN_METERS,
                body.getPosition().y * Constants.PIXELS_IN_METERS);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }


    /**
     * Programamos las acciones que va a realizar nuestro actor principal como el movimiento horizontal o
     * el salto.
     */
    @Override
    public void act(float delta) {

        if (Gdx.input.justTouched() || Gdx.input.isTouched()) {
            jump();
        }
        // Hacer avanzar al jugador si esta vivo.

        if(mustJump){
            mustJump = false;
            jump();
        }

        if (alive) {
            float velocityY = body.getLinearVelocity().y;
            body.setLinearVelocity(Constants.PLAYER_SPEED, velocityY);
        }

        if (isJumping()) {
            body.applyForceToCenter(0, -Constants.IMPULSE_JUMP * 2.5f, true);
        }
    }

    public void jump() {
        if (!isJumping() && alive) {
            jumping = true;

            Vector2 position = body.getPosition();
            body.applyLinearImpulse(0, Constants.IMPULSE_JUMP, position.x, position.y, true);
        }
    }

    /**
     * Siempre debemos destruir las fixtures y los bodies con los que trabajemos para liberar espacio en
     * al memoria de la tarjeta de video.
     */
    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
