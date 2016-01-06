package ui;

import controller.GameController;

import javax.swing.*;

/**
 * Created by etere on 18.12.2015.
 */
public class Menu extends JMenu {
    JMenuItem settings, aboutProgram, start, auto, manual, step;
    SettingsWindow settingsWindow;
    GameController gameController;
    public Menu(GameController gameController){
        this.gameController = gameController;
        initComponents();
        add(settings);
        add(start);
        add(aboutProgram);
        add(step);
    }
    private void initComponents(){
        setText("Меню");
        settings = new JMenuItem("Настройки");
        start = new JMenu("Управление");
        auto = new JMenuItem("Автоматический режим");
        manual = new JMenuItem("Ручной режим");
        step = new JMenuItem("Сделать шаг");
        step.setVisible(false);
        start.add(auto);
        start.add(manual);
        aboutProgram = new JMenuItem("О программе");
        settings.addActionListener(e -> showSettingsWindow());
        auto.addActionListener(e -> startGameAutomatic());
        manual.addActionListener(e -> startGameManual());
        step.addActionListener(e -> nextStep());
        aboutProgram.addActionListener(e -> new JOptionPane().showMessageDialog(this, ABOUT_MESSAGE, "About page", JOptionPane.PLAIN_MESSAGE));
    }
    private void showSettingsWindow(){
        settingsWindow = new SettingsWindow(gameController);
        settingsWindow.setVisible(true);
    }
    private void startGameAutomatic(){
        step.setVisible(false);
        if(gameController.isSimulating()){
            gameController.stopSimulation();
            auto.setText("Автоматический режим");
        } else {
            gameController.startSimulation();
            auto.setText("Остановить");
        }
    }
    private void startGameManual(){
        step.setVisible(true);
    }
    private void nextStep(){
        gameController.getGameOfLifeModel().simulate();
        gameController.getGameField().repaint();

    }
    static final String ABOUT_MESSAGE = "Игра <Жизнь> \nТерентьев Евгений ИВТ-42БО\nпоследняя версия 06.01.2016";
}
