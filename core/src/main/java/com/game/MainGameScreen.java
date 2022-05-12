package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.Actors.ActorPlayer;
import com.game.Actors.ActorSpike;

public class MainGameScreen extends BaseScreen {
    private Stage stage;
    private ActorPlayer actorPlayer;
    private ActorSpike actorSpike;
    private Texture playerTexture;
    private Texture spikesTexture;
    private TextureRegion spikesRegion;

    public MainGameScreen(Game game) {
        super(game);
        playerTexture = new Texture("DinoReescalado.png");
        spikesTexture = new Texture("pinchoReescalado.png");
        spikesRegion = new TextureRegion(spikesTexture);
    }

    @Override
    public void show() {
        stage = new Stage();
        actorPlayer = new ActorPlayer(playerTexture);
        actorSpike = new ActorSpike(spikesRegion);
        stage.addActor(actorPlayer);
        stage.addActor(actorSpike);
        actorPlayer.setPosition(20, 100);
        actorSpike.setPosition(800, 100);
        //darle posicion al jugador
        //actorPlayer.setPosition(0,0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //pide que se actualicen
        stage.act();
        checkColisions();
        //se pinta toodo el escenario de golpe
        stage.draw();
    }


    private void checkColisions() {
        if (actorPlayer.isAlive() && (actorPlayer.getX() + actorPlayer.getWidth()) > actorSpike.getX()) {
            System.out.println("FIN DE LA PARTIDA");
            actorPlayer.setAlive(false);
        }
    }

    @Override
    public void hide() {
        //para eliminar de la memoria cada vez que se cambie de pantalla
        stage.dispose();
    }

    @Override
    public void dispose() {
        playerTexture.dispose();
        spikesTexture.dispose();
    }
}
