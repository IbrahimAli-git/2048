package com.codegym.games.game2048;

import com.codegym.engine.cell.*;

public class Game2048 extends Game {
    private static final int SIDE = 4;
    private int gameField[][] = new int[SIDE][SIDE];

    public static void main(String[] args) {

    }

    @Override
    public void initialize() {
        createGame();
        setScreenSize(SIDE, SIDE);
    }


    private void createGame(){

    }
}


