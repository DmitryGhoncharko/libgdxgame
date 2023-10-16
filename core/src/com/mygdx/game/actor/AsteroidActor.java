package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Класс AsteroidActor представляет собой актера в игровой сцене,
 * который представляет астероид. Астероид падает вниз с постоянной
 * скоростью и исчезает, когда достигает нижней границы экрана.
 * <p>
 * Основные функции:
 * - Отрисовка текстуры астероида на экране.
 * - Автоматическое перемещение астероида вниз с заданной скоростью.
 * - Определение границы актера для обработки столкновений.
 * - Управление скоростью падения астероида.
 * - Освобождение ресурсов при уничтожении актера.
 */

public class AsteroidActor extends Actor {
    private final TextureRegion textureRegion;
    private final Rectangle bounds = new Rectangle();
    private float fallSpeed = 100f;

    /**
     * Конструктор класса. Создает актера астероида с заданной текстурой.
     *
     * @param texture Текстура астероида.
     */
    public AsteroidActor(Texture texture) {
        this.textureRegion = new TextureRegion(texture);
        setBounds(getX(), getY(), textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }

    /**
     * Получение границы актера для обработки столкновений.
     *
     * @return Прямоугольник, представляющий границы актера.
     */
    public Rectangle getBounds() {
        bounds.set((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
        return bounds;
    }

    /**
     * Метод, вызываемый на каждом шаге обновления игровой сцены.
     * Перемещает астероид вниз и удаляет его, если он достиг нижней границы.
     *
     * @param delta Время, прошедшее с предыдущего обновления.
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        setY(getY() - fallSpeed * delta);
        if (getY() + getHeight() < 0) {
            remove();
        }
    }

    /**
     * Метод отрисовки актера на экране.
     *
     * @param batch       Объект Batch для отрисовки.
     * @param parentAlpha Прозрачность родителя.
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    /**
     * Получение скорости падения астероида.
     *
     * @return Скорость падения астероида.
     */
    public float getFallSpeed() {
        return fallSpeed;
    }

    /**
     * Установка скорости падения астероида.
     *
     * @param fallSpeed Новая скорость падения астероида.
     */
    public void setFallSpeed(float fallSpeed) {
        this.fallSpeed = fallSpeed;
    }

    /**
     * Метод для освобождения ресурсов, связанных с актером,
     * при его уничтожении или удалении из сцены.
     */
    public void dispose() {
        textureRegion.getTexture().dispose();
    }
}
