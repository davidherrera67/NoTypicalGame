package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.game.entities.FloorEntity;
import com.game.entities.PlayerEntity;
import com.game.entities.SpikeEntity;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends BaseScreen {

    private Stage stage;

    private World world;

    private PlayerEntity player;

    private List <FloorEntity> floorList = new ArrayList<>();

    private List <SpikeEntity> spikeList = new ArrayList<>();


    public GameScreen(Game game) {
        super(game);
        //TODO. Cambiar la resolucion
        stage = new Stage(new FitViewport(2560, 1440));
        world = new World(new Vector2(0, -10), true);

        world.setContactListener(new ContactListener() {

            private boolean areCollided(Contact contact, Object userA, Object userB){
                Object userDataA = contact.getFixtureA().getUserData();
                Object userDataB = contact.getFixtureB().getUserData();

                if(userDataA == null || userDataB == null){
                    return false;
                }

                return (userDataA.equals(userA) && userDataB.equals(userB) ||
                        userDataA.equals(userB) && userDataB.equals(userA));
            }

            @Override
            public void beginContact(Contact contact) {
                if(areCollided(contact, "player", "floor")){
                    player.setJumping(false);
                }

                if(areCollided(contact, "player","spike")){
                    if(player.isAlive()){
                        player.setAlive(false);
                    }
                }
            }

            @Override
            public void endContact(Contact contact) {
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    @Override
    public void show() {
        Texture playerTexture = game.getManager().get("Dino.png");
        Texture spikeTexture = game.getManager().get("Spike.png");
        Texture floorTexture = game.getManager().get("floor.jpg");
        player = new PlayerEntity(world, playerTexture, new Vector2(1, 2));

        floorList.add(new FloorEntity(floorTexture, world , 1, 1, 1000));
        spikeList.add(new SpikeEntity(spikeTexture, world,  6, 1));

        stage.addActor(player);
        for(FloorEntity floor : floorList){
            stage.addActor(floor);
        }

        for(SpikeEntity spike : spikeList){
            stage.addActor(spike);
        }
    }

    @Override
    public void hide() {
        player.detach();
        player.remove();

        for(FloorEntity floor : floorList){
            floor.detach();
            floor.remove();
        }

        for(SpikeEntity spike : spikeList){
            spike.detach();
            spike.remove();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        world.step(delta, 6, 2);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }
}
