package com.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.game.Constants;

public class FloorEntity extends Actor {

    private Texture texture;

    private World world;

    private Body body;

    private Fixture fixture;

    public FloorEntity(Texture floor, World world, float x, float y, float width) {
        this.texture = floor;
        this.world = world;

        //cOLOCO EL SUELO EN LA POSICION QUE LE CORRESPONDE.
        BodyDef def = new BodyDef();
        def.position.set(x + width / 2, y -0.5f);
        body = world.createBody(def);

        //Le doy forma de caja.
        PolygonShape box = new PolygonShape();
        box.setAsBox(width , 0.5f);
        fixture = body.createFixture(box, 1);
        fixture.setUserData("floor");
        box.dispose();

        setSize(width * Constants.PIXELS_IN_METERS, Constants.PIXELS_IN_METERS);
        setPosition(x * Constants.PIXELS_IN_METERS, (y - 0.5f) * Constants.PIXELS_IN_METERS);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
