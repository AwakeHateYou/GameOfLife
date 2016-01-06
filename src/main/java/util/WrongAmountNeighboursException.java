package util;

/**
 * Ошибка ввода количества соседей.
 * @author Терентьев Евгений, ИВТ42-БО
 */
public class WrongAmountNeighboursException extends Exception {
    /**
     * Конструктор, выводит сообщение.
     */
    public WrongAmountNeighboursException(){
        super("Количесво соседей у каждой клетки от 0 до 8.");
    }
}
