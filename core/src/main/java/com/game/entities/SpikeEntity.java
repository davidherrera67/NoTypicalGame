package com.game.entities;

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

public class SpikeEntity extends Actor {

    private Texture texture;

    private World world;

    private Body body;

    private Fixture fixture;

    public SpikeEntity(Texture texture, World world, float x, float y) {
        this.texture = texture;
        this.world = world;

/**
 *
 * Creamos y posicionamos en el espacio la entidad del actor principal.
 * */

        BodyDef def = new BodyDef();
        def.position.set(x, y + 0.5f);
        body = world.createBody(def);

/**
 *
 * Definimos la forma y el tamaño que va a tener nuestro suelo (En este caso al ser un objeto
 * triangular necesitamos crear un vector para poder dar forma al objeto).
 * */

        PolygonShape box = new PolygonShape();
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f, -0.5f);
        vertices[1] = new Vector2(0.5f, -0.5f);
        vertices[2] = new Vector2(0, 0.5f);
        box.set(vertices);

        fixture = body.createFixture(box, 1);
        fixture.setUserData("spike");
        box.dispose();

        setSize(Constants.PIXELS_IN_METERS, Constants.PIXELS_IN_METERS);
        setPosition(x * Constants.PIXELS_IN_METERS, (y - 0.5f) * Constants.PIXELS_IN_METERS);
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
     * Siempre debemos destruir las fixtures y los bodies con los que trabajemos para liberar espacio en
     * al memoria de la tarjeta de video.
     */
    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
