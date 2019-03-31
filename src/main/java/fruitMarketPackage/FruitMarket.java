package fruitMarketPackage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @Getter
    private List<Delivery> fruitsStorage = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();
    private Logger localLoger = Logger.getLogger(FruitMarket.class);

    public void createSomeDeliveries(int howMany) {
        Random rnd = new Random();
        Delivery delivery = null;
        for (int i = 0; i < howMany; i++) {
            delivery = new Delivery();
            delivery.setDateOfDelivery(LocalDateTime.now().minusDays(rnd.nextInt(20) + 10)
                    .format(DateTimeFormatter.ofPattern("dd-MM-yy")));
            delivery.setFruits(getSomeFruit(rnd.nextInt(FruitsNoShell.values().length)));
            delivery.setLifeShell(rnd.nextInt(10) + 5);
            delivery.setPrice(rnd.nextInt(25) + 25);
            fruitsStorage.add(delivery);
        }
    }

    private FruitsNoShell getSomeFruit(int numberOfFruit) {
        switch (numberOfFruit) {
            case 0:
                return FruitsNoShell.APPLE;
            case 1:
                return FruitsNoShell.BANANA;
            case 2:
                return FruitsNoShell.PEAR;
            case 3:
                return FruitsNoShell.PINEAPPLE;
            case 4:
                return FruitsNoShell.STRAWBERRY;
            default:
                localLoger.error("Incorrect input");
                throw new IndexOutOfBoundsException();
        }
    }

    private void saveToJsonInFile() {
        try {
            objectMapper.writeValue(new File("delivery.txt"), fruitsStorage);
        } catch (IOException e) {
            localLoger.error("IO Exception, delivery.txt wasn't found in your root package");
        }
    }

    public void saveToJsonInFile(String filePath) {
        try {
            objectMapper.writeValue(new File(filePath), fruitsStorage);
        } catch (IOException e) {
            localLoger.error("IO Exception, incorrect filePath");
        }
    }

    private List<Delivery> loadFromJsonFromFile() {
        try {
            return objectMapper.readValue(new File("C:\\Users\\Sergey\\IdeaProjects\\HomeWork10\\delivery1.txt"),
                    new TypeReference<List<Delivery>>() {
                    });
        } catch (IOException e) {
            localLoger.error("IO Exception, delivery1.txt wasn't found in your root package");
        }
        return null;
    }

    public List<Delivery> loadFromJsonFromFile(String filePath) {
        try {
            return objectMapper.readValue(new File(filePath),
                    new TypeReference<List<Delivery>>() {
                    });
        } catch (IOException e) {
            localLoger.error("IO Exception, incorrect filePath");
        }
        return null;
    }

    public void addFruitsToStorage(String pathToJsonFile) {
        List<Delivery> fromFileJson = loadFromJsonFromFile(pathToJsonFile);
        fruitsStorage.addAll(fromFileJson);
    }

    public List<Delivery> availableFruits(Date date) {
        final LocalDate localDate = convertDateToLocal(date);

        return fruitsStorage.stream()
                .filter((a) -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
                    return LocalDate.parse(a.getDateOfDelivery(), DateTimeFormatter.ofPattern("dd-MM-yy"))
                            .plusDays(a.getLifeShell()).isAfter(localDate);
                }).collect(Collectors.toList());
    }

    public List<Delivery> availableFruits(Date date, FruitsNoShell fruit) {
        final LocalDate localDate = convertDateToLocal(date);

        return fruitsStorage.stream()
                .filter((a) -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
                    return LocalDate.parse(a.getDateOfDelivery(), DateTimeFormatter.ofPattern("dd-MM-yy"))
                            .plusDays(a.getLifeShell()).isAfter(localDate);
                }).filter((a) -> a.getFruits().equals(fruit))
                .collect(Collectors.toList());
    }

    public List<Delivery> spoiledFruits(Date date) {
        final LocalDate localDate = convertDateToLocal(date);

        return fruitsStorage.stream()
                .filter((a) -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
                    return LocalDate.parse(a.getDateOfDelivery(), DateTimeFormatter.ofPattern("dd-MM-yy"))
                            .plusDays(a.getLifeShell()).isBefore(localDate);
                }).collect(Collectors.toList());
    }

    public List<Delivery> spoiledFruits(Date date, FruitsNoShell fruit) {
        final LocalDate localDate = convertDateToLocal(date);

        return fruitsStorage.stream()
                .filter((a) -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
                    return LocalDate.parse(a.getDateOfDelivery(), DateTimeFormatter.ofPattern("dd-MM-yy"))
                            .plusDays(a.getLifeShell()).isBefore(localDate);
                }).filter((a) -> a.getFruits().equals(fruit))
                .collect(Collectors.toList());
    }

    private LocalDate convertDateToLocal(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
