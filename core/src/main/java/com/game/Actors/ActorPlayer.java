package com.game.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorPlayer extends Actor {

    private Texture player;
    private boolean alive;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public ActorPlayer(Texture player) {
        this.player = player;
        this.alive = true;
        setSize(player.getWidth(), player.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(player,getX(),getY());
    }
}
