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

public class MenuScreen extends BaseScreen {

    private Stage stage;

    private Skin skin;

    private Image logo;

    private TextButton play, configuration, exit;

    private Texture bg;

    private SpriteBatch batch;

    private OrthographicCamera camera;

    private ExtendViewport viewport;

    public MenuScreen(final Game game) {
        super(game);

        stage = new Stage(new FitViewport(Gdx.app.getGraphics().getWidth() , Gdx.app.getGraphics().getHeight()));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        batch = new SpriteBatch();

        play = new TextButton("Play", skin);
        play.getLabel().setFontScale(3f, 3f);
        configuration = new TextButton("Configuration", skin);
        configuration.getLabel().setFontScale(3f, 3f);
        exit = new TextButton("Exit", skin);
        exit.getLabel().setFontScale(3f, 3f);

        logo = new Image(game.getManager().get("images/tittle.png", Texture.class));
        bg = game.getManager().get("images/bg4K.jpg", Texture.class);

        play.setSize(700, 80);
        configuration.setSize(700, 80);
        exit.setSize(700, 80);

        logo.setPosition(Gdx.graphics.getWidth() / 2.5f, Gdx.graphics.getHeight() /1.5f);
        play.setPosition((Gdx.graphics.getWidth() / 2.5f) - 100f, Gdx.graphics.getHeight() / 2f);
        configuration.setPosition((Gdx.graphics.getWidth() / 2.5f) - 100f, Gdx.graphics.getHeight() / 2.5f);
        exit.setPosition((Gdx.graphics.getWidth() / 2.5f) - 100f, Gdx.graphics.getHeight() / 3.3f);

        stage.addActor(play);
        stage.addActor(logo);
        stage.addActor(configuration);
        stage.addActor(exit);



        play.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(game.gameScreen);
            }
        });

        configuration.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //game.setScreen(game.creditsScreen);
            }
        });

        exit.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(2550, 1440, camera);
        viewport.apply(true);

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        // Dispose assets.
        stage.dispose();
        batch.dispose();
        skin.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.begin();
        batch.draw(bg, 0,0, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight() );
        batch.end();

        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
