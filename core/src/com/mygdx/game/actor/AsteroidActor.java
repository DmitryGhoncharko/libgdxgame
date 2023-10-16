package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class AsteroidActor extends Actor {
    private final TextureRegion textureRegion;
    private final Rectangle bounds = new Rectangle();
    private float fallSpeed = 100f;

    public AsteroidActor(Texture texture) {
        this.textureRegion = new TextureRegion(texture);
        setBounds(getX(), getY(), textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }

    public Rectangle getBounds() {
        bounds.set((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
        return bounds;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setY(getY() - fallSpeed * delta);
        if (getY() + getHeight() < 0) {
            remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public float getFallSpeed() {
        return fallSpeed;
    }

    public void setFallSpeed(float fallSpeed) {
        this.fallSpeed = fallSpeed;
    }

}
