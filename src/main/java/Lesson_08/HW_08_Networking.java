package Lesson_08;

/*
 *   Тигашов Валерий Евгеньевич
 *   Курс - Java Core
 *   Урок №8 (Networking)
 */

public class HW_08_Networking {

    public static void main(String[] args) throws Exception {

        HW_08_URLDemo weather = new HW_08_URLDemo();

        weather.GetWeather();
        weather.printResult();
        weather.setData();
        weather.getData();

    }

}
