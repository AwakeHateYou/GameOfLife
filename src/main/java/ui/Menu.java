package ui;

import javax.swing.*;

/**
 * Created by etere on 18.12.2015.
 */
public class Menu extends JMenu {
    JMenuItem settings, aboutProgramm;
    SettingsWindow settingsWindow;
    public Menu(){
        initComponents();
        add(settings);
        add(aboutProgramm);
    }
    private void initComponents(){
        setText("Меню");
        settings = new JMenuItem("Настройки");
        aboutProgramm = new JMenuItem("О программе");
        settings.addActionListener(e -> {showSettingsWindow();});
        aboutProgramm.addActionListener(e -> {new JOptionPane().showMessageDialog(this, "Hello!", "About program", JOptionPane.PLAIN_MESSAGE);});
    }
    private void showSettingsWindow(){
        settingsWindow = new SettingsWindow();
        settingsWindow.setVisible(true);
    }
}
