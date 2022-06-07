package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.game.Constants;
import com.game.Game;
import com.game.entities.FloorEntity;
import com.game.entities.PlayerEntity;
import com.game.entities.SpikeEntity;

import java.util.ArrayList;
import java.util.List;

public class Level1Screen extends BaseScreen {

    private final Stage stage;

    private final World world;

    private PlayerEntity player;

    private List<FloorEntity> floorList = new ArrayList<>();

    private List<SpikeEntity> spikeList = new ArrayList<>();

    private Sound jumpSound, dieSound, winSound;

    private Music bgMusic;

    private Vector3 position;

    private SpriteBatch batch;

    private Texture bg, finishLine;

    private OrthographicCamera camera;

    private ExtendViewport viewport;

    public Level1Screen(Game game) {
        super(game);

        batch = new SpriteBatch();
        stage = new Stage(new FitViewport(Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight()));

        world = new World(new Vector2(0, -10), true);

        position = new Vector3(stage.getCamera().position);

        bg = game.getManager().get("images/bg4K.jpg");
        finishLine = game.getManager().get("images/finishLine.png");
        jumpSound = game.getManager().get("music/jump.mp3");
        dieSound = game.getManager().get("music/die.mp3");
        bgMusic = game.getManager().get("music/song.ogg");
        winSound = game.getManager().get("music/bites-ta-da-winner.mp3");

        world.setContactListener(new ContactListener() {
            /**
             *
             * Comprueba que dos objetos han colisionado.
             * */
            private boolean areCollided(Contact contact, Object userA, Object userB) {
                Object userDataA = contact.getFixtureA().getUserData();
                Object userDataB = contact.getFixtureB().getUserData();

                if (userDataA == null || userDataB == null) {
                    return false;
                }

                return (userDataA.equals(userA) && userDataB.equals(userB) ||
                        userDataA.equals(userB) && userDataB.equals(userA));
            }

            /**
             *
             * Se lanza al inicio de una colision entre dos objetos.
             * */

            @Override
            public void beginContact(Contact contact) {
                if (areCollided(contact, "player", "floor")) {
                    player.setJumping(false);

                    if (Gdx.input.justTouched()) {
                        jumpSound.play(game.effectsVolume / 100);
                        player.setMustJump(true);
                    }
                }

                if (areCollided(contact, "player", "spike")) {
                    if (player.isAlive()) {
                        player.setAlive(false);


                        bgMusic.stop();
                        dieSound.play(game.effectsVolume / 100);

                        stage.addAction(
                                Actions.sequence(
                                        Actions.delay(1.5f),
                                        Actions.run(() -> game.setScreen(game.gameOverScreen))
                                )
                        );
                    }
                }
            }

            /**
             *
             * Se lanza al final de una colision entre dos objetos.
             * */
            @Override
            public void endContact(Contact contact) {
                if (areCollided(contact, "player", "floor")) {
                    if (player.isAlive()) {
                        jumpSound.play(game.effectsVolume / 100);
                    }
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
    }

    @Override
    public void show() {

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight(), camera);
        viewport.apply(true);

/**
 *
 * Guardamos las texturas en variables para poder trabajar con ellas.
 * */
        Texture playerTexture = game.getManager().get("Dino.png");
        Texture spikeTexture = game.getManager().get("Spike.png");
        Texture floorTexture = game.getManager().get("floor.jpg");

        player = new PlayerEntity(world, playerTexture, new Vector2(1, 2));
/**
 *
 * Asignamos y añadimos las texturas a su objeto correspondiente para mostrarlos por pantalla.
 * */

/**
 * Floors ******************************************************************************************
 * */
        floorList.add(new FloorEntity(floorTexture, world, 0, 1, 1000));

        floorList.add(new FloorEntity(floorTexture, world, 20, 2, 16));
        floorList.add(new FloorEntity(floorTexture, world, 24, 3, 12));
        floorList.add(new FloorEntity(floorTexture, world, 28, 4, 8));
        floorList.add(new FloorEntity(floorTexture, world, 32, 5, 4));

        floorList.add(new FloorEntity(floorTexture, world, 38, 5, 4));
        floorList.add(new FloorEntity(floorTexture, world, 38, 4, 8));
        floorList.add(new FloorEntity(floorTexture, world, 38, 3, 12));
        floorList.add(new FloorEntity(floorTexture, world, 38, 2, 16));

        floorList.add(new FloorEntity(floorTexture, world, 62, 2, 20));
        floorList.add(new FloorEntity(floorTexture, world, 62, 6, 20));

        floorList.add(new FloorEntity(floorTexture, world, 84, 3, 2));
        floorList.add(new FloorEntity(floorTexture, world, 88, 4, 2));
        floorList.add(new FloorEntity(floorTexture, world, 92, 5, 2));
        floorList.add(new FloorEntity(floorTexture, world, 96, 6, 2));
        floorList.add(new FloorEntity(floorTexture, world, 100, 7, 2));
        floorList.add(new FloorEntity(floorTexture, world, 104, 8, 2));

        floorList.add(new FloorEntity(finishLine, world, 120, 2, 2));
/**
 * Spikes ******************************************************************************************
 * */
        spikeList.add(new SpikeEntity(spikeTexture, world, 36, 1));
        spikeList.add(new SpikeEntity(spikeTexture, world, 37, 1));

        spikeList.add(new SpikeEntity(spikeTexture, world, 61, 1));

        spikeList.add(new SpikeEntity(spikeTexture, world, 68, 2));
        spikeList.add(new SpikeEntity(spikeTexture, world, 75, 2));
        spikeList.add(new SpikeEntity(spikeTexture, world, 82, 1));

        for (int i = 84; i < 106; i++) {
            spikeList.add(new SpikeEntity(spikeTexture, world, i, 1));
        }
/**
 * Añadimos los actores a la escena.
 * */

        for (FloorEntity floor : floorList) {
            stage.addActor(floor);
        }

        for (SpikeEntity spike : spikeList) {
            stage.addActor(spike);
        }

        stage.addActor(player);

        stage.getCamera().position.set(position);
        stage.getCamera().update();


        bgMusic.setVolume(game.musicVolume / 100);
        bgMusic.play();

    }

    /**
     * Liberamos espacio de memoria una vez que cerramos la pantalla actual. En caso de no hacerlo puede
     * generar muchos errores en la siguiente ejecución.
     */
    @Override
    public void hide() {

        stage.clear();

        player.detach();
        player.remove();

        for (FloorEntity floor : floorList) {
            floor.detach();
            floor.remove();
        }

        for (SpikeEntity spike : spikeList) {
            spike.detach();
            spike.remove();
        }
        floorList.clear();
        spikeList.clear();

        bgMusic.stop();
    }

    /**
     * Este metodo se lanza X veces por segundo dependiendo de la configuración que nosotros le indiquemos.
     */

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.begin();
        batch.draw(bg, 0, 0, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());

        batch.end();

        stage.act();


        /**
         * Le damos estos valores que son lo que indican las veces que se ejecutara este metodo por
         * segundo ya que es la configuracion que recomienda en la documentacion de libGDX.
         * */

        world.step(delta, 6, 2);

        if (player.getX() > 150 && player.isAlive()) {
            stage.getCamera().translate(Constants.PLAYER_SPEED * delta *
                    Constants.PIXELS_IN_METERS, 0, 0);
        }


        if (player.getX() > 10500 && player.isAlive()) {
            winSound.play(game.effectsVolume / 100);
            stage.addAction(
                    Actions.sequence(
                            Actions.delay(1.1f),
                            Actions.run(() -> game.setScreen(game.gameWonScreen))
                    )
            );
        }

        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
