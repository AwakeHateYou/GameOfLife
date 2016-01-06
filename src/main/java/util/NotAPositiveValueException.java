package util;

/**
 * Ошибка ввода отрицательного значения.
 * @author Терентьев Евгений, ИВТ42-БО
 */
public class NotAPositiveValueException extends Exception {
    public NotAPositiveValueException(){
        super("Вы ввели отрицательное значение.");
    }
}
