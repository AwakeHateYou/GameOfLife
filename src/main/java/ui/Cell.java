package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by etere on 20.12.2015.
 */
public class Cell extends JPanel {
    /** Отступ. */
    private final static int PADDING = 2;

    /** Клетка живая. */
    private boolean isOn;

    /** Цвеь лампочки. */
    private Color color;

    /**
     * Конструктор.
     * @param isOn критерий включённости.
     * @param color цвет лампочки.
     */
    public Cell(boolean isOn, Color color) {
        this.color = color;
        this.isOn = isOn;
        int width = getWidth();
        int height = getHeight();
        int side = width < height ? width : height;
        setSize(side, side);
    }

    /**
     * Отрисовывает лампочку.
     * @param g ссылка на объект рисования.
     */
    public void paint(Graphics g) {
        g.setColor(isOn ? color : Color.black);
        g.fillOval(0, 0, getWidth() - PADDING, getHeight() - PADDING);
    }
}
