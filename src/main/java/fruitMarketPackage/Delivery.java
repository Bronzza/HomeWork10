package fruitMarketPackage;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Delivery {

    public Delivery() {
    }

    @Getter
    @Setter
    private FruitsNoShell fruits;
    @Getter
    @Setter
    private int lifeShell;
    @Getter
    @Setter
    private String dateOfDelivery;
    @Getter
    @Setter
    private int price;

    @Override
    public String toString() {
        return "Delivery{" +
                "fruits=" + fruits +
                ", lifeShell=" + lifeShell +
                ", dateOfDelivery='" + dateOfDelivery + '\'' +
                ", price=" + price +
                '}';
    }
}
