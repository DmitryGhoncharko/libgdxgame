package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screen.MainMenuScreen;

public class SpaceInvadersGame extends Game {
    @Override
    public void create() {
        setScreen(new MainMenuScreen(this));
    }
}
