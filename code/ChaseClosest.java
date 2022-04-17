public class ChaseClosest extends State {
    private Order targetOrder;
    private double distance = 99999999;
    private double targetX;
    private double targetY;
    private double directionX = 0;
    private double directionY = 0;
    private double speed = 0;

    ChaseClosest(Corporation corporation) {
        super(corporation);
        for (Order order : Common.getOrders()) {
            double newDistance = order.getPosition().distanceTo(this.corporation.getPosition().getIntX(), this.corporation.getPosition().getIntY());
            if (this.distance > (newDistance)) {
                this.distance = newDistance;
                this.targetOrder = order;
                this.targetX = order.getPosition().getX();
                this.targetY = order.getPosition().getY();
                this.directionX = (targetX - this.corporation.getPosition().getIntX()) / this.distance;
                this.directionY = (targetY - this.corporation.getPosition().getIntY()) / this.distance;
                this.speed = Common.getRandomGenerator().nextInt(10) + 15;
            }
        }
    }

    @Override
    public void performAction() {
        Order newOrder = this.closestOrder();
        if (this.closestOrder() != this.targetOrder && newOrder != null) {
            double newDistance = newOrder.getPosition().distanceTo(
                    this.corporation.getPosition().getIntX(),
                    this.corporation.getPosition().getIntY());
            this.distance = newDistance;
            this.targetOrder = newOrder;
            this.targetX = this.targetOrder.getPosition().getX();
            this.targetY = this.targetOrder.getPosition().getY();
            this.directionX = (targetX - this.corporation.getPosition().getIntX()) / newDistance;
            this.directionY = (targetY - this.corporation.getPosition().getIntY()) / newDistance;
            this.speed = Common.getRandomGenerator().nextInt(10) + 15;
            this.corporation.getPosition().setX(this.corporation.getPosition().getIntX() + this.directionX * this.speed);
            this.corporation.getPosition().setY(this.corporation.getPosition().getIntY() + this.directionY * this.speed);
        }
        else if (newOrder == null) {
            this.directionX = 0;
            this.directionY = 0;
            this.targetOrder = null;
            this.targetX = 0;
            this.targetY = 0;
        }
        else {
            this.targetX = this.targetOrder.getPosition().getX();
            this.targetY = this.targetOrder.getPosition().getY();
            this.distance = this.targetOrder.getPosition().distanceTo(this.corporation.getPosition().getIntX(), this.corporation.getPosition().getIntY());
            this.directionX = (targetX - this.corporation.getPosition().getIntX()) / this.distance;
            this.directionY = (targetY - this.corporation.getPosition().getIntY()) / this.distance;
            this.corporation.getPosition().setX(this.corporation.getPosition().getIntX() + this.directionX* this.speed);
            this.corporation.getPosition().setY(this.corporation.getPosition().getIntY() + this.directionY* this.speed);
        }
        this.handleSellArms();
    }
    // TODO


}