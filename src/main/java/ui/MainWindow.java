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
//        setSize(300, 400);
//        setResizable(false);
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
//        runningPanel = new RunningPanel(DEFAULT_COLOR);
//        getContentPane().add(runningPanel);
//        JPanel buttonPanel = new JPanel(new FlowLayout());
//        JButton btnOptions = new JButton("Настройки");
//        btnOptions.addActionListener(e -> optionsWindow.setVisible(true));
//        buttonPanel.add(btnOptions);
//        getContentPane().add(buttonPanel);
    }

    /**
     * Изменяет размер главного окна.
     * @param length длина строки.
     */
//    public void resize(int length) {
//        Insets insets = getInsets();
//        setSize(new Dimension(
//                SYMBOL_HEIGHT * length + insets.left + insets.right,
//                SYMBOL_HEIGHT * 2 + insets.bottom + insets.top));
//    }

    /**
     * Точка входа в программу.
     * @param args входные аргументы.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(MainWindow::new);
    }
}
