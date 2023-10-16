package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.stage.GameStage;

/**
 * Класс GameScreen представляет экран игры, в котором отображается игровая сцена.
 * В данном случае используется GameStage для управления актерами и отображения игровых объектов.
 * <p>
 * Основные функции:
 * - Отображение игровой сцены с использованием GameStage.
 * - Обновление сцены и отрисовка каждого кадра.
 * - Очистка экрана перед отрисовкой нового кадра.
 * - Управление размерами сцены при изменении размеров окна.
 * - Обработка событий жизненного цикла экрана (пауза, возобновление и т. д.).
 * - Освобождение ресурсов при уничтожении экрана.
 */
public class GameScreen implements Screen {
    private final Game game;
    private final GameStage gameStage;

    /**
     * Конструктор класса. Создает экран игры с использованием объекта Game
     * и инициализирует игровую сцену GameStage.
     *
     * @param game Объект Game для управления экранами.
     */
    public GameScreen(com.badlogic.gdx.Game game) {
        this.game = game;
        this.gameStage = new GameStage(game);
    }

    /**
     * Метод, вызываемый при отображении данного экрана.
     * Устанавливает вводовой процессор для игровой сцены и фокус на корабль.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(gameStage);
        gameStage.setKeyboardFocus(gameStage.getShipActor());
    }

    /**
     * Метод для обновления и отрисовки сцены каждый кадр.
     *
     * @param delta Время, прошедшее с последнего кадра.
     */
    @Override
    public void render(float delta) {
        update(delta);
        clearScreen();
        draw();
    }

    /**
     * Метод для очистки экрана перед отрисовкой нового кадра.
     */
    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Метод для отрисовки игровой сцены.
     */
    private void draw() {
        gameStage.draw();
    }

    /**
     * Метод для обновления игровой сцены.
     *
     * @param delta Время, прошедшее с последнего кадра.
     */
    private void update(float delta) {
        gameStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    /**
     * Метод для освобождения ресурсов, связанных с экраном,
     * при его уничтожении или смене на другой экран.
     */
    @Override
    public void dispose() {
        gameStage.dispose();
        game.dispose();
    }
}
