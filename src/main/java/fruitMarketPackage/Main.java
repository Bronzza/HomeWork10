package fruitMarketPackage;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FruitMarket fruitMarket = new FruitMarket();
        fruitMarket.createSomeDeliveries(5);
        List<Delivery> deliveries = null;
        fruitMarket.saveToJsonInFile("delivery.txt");
        fruitMarket.getFruitsStorage().stream().
                forEach(System.out::println);
        System.out.println("\n");
        Date dateToCheck = new GregorianCalendar(2019, 2, 21).getTime();
        fruitMarket.availableFruits(dateToCheck).stream()
                .forEach(System.out::println);
        System.out.println("\n");
        fruitMarket.spoiledFruits(dateToCheck).stream()
                .forEach(System.out::println);
    }
}
