package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.game.Game;

public class GameOverScreen extends BaseScreen {


    private Stage stage;

    private Skin skin;

    private Image gameOver;

    private TextButton retry, menu;

    public GameOverScreen(final Game game) {
        super(game);

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getWidth()));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        gameOver = new Image(game.getManager().get("gameover.png", Texture.class));

        retry = new TextButton("Retry", skin);
        menu = new TextButton("Menu", skin);

        retry.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.gameScreen);
            }
        });

        menu.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // And here I go to the menu screen.
                game.setScreen(game.menuScreen);
            }
        });

        gameOver.setPosition(1000 - gameOver.getWidth() / 2, 1000 - gameOver.getHeight());
        retry.setSize(200, 100);
        retry.setPosition(1100, 700);

        menu.setSize(200, 100);
        menu.setPosition(1100, 500);

        stage.addActor(retry);
        stage.addActor(menu);
        stage.addActor(gameOver);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }
}