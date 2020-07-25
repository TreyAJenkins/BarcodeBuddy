import com.sun.tools.javac.comp.Flow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class EquipmentListing extends JFrame {

    JFrame frame;
    private boolean buttonClicked = false;
    private int buttonSelected = 0;

    private JPanel header;

    private Location location;
    private int headerHeight;
    private JButton undo;

    public static final int IDLE = 0;
    public static final int RETURN_MENU = 1;
    public static final int RETURN_SCAN = 2;
    public static final int RETURN_UNDO = 3;
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setUndo(boolean undoEnabled) {
        undo.setEnabled(undoEnabled);
    }

    public EquipmentListing(boolean noScanButton) {
        super();

        this.state = IDLE;

        //this.location = location;
        //this.equipment = new ArrayList<JPanel>();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setPreferredSize(new Dimension(328, 240));
        frame.setPreferredSize(new Dimension(240, 328));
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);

        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        header = new JPanel();
        header.setLayout(layout);
        JButton scan = new JButton("Scan");
        JButton menu = new JButton("Main Menu");
        undo = new JButton("Undo");
        undo.setEnabled(false);

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Undo button");
                setState(RETURN_UNDO);
            }
        });

        scan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setState(RETURN_SCAN);
            }
        });

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setState(RETURN_MENU);
                suicide();
            }
        });

        if (!noScanButton) header.add(scan);
        header.add(menu);
        header.add(undo);
        frame.add(header);
        frame.setVisible(true);
        frame.setVisible(false);
        headerHeight = header.getHeight();

    }

    public void suicide() {
        //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        frame.dispose();
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
            case Equipment.BLUE:
                currPanel.setBackground(Color.BLUE);
                break;
            case Equipment.FLASHING_ORANGE:
                currPanel.setBackground(Color.ORANGE);
            case Equipment.DELETED:
                return null;

        }

        currPanel.add(currButton);
        return currPanel;
    }

    public void setVisibility(boolean visibility) {
        //frame.pack();
        JLabel label = new JLabel(location.getLocation());
        frame.add(label);
        ArrayList<Equipment> equipment = location.getEquipment();
        if (visibility) {
            clear();
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            int count = 0;

            gbc.weighty = 1.0 / equipment.size();
            //gbc.anchor = GridBagConstraints.CENTER;

            for (Equipment equip : equipment) {
                System.out.println(equip);
                gbc.gridy = count;

                JPanel tmpPanel = mkEquipPanel(equip);
                if (tmpPanel != null) {
                    panel.add(tmpPanel, gbc);
                    count++;
                    //TODO: Add handler for button

                }
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
        frame.add(header);
    }
}