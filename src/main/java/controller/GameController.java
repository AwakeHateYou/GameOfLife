package controller;

import model.GameOfLifeModel;

/**
 * Created by etere on 20.12.2015.
 */
public class GameController {
    public GameOfLifeModel getGameOfLifeModel() {
        return gameOfLifeModel;
    }

    private GameOfLifeModel gameOfLifeModel;
    public GameController(){
        gameOfLifeModel = new GameOfLifeModel(700, 300);
    }
}
