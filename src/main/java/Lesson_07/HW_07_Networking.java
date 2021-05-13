package Lesson_07;

/*
 *   Тигашов Валерий Евгеньевич
 *   Курс - Java Core
 *   Урок №7 (Networking)
 */

/*
 *  1)  Реализовать корректный вывод информации о текущей погоде. Создать класс WeatherResponse и десериализовать ответ
 *  сервера. Выводить пользователю только текстовое описание погоды и температуру в градусах цельсия.
 *  2)  Реализовать корректный выход из программы
 *
 *  3)  Реализовать вывод погоды на следующие 5 дней в формате:
 ** В городе CITY на дату DATE ожидается WEATHER_TEXT, температура - TEMPERATURE
 *  где CITY, DATE, WEATHER_TEXT и TEMPERATURE - уникальные значения для каждого дня.
 */

import com.google.gson.Gson;

public class HW_07_Networking {

    public static void main(String[] args) throws Exception {

        HW_07_URLDemo weather = new HW_07_URLDemo();

        weather.GetWeather();
        weather.printResult();

    }

}
