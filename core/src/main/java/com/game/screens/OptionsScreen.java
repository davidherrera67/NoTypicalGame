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
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.game.Game;

public class OptionsScreen extends BaseScreen {

    private Stage stage;

    private Skin skin;

    private Texture bg;

    private TextButton aboutUsButton, menu;

    private Image optionsLabel;

    private Label musicVolume, soundsVolume;

    private SpriteBatch batch;

    private OrthographicCamera camera;

    private Slider musicVolumeSlider, effectVolumeSlider;

    private ExtendViewport viewport;


    public OptionsScreen(Game game) {
        super(game);

        stage = new Stage(new FitViewport(Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight()));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        batch = new SpriteBatch();
        bg = game.getManager().get("images/bg4K.jpg", Texture.class);
        optionsLabel = new Image(game.getManager().get("images/optionsTittle.png", Texture.class));

        musicVolume = new Label("Music Volume: ", skin);
        musicVolume.setFontScale(3, 3);
        musicVolume.setPosition((Gdx.graphics.getWidth() / 10f), Gdx.graphics.getHeight() / 1.5f);


        soundsVolume = new Label("Effects Volume: ", skin);
        soundsVolume.setFontScale(3,3);
        soundsVolume.setPosition((Gdx.graphics.getWidth() / 10f), Gdx.graphics.getHeight() / 2f);


        musicVolumeSlider = new Slider(0, 100, 0.1f, false, skin);
        musicVolumeSlider.setPosition((Gdx.graphics.getWidth() / 2.5f) - 100, Gdx.graphics.getHeight() / 1.5f);
        musicVolumeSlider.setSize(700, 50);
        musicVolumeSlider.setValue(50);
        musicVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.musicVolume = musicVolumeSlider.getValue();
            }
        });

        effectVolumeSlider = new Slider(0, 100, 0.1f, false, skin);
        effectVolumeSlider.setPosition((Gdx.graphics.getWidth() / 2.5f) - 100, Gdx.graphics.getHeight() / 2f);
        effectVolumeSlider.setSize(700, 50);
        effectVolumeSlider.setValue(50);
        effectVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.effectsVolume = effectVolumeSlider.getValue();
            }
        });

        aboutUsButton = new TextButton("About us...", skin);
        aboutUsButton.setSize(700, 80);
        aboutUsButton.getLabel().setFontScale(3f, 3f);
        aboutUsButton.setPosition((Gdx.graphics.getWidth() / 2.5f) - 100, Gdx.graphics.getHeight() / 5f);

        menu = new TextButton("Main menu", skin);
        menu.setSize(700, 80);
        menu.getLabel().setFontScale(3f, 3f);
        menu.setPosition((Gdx.graphics.getWidth() / 2.5f) - 100f, Gdx.graphics.getHeight() / 9f);

        optionsLabel.setPosition((Gdx.graphics.getWidth() / 2.5f) - 200f, Gdx.graphics.getHeight() /1.5f);

        stage.addActor(optionsLabel);
        stage.addActor(aboutUsButton);
        stage.addActor(musicVolumeSlider);
        stage.addActor(musicVolume);
        stage.addActor(soundsVolume);
        stage.addActor(effectVolumeSlider);
        stage.addActor(menu);

        aboutUsButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.aboutUsScreen);
            }
        });

        menu.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
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
        batch.draw(bg, 0, 0, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
        batch.end();

        stage.act();
        stage.draw();
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}
