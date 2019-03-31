package fruitMarketPackage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FruitMarketTest {

    ObjectMapper objectMapper;
    FruitMarket fruitMarket;

    @Before
    public void setUp() {
        fruitMarket = new FruitMarket();
        objectMapper = new ObjectMapper();
        fruitMarket.createSomeDeliveries(1);
        fruitMarket.saveToJsonInFile("testSave.txt");
    }

    @Test
    public void saveToJsonInFile() {
        assertEquals(fruitMarket.getFruitsStorage().get(0).getFruits(),
                fruitMarket.loadFromJsonFromFile("testSave.txt").get(0).getFruits());
        assertEquals(fruitMarket.getFruitsStorage().get(0).getDateOfDelivery(),
                fruitMarket.loadFromJsonFromFile("testSave.txt").get(0).getDateOfDelivery());

    }

    @Test
    public void loadFromJsonFromFile() {
        fruitMarket.createSomeDeliveries(1);
        String dateForTest = fruitMarket.getFruitsStorage().get(0).getDateOfDelivery();
        fruitMarket.saveToJsonInFile("otherFile");
        fruitMarket.loadFromJsonFromFile("otherFile");
        assertEquals(dateForTest, fruitMarket.getFruitsStorage().get(0).getDateOfDelivery());
    }

    @Test
    public void addFruitsToStorage() {
        int sizeOfStorage = fruitMarket.getFruitsStorage().size();
        assertEquals(1, sizeOfStorage);
        fruitMarket.addFruitsToStorage("testSave.txt");
        sizeOfStorage++;
        assertEquals(sizeOfStorage, fruitMarket.getFruitsStorage().size());
    }
}
