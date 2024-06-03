import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout manager
        frame.setLayout(new BorderLayout());

        // Create and add the custom text panel
        JPanel welcomePanel = new CustomTextPanel();
        frame.add(welcomePanel, BorderLayout.NORTH);
    }

    public void addButtons() {
        // Create a panel with a 2x2 GridLayout for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));

        // Initialize buttons
        movies = new JButton("Movies");
        tvshows = new JButton("TV Shows");
        games = new JButton("Video Games");
        books = new JButton("Books");

        // Set the font for the buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 30);
        movies.setFont(buttonFont);
        tvshows.setFont(buttonFont);
        games.setFont(buttonFont);
        books.setFont(buttonFont);

        // Add buttons to the panel
        buttonPanel.add(movies);
        buttonPanel.add(tvshows);
        buttonPanel.add(games);
        buttonPanel.add(books);

        // Add padding around the button panel
        JPanel paddedButtonPanel = new JPanel(new BorderLayout());
        paddedButtonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        paddedButtonPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the button panel to the center of the frame
        frame.add(paddedButtonPanel, BorderLayout.CENTER);

        // Set the frame visible after adding all components
        frame.setVisible(true);
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

    public static void main(String[] args) {
        new HomePage();
    }
}

class CustomTextPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Serif", Font.BOLD, 50));
        g.setColor(Color.BLACK);

        // Get the width of the panel and the string
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        String text = "Welcome!";
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = metrics.getHeight() + 10;

        // Draw the string
        g.drawString(text, x, y);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 100);
    }
}
