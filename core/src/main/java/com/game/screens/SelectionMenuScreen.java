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

public class SelectionMenuScreen extends BaseScreen{


    private Stage stage;

    private Skin skin;

    private Image logo;

    private TextButton level1Button, level2Button, backToMenu;

    private Texture bg;

    private SpriteBatch batch;

    private OrthographicCamera camera;

    private ExtendViewport viewport;

    public SelectionMenuScreen(Game game) {
        super(game);

        stage = new Stage(new FitViewport(Gdx.app.getGraphics().getWidth() , Gdx.app.getGraphics().getHeight()));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        batch = new SpriteBatch();

        logo = new Image(game.getManager().get("images/selectYourLevel.png", Texture.class));
        bg = game.getManager().get("images/bg4K.jpg", Texture.class);



        logo.setPosition((Gdx.graphics.getWidth() / 2.5f) - 200f, Gdx.graphics.getHeight() / 1.6f);

        level1Button = new TextButton("Level 1", skin);
        level1Button.setSize(700, 80);
        level1Button.getLabel().setFontScale(3f, 3f);
        level1Button.setPosition((Gdx.graphics.getWidth() / 5f), Gdx.graphics.getHeight() / 2f);

        level2Button = new TextButton("Level 2", skin);
        level2Button.setSize(700, 80);
        level2Button.getLabel().setFontScale(3f, 3f);
        level2Button.setPosition((Gdx.graphics.getWidth() / 2f), Gdx.graphics.getHeight() / 2f);

        backToMenu = new TextButton("Back", skin);
        backToMenu.setSize(700, 80);
        backToMenu.getLabel().setFontScale(3f, 3f);
        backToMenu.setPosition((Gdx.graphics.getWidth() / 2.5f) - 100f, Gdx.graphics.getHeight() / 3f);


        stage.addActor(level1Button);
        stage.addActor(level2Button);
        stage.addActor(logo);
        stage.addActor(backToMenu);


        level1Button.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(game.level1Screen);
            }
        });

        level2Button.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(game.level2Screen);
            }
        });

        backToMenu.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(game.menuScreen);
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

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        skin.dispose();
    }
}