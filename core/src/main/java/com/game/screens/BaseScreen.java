package com.game.screens;

import com.badlogic.gdx.Screen;
import com.game.Game;

public abstract class BaseScreen implements Screen {

    protected Game game;

    public BaseScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {}

   /**
    *
    * El delta es el valor por defecto que usa libGDX que indica la cantidad de veces por segundo que
    * se ejecutara el metodo render.
    * */
    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
