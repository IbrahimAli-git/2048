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


    private void createGame() {
        createNewNumber();
        createNewNumber();
    }

    private void drawScene() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                setCellColoredNumber(i, j, gameField[j][i]);
            }
        }
    }

    private void createNewNumber() {
        int x, y, z;
        do {
            x = getRandomNumber(SIDE);
            y = getRandomNumber(SIDE);
        } while (gameField[x][y] != 0);

        z = getRandomNumber(10);

        if (z == 9) gameField[x][y] = 4;
        else gameField[x][y] = 2;
    }

    private Color getColorByValue(int value) {
        switch (value){
            case 0 : return Color.BLACK;
            case 2 : return Color.RED;
            case 4 : return Color.BLUE;
            case 8 : return Color.CHOCOLATE;
            case 16 : return Color.FORESTGREEN;
            case 32 : return Color.DARKSLATEGRAY;
            case 64 : return Color.HONEYDEW;
            case 128 : return Color.LIMEGREEN;
            case 256 : return Color.PURPLE;
            case 512 : return Color.VIOLET;
            case 1024 : return Color.SNOW;
            case 2048 : return Color.WHEAT;
            default: return Color.NONE;
        }
    }

    private void setCellColoredNumber(int x, int y, int value) {
       Color color =  getColorByValue(value);
       if (value == 0){
           setCellValueEx(x, y, color, "");
       } else {
           setCellValueEx(x, y, color, String.valueOf(value));
       }
    }

    private boolean compressRow(int[] row){
        int[] tempArray = row.clone();
        boolean isChanged = false;
        
        for (int i = 0; i < row.length - 1; i++) {
            for (int j = 0; j < row.length - 1 - i; j++) {
                int current = row[j];
                int temp = row[j + 1];
                if (current == 0) {
                    row[j] = temp;
                    row[j + 1] = current;
                    isChanged = true;
                }
            }
        }

        return isChanged;
    }

    private boolean mergeRow(int[] row){
        int[] tempArray = row.clone();
        boolean isChanged = false;
        
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] == row[i+1]){
                row[i] += row[i+1];
                row[i+1] = 0;
                isChanged = true;
            }
        }
        
        return isChanged;
    }
}
