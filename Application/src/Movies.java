import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class Movies {
    private JFrame frame;
    private JButton input, back, confirm;
    private JPanel backButton, inputButton, formPanel;
    private JComboBox ratingList;
    private JCheckBox genreCheckBox;
    private JTextField titleField;
    private JLabel titleLabel, ratingLabel, genreLabel;
    private int openEntries = 0;

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
                if (openEntries == 0) {
                    openEntries += 1;
                    entryForm();
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Please finish current entry before starting a new one.",
                            "Error!", JOptionPane.ERROR_MESSAGE);
                }
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
            genreCheckBox = new JCheckBox(genre);
            formPanel.add(genreCheckBox);
        }

        // add confirm button
        confirm = new JButton("Confirm Entry");
        formPanel.add(confirm);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                if (titleField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(formPanel,
                            "Please Enter a Title",
                            "Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    addEntry();
                }
            }
        });

        frame.add(formPanel);
        frame.revalidate();
    }

    public void addEntry() {
        String title = titleField.getText();
        String rating = (String) ratingList.getSelectedItem();

        StringBuilder selectedGenresBuilder = new StringBuilder();
        Component[] components = formPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                if (checkBox.isSelected()) {
                    // Append selected genre to the StringBuilder
                    selectedGenresBuilder.append(checkBox.getText()).append(", ");
                }
            }
        }
        String selectedGenres = selectedGenresBuilder.toString().trim();
        if (!selectedGenres.isEmpty()) {
            selectedGenres = selectedGenres.substring(0, selectedGenres.length() - 1);
        }

        System.out.println("Selected genres: " + selectedGenres);
        frame.dispose();
        new Movies();
    }

}
