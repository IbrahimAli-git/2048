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

    @Override
    public void onKeyPress(Key key) {
        switch (key){
            case UP: moveUp(); break;
            case RIGHT: moveRight(); break;
            case DOWN: moveDown(); break;
            case LEFT: moveLeft(); break;
        }
        if (key == Key.DOWN || key == Key.UP || key == Key.LEFT || key == Key.RIGHT){
            drawScene();
        }
    }

    private void rotateClockwise() {
        for (int i = 0; i < SIDE / 2; i++) {
            for (int j = i; j < SIDE - i - 1; j++) {
                // Swap elements of each cycle in clockwise direction
                int temp = gameField[i][j];
                gameField[i][j] = gameField[SIDE - 1 - j][i];
                gameField[SIDE - 1 - j][i] = gameField[SIDE - 1 - i][SIDE - 1 - j];
                gameField[SIDE - 1 - i][SIDE - 1 - j] = gameField[j][SIDE - 1 - i];
                gameField[j][SIDE - 1 - i] = temp;
            }
        }
    }


    private void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private void moveRight() {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    private void moveLeft() {
        boolean isNewNumberNeeded = false;
        for (int[] row : gameField) {
            boolean wasCompressed = compressRow(row);
            boolean wasMerged = mergeRow(row);
            if (wasMerged) {
                compressRow(row);
            }
            if (wasCompressed || wasMerged) {
                isNewNumberNeeded = true;
            }
        }
        if (isNewNumberNeeded) {
            createNewNumber();
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
