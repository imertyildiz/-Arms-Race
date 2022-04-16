import java.awt.*;
import java.util.Random;

public class ElectronicsOrder extends Order {
    private double targetX;
    private double targetY;
    private double speedX;
    private double speedY;
    private int amount;
    private static final Random randomGenerator = new Random(3215);

    public double getTargetX() {
        return targetX;
    }

    public void setTargetX(double targetX) {
        this.targetX = targetX;
    }

    public double getTargetY() {
        return targetY;
    }

    public void setTargetY(double targetY) {
        this.targetY = targetY;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public ElectronicsOrder(double x, double y, double targetX, double targetY, int amount) {
        super(x, y);
        this.targetX = targetX;
        this.targetY = targetY;
        this.amount = amount;
        int speedRandom = randomGenerator.nextInt(50)+20;
        this.speedX = (targetX - this.getPosition().getIntX())/speedRandom;
        this.speedY = (targetY - this.getPosition().getIntY())/speedRandom;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(new Color(0,182,204));
        if (this.getPosition().getIntY() > 100){
            g2d.fillOval(this.getPosition().getIntX(), this.position.getIntY(), 24,24);
        }
    }

    @Override
    public void step() {
        this.getPosition().setX(this.getPosition().getIntX()+ this.speedX);
        this.getPosition().setY(this.getPosition().getIntY()+ this.speedY);

    }
    // TODO
    // RGB --> (0, 182, 204)
}