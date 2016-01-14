package ui;

import controller.GameController;
import util.NotAPositiveValueException;
import util.PercentageNotCorrectException;
import util.WrongAmountNeighboursException;

import javax.swing.*;
import java.awt.*;

/**
 * Оксно с настройками приложения.
 * @autor Терентьев Евгений
 */
public class SettingsWindow extends JFrame{
    /**
     * Поля для воода значений.
     */
    JSpinner width, height, percentageLiving, updateTimer, reasonLive, reasonDieFrom, reasonDieTo;
    /**
     * Кнопка подтверждения.
     */
    JButton accept;
    /**
     * Ссылка на контроллер.
     */
    GameController gameController;
    public SettingsWindow(GameController gameController){
        this.gameController = gameController;
        setTitle("Настройки");
        setSize(260, 300);
        getContentPane().setLayout(new FlowLayout());
        initialize();
        accept.addActionListener(e -> {
            setGameSettings(gameController);
            this.dispose();
        });
        setResizable(false);
    }

    /**
     * Размещение элементов окна настроек.
     */
    private void initialize(){

        initSize();
        initLivingPane();
        initLivePane();
        initDiePaneFrom();
        initDiePaneTo();
        initUpdatePane();
        setDefaultValues();
        getContentPane().add(accept = new JButton("Принять"));
    }

    /**
     * Инициализация панели размера.
     */
    private void initSize(){
        JPanel sizePanel = new JPanel();
        sizePanel.add(new JLabel("Размеры поля: "));
        sizePanel.add(width = new JSpinner());
        sizePanel.add(height = new JSpinner());
        height.setValue(gameController.getGameOfLifeModel().getHeight());
        getContentPane().add(sizePanel);
    }

    /**
     * Инициализация панели с процентами живых клеток.
     */
    private void initLivingPane(){
        JPanel livingPanel = new JPanel();
        livingPanel.add(new JLabel("Процент живых клеток: "));
        livingPanel.add(percentageLiving = new JSpinner());
        getContentPane().add(livingPanel);
    }

    /**
     * Инициализация панели с интервалом обновления.
     */
    private void initUpdatePane(){
        JPanel updatePanel = new JPanel();
        updatePanel.add(new JLabel("Интервал обновления: "));
        updatePanel.add(updateTimer = new JSpinner());
        getContentPane().add(updatePanel);
    }

    /**
     * Инициализация панели с количеством клеток для жизни.
     */
    private void initLivePane(){
        JPanel livePanel = new JPanel();
        livePanel.add(new JLabel("Клеток для жизни: "));
        livePanel.add(reasonLive = new JSpinner());
        getContentPane().add(livePanel);
    }

    /**
     * Инициализация панели с количеством клеток для смерти от.
     */
    private void initDiePaneFrom(){
        JPanel diePanelFrom= new JPanel();
        diePanelFrom.add(new JLabel("Клеток для смерти от : "));
        diePanelFrom.add(reasonDieFrom = new JSpinner());
        getContentPane().add(diePanelFrom);
    }

    /**
     * Инициализация панели с количеством клеток для до.
     */
    private void initDiePaneTo(){
        JPanel diePanelTo = new JPanel();
        diePanelTo.add(new JLabel("Клеток для смерти до : "));
        diePanelTo.add(reasonDieTo = new JSpinner());
        getContentPane().add(diePanelTo);
    }

    /**
     * Установить значения по умолчанию.
     */
    private void setDefaultValues(){
        width.setValue(gameController.getGameOfLifeModel().getWidth());
        percentageLiving.setValue(gameController.getGameOfLifeModel().getPercentageLiving());
        updateTimer.setValue(gameController.getGameField().getUpdateTimer());
        reasonLive.setValue(gameController.getGameOfLifeModel().getReasonLive());
        reasonDieFrom.setValue(gameController.getGameOfLifeModel().getReasonDieFor());
        reasonDieTo.setValue(gameController.getGameOfLifeModel().getReasonDieTo());
    }

    /**
     * Установка настроек игры.
     * @param gameController контроллер
     */
    private void setGameSettings(GameController gameController){
        try {
            checkSettingsToPositive();
            checkNeighbours();
            checkPercentage();
            pullSettingsToModel(gameController);
            gameController.getGameOfLifeModel().randomizeByPercent();
            gameController.getGameField().repaint();
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
        gameController.getGameField().setPreferredSize(getPreferredSize());
    }
    /**
     * Проверка на положительность.
     * @throws NotAPositiveValueException
     */
    private void checkSettingsToPositive() throws Exception{
        if(Integer.parseInt(width.getValue().toString()) <= 0
                || Integer.parseInt(height.getValue().toString()) <= 0
                || Double.parseDouble(percentageLiving.getValue().toString()) < 0
                || Integer.parseInt(updateTimer.getValue().toString()) < 0
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
    private void checkPercentage() throws Exception{
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
