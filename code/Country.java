import java.awt.*;

public class Country extends Entity {
    private String name;
    private double worth;
    private double cash;
    private double gold;
    private double happiness;

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

    }

    @Override
    public void step() {

    }
    // TODO
    // Country image is 150 x 150
    // Name RGB --> Black
    // Worth RGB --> Blue
    // Cash RGB --> (0, 100, 0)
    // Gold RGB --> Yellow
    // Happiness RGB --> (180, 0, 0)
}