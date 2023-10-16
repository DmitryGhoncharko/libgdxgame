package com.mygdx.game.stage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.actor.AsteroidActor;
import com.mygdx.game.actor.ShipActor;
import com.mygdx.game.screen.MainMenuScreen;

public class GameStage extends Stage {
    private final Game game;
    private final ShipActor shipActor;
    private final Array<AsteroidActor> asteroids;
    private float timeSinceLastAsteroids;

    public GameStage(Game game) {
        shipActor = new ShipActor(new Texture("shuttle.png"));
        addActor(shipActor);
        asteroids = new Array<>();
        timeSinceLastAsteroids = 0;
        this.game = game;
    }

    public ShipActor getShipActor() {
        return shipActor;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        float newX = shipActor.getX();
        timeSinceLastAsteroids += delta;
        if (timeSinceLastAsteroids > 2f) {
            AsteroidActor asteroid = new AsteroidActor(new Texture("asteroid.png"));
            asteroid.setPosition(MathUtils.random(0, getWidth() - asteroid.getWidth()), getHeight());
            asteroids.add(asteroid);
            addActor(asteroid);
            timeSinceLastAsteroids = 0;
        }
        for (AsteroidActor asteroidActor : asteroids) {
            if (asteroidActor.getBounds().overlaps(shipActor.getBounds())) {
                gameOver();
            }
        }
        if (newX < 0) {
            newX = 0;
        } else if (newX + shipActor.getWidth() > getWidth()) {
            newX = getWidth() - shipActor.getWidth();
        }
        for (AsteroidActor asteroidActor : asteroids) {
            asteroidActor.setFallSpeed(asteroidActor.getFallSpeed() + 10f * delta);
        }
        shipActor.setX(newX);
    }

    private void gameOver() {
        game.setScreen(new MainMenuScreen(game));
    }
}
