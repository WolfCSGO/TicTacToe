/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enfocat.tictactoe;

/**
 *
 * @author JoseM
 */
public abstract class PlayerIA extends Player{
    GameBoard gameBoard;
    public PlayerIA(String name, int id, GameBoard gameBoard) {
        super(name, id);
        this.gameBoard = gameBoard;
    }
    
}
