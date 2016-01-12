package ui;

import model.GameOfLifeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Отображение игрового поля.
 */
public class GameField extends JPanel implements Runnable{
    /**
     * Установить поток исполения
     * @param simThread поток
     */
    public void setSimThread(Thread simThread) {
        this.simThread = simThread;
    }

    /**
     * Получить поток исполенения
     * @return поток
     */
    public Thread getSimThread() {
        return simThread;
    }

    /**
     * Поток выполнения
     */
    private Thread simThread = null;
    /**
     * Модель игры
     */
    private GameOfLifeModel lifeModel = null;

    /**
     * Получить таймер обновления
     * @return таймер обновления
     */
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
    private static final Color dead = new Color(0x505050);
    /**
     * Цвет живой клетки.
     */
    private static final Color live = new Color(0xFFFFFF);
    /**
     * Конструктор, редактор поля.
     */
    public GameField() {
        setBackground(Color.BLACK);
        MouseAdapter ma = new MouseAdapter() {
            private boolean pressedLeft = false;
            private boolean pressedRight = false;

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
             * @param e ивент мыши
             */
            private void setCell(MouseEvent e) {
                if (lifeModel != null) {
                    synchronized (lifeModel) {
                        int x = e.getX() / (cellSize + cellGap);
                        int y = e.getY() / (cellSize + cellGap);
                        if (x >= 0 && y >= 0 && x < lifeModel.getWidth() && y < lifeModel.getHeight()) {
                            if (pressedLeft == true) {
                                lifeModel.setCell(x, y, (byte) 1);
                                repaint();
                            }
                            if (pressedRight == true) {
                                lifeModel.setCell(x, y, (byte) 0);
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

    /**
     * Инициалзация игровой модели.
     * @param model
     */
    public void initialize(GameOfLifeModel model) {
        this.lifeModel = model;
    }

    /**
     * Установка таймера обновления поля
     * @param updateTimer таймер
     */
    public void setUpdateTimer(int updateTimer) {
        this.updateTimer = updateTimer;
    }

    @Override
    public void run() {
        repaint();
        while (simThread != null) {
                try {
                   Thread.sleep(updateTimer);
                } catch (InterruptedException e) {
                }
            synchronized (lifeModel) {
                lifeModel.simulate();
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
        if (lifeModel != null) {
            Insets inset = getInsets();
            return new Dimension((cellSize + cellGap) * lifeModel.getWidth() + cellGap + inset.left + inset.right,
                    (cellSize + cellGap) * lifeModel.getHeight() + cellGap + inset.top + inset.bottom);
        } else {
            return new Dimension(100, 100);
        }
    }
    /*
     * Прорисовка содержимого панели.
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (lifeModel != null) {
            synchronized (lifeModel) {
                super.paintComponent(g);
                Insets b = getInsets();
                for (int y = 0; y < lifeModel.getHeight(); y++) {
                    for (int x = 0; x < lifeModel.getWidth(); x++) {
                        byte c = lifeModel.getCell(x, y);
                        g.setColor(c == 1 ? live : dead);
                        g.fillRect(b.left + cellGap + x * (cellSize + cellGap), b.top + cellGap + y
                                * (cellSize + cellGap), cellSize, cellSize);
                    }
                }
            }
        }
    }
}
