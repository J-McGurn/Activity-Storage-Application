import javax.swing.*;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class HomePage {
    private JFrame frame;
    private JButton movies, tvshows, games, books;

    public HomePage() {
        swingWindow();
        addButtons();
        actionListeners();
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

        panel.setLayout(new GridLayout(2, 2, 5, 5));
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

    public void actionListeners() {
        movies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                frame.dispose();
                new Movies();
            }
        });

        tvshows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                frame.dispose();
                new TVShows();
            }
        });

        games.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                frame.dispose();
                new Games();
            }
        });

        books.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                frame.dispose();
                new Books();
            }
        });
    }
}