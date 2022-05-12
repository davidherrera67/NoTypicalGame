package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DScreen extends BaseScreen {
    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private Body dino, floor;
    private Fixture dinoFixture, floorFixture;

    public Box2DScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -10), true);
        renderer = new Box2DDebugRenderer();
        //camara
        camera = new OrthographicCamera(16, 9);
        //para mover la camara 1 metro
        camera.translate(0,3);

        dino = world.createBody(createDinoDef());
        floor = world.createBody(createFloorDef());
        //dino
        PolygonShape dinoShape = new PolygonShape();
        dinoShape.setAsBox(0.5f, 0.5f);
        dinoFixture = dino.createFixture(dinoShape, 1);
        dinoShape.dispose();
        //suelo
        PolygonShape floorShape = new PolygonShape();
        floorShape.setAsBox(500, 1);
        floorFixture = floor.createFixture(floorShape, 1);
        floorShape.dispose();
    }

    private BodyDef createFloorDef() {
        BodyDef def = new BodyDef();
        def.position.set(0, -1);
        def.type = BodyDef.BodyType.StaticBody;
        return def;
    }

    private BodyDef createDinoDef() {
        BodyDef def = new BodyDef();
        def.position.set(0, 10);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Segun la documentacion 6 y 2
        world.step(delta, 6, 2);
        camera.update();
        renderer.render(world, camera.combined);
    }

    @Override
    public void dispose() {
        world.dispose();
        world.destroyBody(dino);
        renderer.dispose();
        dino.destroyFixture(dinoFixture);
        floor.destroyFixture(floorFixture);
    }
}
