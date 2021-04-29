package Lesson_04;

/*
 *   Тигашов Валерий Евгеньевич
 *   Курс - Java Core
 *   Урок №4 (Collection)
 */

/*
 *  1. Написать два метода, которые:
 *  a. Создают одномерный длинный массив, например:
 *  static final int size = 10 000 000;
 *  static final int h = size / 2;
 *  float[] arr = new float[size].
 *  b. Заполняют этот массив единицами.
 *  c. Засекают время выполнения: long a = System.currentTimeMillis().
 *  d. Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
 *  arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2)).
 *  e. Проверяется время окончания метода System.currentTimeMillis().
 *  f. В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).
 *
 *  Отличие первого метода от второго:
 *  первый просто бежит по массиву и вычисляет значения;
 *  второй разбивает один массив на два, в двух потоках высчитывает новые значения и потом снова объединяет эти массивы.
 *  ### Пример деления одного массива на два:
 *  System.arraycopy(arr, 0, a1, 0, h);
 *  System.arraycopy(arr, h, a2, 0, h). ### Пример обратной склейки:
 *  System.arraycopy(a1, 0, arr, 0, h);
 *  System.arraycopy(a2, 0, arr, h, h). ### Примечание:
 *  System.arraycopy() — копирует данные из одного массива в другой:
 *  System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение,
 *  откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем).
 *  ### Уточним замеры времени.
 *  Для первого метода надо считать время только на цикл расчета:
 *  for (int i = 0; i < size; i++)
 *  { arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2)); }
 *  ### Для второго метода замеряете время разбивки массива на два, просчета каждого из двух массивов и склейки.
*/

public class HW_04_Collections {

    static final int size = 10000000;
    static float[] arr = new float[size];

    public static void fillArray_01() {

        for (int i = 0; i < size; i++) {
            arr[i] = 1.0f;
        }

        long a = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.println(System.currentTimeMillis() - a);

    }

    public static void fillArray_02() {

        for (int i = 0; i < size; i++) {
            arr[i] = 1.0f;
        }

        float[] arr1 = new float[size/2];
        float[] arr2 = new float[size/2];

        System.arraycopy(arr, 0, arr1, 0, size/2);
        System.arraycopy(arr, size/2, arr2, 0, size/2);

        ArrayThread thread_1 = new ArrayThread(arr1, "Thread-1");
        ArrayThread thread_2 = new ArrayThread(arr2, "Thread-2");

        try {
            thread_1.join();
            thread_2.join();
        }
        catch (InterruptedException e) {
            System.out.println("Главный поток прерван!");
        }

        System.arraycopy(arr1, 0, arr, 0, size/2);
        System.arraycopy(arr2, 0, arr, size/2, size/2);
        long tm = thread_1.time + thread_2.time;
        System.out.println("Summary time is " + tm);

    }

    public static void main(String[] args) {

        fillArray_01();
        System.out.println("------------");
        fillArray_02();

    }

}

class ArrayThread extends Thread {

    private float[] array;
    long time;

    ArrayThread(float[]array, String name) {
        super(name);
        this.array = array;
        time = 0L;
        start();
    }

    public void run() {

        long a = System.currentTimeMillis();

        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        time = System.currentTimeMillis() - a;
        System.out.println(getName() + " Time = " + time);

    }

}
