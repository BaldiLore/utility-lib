package it.baldilorenzo.utility.utilitylib.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer extends StdSerializer<LocalDate> implements ContextualSerializer {

    private String format = "yyyy-MM-dd";

    public LocalDateSerializer() {
        super(LocalDate.class);
    }

    public LocalDateSerializer(String format) {
        super(LocalDate.class);
        this.format = format;
    }

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {

        try{
            String dateString = localDate.format(DateTimeFormatter.ofPattern(format));
            jsonGenerator.writeString(dateString);
        }catch (Exception e) {
            throw new RuntimeException("Error parsing date with message: " + e.getMessage());
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty beanProperty) {

        try{
            return new LocalDateSerializer(beanProperty.getAnnotation(Value.class).value());
        } catch (Exception e) {
            return new LocalDateSerializer();
        }
    }
}