package lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

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

}
