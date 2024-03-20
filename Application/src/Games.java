import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.*;

public class Games {
    private JFrame frame;
    private JButton back;

    public Games() {
        swingWindow();
        backButton();
    }

    public void swingWindow() {
        frame = new JFrame();
        frame.setTitle("Games");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void backButton() {
        JPanel panel = new JPanel();
        back = new JButton("Back");
        panel.add(back);
        frame.add(panel);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                frame.dispose();
                new HomePage();
            }
        });
    }

}