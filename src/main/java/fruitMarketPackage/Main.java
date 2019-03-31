package fruitMarketPackage;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FruitMarket fruitMarket = new FruitMarket();
        fruitMarket.createSomeDeliveries(2);
        List<Delivery> deliveries = null;
        fruitMarket.saveToJsonInFile();
        fruitMarket.loadFromJsonFromFile("C:\\Users\\Sergey\\IdeaProjects\\HomeWork10\\delivery1.txt");
        fruitMarket.getFruitsStorage().stream().
                forEach(a -> System.out.println(a));
    }
}
