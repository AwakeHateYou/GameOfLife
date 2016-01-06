package util;

/**
 * Ошибка при воводе процентов.
 * @author Терентьев Евгений, ИВТ42-БО
 */
public class PercentageNotCorrectException extends Exception {
    /**
     * Конструктор, выводит сообщение.
     */
    public PercentageNotCorrectException(){super("Введено неверное количество процентов");}
}
