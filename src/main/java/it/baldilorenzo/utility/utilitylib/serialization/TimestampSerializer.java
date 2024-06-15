package it.baldilorenzo.utility.utilitylib.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class TimestampSerializer extends StdSerializer<Timestamp> implements ContextualSerializer {

    private String format = "dd/MM/yyyy HH:mm:ss.SSSSSS";

    public TimestampSerializer() {
        super(Timestamp.class);
    }

    public TimestampSerializer(String format) {
        super(Timestamp.class);
        this.format = format;
    }

    @Override
    public void serialize(Timestamp timestamp, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        try{
            jsonGenerator.writeString(timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern(format)));
        }catch (Exception e) {
            throw new RuntimeException("Error parsing timestamp with message: " + e.getMessage());
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {

        try{
            return new TimestampSerializer(beanProperty.getAnnotation(Value.class).value());
        } catch (Exception e) {
            return new TimestampSerializer();
        }
    }
}
