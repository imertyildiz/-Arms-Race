public class Rest extends State {
    Rest(Corporation corporation) {
        super(corporation);
    }

    @Override
    public void performAction() {
        // Because there is no movement issues, just check if an order touch with this corporate, and handle issues accordingly.
        this.handleSellArms();
    }
    // TODO
}