package com.mygdx.game.stage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.actor.AsteroidActor;
import com.mygdx.game.actor.ShipActor;
import com.mygdx.game.screen.MainMenuScreen;

/**
 * Класс GameStage представляет игровую сцену, на которой происходит основная игровая логика.
 * В данной сцене отображается космический корабль (ShipActor) и астероиды (AsteroidActor).
 * <p>
 * Основные функции:
 * - Создание и управление объектами корабля и астероидов.
 * - Обработка столкновений между кораблем и астероидами.
 * - Отслеживание времени для появления новых астероидов.
 * - Завершение игры при столкновении корабля с астероидом.
 * <p>
 * Также, он осуществляет переход на главный экран при завершении игры.
 */
public class GameStage extends Stage {
    private final Game game;
    private final ShipActor shipActor;
    private final Array<AsteroidActor> asteroids;
    private float timeSinceLastAsteroids;

    /**
     * Конструктор класса. Создает игровую сцену с кораблем и массивом астероидов.
     *
     * @param game Объект Game для управления экранами.
     */
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

    /**
     * Метод для обновления сцены на каждом кадре.
     *
     * @param delta Время, прошедшее с предыдущего кадра.
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        float newX = shipActor.getX();
        timeSinceLastAsteroids += delta;
        // Создание нового астероида каждые 2 секунды
        if (timeSinceLastAsteroids > 2f) {
            AsteroidActor asteroid = new AsteroidActor(new Texture("asteroid.png"));
            asteroid.setPosition(MathUtils.random(0, getWidth() - asteroid.getWidth()), getHeight());
            asteroids.add(asteroid);
            addActor(asteroid);
            timeSinceLastAsteroids = 0;
        }
        // Проверка столкновений с кораблем
        for (AsteroidActor asteroidActor : asteroids) {
            if (asteroidActor.getBounds().overlaps(shipActor.getBounds())) {
                gameOver();
            }
        }
        // Обновление позиции корабля и увеличение скорости астероидов
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

    /**
     * Метод для освобождения ресурсов, связанных с игровой сценой,
     * при завершении игры или переходе на другой экран.
     */
    @Override
    public void dispose() {
        super.dispose();
        game.dispose();
        shipActor.dispose();
    }

    /**
     * Метод для завершения игры и перехода на главный экран.
     */
    private void gameOver() {
        game.setScreen(new MainMenuScreen(game));
    }
}
