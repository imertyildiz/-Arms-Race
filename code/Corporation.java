import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Corporation extends Entity {
    private String name;
    private double cash;
    private Font font = new Font("Verdana", Font.BOLD, 15);
    private String corporationName;
    public State state;

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public Corporation(double x, double y, String name, double cash, String corporationName) {
        super(x, y);
        this.name = name;
        this.cash = cash;
        this.corporationName =corporationName;
    }

    @Override
    public void draw(Graphics2D g2d) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("images/" + this.name+ ".png"));
            g2d.drawImage(image, this.getPosition().getIntX() - 25, this.getPosition().getIntY(), 100, 100, null);
        } catch (Exception e) {
            System.out.println(e);
        }
        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(corporationName, this.getPosition().getIntX(), this.getPosition().getIntY()-10);
        g2d.setFont(font);
        g2d.setColor(Color.BLUE);
        g2d.drawString(this.getState().getClass().getName(), this.getPosition().getIntX(), this.getPosition().getIntY()+125);
        g2d.setFont(font);
        g2d.setColor(new Color(180, 0, 0));
        g2d.drawString(this.cash + "" , this.getPosition().getIntX(), this.getPosition().getIntY() + 150);

    }

    @Override
    public void step() {
        Common.stateFactory(Common.getRandomGenerator().nextInt(4),this);
    }
    // TODO
    // Corporation image is 100 x 100
    // Cash RGB --> (180, 0, 0)
    // Badge is 20 x 20
}