import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class Display extends JPanel {
    public Display() { this.setBackground(new Color(180, 180, 180)); }

    @Override
    public Dimension getPreferredSize() { return super.getPreferredSize(); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Common.getFoodPrice().draw((Graphics2D) g);
        Common.getElectronicsPrice().draw((Graphics2D) g);
        Common.getGoldPrice().draw((Graphics2D) g);

        g.drawLine(Common.getFirstVerticalLineX(), 0, Common.getFirstVerticalLineX(), Common.getHorizontalLineY());
        g.drawLine(Common.getSecondVerticalLineX(), 0, Common.getSecondVerticalLineX(), Common.getHorizontalLineY());
        g.drawLine(0, Common.getHorizontalLineY(), Common.getWindowWidth(), Common.getHorizontalLineY());

        // TODO: draw other entities
        Common.getMexico().draw((Graphics2D) g);
        Common.getChile().draw((Graphics2D) g);
        Common.getPoland().draw((Graphics2D) g);
        Common.getNigeria().draw((Graphics2D) g);
        Common.getMalaysia().draw((Graphics2D) g);

        Common.getLockheed_martin().draw((Graphics2D) g);
        Common.getRaytheon().draw((Graphics2D) g);
        Common.getBoeing().draw((Graphics2D) g);
        Common.getNorthrop_grumman().draw((Graphics2D) g);
        Common.getGeneral_dynamics().draw((Graphics2D) g);

    }
}