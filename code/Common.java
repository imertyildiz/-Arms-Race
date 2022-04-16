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

    // *************************************************** CHANGED BY MERDO **********************************************************

    private static final Country mexico = new Country(150, 670, "Mexico", goldPrice.getCurrentPrice() * 50 + 5000, 5000, 50, 50);
    private static final Country chile = new Country(425, 670, "Chile", goldPrice.getCurrentPrice() * 50 + 5000, 5000, 50, 50);
    private static final Country poland = new Country(700, 670, "Poland", goldPrice.getCurrentPrice() * 50 + 5000, 5000, 50, 50);
    private static final Country nigeria = new Country(975, 670, "Nigeria", goldPrice.getCurrentPrice() * 50 + 5000, 5000, 50, 50);
    private static final Country malaysia = new Country(1250, 670, "Malaysia", goldPrice.getCurrentPrice() * 50 + 5000, 5000, 50, 50);


    private static final Corporation lockheed_martin;
    private static final Corporation raytheon;
    private static final Corporation boeing;
    private static final Corporation northrop_grumman;
    private static final Corporation general_dynamics;
    // *************************************************** CHANGED BY MERDO **********************************************************

    // getters
    public static String getTitle() { return title; }
    public static int getWindowWidth() { return windowWidth; }
    public static int getWindowHeight() { return windowHeight; }


    public static int getFirstVerticalLineX() { return firstVerticalLineX; }
    public static int getSecondVerticalLineX() { return secondVerticalLineX; }
    public static int getHorizontalLineY() { return horizontalLineY; }


    public static Random getRandomGenerator() { return randomGenerator; }


    public static LivePrice getFoodPrice() { return foodPrice; }
    public static LivePrice getElectronicsPrice() { return electronicsPrice; }
    public static LivePrice getGoldPrice() { return goldPrice; }


    public static Country getMexico() { return mexico; }
    public static Country getChile() { return chile; }
    public static Country getPoland() { return poland; }
    public static Country getNigeria() { return nigeria; }
    public static Country getMalaysia() { return malaysia; }


    public static Corporation getLockheed_martin() { return lockheed_martin; }
    public static Corporation getRaytheon() { return raytheon; }
    public static Corporation getBoeing() { return boeing; }
    public static Corporation getNorthrop_grumman() { return northrop_grumman; }
    public static Corporation getGeneral_dynamics() { return general_dynamics; }



    static {
        // TODO: Here you can instantiate entities/fields
        int whichState = 0;
        lockheed_martin = new Corporation(100, 300, "lockheed_martin", 0, "LMT");
        whichState = randomGenerator.nextInt(4);
        lockheed_martin.setState(chooseStateRandom(whichState));
        raytheon = new Corporation(425, 300, "raytheon", 0, "RTX");
        whichState = randomGenerator.nextInt(4);
        raytheon.setState(chooseStateRandom(whichState));
        boeing = new Corporation(750, 300, "boeing", 0, "BA");
        whichState = randomGenerator.nextInt(4);
        boeing.setState(chooseStateRandom(whichState));
        northrop_grumman = new Corporation(1075, 300, "northrop_grumman", 0, "NOC");
        whichState = randomGenerator.nextInt(4);
        northrop_grumman.setState(chooseStateRandom(whichState));
        general_dynamics = new Corporation(1400, 300, "general_dynamics", 0, "GD");
        whichState = randomGenerator.nextInt(4);
        general_dynamics.setState(chooseStateRandom(whichState));
    }

    public static void stepAllEntities() {
        if (randomGenerator.nextInt(200) == 0) foodPrice.step();
        if (randomGenerator.nextInt(300) == 0) electronicsPrice.step();
        if (randomGenerator.nextInt(400) == 0) goldPrice.step();
        // TODO: call other entities' step()
        lockheed_martin.step();
        if (randomGenerator.nextInt(5) == 0) general_dynamics.step();
        mexico.step();
        poland.step();
    }

    private static State chooseStateRandom(int whichState) {
        if (whichState == 0) {
            return new ChaseClosest();
        } else if (whichState == 1) {
            return new GotoXY();

        } else if (whichState == 2) {
            return new Rest();

        } else {
            return new Shake();
        }
    }
}