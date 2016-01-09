package model;

import java.util.Arrays;
import java.util.Random;

/**
 * Контейнер с игровым полем.
 */
public class GameOfLifeModel {
    /**
     * Ширина
     */
    private int width;

    /**
     * Геттер ширины
     * @return ширина
     */
    public int getWidth() {
        return width;
    }

    /**
     * Геттер высоты
     * @return высота
     */
    public int getHeight() {
        return height;
    }

    /**
     * Высота
     */
    private int height;

    /**
     * Сеттер начального количества заполненых ячеек.
     * @param percentageLiving процент
     */
    public void setPercentageLiving(double percentageLiving) {
        this.percentageLiving = percentageLiving;
    }

    /**
     * Сеттер высоты
     * @param height высота
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Сеттер ширины
     * @param width ширина
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Сеттер количества клеток, необходимых для жизни.
     * @param reasonLive количество клеток
     */
    public void setReasonLive(byte reasonLive) {
        this.reasonLive = reasonLive;
    }

    /**
     * Сеттер колчисева соседей, меньше которого клетка умирает
     * @param reasonDieFor количество клеток
     */
    public void setReasonDieFor(byte reasonDieFor) {
        this.reasonDieFor = reasonDieFor;
    }
    /**
     * Сеттер колчисева соседей, больше которого клетка умирает
     * @param reasonDieTo количество клеток
     */
    public void setReasonDieTo(byte reasonDieTo) {
        this.reasonDieTo = reasonDieTo;
    }
    /**
     * Геттер количества клеток, необходимых для смерти если больше
     * @return количество клеток
     */
    public byte getReasonDieTo() {
        return reasonDieTo;
    }
    /**
     * Геттер количества клеток, необходимых для смерти если меньше
     * @return количество клеток
     */
    public byte getReasonDieFor() {
        return reasonDieFor;
    }
    /**
     * Геттер количества клеток, необходимых для жизни
     * @return количество клеток
     */
    public byte getReasonLive() {
        return reasonLive;
    }
    /**
     * Геттер начального количества заполненых ячеек.
     */
    public double getPercentageLiving() {
        return percentageLiving;
    }

    /**
     * Количество клеток, необходимых для жизни
     */
    private byte reasonLive = 3;
    /**
     * Количество клеток, необходимых для смерти меньше
     */
    private byte reasonDieFor = 2;
    /**
     * Количество клеток, необходимых для смерти больше
     */
    private byte reasonDieTo = 3;


    /**
     * Процент живых клеток в начальной позиции
     */
    private double percentageLiving = 0;
    /**
     * Основное поле
     */
    private byte[] mainField;
    /**
     * Поле для действий
     */
    private byte[] backField;
    /**
     * Соседи в одномерном массиве.
     */
    private int[] neighborOffsets;
    /**
     * Расположение соседей
     */
    private int[][] neighborXYOffsets;

    /**
     * Конструктор
     * @param width ширина
     * @param height высота
     */
    public GameOfLifeModel(int width, int height){
        this.width = width;
        this.height = height;
        initFieldContainers();
    }
    public void clearMainField() {
        Arrays.fill(mainField, (byte) 0);
    }

    /**
     * Установить значение клетки
     * @param x абсцисса
     * @param y ордината
     * @param value значение
     */
    public void setCell(int x, int y, byte value) {
        mainField[y * width + x] = value;
    }

    /**
     * Получить значение клетки
     * @param x абсцисса
     * @param y ордината
     * @return значение
     */
    public byte getCell(int x, int y) {
        return mainField[y * width + x];
    }

    /**
     * Инициализация контейнеров
     */
    public void initFieldContainers(){
        mainField = new byte[width * height];
        backField = new byte[width * height];
        neighborOffsets = new int[] { -width - 1, -width, -width + 1, -1, 1, width - 1, width, width + 1 };
        neighborXYOffsets = new int[][] { { -1, -1 }, { 0, -1 }, { 1, -1 }, { -1, 0 }, { 1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 } };
    }

    /**
     * Заполнение основного поля рандомно, основываясь на заданном проценте
     */
    public void randomizeByPercent() {
        for (int i = 0; i < (int)((height-1) * (width-1) * percentageLiving/100); i++) {
            Random r = new Random();
            int axis = r.nextInt(height - 1);
            int ordinate = r.nextInt(width - 1);
            while (mainField[axis + ordinate*(height-1)] == 1) {
                axis = r.nextInt(height-1);
                ordinate = r.nextInt(width-1);
            }

            mainField[axis + ordinate * (height-1)] = 1;
        }
    }
    public void simulate() {
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int j = y * width + x;
                byte n = countNeighbors(j);
                backField[j] = simulateCell(mainField[j], n);
            }
        }
        for (int x = 0; x < width; x++) {
            int j = width * (height - 1);
            byte n = countBorderNeighbors(x, 0);
            backField[x] = simulateCell(mainField[x], n);
            n = countBorderNeighbors(x, height - 1);
            backField[x + j] = simulateCell(mainField[x + j], n);
        }
        for (int y = 1; y < height - 1; y++) {
            int j = width * y;
            byte n = countBorderNeighbors(0, y);
            backField[j] = simulateCell(mainField[j], n);
            n = countBorderNeighbors(width - 1, y);
            backField[j + width - 1] = simulateCell(mainField[j + width - 1], n);
        }
        byte[] t = mainField;
        mainField = backField;
        backField = t;
    }

    /**
     * Подсчет количества соседей
     * @param j координата клетки
     * @return количество соседей
     */
    private byte countNeighbors(int j) {
        byte n = 0;
        for (int i = 0; i < 8; i++) {
            n += mainField[j + neighborOffsets[i]];
        }
        return n;
    }
    /**
     * Симуляция для одной клетки.
     * @param self собственное состояние клетки: 0/1
     * @param neighbors кол-во соседей
     * @return новое состояние клетки: 0/1
     */
    private byte simulateCell(byte self, byte neighbors){
        return (byte) (self == 0 ? (neighbors >= reasonLive ? 1 : 0) : ((neighbors < reasonDieFor || neighbors > reasonDieTo) ? 0 : 1));
    }
    //
    /**
     * Подсчет соседей для граничных клеток.
     * @param x координата х
     * @param y координата у
     * @return кол-во соседей
     */
    private byte countBorderNeighbors(int x, int y) {
        byte n = 0;
        for (int i = 0; i < 8; i++) {
            int bx = (x + neighborXYOffsets[i][0] + width) % width;
            int by = (y + neighborXYOffsets[i][1] + height) % height;
            n += mainField[by * width + bx];
        }
        return n;
    }

}
