package controller;

import model.GameOfLifeModel;
import ui.GameField;

/**
 * Created by etere on 20.12.2015.
 */
public class GameController {
    /**
     * Геттер модели.
     * @return модель
     */
    public GameOfLifeModel getGameOfLifeModel() {
        return gameOfLifeModel;
    }

    /**
     * Модель поля.
     */
    private GameOfLifeModel gameOfLifeModel;

    /**
     * Геттер поля.
     * @return поле
     */
    public GameField getGameField() {
        return gameField;
    }

    /**
     * Игровое поле.
     */
    private GameField gameField;

    /**
     * Конструктор.
     */
    public GameController(){
        this.gameField = new GameField();
        this.gameOfLifeModel = new GameOfLifeModel(50, 50);
        gameField.initialize(gameOfLifeModel);
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
    /**
     * Провека работы симуляции.
     * @return
     */
    public boolean isSimulating() {
        return gameField.getSimThread() != null;
    }
}
