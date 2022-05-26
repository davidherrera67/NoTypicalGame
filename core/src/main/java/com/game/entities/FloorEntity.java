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

    private Texture floor;

    private World world;

    private Body body, leftBody;

    private Fixture fixture, leftFixture;

    public FloorEntity(Texture floor, World world,  float x, float y, float width) {
        this.world = world;
        this.floor = floor;

        // Create the floor body.
        BodyDef def = new BodyDef();
        def.position.set(x + width / 2, y - 0.5f);
        body = world.createBody(def);

        // Give it a box shape.
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, 0.5f);
        fixture = body.createFixture(box, 1);
        fixture.setUserData("floor");
        box.dispose();


        BodyDef leftDef = new BodyDef();
        leftDef.position.set(x, y - 0.55f);
        leftBody = world.createBody(leftDef);

        // As well as the fixture. Remember, use spike user data to make it act like an enemy.
        PolygonShape leftBox = new PolygonShape();
        leftBox.setAsBox(0.5f, 0.45f);
        leftFixture = leftBody.createFixture(leftBox, 1);
        leftFixture.setUserData("spike");
        leftBox.dispose();

        // Now place the actor in the stage by converting the coordinates given in meters to px.
        setSize(width * Constants.PIXELS_IN_METERS, Constants.PIXELS_IN_METERS);
        setPosition(x * Constants.PIXELS_IN_METERS, (y - 0.5f) * Constants.PIXELS_IN_METERS);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(floor, getX(), getY(), getWidth(), getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}