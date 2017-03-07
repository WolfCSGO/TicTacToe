package com.enfocat.tictactoe;

public abstract class Player {

    private String name;
    private int id;
    private int victorias;

    Player(String name, int id) {
        this.name = name;
        this.id = id;
        this.victorias = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public int getVictories()
    {
        return this.victorias;
    }
    public void incrementaVictoria()
    {
        this.victorias++;
    }
    public abstract String getType();

    public abstract int play();
}
