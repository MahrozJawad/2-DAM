package com.particles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Particles extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    ParticleEffect particleEffect;


    @Override
    public void create () {
        batch = new SpriteBatch();

        particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("fireParticle.p"),Gdx.files.internal(""));
        particleEffect.getEmitters().first().setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        particleEffect.start();
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        particleEffect.update(Gdx.graphics.getDeltaTime());
		batch.begin();
        particleEffect.draw(batch);
		batch.end();

        if(particleEffect.isComplete())
            particleEffect.reset();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
