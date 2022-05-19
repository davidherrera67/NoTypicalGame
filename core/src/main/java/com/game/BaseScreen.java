package com.game;

import com.badlogic.gdx.Screen;

public abstract class BaseScreen implements Screen {

    protected Game game;

    public BaseScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    //delta; segundos desde que se refresco la pantalla
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
