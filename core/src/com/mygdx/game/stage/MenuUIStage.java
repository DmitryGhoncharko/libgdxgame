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

public class MenuUIStage extends Stage {
    private final Game game;
    private final Skin skin;

    public MenuUIStage(Game game) {
        this.game = game;
        skin = initSkin();
        addActor(initStartTextButtonStartGame());
        addActor(initExitTextButton());
    }

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

    private Skin initSkin() {
        Skin skin = new Skin();
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        skin.add("default", textButtonStyle);
        return skin;
    }
}
