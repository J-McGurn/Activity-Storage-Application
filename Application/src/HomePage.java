import javax.swing.*;

public class HomePage {
    private JFrame frame;
    private JButton movies, tvshows, games, books;

    public HomePage() {
        swingWindow();
        addButtons();
    }

    public void swingWindow() {
        frame = new JFrame();
        frame.setTitle("Application");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void addButtons() {
        JPanel panel = new JPanel();
        movies = new JButton("Movies");
        tvshows = new JButton("TV Shows");
        games = new JButton("Video Games");
        books = new JButton("Books");
        panel.add(movies);
        panel.add(tvshows);
        panel.add(games);
        panel.add(books);
        frame.add(panel);

    }
}