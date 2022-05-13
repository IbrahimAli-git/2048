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
        drawScene();
    }


    private void createGame(){
        createNewNumber();
        createNewNumber();
    }

    private void drawScene(){
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                setCellColor(i, j, Color.FIREBRICK);
            }
        }
    }

    private void createNewNumber(){
        int x, y, z;
        do {
            x = getRandomNumber(SIDE);
            y = getRandomNumber(SIDE);
        } while (gameField[x][y] != 0);

        z = getRandomNumber(10);

        if (z == 9) gameField[x][y] = 4;
        else gameField[x][y] = 2;
    }
}

