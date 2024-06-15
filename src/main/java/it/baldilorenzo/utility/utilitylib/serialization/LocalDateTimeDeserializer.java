package it.baldilorenzo.utility.utilitylib.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> implements ContextualDeserializer {

    private String format = "yyyy-MM-dd HH:mm:ss.SSSSSSSSS";

    public LocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    public LocalDateTimeDeserializer(String format) {
        super(LocalDateTime.class);
        this.format = format;
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        try{
            return LocalDateTime.parse(jsonParser.readValueAs(String.class), DateTimeFormatter.ofPattern(format));
        }catch (Exception e) {
            throw new RuntimeException("Error parsing datetime with message: " + e.getMessage());
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) {

        try{
            return new LocalDateTimeDeserializer(beanProperty.getAnnotation(Value.class).value());
        } catch (Exception e) {
            return new LocalDateTimeDeserializer();
        }
    }
}
