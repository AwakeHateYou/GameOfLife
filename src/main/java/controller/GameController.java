package controller;

import model.GameOfLife;

/**
 * Created by etere on 20.12.2015.
 */
public class GameController {
    public GameOfLife getGameOfLife() {
        return gameOfLife;
    }

    private GameOfLife gameOfLife;
    public GameController(){
        gameOfLife = new GameOfLife();
    }
}
