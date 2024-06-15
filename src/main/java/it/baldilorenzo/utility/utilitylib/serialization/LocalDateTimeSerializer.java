package it.baldilorenzo.utility.utilitylib.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> implements ContextualSerializer {

    private String format = "yyyy-MM-dd HH:mm:ss.SSSSSSSSS";

    public LocalDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    public LocalDateTimeSerializer(String format) {
        super(LocalDateTime.class);
        this.format = format;
    }

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        try{
            String dateString = localDateTime.format(DateTimeFormatter.ofPattern(format));
            jsonGenerator.writeString(dateString);
        }catch (Exception e) {
            throw new RuntimeException("Error parsing datetime with message: " + e.getMessage());
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {

        try{
            return new LocalDateTimeSerializer(beanProperty.getAnnotation(Value.class).value());
        } catch (Exception e) {
            return new LocalDateTimeSerializer();
        }
    }
}
