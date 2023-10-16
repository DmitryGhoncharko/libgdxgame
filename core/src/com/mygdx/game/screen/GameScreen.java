package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.stage.GameStage;

public class GameScreen implements com.badlogic.gdx.Screen {
    private final Game game;
    private final GameStage gameStage;

    public GameScreen(com.badlogic.gdx.Game game) {
        this.game = game;
        this.gameStage = new GameStage(game);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(gameStage);
        gameStage.setKeyboardFocus(gameStage.getShipActor());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        gameStage.draw();
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

    @Override
    public void dispose() {
        gameStage.dispose();
    }
}
