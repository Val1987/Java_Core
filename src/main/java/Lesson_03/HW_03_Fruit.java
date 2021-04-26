package Lesson_03;

/*
 *   Тигашов Валерий Евгеньевич
 *   Курс - Java Core
 *   Урок №3 (Обобщения)
 */

import java.util.ArrayList;

public class HW_03_Fruit {}

class Fruit {

    protected float weight;
    Fruit() {
        weight = 0.0f;
    }

    public float getWeight() {
        return weight;
    }
}

class Apple extends Fruit {
    Apple() {
        this.weight = 1.0f;
    }
}

class Orange extends Fruit {
    Orange() {
        this.weight = 1.5f;
    }
}

class Box<T extends Fruit> extends ArrayList<T> {

   public void fillBox(T[] array) {
       for (int i = 0; i < array.length; i++) {
           this.add(array[i]);
       }
   }

   public float getWeight() {
       float weight = 0.0f;
       for (T item: this){
           weight += item.getWeight();
       }
       return weight;
   }

   public boolean compare(Box<T> other) {
       float w1 = this.getWeight();
       float w2 = other.getWeight();
       return w1 == w2;
   }

   public void Empty(Box<T> other) {
       while(this.size() > 0){
           other.add(this.remove(0));
       }
   }
}
