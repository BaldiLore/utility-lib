package it.baldilorenzo.utility.utilitylib.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Value;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampDeserializer extends StdDeserializer<Timestamp> implements ContextualDeserializer {

    private String format = "dd/MM/yyyy HH:mm:ss.SSSSSSSSS";

    public TimestampDeserializer() {
        super(Timestamp.class);
    }

    public TimestampDeserializer(String format) {
        super(Timestamp.class);
        this.format = format;
    }

    @Override
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {

        try{
            return Timestamp.valueOf(LocalDateTime.parse(jsonParser.readValueAs(String.class), DateTimeFormatter.ofPattern(format)));
        }catch (Exception e) {
            throw new RuntimeException("Error parsing timestamp with message: " + e.getMessage());
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) {

        try{
            return new TimestampDeserializer(beanProperty.getAnnotation(Value.class).value());
        } catch (Exception e) {
            return new TimestampDeserializer();
        }
    }
}
