public class Shake extends State {
    Shake(Corporation corporation) {
        super(corporation);
    }

    @Override
    public void performAction() {
        // Randomly generated new X will be set
        // Three types are used for not going outside of the screen
        // e.g. if close to right then, go left, and etc.
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
        // After movement works are done, check if an order touch with this corporate, and handle issues accordingly.
        this.handleSellArms();
    }
    // TODO
}