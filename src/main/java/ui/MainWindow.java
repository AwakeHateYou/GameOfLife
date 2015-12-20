package ui;

import controller.GameController;
import model.GameOfLife;

import javax.swing.*;
import java.awt.*;

/**
 * Created by etere on 18.12.2015.
 */
public class MainWindow extends JFrame{
    /** Высота одного символа. */
    private static final int SYMBOL_HEIGHT = 80;
    JMenuBar menuBar;
    /** Строка по умолчанию. */
    private static final String DEFAULT_STRING = "привет мир ";

    /** Длина строки по умолчанию. */
    private static final int DEFAULT_LINE_LENGTH = 5;

    /** Цвет по умолчанию. */
    private static final Color DEFAULT_COLOR = Color.RED;

    /** Скорость по умолчанию. */
    private static final int DEFAULT_SPEED = 50;
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
        GameController gameController = new GameController();
        menuBar = new JMenuBar();
        menuBar.add(new Menu(gameController));
        this.setJMenuBar(menuBar);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Игра в жизнь");
        pack();
        resize(DEFAULT_LINE_LENGTH);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Инициализирует компоненты окна.
     */
    private void initComponents() {
        getContentPane().setLayout(new FlowLayout());
        gameField = new GameField(DEFAULT_COLOR);
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
    public void resize(int length) {
        Insets insets = getInsets();
        setSize(new Dimension(
                SYMBOL_HEIGHT * length + insets.left + insets.right,
                SYMBOL_HEIGHT * 2 + insets.bottom + insets.top));
    }

    /**
     * Точка входа в программу.
     * @param args входные аргументы.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(MainWindow::new);
    }
}
