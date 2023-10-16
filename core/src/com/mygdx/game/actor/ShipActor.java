package com.mygdx.game.actor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Класс ShipActor представляет собой актера в игровой сцене,
 * представляющего космический корабль игрока. Корабль может
 * двигаться влево и вправо при нажатии соответствующих клавиш.
 * <p>
 * Основные функции:
 * - Отрисовка текстуры космического корабля на экране.
 * - Обработка нажатий клавиш для движения корабля.
 * - Определение границы актера для обработки столкновений.
 * - Освобождение ресурсов при уничтожении актера.
 */
public class ShipActor extends Actor {

    private static final float SHIP_SPEED = 30f;
    private final TextureRegion textureRegion;
    private Texture texture;

    /**
     * Конструктор класса. Создает актера космического корабля
     * с заданной текстурой и устанавливает слушатель для обработки
     * клавиш управления.
     *
     * @param texture Текстура космического корабля.
     */
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
     * Метод для движения корабля влево.
     */
    public void moveLeft() {
        setX(getX() - SHIP_SPEED);
    }

    /**
     * Метод для движения корабля вправо.
     */
    public void moveRight() {
        setX(getX() + SHIP_SPEED);
    }

    /**
     * Получение границы актера для обработки столкновений.
     *
     * @return Прямоугольник, представляющий границы актера.
     */
    public Rectangle getBounds() {
        return new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }

    /**
     * Метод для освобождения ресурсов, связанных с актером,
     * при его уничтожении или удалении из сцены.
     */
    public void dispose() {
        texture.dispose();
    }
}
