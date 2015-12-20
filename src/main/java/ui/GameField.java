package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by etere on 20.12.2015.
 */
public class GameField extends JPanel{
    /** Цвет строки. */
    private Color color;

    /**
     * Конструктор.
     * @param color цвет строки.
     */
    public GameField(Color color) {
        this.color = color;
        setBackground(Color.BLACK);
    }

    /**
     * Обновляет отображение.
     * @param matrix текущая матрица.
     */
//    public void updatePanel(int[][] matrix) {
//        removeAll();
//        setLayout(new GridLayout(matrix.length, matrix[0].length));
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                add(new Bulb(matrix[i][j] == 1, color));
//            }
//        }
//        revalidate();
//        repaint();
//    }

    /**
     * Устанавливает цвет строки.
     * @param color цвет.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Возвращает цвет строки.
     * @return цвет.
     */
    public Color getColor() {
        return this.color;
    }
}
