package ui;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

/**
 * Главное окно программы. Точка входа.
 */
public class MainWindow extends JFrame{
    /**
     * Панель меню.
     */
    private JMenuBar menuBar;
    /**
     * Управление игрой.
     */
    private GameController gameController;
    /**
     * Конструктор.
     */
    private JButton nextStepButton;
    private Menu menu;
    public MainWindow() {
        initComponents();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Игра в жизнь");
        pack();
        setVisible(true);
    }

    /**
     * Инициализирует компоненты окна.
     */
    private void initComponents() {
        getContentPane().setLayout(new FlowLayout());
        gameController = new GameController();
        getContentPane().add(gameController.getGameField());
        menuBar = new JMenuBar();
        menuBar.add(menu = new Menu(gameController));
        menuBar.add(nextStepButton = new JButton("Сделать 1 шаг"));
        nextStepButton.addActionListener(e -> nextStep());
        this.setJMenuBar(menuBar);
    }

    /**
     * Один шаг симуляции.
     */
    private void nextStep(){
        menu.prepareManualStep();
        gameController.oneStep();
    }
    /**
     * Точка входа в программу.
     * @param args входные аргументы.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(MainWindow::new);
    }
}
