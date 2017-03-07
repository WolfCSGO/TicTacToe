/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enfocat.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JoseM
 */
public class PlayerIAEasy extends PlayerIA {

    public PlayerIAEasy(String name, int id, GameBoard gameBoard) {
        super(name, id, gameBoard);
    }

    private List<Integer> getFreeTiles() {
        List<Integer> lista = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (this.gameBoard.isTileFree(i)) {
                lista.add(i);
            }
        }
        return lista;
    }

    @Override
    public String getType() {
        return "IA Easy";
    }

    @Override
    public int play() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(PlayerIAEasy.class.getName()).log(Level.SEVERE, null, ex);
        }
        Random r = new Random();
        List<Integer> lista = getFreeTiles();
        int pos = r.nextInt(lista.size());
        return lista.get(pos);
    }

}
