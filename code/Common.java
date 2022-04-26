import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Common {
    private static final String title = "Arms Race";
    private static final int windowWidth = 1650;
    private static final int windowHeight = 1000;

    private static final int firstVerticalLineX = 500;
    private static final int secondVerticalLineX = 1250;
    private static final int horizontalLineY = 100;

    private static final Random randomGenerator = new Random(1234);

    private static final LivePrice foodPrice = new LivePrice(30, 65, "Food Products", 5, 1, 1, 10);
    private static final LivePrice electronicsPrice = new LivePrice(580, 65, "Consumer Electronics", 30, 2, 10, 50);
    private static final LivePrice goldPrice = new LivePrice(1300, 65, "Gold", 75, 3, 50, 100);

    // Initialization of Countries
    private static final Country mexico = new Country(150, 670, "Mexico", goldPrice.getCurrentPrice() * 50 + 5000, 5000, 50, 50, "MX");
    private static final Country chile = new Country(425, 670, "Chile", goldPrice.getCurrentPrice() * 50 + 5000, 5000, 50, 50, "CL");
    private static final Country poland = new Country(700, 670, "Poland", goldPrice.getCurrentPrice() * 50 + 5000, 5000, 50, 50, "PL");
    private static final Country nigeria = new Country(975, 670, "Nigeria", goldPrice.getCurrentPrice() * 50 + 5000, 5000, 50, 50, "NG");
    private static final Country malaysia = new Country(1250, 670, "Malaysia", goldPrice.getCurrentPrice() * 50 + 5000, 5000, 50, 50, "MY");

    // Declaration of Corporations
    private static final Corporation lockheed_martin;
    private static final Corporation raytheon;
    private static final Corporation boeing;
    private static final Corporation northrop_grumman;
    private static final Corporation general_dynamics;

    // Initialization of orders. I held the orders of countries in the common class.
    // DeletedOrders is used for holding the information of will be deleted orders after the loop of orders.
    private static final List<Order> orders = new ArrayList<>();
    private static final List<Order> deletedOrders = new ArrayList<>();

    // getters of much thing
    public static String getTitle() {
        return title;
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }


    public static int getFirstVerticalLineX() {
        return firstVerticalLineX;
    }

    public static int getSecondVerticalLineX() {
        return secondVerticalLineX;
    }

    public static int getHorizontalLineY() {
        return horizontalLineY;
    }


    public static Random getRandomGenerator() {
        return randomGenerator;
    }


    public static LivePrice getFoodPrice() {
        return foodPrice;
    }

    public static LivePrice getElectronicsPrice() {
        return electronicsPrice;
    }

    public static LivePrice getGoldPrice() {
        return goldPrice;
    }


    public static Country getMexico() {
        return mexico;
    }

    public static Country getChile() {
        return chile;
    }

    public static Country getPoland() {
        return poland;
    }

    public static Country getNigeria() {
        return nigeria;
    }

    public static Country getMalaysia() {
        return malaysia;
    }


    public static Corporation getLockheed_martin() {
        return lockheed_martin;
    }

    public static Corporation getRaytheon() {
        return raytheon;
    }

    public static Corporation getBoeing() {
        return boeing;
    }

    public static Corporation getNorthrop_grumman() {
        return northrop_grumman;
    }

    public static Corporation getGeneral_dynamics() {
        return general_dynamics;
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static List<Order> getDeletedOrders() {
        return deletedOrders;
    }

    static {
        // TODO: Here you can instantiate entities/fields
        // Initialization of Corporations
        lockheed_martin = new Corporation(100, 300, "lockheed_martin", 0, "LMT");
        stateFactory(randomGenerator.nextInt(4), lockheed_martin);
        raytheon = new Corporation(425, 300, "raytheon", 0, "RTX");
        stateFactory(randomGenerator.nextInt(4), raytheon);
        boeing = new Corporation(750, 300, "boeing", 0, "BA");
        stateFactory(randomGenerator.nextInt(4), boeing);
        northrop_grumman = new Corporation(1075, 300, "northrop_grumman", 0, "NOC");
        stateFactory(randomGenerator.nextInt(4), northrop_grumman);
        general_dynamics = new Corporation(1400, 300, "general_dynamics", 0, "GD");
        stateFactory(randomGenerator.nextInt(4), general_dynamics);
    }

    public static void stepAllEntities() {
        // Randomly food - electronic and gold price changes.

        if (randomGenerator.nextInt(50) == 0) foodPrice.step();
        if (randomGenerator.nextInt(50) == 0) electronicsPrice.step();
        if (randomGenerator.nextInt(50) == 0) {
            // After changing gold price, countries worth is updated
            goldPrice.step();
            mexico.setWorth(mexico.getCash() + mexico.getGold() * Common.getGoldPrice().getCurrentPrice());
            chile.setWorth(chile.getCash() + chile.getGold() * Common.getGoldPrice().getCurrentPrice());
            poland.setWorth(poland.getCash() + poland.getGold() * Common.getGoldPrice().getCurrentPrice());
            nigeria.setWorth(nigeria.getCash() + nigeria.getGold() * Common.getGoldPrice().getCurrentPrice());
            malaysia.setWorth(malaysia.getCash() + malaysia.getGold() * Common.getGoldPrice().getCurrentPrice());
        }
        // TODO: call other entities' step()
        // Step functions of Corporations
        // Random time is used in functions. For more informations go and read their comments.
        lockheed_martin.step();
        raytheon.step();
        boeing.step();
        northrop_grumman.step();
        general_dynamics.step();

        // Randomly generated step functions of Countries
        if (randomGenerator.nextInt(30) == 0) mexico.step();
        if (randomGenerator.nextInt(30) == 0) chile.step();
        if (randomGenerator.nextInt(30) == 0) poland.step();
        if (randomGenerator.nextInt(30) == 0) nigeria.step();
        if (randomGenerator.nextInt(30) == 0) malaysia.step();

        for (Order order : Common.getOrders()) {
            // Step functions of Orders that is currently in the window.
            order.step();
        }
        // If an order is out of window it will be deleted. Hence, it can not be drawn
        orders.removeAll(deletedOrders);
        // Deleted orders list should be cleared.
        deletedOrders.clear();
    }

    // State Factory function is used for generating randomly a state and assign the new state to the Corporation on the function parameter
    // parameter whichState is randomly generated by the function caller
    public static void stateFactory(int whichState, Corporation corporation) {
        if (whichState == 0) {
            corporation.setState(new ChaseClosest(corporation));
        } else if (whichState == 1) {
            corporation.setState(new GotoXY(corporation));

        } else if (whichState == 2) {
            corporation.setState(new Rest(corporation));

        } else {
            corporation.setState(new Shake(corporation));
        }
    }

    // orderFactory function is used for generating randomly an order and adding the new order to the Orders List on the Common Class.
    // parameter whichState is randomly generated by the function caller
    // According to the happiness of country parameter, function will work.
    public static void orderFactory(int whichState, Country country) {
        if (country.getHappiness() < 50.0) {
            if (whichState == 0) {
                orders.add(new BuyGoldOrder(country.getPosition().getIntX(), country.getPosition().getIntY() - 125, Common.getRandomGenerator().nextInt(Common.getWindowWidth()), 100, Common.getRandomGenerator().nextInt(5) + 1, country));
            } else if (whichState == 1) {
                orders.add(new SellGoldOrder(country.getPosition().getIntX(), country.getPosition().getIntY() - 125, Common.getRandomGenerator().nextInt(Common.getWindowWidth()), 100, Common.getRandomGenerator().nextInt(5) + 1, country));

            } else if (whichState == 2) {
                orders.add(new ElectronicsOrder(country.getPosition().getIntX(), country.getPosition().getIntY() - 125, Common.getRandomGenerator().nextInt(Common.getWindowWidth()), 100, Common.getRandomGenerator().nextInt(5) + 1, country));
            } else {
                orders.add(new FoodOrder(country.getPosition().getIntX(), country.getPosition().getIntY() - 125, Common.getRandomGenerator().nextInt(Common.getWindowWidth()), 100, Common.getRandomGenerator().nextInt(5) + 1, country));
            }
        } else {
            // whichState1 is created for equaling the chance of buy or sell gold order.
            int whichState1 = getRandomGenerator().nextInt(2);
            if (whichState1 == 0) {
                orders.add(new BuyGoldOrder(country.getPosition().getIntX(), country.getPosition().getIntY() - 125, Common.getRandomGenerator().nextInt(Common.getWindowWidth()), 100, Common.getRandomGenerator().nextInt(5) + 1, country));
            } else {
                orders.add(new SellGoldOrder(country.getPosition().getIntX(), country.getPosition().getIntY() - 125, Common.getRandomGenerator().nextInt(Common.getWindowWidth()), 100, Common.getRandomGenerator().nextInt(5) + 1, country));
            }
        }

    }
}