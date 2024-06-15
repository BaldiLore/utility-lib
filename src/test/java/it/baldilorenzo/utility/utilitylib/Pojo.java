package it.baldilorenzo.utility.utilitylib;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.baldilorenzo.utility.utilitylib.serialization.*;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pojo {
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Value("dd/MM/yyyy")
    public LocalDate localDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Value("dd/MM/yyyy HH:mm:ss.SSSSSSSSS")
    public LocalDateTime localDateTime;

    @JsonSerialize(using = TimestampSerializer.class)
    @JsonDeserialize(using = TimestampDeserializer.class)
    @Value("dd/MM/yyyy HH:mm:ss.SSSSSSSSS")
    public Timestamp timestamp;
}
