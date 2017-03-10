package com.enfocat.tictactoe;

import java.util.Random;

public class Game {

    GameBoard gameBoard;
    Player players[];
    Player winner;

    public Game() {
        this.init();
    }

    public Game(Player j1, Player j2) {
        this.init();
        this.players[0] = j1;
        this.players[1] = j2;
    }

    public void setPlayer(int playerNumber, Player j) {
        this.players[playerNumber - 1] = j;
    }

    private void init() {

        this.gameBoard = new GameBoard();
        this.players = new Player[2];
        this.winner = null;
    }

    public Player getNewPlayerHuman(String name, int id, boolean invertirControles) {
        return new PlayerHuman(name, id, invertirControles);
    }

    public Player getNewIAEasy(String name, int id) {
        return new PlayerIAEasy(name, id, this.gameBoard);
    }

    public Player getNewIAHardcore(String name, int id) {
        return new PlayerIAHardcore(name, id, this.gameBoard);
    }

    private void resetGame() {
        this.gameBoard.resetGameBoard();
        this.winner = null;
    }

    public void play() {
        Console.clear();
        this.resetGame();
        //System.out.println("Estas son las coordenadas del tablero:");
        //System.out.println(this.gameBoard.getGameBoardTiles());
        // System.out.println("");
        for (Player j : this.players) {
            System.out.println(j.printTilesNumber());
        }
        Random r = new Random();
        int whosTurn = r.nextInt(2);
        while (this.gameBoard.getZeros() > 0 && !this.existsWinner()) {
            Player current = this.players[whosTurn];
            System.out.println("> Es el turno de " + current.getName());
            int jugada = current.play();
            while (!this.gameBoard.isTileValid(jugada)) {
                System.out.println("Introduce una casilla vacia!");
                jugada = current.play();
            }
            this.gameBoard.setGameBoard(jugada, current.getId());
            System.out.println();
            System.out.println(this.gameBoard.toString());
            if (this.gameBoard.checkPlayerWins(current.getId())) {
                this.setWinner(current);
            }
            whosTurn = (whosTurn + 1) % 2;
        }
        if (!this.existsWinner()) {
            System.out.println("> Ha sido un empate.");
        } else {
            System.out.println("El ganador es " + this.getWinner().getName());
        }
    }

    private Player getWinner() {
        return this.winner;
    }

    public int getWinnerId() {
        if (!this.existsWinner()) {
            return 0;
        }
        return this.getWinner().getId();
    }

    private void setWinner(Player winner) {
        this.winner = winner;
        this.winner.incrementaVictoria();
    }

    private boolean existsWinner() {
        return this.winner != null;
    }
}
