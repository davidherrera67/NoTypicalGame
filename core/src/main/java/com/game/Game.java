package com.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.game.screens.AboutUsScreen;
import com.game.screens.BaseScreen;
import com.game.screens.GameOverScreen;
import com.game.screens.GameWonScreen;
import com.game.screens.Level1Screen;
import com.game.screens.Level2Screen;
import com.game.screens.LoadingScreen;
import com.game.screens.MenuScreen;
import com.game.screens.OptionsScreen;
import com.game.screens.SelectionMenuScreen;


/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Game extends com.badlogic.gdx.Game {

    private AssetManager manager;

    public BaseScreen loadingScreen, menuScreen, level1Screen, level2Screen, gameOverScreen, optionsScreen, aboutUsScreen, gameWonScreen, selectionMenuScreen;

    public float musicVolume = 50;
    public float effectsVolume = 50;

    public AssetManager getManager() {
        return manager;
    }

    @Override
    public void create() {
        manager = new AssetManager();

/**
 *
 * UTILIZAMOS EL MANEGER PARA CARGAR LAS TEXTURAS DE ANTEMANO PARA OPTIMIZAR EL USO DE MEMORIA DE LA TARJETA GRÁFICA.
 * */

        manager.load("Dino.png", Texture.class);
        manager.load("Spike.png", Texture.class);
        manager.load("floor.jpg", Texture.class);
        manager.load("gameover.png", Texture.class);
        manager.load("logo.png", Texture.class);
        manager.load("vaporwave.jpg", Texture.class);
        manager.load("images/tittle.png", Texture.class);
        manager.load("images/bg4K.jpg", Texture.class);
        manager.load("images/bgGreen.jpg", Texture.class);
        manager.load("images/aboutUsTittle.png", Texture.class);
        manager.load("images/optionsTittle.png", Texture.class);
        manager.load("images/gameOverTittle.png", Texture.class);
        manager.load("images/gameWonTittle.png", Texture.class);
        manager.load("images/selectYourLevel.png", Texture.class);
        manager.load("images/finishLine.png", Texture.class);
        manager.load("music/die.mp3", Sound.class);
        manager.load("music/jump.mp3", Sound.class);
        manager.load("music/song.ogg", Music.class);
        manager.load("music/level2_like_wooh_wooh_.mp3", Music.class);
        manager.load("music/bites-ta-da-winner.mp3", Sound.class);
        manager.load("images/Level2Bricks.PNG", Texture.class);
        manager.load("images/level2Floor.PNG", Texture.class);




        manager.finishLoading();


        loadingScreen = new LoadingScreen(this);
/**
 *
 * Con esta linea indicamos cual va a ser la pantalla principal de nuestra app.
 * */
        setScreen(loadingScreen);
    }

    public void finishLoading() {
        menuScreen = new MenuScreen(this);
        level1Screen = new Level1Screen(this);
        level2Screen = new Level2Screen(this);
        gameOverScreen = new GameOverScreen(this);
        optionsScreen = new OptionsScreen(this);
        aboutUsScreen = new AboutUsScreen(this);
        gameWonScreen = new GameWonScreen(this);
        selectionMenuScreen = new SelectionMenuScreen(this);
        setScreen(menuScreen);
    }
}