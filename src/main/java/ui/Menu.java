package ui;

import controller.GameController;

import javax.swing.*;

/**
 * Created by etere on 18.12.2015.
 */
public class Menu extends JMenu {
    JMenuItem settings, aboutProgram, start;
    SettingsWindow settingsWindow;
    GameController gameController;
    public Menu(GameController gameController){
        this.gameController = gameController;
        initComponents();
        add(settings);
        add(start);
        add(aboutProgram);
    }
    private void initComponents(){
        setText("Меню");
        settings = new JMenuItem("Настройки");
        start = new JMenuItem("Запустить");
        aboutProgram = new JMenuItem("О программе");
        settings.addActionListener(e -> showSettingsWindow());
        start.addActionListener(e -> startGame());
        aboutProgram.addActionListener(e -> new JOptionPane().showMessageDialog(this, "Hello!", "About program", JOptionPane.PLAIN_MESSAGE));
    }
    private void showSettingsWindow(){
        settingsWindow = new SettingsWindow(gameController);
        settingsWindow.setVisible(true);
    }
    private void startGame(){

    }
}
