import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Country extends Entity {
    private String name;
    private double worth;
    private double cash;
    private double gold;
    private double happiness;
    private String nickName;
    private Font font = new Font("Verdana", Font.BOLD, 15);


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public double getHappiness() {
        return happiness;
    }

    public void setHappiness(double happiness) {
        this.happiness = happiness;
    }

    public Country(double x, double y, String name, double worth, double cash, double gold, double happiness, String nickName) {
        super(x, y);
        this.name = name;
        this.worth = worth;
        this.cash = cash;
        this.gold = gold;
        this.happiness = happiness;
        this.nickName = nickName;
    }

    @Override
    public void draw(Graphics2D g2d) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("images/" + this.name.toLowerCase() + ".png"));
            g2d.drawImage(image, this.getPosition().getIntX(), this.getPosition().getIntY() - 125, 150, 150, null);
        } catch (Exception e) {
            System.out.println(e);
        }
        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(this.name, this.getPosition().getIntX() + 30, this.getPosition().getIntY() + 50);
        g2d.setFont(font);
        g2d.setColor(Color.BLUE);
        g2d.drawString("Worth: " + (int) this.worth + "$", this.getPosition().getIntX(), this.getPosition().getIntY() + 75);
        g2d.setFont(font);
        g2d.setColor(new Color(0, 100, 0));
        g2d.drawString("Cash: " + (int) this.cash + "$", this.getPosition().getIntX(), this.getPosition().getIntY() + 100);
        g2d.setFont(font);
        g2d.setColor(Color.YELLOW);
        g2d.drawString("Gold: " + (int) this.gold, this.getPosition().getIntX(), this.getPosition().getIntY() + 125);
        g2d.setColor(new Color(180, 0, 0));
        g2d.setFont(font);
        g2d.drawString("Happiness: " + (int) this.happiness + "%", this.getPosition().getIntX(), this.getPosition().getIntY() + 150);

    }
    @Override
    public void step() {
        Common.orderFactory(Common.getRandomGenerator().nextInt(4), this.getHappiness(), this);
    }
    // TODO
    // Country image is 150 x 150
    // Name RGB --> Black
    // Worth RGB --> Blue
    // Cash RGB --> (0, 100, 0)
    // Gold RGB --> Yellow
    // Happiness RGB --> (180, 0, 0)
}