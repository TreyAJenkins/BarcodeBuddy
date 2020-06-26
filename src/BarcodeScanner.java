import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.ds.v4l4j.V4l4jDriver;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class BarcodeScanner extends JFrame {


    static {
        if (!System.getProperty("os.name").contains("Windows")) {
            Webcam.setDriver(new V4l4jDriver()); // this is important
        }
    }

    static boolean running = true;

    private Webcam webcam;
    private WebcamPanel panel;
    //private JTextArea textArea;

    public BarcodeScanner() {
        super();

        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getDefault();
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        //textArea = new JTextArea();
        //textArea.setEditable(false);
        //textArea.setPreferredSize(size);

        add(panel);
        //add(textArea);

        pack();
        //setVisible(true);
    }

    public String scan() {
        setVisible(true);
        while (true) {

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                    setVisible(false);
                    return result.getText();
                } catch (NotFoundException e) {
                    // fall thru, it means there is no QR code in image
                }
            }
        }
    }

    public void close() {
        webcam.close();
        this.dispose();
    }

}
