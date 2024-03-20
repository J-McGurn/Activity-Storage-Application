import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.*;

public class Movies {
    private JFrame frame;
    private JButton input, back;
    private JPanel buttonp, inputp, inputButton;
    private JComboBox rating;
    private JCheckBox genres;
    private JTextField title;

    public Movies() {
        swingWindow();
        buttons();

    }

    public void swingWindow() {
        frame = new JFrame();
        frame.setTitle("Movies");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void buttons() {
        inputButton = new JPanel();
        input = new JButton("Input New Entry");
        inputButton.add(input);
        frame.add(inputButton);

        buttonp = new JPanel();
        back = new JButton("Back");
        buttonp.add(back);
        frame.add(buttonp);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                frame.dispose();
                new HomePage();
            }
        });
    }

}
