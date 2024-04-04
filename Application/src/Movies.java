import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    private ArrayList<String> selectedGenres;

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
        selectedGenres = new ArrayList<>();
        String[] genres = { "Action", "Adventure", "Animation", "Comedy", "Crime",
                "Drama", "Fantasy", "Historical", "Horror", "Musical",
                "Mystery", "Romance", "Sci-Fi", "Thriller", "War", "Western" };
        for (String genre : genres) {
            genreCheckBox = new JCheckBox(genre);
            formPanel.add(genreCheckBox);
            genreCheckBox.addItemListener(e -> {
                // Check if the checkbox is selected
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Add the selected genre to the ArrayList
                    selectedGenres.add(genreCheckBox.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    // Remove the deselected genre from the ArrayList
                    selectedGenres.remove(genreCheckBox.getText());
                }
            });
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
        StringBuilder genresBuilder = new StringBuilder();
        for (int i = 0; i < selectedGenres.size(); i++) {
            genresBuilder.append(selectedGenres.get(i)); // Append the genre to the StringBuilder
            if (i != selectedGenres.size() - 1) { // Add a comma if it's not the last genre
                genresBuilder.append(", ");
            }
        }
        String genres = genresBuilder.toString();
        System.out.println(genres);
    }

}
