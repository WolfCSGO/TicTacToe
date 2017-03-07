package com.enfocat.tictactoe;


import java.util.Scanner;

public class PlayerHuman extends Player {
    
    PlayerHuman(String name, int id)
    {
        super(name,id);
    }
    @Override
    public int play()
    {
        System.out.println("Introduce una coordenada:");
        Scanner teclado = new Scanner(System.in);
        int entrada = teclado.nextInt();
        while(!this.isCoordValid(entrada))
        {
            System.out.println("Error! Introduce una coordenada entre el 1 y el 9:");
            entrada = teclado.nextInt();
        }
        return entrada;
    }
    private boolean isCoordValid(int coord)
    {
        return coord>0 && coord<=9;
    }

    @Override
    public String getType() {
        return "Human";
    }
}
