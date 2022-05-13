package com.codegym.games.game2048;

import com.codegym.engine.cell.*;

public class Game2048 extends Game {
    private static final int SIDE = 4;

    public static void main(String[] args) {

    }

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
    }
}

