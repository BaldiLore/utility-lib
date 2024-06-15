package it.baldilorenzo.utility.utilitylib.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> implements ContextualDeserializer {

    private String format = "yyyy-MM-dd";

    public LocalDateDeserializer() {
        super(LocalDate.class);
    }

    public LocalDateDeserializer(String format) {
        super(LocalDate.class);
        this.format = format;
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        try{
            return LocalDate.parse(jsonParser.readValueAs(String.class), DateTimeFormatter.ofPattern(format));
        }catch (Exception e) {
            throw new RuntimeException("Error parsing date with message: " + e.getMessage());
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) {

        try{
            return new LocalDateDeserializer(beanProperty.getAnnotation(Value.class).value());
        } catch (Exception e) {
            return new LocalDateDeserializer();
        }
    }
}
