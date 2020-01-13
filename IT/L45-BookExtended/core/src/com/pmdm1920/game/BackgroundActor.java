package com.pmdm1920.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class BackgroundActor extends Actor {
    TextureRegion texture;

    public BackgroundActor(TextureRegion texture, float time) {
        super();
        this.texture = texture;
        this.setPosition(0, 0);
        this.setSize(texture.getRegionWidth(), texture.getRegionHeight());

        this.addAction(Actions.forever(
                Actions.sequence(
                        Actions.moveTo(-texture.getRegionWidth() / 2, 0, time),
                        Actions.moveTo(0,0)
                )
        ));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(
                texture,
                getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(),
                getRotation());
    }
}
