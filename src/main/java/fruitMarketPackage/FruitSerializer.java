package fruitMarketPackage;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class FruitSerializer extends StdSerializer {

    public FruitSerializer() {
        super(Fruits.class);
    }

    public FruitSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        Fruits fruits = (Fruits)o;
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("Fruit");
        jsonGenerator.writeString(fruits.name());
        jsonGenerator.writeFieldName("shelfLife");
        jsonGenerator.writeString(String.valueOf(fruits.getShelfLife()));
        jsonGenerator.writeEndObject();
    }
}
