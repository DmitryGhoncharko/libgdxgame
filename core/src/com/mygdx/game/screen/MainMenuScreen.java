package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.stage.MenuUIStage;

/**
 * Класс MainMenuScreen представляет главное меню игры.
 * Этот экран отображает меню с кнопками и обрабатывает события пользователя.
 * <p>
 * Основные функции:
 * - Отображение главного меню с использованием MenuUIStage.
 * - Обновление и отрисовка сцены каждый кадр.
 * - Очистка экрана перед отрисовкой нового кадра.
 * - Обработка событий жизненного цикла экрана (пауза, возобновление и т. д.).
 * - Освобождение ресурсов при уничтожении экрана.
 */
public class MainMenuScreen implements Screen {
    private final Game game;
    private final Stage stage;

    /**
     * Конструктор класса. Создает экран главного меню с использованием объекта Game
     * и инициализирует Stage для управления интерфейсом.
     *
     * @param game Объект Game для управления экранами.
     */
    public MainMenuScreen(Game game) {
        this.game = game;
        stage = new MenuUIStage(game);
    }

    /**
     * Метод, вызываемый при отображении данного экрана.
     * Устанавливает вводовой процессор для Stage.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Метод для обновления и отрисовки сцены каждый кадр.
     *
     * @param delta Время, прошедшее с последнего кадра.
     */
    @Override
    public void render(float delta) {
        update();
        clearScreen();
        draw();
    }

    /**
     * Метод для отрисовки сцены главного меню.
     */
    private void draw() {
        stage.draw();
    }

    /**
     * Метод для обновления сцены главного меню.
     */
    private void update() {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
    }

    /**
     * Метод для очистки экрана перед отрисовкой нового кадра.
     */
    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {

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
        stage.dispose();
        game.dispose();
    }
}
