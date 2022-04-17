public class Shake extends State {
    Shake(Corporation corporation) {
        super(corporation);
    }

    @Override
    public void performAction() {
        if(this.corporation.getPosition().getIntX() < 80){
            this.corporation.getPosition().setX(this.corporation.getPosition().getIntX()+Common.getRandomGenerator().nextInt(10));
        }
        else if (this.corporation.getPosition().getIntX() > (Common.getWindowWidth()-80)){
            this.corporation.getPosition().setX(this.corporation.getPosition().getIntX()-Common.getRandomGenerator().nextInt(10));
        }
        else{
            this.corporation.getPosition().setX(this.corporation.getPosition().getIntX()+Common.getRandomGenerator().nextInt(20)-10);
        }

        if(this.corporation.getPosition().getIntY() < 180){
            this.corporation.getPosition().setY(this.corporation.getPosition().getIntY()+Common.getRandomGenerator().nextInt(10));
        }
        else if (this.corporation.getPosition().getIntY() > (480)){
            this.corporation.getPosition().setY(this.corporation.getPosition().getIntY()-Common.getRandomGenerator().nextInt(10));
        }
        else{
            this.corporation.getPosition().setY(this.corporation.getPosition().getIntY()+Common.getRandomGenerator().nextInt(20)-10);
        }
        this.handleSellArms();
    }
    // TODO
}