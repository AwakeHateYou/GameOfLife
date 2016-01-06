package ui;

import model.GameOfLifeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by etere on 20.12.2015.
 */
public class GameField extends JPanel implements Runnable{

    private Thread simThread = null;
    private GameOfLifeModel life = null;

    public int getUpdateTimer() {
        return updateTimer;
    }

    /**
     * Задержка в мс между шагами симуляции.
     */
    private int updateTimer = 100;
    /**
     * Размер клетки на экране.
     */
    private int cellSize = 8;
    /**
     * Промежуток между клетками.
     */
    private int cellGap = 1;
    /**
     * Цвет мертвой клетки.
     */
    private static final Color c0 = new Color(0x505050);
    /**
     * Цвет живой клетки.
     */
    private static final Color c1 = new Color(0xFFFFFF);
    /**
     * Включен автоматический режим.
     */
    public GameField() {
        setBackground(Color.BLACK);

        // редактор поля
        MouseAdapter ma = new MouseAdapter() {
            private boolean pressedLeft = false; // нажата левая кнопка мыши
            private boolean pressedRight = false; // нажата правая кнопка мыши

            @Override
            public void mouseDragged(MouseEvent e) {
                setCell(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    pressedLeft = true;
                    pressedRight = false;
                    setCell(e);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    pressedLeft = false;
                    pressedRight = true;
                    setCell(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    pressedLeft = false;
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    pressedRight = false;
                }
            }

            /**
             * Устанавливает/стирает клетку.
             *
             * @param e
             */
            private void setCell(MouseEvent e) {
                if (life != null) {
                    synchronized (life) {
                        // рассчитываем координаты клетки, на которую указывает
                        // курсор мыши
                        int x = e.getX() / (cellSize + cellGap);
                        int y = e.getY() / (cellSize + cellGap);
                        if (x >= 0 && y >= 0 && x < life.getWidth() && y < life.getHeight()) {
                            if (pressedLeft == true) {
                                life.setCell(x, y, (byte) 1);
                                repaint();
                            }
                            if (pressedRight == true) {
                                life.setCell(x, y, (byte) 0);
                                repaint();
                            }
                        }
                    }
                }
            }
        };
        addMouseListener(ma);
        addMouseMotionListener(ma);
    }

//    public GameOfLifeModel getLifeModel() {
//        return life;
//    }

    public void initialize(GameOfLifeModel model) {
        this.life = model;
    }

    public void setUpdateTimer(int updateTimer) {
        this.updateTimer = updateTimer;
    }

    /**
     * Запуск симуляции.
     */
    public void startSimulation() {
        if (simThread == null) {
            simThread = new Thread(this);
            simThread.start();
        }
    }

    /**
     * Остановка симуляции.
     */
    public void stopSimulation() {
        simThread = null;
    }

    public boolean isSimulating() {
        return simThread != null;
    }

    @Override
    public void run() {
        repaint();
        while (simThread != null) {
                try {
                   Thread.sleep(updateTimer);
                } catch (InterruptedException e) {
                }

            // синхронизация используется для того, чтобы метод paintComponent
            // не выводил на экран
            // содержимое поля, которое в данный момент меняется
            synchronized (life) {
                life.simulate();
            }
            repaint();
        }
        repaint();
    }

    /*
     * Возвращает размер панели с учетом размера поля и клеток.
     */
    @Override
    public Dimension getPreferredSize() {
        if (life != null) {
            Insets b = getInsets();
            return new Dimension((cellSize + cellGap) * life.getWidth() + cellGap + b.left + b.right,
                    (cellSize + cellGap) * life.getHeight() + cellGap + b.top + b.bottom);
        } else {
            return new Dimension(100, 100);
        }
    }

    /*
     * Прорисовка содержимого панели.
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (life != null) {
            synchronized (life) {
                super.paintComponent(g);
                Insets b = getInsets();
                for (int y = 0; y < life.getHeight(); y++) {
                    for (int x = 0; x < life.getWidth(); x++) {
                        byte c = life.getCell(x, y);
                        g.setColor(c == 1 ? c1 : c0);
                        g.fillRect(b.left + cellGap + x * (cellSize + cellGap), b.top + cellGap + y
                                * (cellSize + cellGap), cellSize, cellSize);
                    }
                }
            }
        }
    }
}
