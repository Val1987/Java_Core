package Lesson_05;

/*
 *   Тигашов Валерий Евгеньевич
 *   Курс - Java Core
 *   Урок №5 (Input-Output Facilities)
 */

/*
 *  Реализовать сохранение данных в csv файл;
 *  Реализовать загрузку данных из csv файла. Файл читается целиком.
 *
 *  Структура csv файла:
 *  Строка заголовок с набором столбцов
 *  Набор строк с целочисленными значениями
 *
 *  Разделитель между столбцами - символ точка с запятой (;)
 *
 *  Пример:
 *
 *  Value 1;Value 2;Value 3
 *  100;200;123
 *  300,400,500
 *
 *  Для хранения данных использовать класс вида:
 *
 *  public class AppData {
 *  private String[] header;
 *  private int[][] data;
 *
 *  // ...
 *  }
 *
 */

import java.io.*;
import java.util.ArrayList;

public class HW_05_IOFacilities {

    public static void main(String[] args) {

        String[] header = {
                "value-1", "value-2", "value-3"
        };

        int data[][] = {
                {100, 200, 300},
                {400, 500, 600},
                {700, 800, 900}
        };

        AppData start = new AppData(header, data);
        start.WriteToFile("File.csv");
        start.ReadFromFile("File.csv");
        start.PrintConsole();

    }

}

class AppData {

    private String[] header;
    private int[][] data;

    public AppData(String[] header, int[][] data) {

        this.header = new String[header.length];
        for (int i = 0; i < header.length; i++) {
            this.header[i] = header[i];
        }

        this.data = new int[data.length][];
        for (int i = 0; i < data.length; i++) {
            this.data[i] = new int[data[i].length];
        }

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                this.data[i][j] = data[i][j];
            }
        }

    }

    public void WriteToFile(String FileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileName))) {
            StringBuffer bufferStr = new StringBuffer();
            for (int i = 0; i < header.length; i++) {
                bufferStr.append(header[i]);
                if(i < header.length -1)
                    bufferStr.append(';');
            }
            bufferStr.append('\n');
            writer.write(bufferStr.toString());

            for (int i = 0; i < data.length; i++) {
                StringBuffer buffer = new StringBuffer();
                for (int j = 0; j < data[i].length; j++) {
                    buffer.append(data[i][j]);
                    if (j < data[i].length -1)
                        buffer.append(';');
                }
                buffer.append('\n');
                writer.write(buffer.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ReadFromFile(String FileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(FileName))) {
            String header = reader.readLine();
            if(header != null) {
                String[] hdr = header.split(";");
                for (int i = 0; i < hdr.length; i++) {
                    this.header[i] = hdr[i];
                }
            }
            String str;
            ArrayList<String> arrList = new ArrayList<String>();
            while ((str = reader.readLine()) != null) {
                arrList.add(str);
            }
            for (int i = 0; i < arrList.size(); i++) {
                String[] numbers = arrList.get(i).split(";");
                for (int j = 0; j < numbers.length; j++) {
                    try {
                        data[i][j] = Integer.parseInt(numbers[j]);
                    } catch (NumberFormatException ex) {
                        System.out.println("Не удалось преобразовать значение!");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void PrintConsole() {
        StringBuffer bufferCons = new StringBuffer();
        for (int i = 0; i < header.length; i++) {
            bufferCons.append(header[i]);
            if(i < header.length -1)
                bufferCons.append(';');
        }
        System.out.println(bufferCons.toString());
        for (int i = 0; i < data.length; i++) {
            bufferCons.delete(0, bufferCons.length());
            for (int j = 0; j < data[i].length; j++) {
                bufferCons.append(data[i][j]);
                if(j < data[i].length)
                    bufferCons.append(';');
            }
            System.out.println(bufferCons.toString());
        }

    }

}





