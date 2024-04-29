package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.ArrayList;
import java.util.Random;

public class MySQLAccess {

    /*
     * Create a new class: SQLAccessUrl.java
     *
     * In the "url" String:
     * replace 'dbo' with the name of your database
     * replace 'root' if needed
     * set password=YOUR_PASSWORD
     * 
     * package lib;
     * 
     * public class SQLAccessUrl {
     * public String url(){
     * String url =
     * "jdbc:mysql://localhost:3306/dbo?user=root&password=YOUR_PASSWORD&allowPublicKeyRetrieval=true&useSSL=false";
     * return url;
     * }
     * }
     */

    SQLAccessUrl myUrl = new SQLAccessUrl();
    String url = myUrl.url();

    public int randomID(String table, String column) {
        Random random = new Random();
        int randomID;
        do {
            randomID = random.nextInt(Integer.MAX_VALUE); // Generate positive random ID
        } while (randomID <= 0);

        try (Connection connect = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connect
                    .prepareStatement("SELECT * FROM " + table + " WHERE " + column + " = ?");
            preparedStatement.setInt(1, randomID);
            ResultSet result = preparedStatement.executeQuery();
            if (!result.next()) {
                return randomID;
            } else {
                randomID(table, column);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Object[][] getTableData(String table) {
        ArrayList<Object> tableData = new ArrayList<>();
        try (Connection connect = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM " + table);

            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Object[] row = new Object[columnCount - 1];
                for (int i = 2; i <= columnCount; i++) {
                    row[i - 2] = resultSet.getObject(i);
                }
                tableData.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object[][] data = tableData.toArray(new Object[0][]);
        return data;
    }

    public boolean addMovie(String title, String rating, String genres) {
        try (Connection connect = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement1 = connect.prepareStatement("SELECT * FROM movies WHERE title = ?");
            preparedStatement1.setString(1, title);
            ResultSet resultSet = preparedStatement1.executeQuery();
            if (!resultSet.next()) {
                PreparedStatement preparedStatement = connect
                        .prepareStatement("INSERT INTO movies (movieID, title, rating, genres) VALUES (?, ?, ?, ?)");
                preparedStatement.setInt(1, randomID("movies", "movieID"));
                preparedStatement.setString(2, title);
                preparedStatement.setString(3, rating);
                preparedStatement.setString(4, genres);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
