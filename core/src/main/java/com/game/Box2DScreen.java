package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DScreen extends BaseScreen {

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private Body dino, floor, pinchoBody;
    private Fixture dinoFixture, floorFixture, pinchoFixture;
    private boolean debeSaltar, isJumping, dinoAlive = true;

    public Box2DScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -10), true);
        renderer = new Box2DDebugRenderer();
        //Camara
        camera = new OrthographicCamera(32, 18);
        //para mover la camara 1 metro
        camera.translate(0,3);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                if(fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("floor") ||
                        fixtureA.getUserData().equals("floor") && fixtureB.getUserData().equals("player") ){
                    if(Gdx.input.isTouched()){
                        debeSaltar = true;
                    }
                    isJumping = false;
                }



                if(fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("spike") ||
                        fixtureA.getUserData().equals("spike") && fixtureB.getUserData().equals("player") ){
                    dinoAlive = false;
                }
            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                if(fixtureA == dinoFixture && fixtureB == floorFixture){
                    isJumping = true;
                }

                if(fixtureB == dinoFixture && fixtureA == floorFixture){
                    isJumping = true;
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                ;
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
                ;
            }
        });

        dino = world.createBody(createDinoDef());
        floor = world.createBody(createFloorDef());
        pinchoBody = world.createBody(createPinchoBodyDef(5));



        //Creamos el actorPricipal..................................................................
        PolygonShape dinoShape = new PolygonShape();
        dinoShape.setAsBox(0.5f, 0.5f);
        dinoFixture = dino.createFixture(dinoShape, 1);
        dinoShape.dispose();


        //Creamos el suelo..........................................................................
        PolygonShape floorShape = new PolygonShape();
        floorShape.setAsBox(500, 1);
        floorFixture = floor.createFixture(floorShape, 1);
        floorShape.dispose();

        //Creamos el pincho
        pinchoFixture = createPinchoFixture(pinchoBody);

        dinoFixture.setUserData("player");
        floorFixture.setUserData("floor");
        pinchoFixture.setUserData("spike");

    }

    private BodyDef createPinchoBodyDef(float x) {
        BodyDef def = new BodyDef();
        def.position.set(x, 0.5f);
        def.type = BodyDef.BodyType.StaticBody;
        return def;
    }

    private BodyDef createFloorDef() {
        BodyDef def = new BodyDef();
        def.position.set(0, -1);
        def.type = BodyDef.BodyType.StaticBody;
        return def;
    }

    private BodyDef createDinoDef() {
        BodyDef def = new BodyDef();
        def.position.set(-5, 0);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    private Fixture createPinchoFixture(Body pinchoBody){
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f, -0.5f);
        vertices[1] = new Vector2(0.5f, -0.5f);
        vertices[2] = new Vector2(0, 0.5f);

        PolygonShape shape = new PolygonShape();
        shape.set(vertices);

        Fixture fix = pinchoBody.createFixture(shape, 1);
        shape.dispose();
        return fix;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(debeSaltar){
            debeSaltar = false;
            saltar();
        }

        if(Gdx.input.justTouched() && !isJumping){
           debeSaltar = true;
        }

        if(dinoAlive){
            float velocidadY = dino.getLinearVelocity().y;
            dino.setLinearVelocity(8, velocidadY);
        }

        //Segun la documentacion 6 y 2
        world.step(delta, 6, 2);
        camera.update();
        renderer.render(world, camera.combined);
    }

    @Override
    public void dispose() {

        world.destroyBody(dino);
        world.destroyBody(floor);
        world.destroyBody(pinchoBody);

        dino.destroyFixture(dinoFixture);
        floor.destroyFixture(floorFixture);
        pinchoBody.destroyFixture(pinchoFixture);

        renderer.dispose();
        world.dispose();

    }

    private void saltar(){
        Vector2 position = dino.getPosition();
        dino.applyLinearImpulse(0, 5, position.x, position.y, true);
    }



}
