package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Prompt extends JFrame {

    JFrame frame;
    private boolean buttonClicked = false;


    public Prompt() {
        super();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setPreferredSize(new Dimension(328, 240));
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setVisible(false);

    }

    public void setVisible(boolean visibility) {
        //frame.pack();
        frame.setVisible(visibility);
    }

    public void prompt(String promptText, String buttonText) {
        buttonClicked = false;
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel(promptText);
        JButton button = new JButton(buttonText);


        Font labelFont = label.getFont();
        String labelText = label.getText();
        int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = (int) (frame.getWidth() * 0.75);
        // Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;
        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        // Set the label's font size to the newly determined size.
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, newFontSize));

        gbc.weighty = 0.3; // weight for label
        gbc.anchor = GridBagConstraints.SOUTH;
        panel.add(label, gbc);
        gbc.weighty = 0.3; // weight for button
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        button.setPreferredSize(new Dimension((int) (frame.getWidth() * 0.75), (int) (frame.getHeight() * 0.5 * 0.75)));
        panel.add(button, gbc);

        Font buttonFont = button.getFont();
        stringWidth = button.getFontMetrics(buttonFont).stringWidth(buttonText);
        componentWidth = (int) (frame.getWidth() * 0.75 * 0.5);
        // Find out how much the font can grow in width.
        widthRatio = (double)componentWidth / (double)stringWidth;
        newFontSize = (int)(buttonFont.getSize() * widthRatio);
        // Set the buttons's font size to the newly determined size.
        button.setFont(new Font(buttonFont.getName(), Font.PLAIN, newFontSize));
        button.setFocusPainted(false);

        frame.add(panel);
        this.setVisible(true);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prompt.this.buttonClicked = true;
            }
        } );

        while (!buttonClicked) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        this.clear();
        this.setVisible(false);
    }

    public void clear() {
        frame.removeAll();
    }

}
