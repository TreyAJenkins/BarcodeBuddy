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
        frame.setVisible(true);
        frame.setVisible(false);

        JPanel header = new JPanel();
        JButton menu = new JButton("Main Menu");
        JButton newLoction = new JButton("New Location");
        JButton undo = new JButton("Undo");
        header.add(menu);
        header.add(newLoction);
        header.add(undo);
        frame.add(header);

    }

    public int addEquipment(String name, int type) {
        JPanel currPanel = new JPanel();
        JButton currButton = new JButton(name);

        //TODO: implement type
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
            gbc.anchor = GridBagConstraints.CENTER;

            for (JPanel equip : equipment) {
                System.out.println( ((JButton) equip.getComponents()[0]).getText() );
                gbc.gridy = count;
                panel.add(equip, gbc);
                count++;
                //TODO: Add handler for button
            }
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setPreferredSize(frame.getSize());
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            frame.add(scrollPane);
        }
        frame.setVisible(visibility);

    }


    public void clear() {
        equipment.clear();
        frame.getContentPane().removeAll();
    }

}