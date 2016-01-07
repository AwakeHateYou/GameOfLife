package ui;

import controller.GameController;

import javax.swing.*;

/**
 * Меню с выбором режимов и изменением натроек.
 */
public class Menu extends JMenu {
    /**
     * Пункты меню.
     */
    JMenuItem settings, aboutProgram, start, auto, manual, step;
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
        add(settings);
        add(start);
        add(aboutProgram);
        add(step);
    }

    /**
     * Размещение этементов меню.
     */
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
        bind();
    }

    /**
     * Установка связей пунктов меню.
     */
    private void bind(){
        settings.addActionListener(e -> showSettingsWindow());
        auto.addActionListener(e -> startGameAutomatic());
        manual.addActionListener(e -> startGameManual());
        step.addActionListener(e -> nextStep());
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
