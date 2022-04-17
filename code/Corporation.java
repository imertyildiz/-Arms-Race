import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Corporation extends Entity {
    private String name;
    private double cash;
    private static Font font = new Font("Verdana", Font.BOLD, 15);
    private String corporationName;
    private State state;
    private boolean isAwardedWhite = false;
    private boolean isAwardedYellow = false;
    private boolean isAwardedRed = false;

    public void setAwardedWhite(boolean awardedWhite) {
        isAwardedWhite = awardedWhite;
    }

    public void setAwardedYellow(boolean awardedYellow) {
        isAwardedYellow = awardedYellow;
    }

    public void setAwardedRed(boolean awardedRed) {
        isAwardedRed = awardedRed;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public Corporation(double x, double y, String name, double cash, String corporationName) {
        super(x, y);
        this.name = name;
        this.cash = cash;
        this.corporationName = corporationName;
    }

    @Override
    public void draw(Graphics2D g2d) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("images/" + this.name+ ".png"));
            g2d.drawImage(image, this.getPosition().getIntX() - 50, this.getPosition().getIntY()-50, 100, 100, null);
        } catch (Exception e) {
            System.out.println(e);
        }
        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(corporationName, this.getPosition().getIntX()-15, this.getPosition().getIntY()-60);
        g2d.setFont(font);
        g2d.setColor(Color.BLUE);
        g2d.drawString(this.getState().getClass().getName(), this.getPosition().getIntX()-40, this.getPosition().getIntY()+75);
        g2d.setFont(font);
        g2d.setColor(new Color(180, 0, 0));
        g2d.drawString((int) this.cash + "" , this.getPosition().getIntX()-40, this.getPosition().getIntY() + 100);
        if (this.isAwardedWhite){
            g2d.setColor(Color.WHITE);
            g2d.fillRect(this.getPosition().getIntX() - 50, this.getPosition().getIntY() -100,20,20);
        }
        if (this.isAwardedYellow){
            g2d.setColor(Color.YELLOW);
            g2d.fillRect(this.getPosition().getIntX() - 20, this.getPosition().getIntY() -100,20,20);
        }
        if (this.isAwardedRed){
            g2d.setColor(Color.RED);
            g2d.fillRect(this.getPosition().getIntX() + 10, this.getPosition().getIntY() -100,20,20);
        }
    }

    @Override
    public void step() {
        this.state.performAction();
        if (Common.getRandomGenerator().nextInt(50) == 0) Common.stateFactory(Common.getRandomGenerator().nextInt(4),this);
    }
    // TODO
    // Corporation image is 100 x 100
    // Cash RGB --> (180, 0, 0)
    // Badge is 20 x 20
}