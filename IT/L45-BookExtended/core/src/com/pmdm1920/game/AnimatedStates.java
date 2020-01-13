package com.pmdm1920.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Timer;

import java.util.HashMap;
import java.util.List;

public class AnimatedStates implements Disposable {
    private final float FPS = 10;
    private HashMap<String, Animation> animations;
    private float timeLinePosition;
    private Timer.Task timer;
    private String currentRegion;
    private boolean resetTimeLine;

    public AnimatedStates(
            TextureAtlas atlas,
            String regionNamespace,
            List<String> regions,
            String currentRegion) {
        final float frameDuration = 1/FPS;
        resetTimeLine = true;
        this.currentRegion = currentRegion;
        animations = new HashMap();

        for (String region : regions){
            Animation animation = new Animation(
                                        frameDuration,
                                        atlas.findRegions(regionNamespace+"/"+region),
                                        Animation.PlayMode.LOOP);
            animations.put(region, animation);
        }

        timer = Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (resetTimeLine){
                    resetTimeLine = false;
                    timeLinePosition = 0;
                }
                else
                    timeLinePosition += frameDuration;
            }
        }, frameDuration, frameDuration);
    }

    public TextureRegion getCurrentFrame(String region) {
        if (region != currentRegion) {
            currentRegion = region;
            resetTimeLine = true;
        }
        return (TextureRegion) animations.get(region).getKeyFrame(timeLinePosition);
    }


    @Override
    public void dispose() {
        timeLinePosition = 0;
        animations.clear();
        animations = null;
        timer.cancel();
        timer = null;
    }
}
