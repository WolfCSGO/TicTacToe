package com.enfocat.tictactoe;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mati
 */
public class Programa {

    private static Player[] jugadores = {new PlayerHuman("Player 1", 1),
        new PlayerHuman("Player 2", 2)};
    private static boolean rematch = false;
    private static Game juego;

    public static void main(String[] args) {
        juego = new Game(jugadores[0], jugadores[1]);
        //juego.play();
        imprimeMenu();
        int opcion = leerEntero();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    choosePlayer(1);
                    break;
                case 2:
                    choosePlayer(2);
                    break;
                case 3:
                    juego.play();
                    System.out.println(jugadores[0].getName() + " [" + jugadores[0].getVictories() + "] - ["
                            + jugadores[1].getVictories() + "] " + jugadores[1].getName());
                    askForRematch();
                    break;
                default:
                    System.out.println("Escoge una opción válida por favor.");
                    break;
            }
            if (!rematch) {
                imprimeMenu();
                opcion = leerEntero();
            }
        }
    }

    private static void choosePlayer(int playerNumber) {
        System.out.println();
        imprimeMenuChoosePlayer(playerNumber);
        int opcion = leerEntero();
        while (opcion != 1 && opcion != 2 && opcion != 3) {
            imprimeMenuChoosePlayer(playerNumber);
            opcion = leerEntero();
        }
        switch (opcion) {
            case 1:
                createHumanPlayer(playerNumber);
                break;
            case 2:
                jugadores[playerNumber - 1] = juego.getNewIAEasy("IA Easy " + playerNumber, playerNumber);
                break;
            case 3:
                jugadores[playerNumber - 1] = juego.getNewIAHardcore("IA Hardcore " + playerNumber, playerNumber);
                break;
        }

        juego.setPlayer(playerNumber, jugadores[playerNumber - 1]);
    }

    private static int leerEntero() {
        System.out.print("\nSeleccionar una opción: ");
        Scanner teclado = new Scanner(System.in);
        return teclado.nextInt();
    }

    private static void imprimeControles() {
        System.out.println("Quieres invertir los controles?");
        System.out.println("1. Si");
        System.out.println("2. Si");
    }

    private static void createHumanPlayer(int playerNumber) {
        imprimeControles();
        int opcion = leerEntero();
        while (opcion != 1 && opcion != 2) {
            System.out.println("Escoge 1 o 2");
            imprimeControles();
            opcion = leerEntero();
        }
        jugadores[playerNumber - 1] = juego.getNewPlayerHuman("Jugador " + playerNumber, playerNumber, opcion == 1);
    }

    private static void askForRematch() {
        rematch = false;
        imprimeMenuRematch();
        int opcion = leerEntero();
        while (opcion != 1 && opcion != 2) {
            imprimeMenuRematch();
            opcion = leerEntero();
        }
        if (opcion == 1) {
            rematch = true;
        }
    }

    private static void imprimeMenu() {
        Console.clear();
        System.out.println("1. Escoger Jugador 1: [" + jugadores[0].getType() + "]");
        System.out.println("2. Escoger Jugador 2: [" + jugadores[1].getType() + "]");
        System.out.println("3. Jugar");
        System.out.println("-------------");
        System.out.println("0. Salir");
    }

    private static void imprimeMenuChoosePlayer(int playerNumber) {
        Console.clear();
        System.out.println("Que quieres que sea jugador " + playerNumber);
        System.out.println("1. Humano");
        System.out.println("2. IA Facil");
        System.out.println("3. IA Díficil");
    }

    private static void imprimeMenuRematch() {
        System.out.println("¿Quieres volver a jugar?");
        System.out.println("1. Si");
        System.out.println("2. No");
    }
}
