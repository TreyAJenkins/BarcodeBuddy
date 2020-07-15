package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EquipmentListing extends JFrame {

    private static final int DELETED = -1;
    private static final int GREEN = 0;
    private static final int YELLOW = 1;
    private static final int FLASHING_BLUE = 2;

    JFrame frame;
    private boolean buttonClicked = false;
    private int buttonSelected = 0;

    private String location;
    private ArrayList<JPanel> equipment;
    int headerHeight;


    public EquipmentListing(String location) {
        super();

        this.location = location;
        this.equipment = new ArrayList<JPanel>();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setPreferredSize(new Dimension(328, 240));
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);

        JPanel header = new JPanel();
        JButton menu = new JButton("Main Menu");
        JButton newLoction = new JButton("New Location");
        JButton undo = new JButton("Undo");
        header.add(menu);
        header.add(newLoction);
        header.add(undo);
        frame.add(header);
        frame.setVisible(true);
        frame.setVisible(false);
        headerHeight = header.getHeight();

    }

    public int addEquipment(String name, int type) {
        JPanel currPanel = new JPanel();
        JButton currButton = new JButton(name);
        currButton.setOpaque(true);
        //currButton.setBorderPainted(false);
        switch (type) {
            case GREEN:
                currButton.setBackground(Color.GREEN);
                break;
            case YELLOW:
                currButton.setBackground(Color.YELLOW);
                break;
            case FLASHING_BLUE:
                currButton.setBackground(Color.BLUE);
                break;
            case DELETED:
                return equipment.size();
        }

        currPanel.add(currButton);
        equipment.add(currPanel);
        return equipment.size();
    }

    public void setVisible(boolean visibility) {
        //frame.pack();

        if (visibility) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            int count = 0;

            gbc.weighty = 1.0 / equipment.size();
            //gbc.anchor = GridBagConstraints.CENTER;

            for (JPanel equip : equipment) {
                System.out.println( ((JButton) equip.getComponents()[0]).getText() );
                gbc.gridy = count;
                panel.add(equip, gbc);
                count++;
                //TODO: Add handler for button
            }
            JScrollPane scrollPane = new JScrollPane(panel);

            Dimension newSize = frame.getSize();
            newSize.setSize(frame.getWidth(), newSize.getHeight() - headerHeight);
            scrollPane.setPreferredSize(newSize);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            System.out.println(scrollPane.getSize());

            frame.add(scrollPane);
        }
        frame.setVisible(visibility);

    }


    public void clear() {
        equipment.clear();
        frame.getContentPane().removeAll();
    }

}