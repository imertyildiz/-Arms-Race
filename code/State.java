public abstract class State {
    // TODO
    Corporation corporation;
    State (Corporation corporation){
        this.corporation = corporation;
    }

    public abstract void performAction();

    protected Order closestOrder() {
        Order closeOrder = null;
        double distance = 999999;
        for (Order order : Common.getOrders()) {
            if(order.getClass().getName().equals("BuyGoldOrder") || order.getClass().getName().equals("SellGoldOrder")){
                double newDistance = order.getPosition().distanceTo(this.corporation.getPosition().getIntX(), this.corporation.getPosition().getIntY());
                if (distance > (newDistance)){
                    closeOrder = order;
                }
            }
        }
        return closeOrder;
    }

    protected void handleSellArms(){
        double corporation_x1 = this.corporation.getPosition().getIntX() -50;
        double corporation_x2=this.corporation.getPosition().getIntX() +50;
        double corporation_y1=this.corporation.getPosition().getIntY() -50;
        double corporation_y2=this.corporation.getPosition().getIntY() +50;
        for (Order order : Common.getOrders()) {

            if(order.getClass().getName().equals("BuyGoldOrder")){
                if (order.getPosition().getIntX() >= (corporation_x1 -25) &&  order.getPosition().getIntX() <= (corporation_x2)){
                    if (order.getPosition().getIntY() >= (corporation_y1 -13) &&  order.getPosition().getIntY() <= (corporation_y2+13)){
                        this.corporation.setCash(this.corporation.getCash() + ((BuyGoldOrder) order).getAmount() * Common.getGoldPrice().getCurrentPrice());
                        if (this.corporation.getCash() >= 2000){
                            this.corporation.setAwardedWhite(true);
                        }
                        if (this.corporation.getCash() >= 4000){
                            this.corporation.setAwardedYellow(true);
                        }
                        if (this.corporation.getCash() >= 6000){
                            this.corporation.setAwardedRed(true);
                        }
                        ((BuyGoldOrder) order).getCountry().setCash(((BuyGoldOrder) order).getCountry().getCash() - ((BuyGoldOrder) order).getAmount() * Common.getGoldPrice().getCurrentPrice());
                        ((BuyGoldOrder) order).getCountry().setWorth(((BuyGoldOrder) order).getCountry().getCash() + ((BuyGoldOrder) order).getCountry().getGold() * Common.getGoldPrice().getCurrentPrice());
                        ((BuyGoldOrder) order).getCountry().setHappiness(((BuyGoldOrder) order).getCountry().getHappiness() - ((BuyGoldOrder) order).getAmount() * 0.1);
                        Common.getDeletedOrders().add(order);
                    }
                }
            }
            else if(order.getClass().getName().equals("SellGoldOrder")){
                if (order.getPosition().getIntX() >= (corporation_x1 -25) &&  order.getPosition().getIntX() <= (corporation_x2)){
                    if (order.getPosition().getIntY() >= (corporation_y1 -13) &&  order.getPosition().getIntY() <= (corporation_y2+13)){
                        this.corporation.setCash(this.corporation.getCash() + ((SellGoldOrder) order).getAmount() * Common.getGoldPrice().getCurrentPrice());
                        if (this.corporation.getCash() >= 2000){
                            this.corporation.setAwardedWhite(true);
                        }
                        if (this.corporation.getCash() >= 4000){
                            this.corporation.setAwardedYellow(true);
                        }
                        if (this.corporation.getCash() >= 6000){
                            this.corporation.setAwardedRed(true);
                        }
                        ((SellGoldOrder) order).getCountry().setGold(((SellGoldOrder) order).getCountry().getGold() - ((SellGoldOrder) order).getAmount() );
                        ((SellGoldOrder) order).getCountry().setWorth(((SellGoldOrder) order).getCountry().getCash() + ((SellGoldOrder) order).getCountry().getGold() * Common.getGoldPrice().getCurrentPrice());
                        ((SellGoldOrder) order).getCountry().setHappiness(((SellGoldOrder) order).getCountry().getHappiness() - ((SellGoldOrder) order).getAmount() * 0.1);
                        Common.getDeletedOrders().add(order);
                    }
                }
            }
        }
        Common.getOrders().removeAll(Common.getDeletedOrders());
        Common.getDeletedOrders().clear();
    }
}