package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.game.Game;

public class AboutUsScreen extends BaseScreen{

    private Stage stage;

    private Skin skin;

    private Texture bg;

    private TextButton backToOptionsButton;

    private Label info;

    private Image aboutUsLabel;

    private SpriteBatch batch;

    private OrthographicCamera camera;

    private ExtendViewport viewport;

    public AboutUsScreen(Game game) {
        super(game);

        stage = new Stage(new FitViewport(Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight()));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        batch = new SpriteBatch();
        bg = game.getManager().get("images/bg4K.jpg", Texture.class);
        aboutUsLabel = new Image(game.getManager().get("images/aboutUsTittle.png", Texture.class));



        backToOptionsButton = new TextButton("Back", skin);
        backToOptionsButton.setSize(700, 80);
        backToOptionsButton.getLabel().setFontScale(3f, 3f);
        backToOptionsButton.setPosition((Gdx.graphics.getWidth() / 2.5f) - 100f,Gdx.graphics.getHeight() / 3f);

        aboutUsLabel.setPosition((Gdx.graphics.getWidth() / 2.5f) - 200f, Gdx.graphics.getHeight() /1.5f);

        info = new Label("Trabajo de fin de grado en Desarrollo de aplicaciones multiplataforma en el I.E.S Gines Perez Chirinos. \n" +
                "Realizado por: \n" +
                "Ruben Garcia Garcia \n" +
                "David Herrera Costa"
                , skin);
        info.setSize(1000, 500);
        info.setFontScale(3f, 3f);
        info.setPosition((Gdx.graphics.getWidth() / 2.5f) - 800f, Gdx.graphics.getHeight() / 2f);


        stage.addActor(backToOptionsButton);
        stage.addActor(info);
        stage.addActor(aboutUsLabel);



        backToOptionsButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.optionsScreen);
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
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.begin();
        batch.draw(bg, 0, 0, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }


    @Override
    public void dispose() {
        // Dispose assets.
        stage.dispose();
        batch.dispose();
        skin.dispose();
    }
}
