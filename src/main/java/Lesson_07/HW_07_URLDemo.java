package Lesson_07;

/*
 *   Тигашов Валерий Евгеньевич
 *   Курс - Java Core
 *   Урок №7 (Networking)
 */

import com.google.gson.Gson;
import java.io.InputStream;
import java.net.*;
import java.util.*;

public class HW_07_URLDemo {

    Gson gson;
    HW_07_WeatherResult weatherResult;

    public HW_07_URLDemo() {

        gson = new Gson();
        weatherResult = new HW_07_WeatherResult();

    }

    public int GetWeather() throws Exception{

        URL url = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=59.95&lon=30.317&lang=ru&units=metric&exclude=current,minutely,hourly,alerts&appid=4b6b844670c148a1c6c74f5fbd42b42b");

        HttpURLConnection hpCon = (HttpURLConnection) url.openConnection();
        int code = hpCon.getResponseCode();
        if(code == 200) {
            InputStream stream = hpCon.getInputStream();
            int length = hpCon.getContentLength();
            byte[] buffer = new byte[length];
            stream.read(buffer);
            String str = new String(buffer);

            weatherResult = gson.fromJson(str, HW_07_WeatherResult.class);


        }
        return code;

    }

    public void printResult() {

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Погода в городе Санкт-Петербург ( " + weatherResult.lat + ", " + weatherResult.lon + " )" );
        for (int i = 0; i < weatherResult.daily.length; i++) {

            System.out.println("--------------------------------------------------------------------------------");
            Date dt = new Date(weatherResult.daily[i].dt*1000);
            System.out.println("Дата " + dt);
            System.out.println("Погода " + weatherResult.daily[i].weather[0].description);
            System.out.println("Температура:");
            System.out.println("Днем: " + weatherResult.daily[i].temp.day + ", Ночью: " + weatherResult.daily[i].temp.night);
            System.out.println("Скорость ветера " + weatherResult.daily[i].wind_speed);
            System.out.println("Влажность " + weatherResult.daily[i].humidity);

        }

    }

}
