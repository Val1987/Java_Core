package Lesson_02;

/*
 *   Тигашов Валерий Евгеньевич
 *   Java Core
 *   Урок №2 (Исключения)
 */

/*
 *  1. Написать метод, на вход которому подается двумерный строковый массив размером 4х4. При подаче массива другого размера
 *  необходимо бросить исключение MyArraySizeException.
 *  2. Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать. Если в каком-то элементе
 *  преобразование не удалось (например, если в ячейке лежит символ или текст вместо числа), надо бросить исключение
 *  MyArrayDataException с детализацией, в какой ячейке неверные данные.
 *  3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException
 *  и вывести результат расчета.
 */

class myArraySizeException extends Exception {
    myArraySizeException(String message) {
        super(message);
    }
}

class myArrayDataException extends Exception {
    myArrayDataException(String message) {
        super(message);
    }
}

public class HW_02_Exceptions {

    static int arrayAccept(String[][] array) throws myArraySizeException {

        boolean size = false;
            if(array.length == 4)
                for (int i = 0; i < array.length; i++) {
                    if(array[i].length != 4) {
                        size = true; break;
                    }
                }
            else
                size = true;
            if(size)
                throw new myArraySizeException("Размер массива не соответствует 4X4");

        int summa = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    int k = Integer.parseInt(array[i][j]);
                    summa += k;
                }
                catch(NumberFormatException ex) {
                    try {
                        throw new myArrayDataException("Не может быть преобразовано: " + i + "," + j + ".");
                    }
                    catch(myArrayDataException ex1)
                    {
                        System.out.println(ex1.getMessage());
                    }

                }
            }

        }
        return summa;

    }

    public static void main(String[] args) {

        String[][] array = {
                {"12", "65", "2", "8"},
                {"6", "F", "4", "1"},
                {"J", "0", "10", "23"},
                {"87", "5", "0", "S"}
        };
        try {
            int summa = arrayAccept(array);
            System.out.println("Сума значений = " + summa);
        }
        catch(myArraySizeException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
