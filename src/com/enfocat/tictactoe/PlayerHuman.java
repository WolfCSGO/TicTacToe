package com.enfocat.tictactoe;

import java.util.Scanner;

public class PlayerHuman extends Player {

    private boolean reverse;
    private static int[] reverseInput = {7, 8, 9, 4, 5, 6, 1, 2, 3};

    PlayerHuman(String name, int id) {
        super(name, id);
        this.reverse = false;
    }

    PlayerHuman(String name, int id, boolean reverse) {
        super(name, id);
        this.reverse = true;
    }

    @Override
    public int play() {
        System.out.print("Introduce una coordenada: ");
        Scanner teclado = new Scanner(System.in);
        int entrada = teclado.nextInt();
        while (!this.isCoordValid(entrada)) {
            System.out.print("Error! Introduce una coordenada entre el 1 y el 9: ");
            entrada = teclado.nextInt();
        }
        if (this.reverse) {
            entrada = PlayerHuman.reverseInput[entrada - 1];
        }
        return entrada;
    }

    @Override
    public String printTilesNumber() {
        String ret = "Hola " + this.getName()+", estas son tus coordenadas:\n";
        if (this.reverse) {
            int numbers[] = {7,8,9,4,5,6,1,2,3};
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    ret = ret + numbers[(i * 3 + j)] + " ";
                }
                ret = ret + "\n";
            }
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    ret = ret + (i * 3 + j + 1) + " ";
                }
                ret = ret + "\n";
            }
        }
        return ret;
    }

    private boolean isCoordValid(int coord) {
        return coord > 0 && coord <= 9;
    }

    @Override
    public String getType() {
        return "Human";
    }
}
