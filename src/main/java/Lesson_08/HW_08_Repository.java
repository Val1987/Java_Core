package Lesson_08;

/*
 *   Тигашов Валерий Евгеньевич
 *   Курс - Java Core
 *   Урок №8 (Networking)
 */

import org.sqlite.JDBC;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class HW_08_Repository {

    private static final String CON_STR = "jdbc:sqlite:C:/Users/Christian/Documents/QA Tester/Java/Java_Core/src/main/java/Lesson_08/myfin.db";
    private static HW_08_Repository instance = null;
    private Connection connection;

    public HW_08_Repository() throws SQLException {

        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(CON_STR);

    }

    public void CreateTable() {

        String SQL = "Create table if not exists Weather ( " + "city Text not null, " + "localDate text not null, " + "weatherText text not null, " + "temperature real not null )";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {

            statement.execute();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void InsertDate(String city, String localDate, String weatherText, double temperature) {

        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Weather(`city`, `localDate`, `weatherText`, 'temperature') " +
                        "VALUES(?, ?, ?, ?)")) {
            statement.setObject(1, city);
            statement.setObject(2, localDate);
            statement.setObject(3, weatherText);
            statement.setObject(4, temperature);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public List<HW_08_Data> getAllData() {

        try (Statement statement = this.connection.createStatement()) {
            List<HW_08_Data> information = new ArrayList<HW_08_Data>();
            String SQL = "select city, localDate, weatherText, temperature from Weather";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                information.add(new HW_08_Data(resultSet.getString("city"),
                        resultSet.getString("localDate"),
                        resultSet.getString("weatherText"),
                        resultSet.getDouble("temperature")));
            }
            return information;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    public void close() throws SQLException {
        this.connection.close();
    }

}
