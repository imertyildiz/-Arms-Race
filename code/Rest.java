public class Rest extends State {
    Rest(Corporation corporation) {
        super(corporation);
    }

    @Override
    public void performAction() {
        this.handleSellArms();

    }
    // TODO
}