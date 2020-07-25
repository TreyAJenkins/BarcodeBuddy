import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Prompt extends JFrame {

    JFrame frame;
    private boolean buttonClicked = false;
    private int buttonSelected = 0;

    /**
     * Prompts
     */
    public Prompt() {
        super(); // We need a superhero to save us from this code
        frame = new JFrame(); // make something new
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make it go away when we abandon it
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // dont do whatever this did
        //frame.setPreferredSize(new Dimension(328, 240)); // set a size
        frame.setPreferredSize(new Dimension(240, 328)); // set a size
        frame.setUndecorated(true); // no decorations
        frame.pack(); // pack it
        frame.setLocationRelativeTo(null); // give it no sense of location
        frame.setVisible(true); // let's see it
        frame.setVisible(false); // never mind

    }

    public void setVisiblity(boolean visibility) {
        //frame.pack();
        System.out.println("Visibility: " + visibility);
        frame.setVisible(visibility);
        if (visibility) {
            frame.toFront();
        } else {
            frame.toBack();
        }
        frame.repaint();
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
        setVisiblity(true);

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
        buttonClicked = false;

        clear();
        setVisiblity(false);
    }

    public int menu(ArrayList<String> objects) {
        clear();
        buttonClicked = false;
        buttonSelected = 0;
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());


        int count = 0;

        for (String text : objects) {
            JButton button = new JButton(text);
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.weighty = 1.0 / objects.size();
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridy = count;

            // set size of button
            button.setPreferredSize(new Dimension((int) (frame.getWidth() * 0.75), (int) (frame.getHeight() * gbc.weighty * 0.75)));


            // Set button text size
            Font buttonFont = button.getFont();
            int stringWidth = button.getFontMetrics(buttonFont).stringWidth(text);
            int componentWidth = (int) (frame.getWidth() * 0.75 * 0.5);
            double widthRatio = (double)componentWidth / (double)stringWidth;
            int newFontSize = (int)(buttonFont.getSize() * widthRatio);
            button.setFont(new Font(buttonFont.getName(), Font.PLAIN, newFontSize));
            button.setFocusPainted(false);

            int finalCount = count;
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Prompt.this.buttonClicked = true;
                    Prompt.this.buttonSelected = finalCount;
                }
            } );

            panel.add(button, gbc);

            count++;
        }

        frame.add(panel);
        setVisiblity(true);



        while (!buttonClicked) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        clear();
        setVisiblity(false);

        return buttonSelected;
    }

    public void suicide() {
        //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        frame.dispose();
    }


    public void clear() {
        frame.getContentPane().removeAll();
        setVisiblity(false);
    }



}
