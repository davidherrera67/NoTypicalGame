package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainGameScreen extends BaseScreen{
    private Stage stage;
    private ActorPlayer actorPlayer;

    public MainGameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        stage = new Stage();
        actorPlayer = new ActorPlayer();
        stage.addActor(actorPlayer);
        //darle posicion al jugador
        //actorPlayer.setPosition(0,0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //pide que se actualicen
        stage.act();
        //se pinta toodo el escenario de golpe
        stage.draw();
    }

    @Override
    public void hide() {
        //para eliminar de la memoria cada vez que se cambie de pantalla
        stage.dispose();
    }

}
