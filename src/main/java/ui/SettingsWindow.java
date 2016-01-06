package ui;

import controller.GameController;
import util.NotAPositiveValueException;
import util.PercentageNotCorrectException;
import util.WrongAmountNeighboursException;

import javax.swing.*;
import java.awt.*;

/**
 * Created by etere on 18.12.2015.
 */
public class SettingsWindow extends JFrame{
    JSpinner width, height, percentageLiving, updateTimer, reasonLive, reasonDieFrom, reasonDieTo;
    JButton accept;
    public SettingsWindow(GameController gameController){
        setTitle("Настройки");
        setSize(260, 300);
        getContentPane().setLayout(new FlowLayout()); //строк и столбцов
        addComponentsToPane(getContentPane(), gameController);
        accept.addActionListener(e -> setGameSettings(gameController));
        setResizable(false);
    }
    private void addComponentsToPane(Container pane, GameController gameController ){
        JPanel sizePanel = new JPanel();
        sizePanel.add(new JLabel("Размеры поля: "));
        sizePanel.add(width = new JSpinner());
        width.setValue(gameController.getGameOfLifeModel().getWidth());
        sizePanel.add(height = new JSpinner());
        height.setValue(gameController.getGameOfLifeModel().getHeight());
        JPanel livingPanel = new JPanel();
        livingPanel.add(new JLabel("Процент живых клеток: "));
        livingPanel.add(percentageLiving = new JSpinner());
        percentageLiving.setValue(gameController.getGameOfLifeModel().getPercentageLiving());
        JPanel updatePanel = new JPanel();
        updatePanel.add(new JLabel("Интервал обновления: "));
        updatePanel.add(updateTimer = new JSpinner());
        updateTimer.setValue(gameController.getGameField().getUpdateTimer());
        JPanel livePannel = new JPanel();
        livePannel.add(new JLabel("Клеток для жизни: "));
        livePannel.add(reasonLive = new JSpinner());
        reasonLive.setValue(gameController.getGameOfLifeModel().getReasonLive());
        JPanel diePannelFrom= new JPanel();
        diePannelFrom.add(new JLabel("Клеток для смерти от : "));
        diePannelFrom.add(reasonDieFrom = new JSpinner());
        reasonDieFrom.setValue(gameController.getGameOfLifeModel().getReasonDieFor());
        JPanel diePannelTo = new JPanel();
        diePannelTo.add(new JLabel("Клеток для смерти до : "));
        diePannelTo.add(reasonDieTo = new JSpinner());
        reasonDieTo.setValue(gameController.getGameOfLifeModel().getReasonDieTo());
        pane.add(sizePanel);
        pane.add(livingPanel);
        pane.add(livePannel);
        pane.add(diePannelFrom);
        pane.add(diePannelTo);
        pane.add(updatePanel);
        pane.add(accept = new JButton("Принять"));
    }

    /**
     * Установка настроек игры.
     * @param gameController контроллер
     */
    private void setGameSettings(GameController gameController){
        try {
            checkSettingsToPositive();
            checkNeighbours();
            checkPercentege();
            pullSettingsToModel(gameController);
            gameController.getGameField().getPreferredSize();
            this.setVisible(false);
        }catch (Exception e){
            catchException(e);
        }
    }
    /**
     * Отправляет настройки в моделью
     * @param gameController контроллер
     */
    private void pullSettingsToModel(GameController gameController){
        gameController.getGameOfLifeModel().setWidth(Integer.parseInt(width.getValue().toString()));
        gameController.getGameOfLifeModel().setHeight(Integer.parseInt(height.getValue().toString()));
        gameController.getGameOfLifeModel().setPercentageLiving(Double.parseDouble(percentageLiving.getValue().toString()));
        gameController.getGameField().setUpdateTimer(Integer.parseInt(updateTimer.getValue().toString()));
        gameController.getGameOfLifeModel().setReasonLive(Byte.parseByte(reasonLive.getValue().toString()));
        gameController.getGameOfLifeModel().setReasonDieFor(Byte.parseByte(reasonDieFrom.getValue().toString()));
        gameController.getGameOfLifeModel().setReasonDieTo(Byte.parseByte(reasonDieTo.getValue().toString()));
        gameController.getGameOfLifeModel().initFieldContainers();
    }
    /**
     * Проверка на положительность.
     * @throws NotAPositiveValueException
     */
    private void checkSettingsToPositive() throws Exception{
        if(Integer.parseInt(width.getValue().toString()) <= 0
                || Integer.parseInt(height.getValue().toString()) <= 0
                || Double.parseDouble(percentageLiving.getValue().toString()) <= 0
                || Integer.parseInt(updateTimer.getValue().toString()) <= 0
                || Byte.parseByte(reasonLive.getValue().toString()) < 0
                || Byte.parseByte(reasonDieFrom.getValue().toString()) < 0
                || Byte.parseByte(reasonDieTo.getValue().toString()) < 0)
            throw new NotAPositiveValueException();

    }
    /**
     * Проверка на корректность количества соседей.
     * @throws WrongAmountNeighboursException
     */
    private void checkNeighbours() throws Exception{
        if(Byte.parseByte(reasonLive.getValue().toString()) >= 8
                || Byte.parseByte(reasonDieFrom.getValue().toString()) >= 8
                || Byte.parseByte(reasonDieTo.getValue().toString()) >= 8)
            throw new WrongAmountNeighboursException();

    }
    /**
     * Проверка введеных процентов.
     * @throws PercentageNotCorrectException
     */
    private void checkPercentege() throws Exception{
        if(Double.parseDouble(percentageLiving.getValue().toString()) > 100)
            throw new PercentageNotCorrectException();
    }
    /**
     * Ловит все исключения.
     * @param e - NumberFormatException, NotAPositiveValueException, WrongAmpuntNeighboursException.
     */
    private static void catchException(Exception e) {
        new JOptionPane().showMessageDialog(null, e.getMessage(), "Alert", JOptionPane.ERROR_MESSAGE);
    }
}
