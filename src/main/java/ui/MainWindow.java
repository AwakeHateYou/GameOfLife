package ui;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by etere on 18.12.2015.
 */
public class MainWindow extends JFrame{

    JMenuBar menuBar;
    GameController gameController;

    /**
     * Игровое поле.
     */
    private GameField gameField;

    /** Ссылка на окно настроек. */
  //  private OptionsWindow optionsWindow;

    /** Ссылка на панель, отрисовывающую строку. */
   // private RunningPanel runningPanel;

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
        gameField = new GameField();
        gameController = new GameController(gameField);
        gameField.initialize(gameController.getGameOfLifeModel());
        getContentPane().add(gameField);
    }

    /**
     * Изменяет размер главного окна.
     * @param length длина строки.
     */
    public void resize() {
        Insets insets = getInsets();
        setSize(gameController.getGameField().getPreferredSize());
    }

    /**
     * Точка входа в программу.
     * @param args входные аргументы.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(MainWindow::new);
    }
}
