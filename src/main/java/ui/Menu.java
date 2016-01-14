package ui;

import controller.GameController;

import javax.swing.*;

/**
 * Меню с выбором режимов и изменением натроек.
 * @autor Терентьев Евгений
 */
public class Menu extends JMenu {
    /**
     * Пункты меню.
     */
    JMenuItem settings, aboutProgram, start, stop;
    /**
     * Оксно настроек.
     */
    SettingsWindow settingsWindow;
    /**
     * Ссылка на контроллер игрыю
     */
    GameController gameController;

    /**
     * Конструктор
     * @param gameController контроллер игры
     */
    public Menu(GameController gameController){
        this.gameController = gameController;
        initComponents();
        placeComponents();
    }

    /**
     * Инициализация элементов меню.
     */
    private void initComponents(){
        setText("Меню");
        settings = new JMenuItem("Настройки");
        start = new JMenuItem("Запустить в автоматическом режиме");
        stop = new JMenuItem("Остановить");
        stop.setEnabled(false);
        aboutProgram = new JMenuItem("О программе");
        bind();
    }

    /**
     * Размещение элементов меню.
     */
    private void placeComponents(){
        add(settings);
        add(start);
        add(stop);
        add(aboutProgram);
    }

    /**
     * Установка связей пунктов меню.
     */
    private void bind(){
        settings.addActionListener(e -> showSettingsWindow());
        start.addActionListener(e -> startGameAutomatic());
        stop.addActionListener(e -> stopGame());
        aboutProgram.addActionListener(e -> new JOptionPane().showMessageDialog(this, ABOUT_MESSAGE, "About page", JOptionPane.PLAIN_MESSAGE));
    }

    /**
     * Вывод окна с настройками.
     */
    private void showSettingsWindow(){
        settingsWindow = new SettingsWindow(gameController);
        settingsWindow.setVisible(true);
    }

    /**
     * Запуск игры в автоматическом режиме.
     */
    private void startGameAutomatic(){
        gameController.startSimulation();
        stop.setEnabled(true);
        start.setEnabled(false);
    }

    /**
     * Остановка игры.
     */
    private void stopGame(){
        gameController.stopSimulation();
        stop.setEnabled(false);
        start.setEnabled(true);
    }

    /**
     * Установка значений по умолчанию.
     */
    public void prepareManualStep(){
        stop.setEnabled(false);
        start.setEnabled(true);
    }
    /**
     * Информация о программе.
     */
    static final String ABOUT_MESSAGE = "Игра <Жизнь> \nТерентьев Евгений ИВТ-42БО\nпоследняя версия 06.01.2016";
}
