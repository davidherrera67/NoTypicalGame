package com.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Game extends com.badlogic.gdx.Game {

    private AssetManager manager;

    public AssetManager getManager(){
        return manager;
    }

    @Override
    public void create() {
        manager = new AssetManager();
//      Cargar texturas
        manager.load("Dino.png", Texture.class);
        manager.load("Spike.png", Texture.class);
        manager.load("floor.jpg", Texture.class);
        manager.finishLoading();

        setScreen(new GameScreen(this));
    }
}