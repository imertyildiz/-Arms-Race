public class GotoXY extends State {
    private double targetX;
    private double targetY;
    private double speedX;
    private double speedY;
    private int howMany = 0;
    private int speedRandom;
    GotoXY(Corporation corporation) {
        super(corporation);
        targetX = Common.getRandomGenerator().nextInt(Common.getWindowWidth()-240) + 120;
        targetY = Common.getRandomGenerator().nextInt(340)+ 160;
        speedRandom = Common.getRandomGenerator().nextInt(15) + 20;
        this.speedX = (targetX - this.corporation.getPosition().getIntX()) / speedRandom;
        this.speedY = (targetY - this.corporation.getPosition().getIntY()) / speedRandom;
    }

    @Override
    public void performAction() {
        if(this.howMany > this.speedRandom){
            this.targetX = Common.getRandomGenerator().nextInt(Common.getWindowWidth()-240) + 120;
            this.targetY = Common.getRandomGenerator().nextInt(340)+ 160;
            this.speedRandom = Common.getRandomGenerator().nextInt(15) + 20;
            this.speedX = (targetX - this.corporation.getPosition().getIntX()) / this.speedRandom;
            this.speedY = (targetY - this.corporation.getPosition().getIntY()) / this.speedRandom;
            this.howMany = 0;
        }
        else{
            this.corporation.getPosition().setX(this.corporation.getPosition().getIntX() + this.speedX);
            this.corporation.getPosition().setY(this.corporation.getPosition().getIntY() + this.speedY);
            this.howMany++;
        }
        this.handleSellArms();
    }
    // TODO
}