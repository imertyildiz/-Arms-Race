import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Country extends Entity {
    private String name;
    private double worth;
    private double cash;
    private double gold;
    private double happiness;
    private Font font = new Font("Verdana", Font.BOLD, 15);
    private List<Order> orderList = new ArrayList<>();
    private static final Random randomGenerator = new Random(3215);

    public Country(double x, double y, String name, double worth, double cash, double gold, double happiness) {
        super(x, y);
        this.name = name;
        this.worth = worth;
        this.cash = cash;
        this.gold = gold;
        this.happiness = happiness;
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
        g2d.drawString("Worth: " + this.worth + "$", this.getPosition().getIntX(), this.getPosition().getIntY() + 75);
        g2d.setFont(font);
        g2d.setColor(new Color(0, 100, 0));
        g2d.drawString("Cash: " + this.cash + "$", this.getPosition().getIntX(), this.getPosition().getIntY() + 100);
        g2d.setFont(font);
        g2d.setColor(Color.YELLOW);
        g2d.drawString("Gold: " + this.gold, this.getPosition().getIntX(), this.getPosition().getIntY() + 125);
        g2d.setColor(new Color(180, 0, 0));
        g2d.setFont(font);
        g2d.drawString("Happiness: " + this.happiness + "%", this.getPosition().getIntX(), this.getPosition().getIntY() + 150);
        for (Order order : this.orderList) {
            order.draw(g2d);
        }
    }

    @Override
    public void step() {
        int random = randomGenerator.nextInt(50);
        if (random == 0) {
            this.orderList.add(new ElectronicsOrder(this.getPosition().getIntX(), this.getPosition().getIntY() - 125, randomGenerator.nextInt(750) + 500, 100, randomGenerator.nextInt(5) + 1));
        }
        for (Order order : this.orderList) {
            order.step();
        }


    }
    // TODO
    // Country image is 150 x 150
    // Name RGB --> Black
    // Worth RGB --> Blue
    // Cash RGB --> (0, 100, 0)
    // Gold RGB --> Yellow
    // Happiness RGB --> (180, 0, 0)
}