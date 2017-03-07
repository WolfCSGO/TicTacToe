/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enfocat.tictactoe;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JoseM
 */
public class PlayerIAHardcore extends PlayerIA {

    static void shuffleArray(int[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);

            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public PlayerIAHardcore(String name, int id, GameBoard gameBoard) {
        super(name, id, gameBoard);
    }

    private int mustForceMove(int playerId) {
        int rival = playerId == 1 ? 2 : 1;
        if (this.gameBoard.getTileValue(5) == playerId) {
            int[] vertex = {4, 8, 6, 2};
            for (int i = 0; i < vertex.length; i++) {
                if (this.gameBoard.isTileFree(vertex[i])) {
                    for (int[] move : GameBoard.WINNERS) {
                        if (this.moveContains(move, 5)
                                && this.moveContains(move, vertex[i])
                                && !this.moveContainsPlayerTile(move, rival)
                                && !existsIntersection(vertex[i], rival)) {
                            return vertex[i];
                        }
                    }
                }
            }
        } else {
            int[] myCorners = this.gameBoard.getCornersOfPlayer(playerId);
            int[] freeCorners = this.gameBoard.getCornersOfPlayer(0);
            for (int mc : myCorners) {
                for (int fc : freeCorners) {
                    if (mc != fc) {
                        return fc;
                    }
                }
            }
        }
        return 0;
    }
    @Override
    public String getType()
    {
        return "IA Hardcore";
    }

    @Override
    public int play() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(PlayerIAEasy.class.getName()).log(Level.SEVERE, null, ex);
        }
        int betterPos;
        int myself = this.getId();
        int rival = this.getRivalId();
        //Puedo ganar?
        betterPos = this.getWinningTile(myself);
        if (betterPos > 0) {
            return betterPos;
        }
        //Puede el rival ganar?
        betterPos = this.getWinningTile(rival);
        if (betterPos > 0) {
            return betterPos;
        }

        //Mirar si puedo hacer un doblete
        betterPos = this.getFreeIntersection(myself);
        if (betterPos > 0) {
            return betterPos;
        }
        //Evitar que el rival haga un doblete
        betterPos = this.getFreeIntersection(rival);
        if (betterPos > 0) {
            //Mirar si debemos forzar a que coloque una ficha para evitar que haga un doblete
            int forceTile = this.mustForceMove(myself);

            if (forceTile > 0) {
                return forceTile;
            } else {
                return betterPos;
            }

        }
        //Centro desocupado?
        int[] center = {5};
        betterPos = this.makeMoveWithTile(center);
        if (betterPos > 0) {
            return betterPos;
        }
        //Esquinas
        int[] corners = {1, 3, 9, 7};
        shuffleArray(corners);
        betterPos = this.makeMoveWithTile(corners);
        if (betterPos > 0) {
            return betterPos;
        }
        //Lados
        int[] vertex = {4, 8, 6, 2};
        shuffleArray(vertex);
        betterPos = this.makeMoveWithTile(vertex);

        return betterPos;
    }

    private int countTileOf(int playerId) {
        int count = 0;
        for (int i = 1; i <= 9; i++) {
            if (this.gameBoard.getTileValue(i) == playerId) {
                count++;
            }
        }
        return count;
    }

    private int[] getTilesWith(int playerId) {
        int[] playerPositions = new int[this.countTileOf(playerId)];
        int counter = 0;
        for (int i = 1; i <= 9; i++) {
            if (this.gameBoard.getTileValue(i) == playerId) {
                playerPositions[counter++] = i;
            }
        }
        return playerPositions;
    }

    private int getFreeIntersection(int playerId) {
        int[] emptyTile = this.getTilesWith(0);
        for (int tile : emptyTile) {
            if (existsIntersection(tile, playerId)) {
                return tile;
            }
        }
        return 0;
    }

    private boolean existsIntersection(int tile, int playerId) {
        int rival = playerId == 1 ? 2 : 1;
        for (int[] move : GameBoard.WINNERS) {
            if (this.moveContains(move, tile)
                    && !this.moveContainsPlayerTile(move, rival)
                    && this.moveContainsPlayerTile(move, playerId)) {
                for (int[] move2 : GameBoard.WINNERS) {
                    if (move != move2) {
                        if (this.moveContains(move2, tile)
                                && (!this.moveContainsPlayerTile(move2, rival)
                                && this.moveContainsPlayerTile(move2, playerId))) {
                            return true;
                        }

                    }
                }
            }
        }
        return false;
    }

    private boolean moveContainsPlayerTile(int[] move, int playerId) {
        for (int i = 0; i < move.length; i++) {
            int value = this.gameBoard.getTileValue(move[i]);
            if (value == playerId) {
                return true;
            }
        }
        return false;
    }

    private boolean moveContains(int[] move, int tile) {
        for (int i = 0; i < move.length; i++) {
            if (move[i] == tile) {
                return true;
            }
        }
        return false;
    }

    private int getWinningTile(int playerId) {
        for (int[] jugada : GameBoard.WINNERS) {
            if (this.gameBoard.getTileValue(jugada[0]) == playerId
                    && (this.gameBoard.getTileValue(jugada[1]) == playerId)
                    && (this.gameBoard.getTileValue(jugada[2]) == 0)) {
                return jugada[2];
            } else if (this.gameBoard.getTileValue(jugada[0]) == 0
                    && (this.gameBoard.getTileValue(jugada[1]) == playerId)
                    && (this.gameBoard.getTileValue(jugada[2]) == playerId)) {
                return jugada[0];
            } else if (this.gameBoard.getTileValue(jugada[0]) == playerId
                    && (this.gameBoard.getTileValue(jugada[1]) == 0)
                    && (this.gameBoard.getTileValue(jugada[2]) == playerId)) {
                return jugada[1];
            }
        }
        return 0;
    }

    private int makeMoveWithTile(int[] tiles) {
        for (int i = 0; i < tiles.length; i++) {
            if (this.gameBoard.isTileFree(tiles[i])) {
                return tiles[i];
            }
        }
        return 0;
    }

    private int getRivalId() {
        return this.getId() == 1 ? 2 : 1;
    }
}
