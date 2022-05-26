package com.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.game.screens.BaseScreen;
import com.game.screens.GameOverScreen;
import com.game.screens.GameScreen;
import com.game.screens.LoadingScreen;
import com.game.screens.MenuScreen;

import org.w3c.dom.Text;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Game extends com.badlogic.gdx.Game {

    private AssetManager manager;

    public BaseScreen loadingScreen, menuScreen, gameScreen, gameOverScreen;

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
        manager.load("images/tittle.png", Texture.class);
        //manager.load("bg.png", Texture.class);
        manager.load("images/bg4K.jpg", Texture.class);
        manager.load("music/die.mp3", Sound.class);
        manager.load("music/jump.mp3", Sound.class);
        manager.load("music/song.ogg", Music.class);
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
        gameScreen = new GameScreen(this);
        gameOverScreen = new GameOverScreen(this);
        setScreen(menuScreen);
    }
}