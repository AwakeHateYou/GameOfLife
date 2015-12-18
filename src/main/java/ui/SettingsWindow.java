package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by etere on 18.12.2015.
 */
public class SettingsWindow extends JFrame{
    //JLabel sizeNLabel = new JLabel("Egor");
    JSpinner sizeN, sizeM;
    public SettingsWindow(){
        setTitle("Настройки");
        setSize(300, 300);
        getContentPane().setLayout(new GridLayout(10, 2)); //строка и столбцов
        add(sizeN = new JSpinner());
        add(sizeN = new JSpinner());
      //  add(sizeNLabel);

        //add(settingsFrame);
    }
}
