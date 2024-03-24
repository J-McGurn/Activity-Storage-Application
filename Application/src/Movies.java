import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class Movies {
    private JFrame frame;
    private JButton input, back, confirm;
    private JPanel backButton, inputButton, formPanel;
    private JComboBox ratingList;
    private JCheckBox genreList;
    private JTextField titleField;
    private JLabel titleLabel, ratingLabel, genreLabel;

    public Movies() {
        swingWindow();
        buttons();
    }

    public void swingWindow() {
        frame = new JFrame();
        frame.setTitle("Movies");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
    }

    public void buttons() {
        inputButton = new JPanel();
        input = new JButton("Input New Entry");
        inputButton.add(input);
        frame.add(inputButton);

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entryForm();
            }
        });

        backButton = new JPanel();
        back = new JButton("Back");
        backButton.add(back);
        frame.add(backButton);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                frame.dispose();
                new HomePage();
            }
        });
    }

    public void entryForm() {
        formPanel = new JPanel();
        formPanel = new JPanel(new GridLayout(0, 4));
        // Add title
        titleLabel = new JLabel("Title:");
        titleField = new JTextField(20);
        formPanel.add(titleLabel);
        formPanel.add(titleField);

        // Add star ratings
        ratingLabel = new JLabel("Rating:");
        String[] ratings = { "5 Star", "4 Star", "3 Star", "2 Star", "1 Star" };
        ratingList = new JComboBox<>(ratings);
        formPanel.add(ratingLabel);
        formPanel.add(ratingList);

        // Add genres
        genreLabel = new JLabel("Genre(s):");
        formPanel.add(genreLabel);
        String[] genres = { "Action", "Adventure", "Animation", "Comedy", "Crime",
                "Drama", "Fantasy", "Historical", "Horror", "Musical",
                "Mystery", "Romance", "Sci-Fi", "Thriller", "War", "Western" };
        for (String genre : genres) {
            genreList = new JCheckBox(genre);
            formPanel.add(genreList);
        }

        // add confirm button
        confirm = new JButton("Confirm Entry");
        formPanel.add(confirm);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

            }
        });

        frame.add(formPanel);
        frame.revalidate();
    }

}
