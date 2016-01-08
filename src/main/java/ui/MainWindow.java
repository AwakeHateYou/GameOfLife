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
    JMenuBar menuBar;
    /**
     * Управление игрой.
     */
    GameController gameController;

    /**
     * Игровое поле.
     */
    private GameField gameField;
    /**
     * Конструктор.
     */
    public MainWindow() {
        initComponents();
        menuBar = new JMenuBar();
        menuBar.add(new Menu(gameController));
        this.setJMenuBar(menuBar);
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
    }
    /**
     * Точка входа в программу.
     * @param args входные аргументы.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(MainWindow::new);
    }
}
