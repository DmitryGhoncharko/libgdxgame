package com.mygdx.game.stage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.screen.GameScreen;

/**
 * Класс MenuUIStage представляет собой сцену пользовательского интерфейса для главного меню игры.
 * Включает кнопку "Start game" для запуска игры и кнопку "Exit" для выхода из приложения.
 * <p>
 * Основные функции:
 * - Инициализация и отображение кнопок "Start game" и "Exit".
 * - Обработка событий клика на кнопках и выполнение соответствующих действий.
 * - Освобождение ресурсов при завершении использования сцены.
 * <p>
 * Кнопка "Start game":
 * - При клике на кнопку "Start game" создается экран игры (GameScreen) и устанавливается текущим экраном.
 * <p>
 * Кнопка "Exit":
 * - При клике на кнопку "Exit" завершается приложение.
 */
public class MenuUIStage extends Stage {
    private final Game game;
    private final Skin skin;

    /**
     * Конструктор класса. Инициализирует сцену пользовательского интерфейса для главного меню.
     *
     * @param game Объект Game для управления экранами.
     */
    public MenuUIStage(Game game) {
        this.game = game;
        skin = initSkin();
        addActor(initStartTextButtonStartGame());
        addActor(initExitTextButton());
    }

    /**
     * Метод для инициализации кнопки "Start game".
     *
     * @return Созданная кнопка "Start game".
     */
    private TextButton initStartTextButtonStartGame() {

        TextButton startButton = new TextButton("Start game", skin);
        startButton.getLabel().setAlignment(Align.center);

        startButton.setPosition((this.getWidth() - startButton.getWidth()) / 2, (this.getHeight() - startButton.getHeight()) / 2);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });
        return startButton;
    }

    /**
     * Метод для инициализации кнопки "Exit".
     *
     * @return Созданная кнопка "Exit".
     */
    private TextButton initExitTextButton() {

        TextButton exitButton = new TextButton("Exit", skin);
        exitButton.getLabel().setAlignment(Align.center);
        exitButton.setPosition((this.getWidth() - exitButton.getWidth()) / 2, ((this.getHeight() - exitButton.getHeight()) / 2) - 40f);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        return exitButton;
    }

    /**
     * Метод для инициализации стилей интерфейса.
     *
     * @return Созданный объект Skin для стилей интерфейса.
     */
    private Skin initSkin() {
        Skin skin = new Skin();
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        skin.add("default", textButtonStyle);
        return skin;
    }

    /**
     * Метод для освобождения ресурсов, связанных с пользовательским интерфейсом, при завершении использования сцены.
     */
    @Override
    public void dispose() {
        super.dispose();
        skin.dispose();
        game.dispose();
    }
}
