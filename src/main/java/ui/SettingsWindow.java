package ui;

import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;

/**
 * Created by etere on 18.12.2015.
 */
public class SettingsWindow extends JFrame{
    //JLabel sizeNLabel = new JLabel("Egor");
    JSpinner sizeN, sizeM, percentageLiving, updateTimer;
    JButton accept;
    public SettingsWindow(){
        setTitle("Настройки");
        setSize(260, 180);
        getContentPane().setLayout(new FlowLayout()); //строк и столбцов
        addComponentsToPane(getContentPane());
        setResizable(false);
    }
    private void addComponentsToPane(Container pane){
        JPanel sizePanel = new JPanel();
        sizePanel.add(new JLabel("Размеры поля: "));
        sizePanel.add(sizeN = new JSpinner());
        sizePanel.add(sizeM = new JSpinner());
        pane.add(sizePanel);
        JPanel livingPanel = new JPanel();
        livingPanel.add(new JLabel("Процент живых клеток: "));
        livingPanel.add(percentageLiving = new JSpinner());
        pane.add(livingPanel);
        JPanel updatePanel = new JPanel();
        updatePanel.add(new JLabel("Интервал обновления: "));
        updatePanel.add(updateTimer = new JSpinner());
        add(updatePanel);
        add(accept = new JButton("Принять"));

    }
}
