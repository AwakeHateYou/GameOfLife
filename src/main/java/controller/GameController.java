package controller;

import model.GameOfLifeModel;
import ui.GameField;

/**
 * Created by etere on 20.12.2015.
 */
public class GameController {
    public GameOfLifeModel getGameOfLifeModel() {
        return gameOfLifeModel;
    }

    private GameOfLifeModel gameOfLifeModel;

    public GameField getGameField() {
        return gameField;
    }

    private GameField gameField;
    public GameController(GameField gameField){
        this.gameField = gameField;
        this.gameOfLifeModel = new GameOfLifeModel(50, 50);
    }
}
