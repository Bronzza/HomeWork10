package fruitMarketPackage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {

    private Fruits fruits;
    private String dateOfDelivery;
    private int price;

    @Override
    public String toString() {
        return "Delivery{" +
                "fruits=" + fruits +
                ", dateOfDelivery='" + dateOfDelivery + '\'' +
                ", price=" + price +
                '}';
    }
}
