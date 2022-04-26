public class ChaseClosest extends State {
    // target Order of a Corporation.
    private Order targetOrder;
    // Distance between target and corp.
    private double distance = 99999999;
    // Y value of targetOrder
    private double targetY;
    // X value of targetOrder
    private double targetX;
    // directionX is a direction vector of the Corp in the X.
    private double directionX = 0;
    // directionX is a direction vector of the Corp in the Y.
    private double directionY = 0;
    // speed of the corp.
    private double speed = 0;


    // Initialization of a Chase Closest.
    ChaseClosest(Corporation corporation) {
        super(corporation);
        // Finding closest order and it's settings.
        for (Order order : Common.getOrders()) {
            double newDistance = order.getPosition().distanceTo(this.corporation.getPosition().getIntX(), this.corporation.getPosition().getIntY());
            if (this.distance > (newDistance)) {
                this.distance = newDistance;
                this.targetOrder = order;
                this.targetX = order.getPosition().getX();
                this.targetY = order.getPosition().getY();
                // directions are calculated here.
                this.directionX = (targetX - this.corporation.getPosition().getIntX()) / this.distance;
                this.directionY = (targetY - this.corporation.getPosition().getIntY()) / this.distance;
                // Random Speed is calculated here.
                this.speed = Common.getRandomGenerator().nextInt(10) + 25;
            }
        }
    }

    // Overriding performAction Function.
    // Here I handle the state spesific issues.
    @Override
    public void performAction() {
        Order newOrder = this.closestOrder();
        // If a new order is the closest order diff from existing one, new random speed is assigned.
        // Other calculations is done here, e.g. directions, targets, etc.
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
            this.speed = Common.getRandomGenerator().nextInt(10) + 25;
            this.corporation.getPosition().setX(this.corporation.getPosition().getIntX() + this.directionX * this.speed);
            this.corporation.getPosition().setY(this.corporation.getPosition().getIntY() + this.directionY * this.speed);
        }
        // if there is no gold order in program it will wait with no movement
        else if (newOrder == null) {
            this.directionX = 0;
            this.directionY = 0;
            this.targetOrder = null;
            this.targetX = 0;
            this.targetY = 0;
        }
        // if the closest order is not change after 1 step of program it will update the coordinates of corporation
        else {
            this.targetX = this.targetOrder.getPosition().getX();
            this.targetY = this.targetOrder.getPosition().getY();
            this.distance = this.targetOrder.getPosition().distanceTo(this.corporation.getPosition().getIntX(), this.corporation.getPosition().getIntY());
            this.directionX = (targetX - this.corporation.getPosition().getIntX()) / this.distance;
            this.directionY = (targetY - this.corporation.getPosition().getIntY()) / this.distance;
            this.corporation.getPosition().setX(this.corporation.getPosition().getIntX() + this.directionX* this.speed);
            this.corporation.getPosition().setY(this.corporation.getPosition().getIntY() + this.directionY* this.speed);
        }
        // After movement works are done, check if an order touch with this corporate, and handle issues accordingly.
        this.handleSellArms();
    }
    // TODO


}