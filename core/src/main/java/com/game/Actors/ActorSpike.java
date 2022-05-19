package com.game.Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorSpike extends Actor {

    private TextureRegion spikes;

    public ActorSpike(TextureRegion spikes) {
        this.spikes = spikes;
        setSize(spikes.getRegionWidth(), spikes.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(spikes, getX(), getY());

    }

    @Override
    public void act(float delta) {
        setX(getX() - 250 * delta);
    }
}
