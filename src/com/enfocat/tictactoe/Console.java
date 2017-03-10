/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enfocat.tictactoe;

/**
 *
 * @author mati
 */
public class Console {
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
