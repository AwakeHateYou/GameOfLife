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
    /**
     * Остановка симуляции.
     */
    public void stopSimulation() {
        gameField.setSimThread(null);
    }
    /**
     * Запуск симуляции.
     */
    public void startSimulation() {
        if (gameField.getSimThread() == null) {
            gameField.setSimThread(new Thread(gameField));
            gameField.getSimThread().start();
        }
    }
    public boolean isSimulating() {
        return gameField.getSimThread() != null;
    }
}
