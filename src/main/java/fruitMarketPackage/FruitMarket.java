package fruitMarketPackage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FruitMarket {
    private final static String TIME_FORMAT = "dd-MM-yy";
    private final static Logger LOCAL_LOGGER = Logger.getLogger(FruitMarket.class);

    @Getter
    private List<Delivery> fruitStorage = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();

    public void createSomeDeliveries(int quantity) {
        Random rnd = new Random();
        Delivery delivery = null;
        Delivery deliveryFruit = null;
        for (int i = 0; i < quantity; i++) {
            deliveryFruit = new Delivery();
            deliveryFruit.setDateOfDelivery(LocalDateTime.now().minusDays(rnd.nextInt(20) + 10)
                    .format(DateTimeFormatter.ofPattern(TIME_FORMAT)));
            deliveryFruit.setFruits(getSomeFruit(rnd.nextInt(Fruits.values().length)));
            deliveryFruit.setPrice(rnd.nextInt(25) + 25);
            fruitStorage.add(deliveryFruit);
        }
    }

    private Fruits getSomeFruit(int numberOfFruit) {
        switch (numberOfFruit) {
            case 0:
                return Fruits.APPLE;
            case 1:
                return Fruits.BANANA;
            case 2:
                return Fruits.PEAR;
            case 3:
                return Fruits.PINEAPPLE;
            case 4:
                return Fruits.STRAWBERRY;
            default:
                LOCAL_LOGGER.error("Incorrect input");
                throw new IndexOutOfBoundsException();
        }
    }


    public void saveToJsonInFile(String filePath) {
        try {
            objectWriter.with(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                    .writeValue(new File(filePath), fruitStorage);
        } catch (IOException e) {
            LOCAL_LOGGER.error("IO Exception, incorrect filePath");
        }
    }

    public List<Delivery> loadFromJsonFromFile(String filePath) {
        try {
            return objectMapper.reader(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
                    .forType(new TypeReference<List<Delivery>>() {
                    })
                    .readValue(new File(filePath));
        } catch (IOException e) {
            LOCAL_LOGGER.error("IO Exception, incorrect filePath");
        }
        return null;
    }

    public void addFruitsToStorage(String pathToJsonFile) {
        List<Delivery> fromFileJson = loadFromJsonFromFile(pathToJsonFile);
        fruitStorage.addAll(fromFileJson);
    }

    public List<Delivery> availableFruits(Date date) {
        final LocalDate localDate = convertDateToLocal(date);
        return fruitStorage.stream()
                .filter((a) -> {
                    return LocalDate.parse(a.getDateOfDelivery(), DateTimeFormatter.ofPattern(TIME_FORMAT))
                            .plusDays(a.getFruits().getShelfLife()).isAfter(localDate);
                }).collect(Collectors.toList());
    }

    public List<Delivery> availableFruits(Date date, Fruits fruit) {
        return availableFruits(date)
                .stream()
                .filter((a) -> a.getFruits().equals(fruit))
                .collect(Collectors.toList());
    }

    public List<Delivery> spoiledFruits(Date date) {
        final LocalDate localDate = convertDateToLocal(date);
        return fruitStorage.stream()
                .filter((a) -> {
                    return LocalDate.parse(a.getDateOfDelivery(), DateTimeFormatter.ofPattern(TIME_FORMAT))
                            .plusDays(a.getFruits().getShelfLife()).isBefore(localDate);
                }).collect(Collectors.toList());
    }

    public List<Delivery> spoiledFruits(Date date, Fruits fruit) {
        return spoiledFruits(date)
                .stream()
                .filter((a) -> a.getFruits().equals(fruit))
                .collect(Collectors.toList());
    }

    private LocalDate convertDateToLocal(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
