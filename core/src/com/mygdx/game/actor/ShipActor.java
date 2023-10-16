package com.mygdx.game.actor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ShipActor extends Actor {

    private final float speed = 30f;
    private final TextureRegion textureRegion;
    private Texture texture;

    public ShipActor(Texture texture) {
        this.textureRegion = new TextureRegion(texture);
        setBounds(getX(), getY(), textureRegion.getRegionWidth(), textureRegion.getRegionHeight());


        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.A:
                        moveLeft();
                        break;
                    case Input.Keys.D:
                        moveRight();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public void moveLeft() {
        setX(getX() - speed);
    }

    public void moveRight() {
        setX(getX() + speed);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }

}
