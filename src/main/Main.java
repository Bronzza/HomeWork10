package fruitMarketPackage;

import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        FruitMarket fruitMarket = new FruitMarket();
        fruitMarket.createSomeDeliveries(10);
        fruitMarket.saveToJsonInFile("delivery.txt");
        fruitMarket.getFruitsStorage().stream().
                forEach(System.out::println);
        System.out.println("\n");
        Date dateToCheck = new GregorianCalendar(2019, 2, 21).getTime();
        fruitMarket.availableFruits(dateToCheck).stream()
                .forEach(System.out::println);
        System.out.println("\n");
        fruitMarket.spoiledFruits(dateToCheck, FruitsNoShell.BANANA).stream()
                .forEach(System.out::println);
    }
}
