package fruitMarketPackage;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FruitMarket fruitMarket = new FruitMarket();
        fruitMarket.createSomeDeliveries(5);
        fruitMarket.saveToJsonInFile("delivery1.txt");
        List<Delivery> dfl = fruitMarket.loadFromJsonFromFile("delivery1.txt");

        printLists(fruitMarket.getFruitStorage());
        System.out.println("\n");
        Date dateToCheck = new GregorianCalendar(2019, 2, 21).getTime();
        System.out.println("\n");
        printLists(fruitMarket.availableFruits(dateToCheck));
        printLists(fruitMarket.spoiledFruits(dateToCheck, Fruits.BANANA));
    }

    private static void printLists(List list){
        list.forEach(System.out::println);
    }
}
