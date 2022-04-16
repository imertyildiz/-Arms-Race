import java.awt.*;

public class FoodOrder extends Order {
    private final double speedX;
    private final double speedY;
    private final int amount;
    private final Country country;

    public FoodOrder(double x, double y, double targetX, double targetY, int amount, Country country) {
        super(x, y);
        this.amount = amount;
        this.country = country;
        int speedRandom = Common.getRandomGenerator().nextInt(50) + 20;
        this.speedX = (targetX - this.getPosition().getIntX()) / speedRandom;
        this.speedY = (targetY - this.getPosition().getIntY()) / speedRandom;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(new Color(245, 222, 179));
        g2d.drawString(this.country.getNickName(), this.getPosition().getIntX(), this.getPosition().getIntY() - 10);
        g2d.fillOval(this.getPosition().getIntX(), this.position.getIntY(), 24, 24);
        g2d.setColor(Color.BLACK);
        g2d.drawString("" + this.amount, this.getPosition().getIntX() + 7, this.getPosition().getIntY() + 15);

    }

    @Override
    public void step() {
        this.getPosition().setX(this.getPosition().getIntX() + this.speedX);
        this.getPosition().setY(this.getPosition().getIntY() + this.speedY);
        if (this.getPosition().getIntY() <= 100) {
            this.country.setCash(this.country.getCash() - amount * Common.getFoodPrice().getCurrentPrice());
            this.country.setHappiness(this.country.getHappiness() + amount * 0.2);
            this.country.setWorth(this.country.getCash() + this.country.getGold() * Common.getGoldPrice().getCurrentPrice());
            Common.getDeletedOrders().add(this);
        }
    }
    // TODO
    // RGB --> (245, 222, 179)
}