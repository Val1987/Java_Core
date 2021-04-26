package Lesson_03;

/*
 *   Тигашов Валерий Евгеньевич
 *   Курс - Java Core
 *   Урок №3 (Обобщения)
*/

/*
 *  1) Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
 *  2) Задача:
 *  >   Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
 *  >   Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну
 *  коробку нельзя сложить и яблоки, и апельсины;
 *  >   Для хранения фруктов внутри коробки можно использовать ArrayList;
 *  >   Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество:
 *  вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
 *  >   Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую подадут в
 *  compare() в качестве параметра. true – если их массы равны, false в противоположном случае. Можно сравнивать
 *  коробки с яблоками и апельсинами;
 *  >   Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним про сортировку фруктов:
 *  нельзя яблоки высыпать в коробку с апельсинами. Соответственно, в текущей коробке фруктов не остается, а в другую
 *  перекидываются объекты, которые были в первой;
 *  >   Не забываем про метод добавления фрукта в коробку.**
*/

import java.util.ArrayList;

public class HW_03_Generalizations {

    public static void main(String[] args) {

        Box<Apple> apples = new Box<Apple>();
        Apple[] arrayApp = new Apple[9];
        for (int i = 0; i < arrayApp.length; i++) {
            arrayApp[i] = new Apple();
        }
        apples.fillBox(arrayApp);

        Box<Orange> oranges = new Box<Orange>();
        Orange[] arrayOrg = new Orange[15];
        for (int i = 0; i < arrayOrg.length; i++) {
            arrayOrg[i] = new Orange();
        }
        oranges.fillBox(arrayOrg);

        Box<Apple> box2 = new Box<Apple>();
        Apple[] arrayApp2 = new Apple[9];
        for (int i = 0; i < arrayApp2.length; i++) {
            arrayApp2[i] = new Apple();
        }
        box2.fillBox(arrayApp2);

//        apples.Empty(box2); // Запуск функции "Пересыпать Фрукты".

        //**************************************************************

        System.out.println("Вес коробки яблок = " + apples.getWeight());
        System.out.println("Вес коробки апельсинов = " + oranges.getWeight());
        System.out.println("Вес коробки яблок №2 = " + box2.getWeight());

    }

}
