public class GotoXY extends State {
    // Y value of targetOrder
    private double targetY;
    // X value of targetOrder
    private double targetX;
    // speed of corp in X
    private double speedX;
    // speed of corp in Y
    private double speedY;
    // Howmany is used for checking the whether corp is went to target.
    // howmany is like step. E.g. corp will go (x1,y1). It takes 15 step (randomly generated speedRandom).
    private int howMany = 0;
    // Randomly generated step count of a corp.
    // step count is directly affect the speed.
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
        // If the corp is went to the target, a new coordinate is randomly choosen and step count is rondomly generated.
        // step count (speedRandom) is directly affect the speed.
        if(this.howMany > this.speedRandom){
            this.targetX = Common.getRandomGenerator().nextInt(Common.getWindowWidth()-240) + 120;
            this.targetY = Common.getRandomGenerator().nextInt(340)+ 160;
            this.speedRandom = Common.getRandomGenerator().nextInt(15) + 20;
            this.speedX = (targetX - this.corporation.getPosition().getIntX()) / this.speedRandom;
            this.speedY = (targetY - this.corporation.getPosition().getIntY()) / this.speedRandom;
            this.howMany = 0;
        }
        // If movement is continue, then necessary issues are handled.
        else{
            this.corporation.getPosition().setX(this.corporation.getPosition().getIntX() + this.speedX);
            this.corporation.getPosition().setY(this.corporation.getPosition().getIntY() + this.speedY);
            this.howMany++;
        }
        // After movement works are done, check if an order touch with this corporate, and handle issues accordingly.
        this.handleSellArms();
    }
    // TODO
}