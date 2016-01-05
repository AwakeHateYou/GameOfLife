package ui;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by etere on 18.12.2015.
 */
public class SettingsWindow extends JFrame{
    JSpinner width, height, percentageLiving, updateTimer, reasonLive, reasonDie;
    JButton accept;
    public SettingsWindow(GameController gameController){
        setTitle("Настройки");
        setSize(260, 250);
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
        //percentageLiving.setValue();
        JPanel updatePanel = new JPanel();
        updatePanel.add(new JLabel("Интервал обновления: "));
        updatePanel.add(updateTimer = new JSpinner());
        updateTimer.setValue(gameController.getGameField().getUpdateTimer());
        JPanel livePannel = new JPanel();
        livePannel.add(new JLabel("Клеток для жизни: "));
        livePannel.add(reasonLive = new JSpinner());
        JPanel diePannel = new JPanel();
        diePannel.add(new JLabel("Клеток для смерти: "));
        diePannel.add(reasonDie = new JSpinner());
        pane.add(sizePanel);
        pane.add(livingPanel);
        pane.add(livePannel);
        pane.add(diePannel);
        pane.add(updatePanel);
        pane.add(accept = new JButton("Принять"));
    }
    private void setGameSettings(GameController gameController){
        gameController.getGameOfLifeModel().setWidth(Integer.parseInt(width.getValue().toString()));
        gameController.getGameOfLifeModel().setHeight(Integer.parseInt(height.getValue().toString()));
        gameController.getGameOfLifeModel().setPercentageLiving(Double.parseDouble(percentageLiving.getValue().toString()));
        gameController.getGameField().setUpdateTimer(Integer.parseInt(updateTimer.getValue().toString()));
        gameController.getGameOfLifeModel().initFieldContainers();
        gameController.getGameField().getPreferredSize();
        this.setVisible(false);
    }
}
