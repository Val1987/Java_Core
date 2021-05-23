package Lesson_08;

/*
 *   Тигашов Валерий Евгеньевич
 *   Курс - Java Core
 *   Урок №8 (Networking)
 */

import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class HW_08_URLDemo {

    Gson gson;
    HW_08_WeatherResult weatherResult;

    public HW_08_URLDemo() {

        gson = new Gson();
        weatherResult = new HW_08_WeatherResult();

    }

    public int GetWeather() throws Exception {

        URL url = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=59.95&lon=30.317&lang=ru&units=metric&exclude=current,minutely,hourly,alerts&appid=4b6b844670c148a1c6c74f5fbd42b42b");

        HttpURLConnection hpCon = (HttpURLConnection) url.openConnection();
        int code = hpCon.getResponseCode();
        if (code == 200) {
            InputStream stream = hpCon.getInputStream();
            int length = hpCon.getContentLength();
            byte[] buffer = new byte[length];
            stream.read(buffer);
            String str = new String(buffer);

            weatherResult = gson.fromJson(str, HW_08_WeatherResult.class);


        }
        return code;

    }

    public void printResult() {

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Погода в городе Санкт-Петербург ( " + weatherResult.lat + ", " + weatherResult.lon + " )");
        for (int i = 0; i < weatherResult.daily.length; i++) {

            System.out.println("--------------------------------------------------------------------------------");
            Date dt = new Date(weatherResult.daily[i].dt * 1000);
            System.out.println("Дата " + dt);
            System.out.println("Погода " + weatherResult.daily[i].weather[0].description);
            System.out.println("Температура:");
            System.out.println("Днем: " + weatherResult.daily[i].temp.day + ", Ночью: " + weatherResult.daily[i].temp.night);
            System.out.println("Скорость ветера " + weatherResult.daily[i].wind_speed);
            System.out.println("Влажность " + weatherResult.daily[i].humidity);

        }

    }

    public void setData() {

        try {
            HW_08_Repository data = new HW_08_Repository();
            data.CreateTable();
            String city = "Санкт-Петербург";
            HW_08_Daily[] daily = weatherResult.daily;
            for (int i = 0; i < daily.length; i++) {
                Date dt = new Date(daily[i].dt * 1000);
                String sdt = dt.toString();
                String wt = "Влажность " + weatherResult.daily[i].humidity
                        + " Скорость ветера " + weatherResult.daily[i].wind_speed
                        + " Погода " + weatherResult.daily[i].weather[0].description;
                data.InsertDate(city, sdt, wt, daily[i].temp.day);
            }
            data.close();

        } catch (SQLException e) {
            System.out.println("Can not Connect to DataBase");
        }

    }

    public void getData() {

        try {
            HW_08_Repository data = new HW_08_Repository();
            List<HW_08_Data> list = data.getAllData();
            for (HW_08_Data dt: list) {
                System.out.println("City " + dt.city + "Date " + dt.localDate + "Weather " + dt.weatherText + "Temperature " + dt.temperature);

            }
            data.close();

        } catch (SQLException e) {
            System.out.println("Can not Connect to DataBase");
        }

    }

}
