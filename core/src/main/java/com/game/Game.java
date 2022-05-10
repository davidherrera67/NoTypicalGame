package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture image;
	private int altura,anchura;

	@Override
	public void create() {
		batch = new SpriteBatch();
		image = new Texture("vaporwave.jpg");
		altura = Gdx.graphics.getHeight();
		anchura = Gdx.graphics.getWidth();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
		//limpiar la pantalla para que no se vea lo anterior
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(image, 0, 0,anchura,altura);
		batch.end();
	}
	//liberar las texturas para mejorar rendimiento
	@Override
	public void dispose() {
		batch.dispose();
		image.dispose();
	}
}