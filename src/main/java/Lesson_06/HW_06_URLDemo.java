package Lesson_06;

/*
 *   Тигашов Валерий Евгеньевич
 *   Курс - Java Core
 *   Урок №6 (Networking)
 */

import java.io.InputStream;
import java.net.*;
import java.util.*;

public class HW_06_URLDemo {

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
            System.out.println();
            System.out.println(str);
        }
        return code;

    }

}
