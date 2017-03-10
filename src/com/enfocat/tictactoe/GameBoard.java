package com.enfocat.tictactoe;

enum FICHAS {

    VACIA(" "),
    CRUZ("X"),
    REDONDA("O");

    String valor;

    FICHAS(String ficha) {
        this.valor = ficha;
    }

    @Override
    public String toString() {
        return this.valor;
    }
}

public final class GameBoard {

    public static final int[][] WINNERS = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9},
        {1, 4, 7},
        {2, 5, 8},
        {3, 6, 9},
        {1, 5, 9},
        {3, 5, 7}};

    private final int[] gameBoard;
    private int tilesOccuped = 0;

    public GameBoard() {
        this.gameBoard = new int[9];
        this.resetGameBoard();
    }

    public void resetGameBoard() {
        this.tilesOccuped = 0;
        for (int i = 0; i < 9; i++) {
            this.gameBoard[i] = 0;
        }
    }

    public int getTurnNumber() {
        return this.tilesOccuped + 1;
    }

    public int getTileValue(int coord) {
        return this.gameBoard[coord - 1];
    }

    public boolean isTileValid(int coord) {
        if (coord <= 0) {
            return false;
        }
        return coord <= 9;
    }

    public boolean isTileFree(int coord) {
        return this.gameBoard[coord - 1] == 0;
    }

    public int getZeros() {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            if (this.gameBoard[i] == 0) {
                sum++;
            }
        }
        return sum;
    }

    public void setGameBoard(int move, int playerId) {
        this.gameBoard[move - 1] = playerId;
        this.tilesOccuped++;
    }

    public boolean checkPlayerWins(int playerId) {
        for (int[] jugada : WINNERS) {
            if (this.checkWinnerMove(jugada, playerId)) {
                return true;
            }
        }
        return false;
    }

    public int[] getCornersOfPlayer(int playerId) {
        int[] corners = {1, 3, 7, 9};
        int count = 0;
        for (int i = 0; i < corners.length; i++) {
            if (this.getTileValue(corners[i]) == playerId) {
                count++;
            }
        }
        int[] res = new int[count];
        count = 0;
        for (int i = 0; i < corners.length; i++) {
            if (this.getTileValue(corners[i]) == playerId) {
                res[count++]=corners[i];
            }
        }
        return res;
    }

    private boolean checkWinnerMove(int[] moves, int playerId) {
        if (this.gameBoard[moves[0] - 1] != playerId) {
            return false;
        }
        if (this.gameBoard[moves[0] - 1] == this.gameBoard[moves[1] - 1]
                && this.gameBoard[moves[1] - 1] == this.gameBoard[moves[2] - 1]) {
            return true;
        }
        return false;
    }

    public void draw() {
        System.out.println(this);
    }
    public String getGameBoardTiles()
    {
        String ret = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ret = ret + (i * 3 + j + 1) + " ";
            }
            ret = ret + "\n";
        }
        return ret;
    }
    public void drawCoords() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print((i * 3 + j + 1) + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    @Override
    public String toString() {
        String cadena = "";
        
        cadena += "+---+---+---+\n";
        for (int i = 0; i < 3; i++) {
            cadena += "|";
            for (int j = 0; j < 3; j++) {
                int coordValue = this.getTileValue(i * 3 + j + 1);
                switch (coordValue) {
                    case 1:
                        cadena += " " + FICHAS.CRUZ + " |";
                        break;
                    case 2:
                        cadena += " " + FICHAS.REDONDA + " |";
                        break;
                    default:
                        cadena += " " + FICHAS.VACIA + " |";
                        break;
                }
            }
            cadena += "\n+---+---+---+\n";
        }
        cadena += "\n";
        return cadena;
    }
}
