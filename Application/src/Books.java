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
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import lib.MySQLAccess;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Color;

import java.util.*;

public class Books {
    private JFrame frame;
    private JButton input, delete, back;
    private JPanel buttonsPanel, tablePanel;
    private JTable table;
    private JScrollPane scrollPane;

    public Books() {
        swingWindow();
        buttons();
        table();
    }

    public void swingWindow() {
        frame = new JFrame();
        frame.setTitle("Books");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application when main window is closed
        frame.setVisible(true);
    }

    public void buttons() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        input = new JButton("Input New Entry");
        input.setFont(buttonFont);
        buttonsPanel.add(input);

        input.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the entry form window
                EntryForm entryForm = new EntryForm(frame);
                entryForm.setVisible(true);

                // Disable main book window until entry form window is closed
                frame.setEnabled(false);
            }
        });

        delete = new JButton("Delete");
        delete.setFont(buttonFont);
        buttonsPanel.add(delete);
        frame.add(buttonsPanel, BorderLayout.NORTH);

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteForm deleteForm = new DeleteForm(frame);
                deleteForm.setVisible(true);

                frame.setEnabled(false);
            }
        });

        back = new JButton("Back");
        back.setFont(buttonFont);
        frame.add(back, BorderLayout.SOUTH);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                frame.dispose();
                new HomePage();
            }
        });

    }

    public void table() {
        tablePanel = new JPanel(new BorderLayout());

        String[] columnNames = { "Title", "Author", "Rating", "Type" };

        try {
            MySQLAccess dbAccess = new MySQLAccess();
            Object[][] data = dbAccess.getTableData("books");
            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            table = new JTable(model);
            table.setEnabled(false);

            table.getTableHeader().setBackground(new Color(0, 153, 0));
            table.setDefaultRenderer(Object.class, new OddEvenRowRenderer());

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
            tablePanel.add(scrollPane, BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.add(tablePanel, BorderLayout.CENTER);
    }

    class OddEvenRowRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (row % 2 == 0) {
                renderer.setBackground(Color.LIGHT_GRAY); // Even rows
            } else {
                renderer.setBackground(Color.WHITE); // Odd rows
            }
            return renderer;
        }
    }

    // EntryForm class for creating the entry form window
    class EntryForm extends JFrame {
        private JPanel formPanel;
        private JButton confirm;
        private JComboBox<String> ratingList, typeList;
        private JTextField titleField, authorField;
        private JLabel titleLabel, authorLabel, ratingLabel, typeLabel;

        public EntryForm(JFrame parentFrame) {
            setTitle("Add New Entry");
            setSize(400, 250);
            setLocationRelativeTo(parentFrame);
            setResizable(false);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose entry form window instead of closing entire
                                                               // application
            setLayout(new BorderLayout());

            formPanel = new JPanel(new GridLayout(0, 2));

            // Add title
            titleLabel = new JLabel("Title:");
            titleField = new JTextField(20);
            formPanel.add(titleLabel);
            formPanel.add(titleField);

            authorLabel = new JLabel("Author:");
            authorField = new JTextField(20);
            formPanel.add(authorLabel);
            formPanel.add(authorField);

            // Add star ratings
            ratingLabel = new JLabel("Rating:");
            String[] ratings = { "5 Stars", "4 Stars", "3 Stars", "2 Stars", "1 Star" };
            ratingList = new JComboBox<>(ratings);
            formPanel.add(ratingLabel);
            formPanel.add(ratingList);

            // Add type
            typeLabel = new JLabel("Type:");
            String[] types = { "Fiction", "Non-Fiction", "Educational", "Poetry", "Graphic Novel/ Comic" };
            typeList = new JComboBox<>(types);
            formPanel.add(typeLabel);
            formPanel.add(typeList);

            // add confirm button
            confirm = new JButton("Confirm Entry");
            confirm.setBackground(Color.GREEN);
            formPanel.add(confirm);

            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (titleField.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(formPanel,
                                "Please Enter a Title",
                                "Error!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (authorField.getText().trim().isEmpty()) {
                            JOptionPane.showMessageDialog(formPanel,
                                    "Please Enter an Author",
                                    "Error!", JOptionPane.ERROR_MESSAGE);
                        } else {
                            // Get data from form fields
                            String title = titleField.getText();
                            String author = authorField.getText();
                            String rating = (String) ratingList.getSelectedItem();
                            String type = (String) typeList.getSelectedItem();

                            // Call addEntry method with the data
                            if (addEntry(title, author, rating, type)) {

                                // Close the entry form window
                                dispose();
                                // Enable main book window
                                parentFrame.setEnabled(true);
                            }
                        }
                    }
                }
            });
            // Add a WindowListener to handle window closing event
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    // Enable main book window when entry form window is closed using "X" button
                    parentFrame.setEnabled(true);
                }
            });

            add(formPanel, BorderLayout.CENTER);
        }

        private Boolean addEntry(String title, String author, String rating, String type) {
            MySQLAccess dbAccess = new MySQLAccess();
            boolean result = dbAccess.addBook(title, author, rating, type);
            if (!result) {
                JOptionPane.showMessageDialog(this,
                        "This book title has already been added.",
                        "Error!", JOptionPane.ERROR_MESSAGE);

            } else {
                // Refresh the table data
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0); // Clear existing rows
                Object[][] data = dbAccess.getTableData("Books");
                for (Object[] row : data) {
                    model.addRow(row); // Add new rows
                }
            }
            return result;
        }

    }

    class DeleteForm extends JFrame {
        private JPanel formPanel;
        private JLabel titleLabel;
        private JTextField titleField;
        private JButton confirm;

        public DeleteForm(JFrame parentFrame) {
            setTitle("Delete Entry");
            setSize(400, 100);
            setLocationRelativeTo(parentFrame);
            setResizable(false);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            formPanel = new JPanel();

            titleLabel = new JLabel("Title: ");
            titleField = new JTextField(30);
            formPanel.add(titleLabel);
            formPanel.add(titleField);

            confirm = new JButton("Confirm Entry");
            confirm.setBackground(Color.GREEN);
            formPanel.add(confirm);

            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (titleField.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(formPanel,
                                "Please Enter a Title",
                                "Error!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String title = titleField.getText();

                        if (deleteEntry(title)) {

                            // Close the entry form window
                            dispose();
                            // Enable main book window
                            parentFrame.setEnabled(true);
                        }
                    }
                }

            });

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    // Enable main book window when entry form window is closed using "X" button
                    parentFrame.setEnabled(true);
                }
            });

            add(formPanel, BorderLayout.CENTER);
        }

        private boolean deleteEntry(String title) {
            MySQLAccess dbAccess = new MySQLAccess();
            boolean result = dbAccess.deleteEntry(title, "books");
            if (!result) {
                JOptionPane.showMessageDialog(this,
                        "This book could not be deleted.",
                        "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                // Refresh the table data
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0); // Clear existing rows
                Object[][] data = dbAccess.getTableData("books");
                for (Object[] row : data) {
                    model.addRow(row); // Add new rows
                }
            }
            return result;
        }
    }
}
