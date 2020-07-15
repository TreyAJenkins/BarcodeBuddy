import com.sun.tools.javac.comp.Flow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EquipmentListing extends JFrame {

    JFrame frame;
    private boolean buttonClicked = false;
    private int buttonSelected = 0;

    private Location location;
    int headerHeight;

    public EquipmentListing() {
        super();

        //this.location = location;
        //this.equipment = new ArrayList<JPanel>();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setPreferredSize(new Dimension(328, 240));
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);

        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        JPanel header = new JPanel();
        header.setLayout(layout);
        JButton scan = new JButton("Scan");
        JButton menu = new JButton("Main Menu");
        JButton undo = new JButton("Undo");
        header.add(scan);
        header.add(menu);
        header.add(undo);
        frame.add(header);
        frame.setVisible(true);
        frame.setVisible(false);
        headerHeight = header.getHeight();

    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getCurrentLocation() {
        return location;
    }

    private JPanel mkEquipPanel(Equipment equip) {
        JPanel currPanel = new JPanel();
        JButton currButton = new JButton(equip.toString());
        currPanel.setOpaque(true);
        //currButton.setBorderPainted(false);
        switch (equip.getType()) {
            case Equipment.NOT_SCANNED:
                currPanel.setBackground(Color.red);
                break;
            case Equipment.GREEN:
                currPanel.setBackground(Color.GREEN);
                break;
            case Equipment.YELLOW:
                currPanel.setBackground(Color.YELLOW);
                break;
            case Equipment.FLASHING_BLUE:
                currPanel.setBackground(Color.BLUE);
                break;
        }

        currPanel.add(currButton);
        return currPanel;
    }

    public void setVisible(boolean visibility) {
        //frame.pack();
        JLabel label = new JLabel(location.getLocation());
        frame.add(label);
        ArrayList<Equipment> equipment = location.getEquipment();
        if (visibility) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            int count = 0;

            gbc.weighty = 1.0 / equipment.size();
            //gbc.anchor = GridBagConstraints.CENTER;

            for (Equipment equip : equipment) {
                System.out.println(equip);
                gbc.gridy = count;


                panel.add(mkEquipPanel(equip), gbc);


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
        frame.getContentPane().removeAll();
    }
}