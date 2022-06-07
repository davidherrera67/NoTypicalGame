package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.game.Game;

public class GameOverScreen extends BaseScreen {

    private Stage stage;

    private Skin skin;

    private Image gameOverTittle;

    private TextButton retryButton, backToMenuButton;

    private Texture backGroundImage;

    private SpriteBatch batch;

    private OrthographicCamera camera;

    private ExtendViewport viewport;


    public GameOverScreen(final Game game) {
        super(game);

        stage = new Stage(new FitViewport(Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight()));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        batch = new SpriteBatch();

        gameOverTittle = new Image(game.getManager().get("images/gameOverTittle.png", Texture.class));

        backGroundImage = game.getManager().get("images/bg4K.jpg", Texture.class);

        retryButton = new TextButton("Retry", skin);

        backToMenuButton = new TextButton("Menu", skin);

        retryButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.selectionMenuScreen);
            }
        });

        backToMenuButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // And here I go to the menu screen.
                game.setScreen(game.menuScreen);
            }
        });

        gameOverTittle.setPosition((Gdx.graphics.getWidth() / 2.5f) - 200f, Gdx.graphics.getHeight() / 1.8f);

        retryButton.setSize(700, 80);
        retryButton.setPosition((Gdx.graphics.getWidth() / 2.5f) - 100f, Gdx.graphics.getHeight() / 2f);
        retryButton.getLabel().setFontScale(3f, 3f);

        backToMenuButton.setSize(700, 80);
        backToMenuButton.setPosition((Gdx.graphics.getWidth() / 2.5f) - 100f, Gdx.graphics.getHeight() / 2.8f);
        backToMenuButton.getLabel().setFontScale(3f, 3f);

        stage.addActor(retryButton);
        stage.addActor(backToMenuButton);
        stage.addActor(gameOverTittle);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(2550, 1440, camera);
        viewport.apply(true);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.begin();
        batch.draw(backGroundImage, 0, 0, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
