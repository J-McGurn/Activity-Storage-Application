package lib;

// replace ****** with root password
public class SQLAccessUrl {
    public String url() {
        String url = "jdbc:mysql://localhost:3306/activity_storage?user=root&password=******!&allowPublicKeyRetrieval=true&useSSL=false";
        return url;
    }
}