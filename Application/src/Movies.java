import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import lib.MySQLAccess;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.SQLException;

public class Movies {
    private JFrame frame;
    private JButton input, back, confirm;
    private JPanel backButton, inputButton, formPanel, tablePanel;
    private JComboBox<String> ratingList;
    private JCheckBox genreCheckBox;
    private JTextField titleField;
    private JLabel titleLabel, ratingLabel, genreLabel;
    private int openEntries = 0;
    private JTable table;
    private JScrollPane scrollPane;

    public Movies() {
        swingWindow();
        buttons();
        table();
    }

    public void swingWindow() {
        frame = new JFrame();
        frame.setTitle("Movies");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }

    public void buttons() {
        inputButton = new JPanel();
        input = new JButton("Input New Entry");
        inputButton.add(input);
        frame.add(inputButton, BorderLayout.NORTH);

        input.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
                if (openEntries == 0) {
                    openEntries += 1;
                    inputButton.hide();
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
        frame.add(backButton, BorderLayout.SOUTH);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                frame.dispose();
                new HomePage();
            }
        });
    }

    public void table() {
        tablePanel = new JPanel();
        // tablePanel.setPreferredSize(new Dimension(550, 550));

        String[] columnNames = { "Movie ID", "Title", "Rating", "Genres" };

        try {
            MySQLAccess dbAccess = new MySQLAccess();
            Object[][] data = dbAccess.getTableData("movies");
            table = new JTable(data, columnNames);
            table.setEnabled(false);

            TableColumnModel columnModel = table.getColumnModel();
            for (int col = 0; col < table.getColumnCount(); col++) {
                TableColumn column = columnModel.getColumn(col);
                int maxWidth = 0;
                for (int row = 0; row < table.getRowCount(); row++) {
                    TableCellRenderer renderer = table.getCellRenderer(row, col);
                    Component comp = table.prepareRenderer(renderer, row, col);
                    maxWidth = Math.max(comp.getPreferredSize().width, maxWidth);
                }
                column.setPreferredWidth(maxWidth); // Add some padding
            }

            scrollPane = new JScrollPane(table);
            tablePanel.add(scrollPane// , BorderLayout.CENTER
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.add(tablePanel// , BorderLayout.CENTER
        );
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
        String[] ratings = { "5 Stars", "4 Stars", "3 Stars", "2 Stars", "1 Star" };
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

        frame.add(formPanel, BorderLayout.NORTH);
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

        MySQLAccess dbAccess = new MySQLAccess();
        dbAccess.addMovie(title, rating, selectedGenres);

        frame.dispose();
        new Movies();
    }

}
