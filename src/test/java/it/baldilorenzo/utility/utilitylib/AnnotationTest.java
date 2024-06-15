package it.baldilorenzo.utility.utilitylib;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

class AnnotationTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void localDateSerializerTest() throws JsonProcessingException, JSONException {

        Pojo testClass = new Pojo();
        testClass.localDate = LocalDate.of(2000, 5, 26);

        String jsonString = mapper.writeValueAsString(testClass);
        JSONObject jsonObject = new JSONObject(jsonString);

        assertThat(jsonObject.getString("localDate")).isEqualTo("26/05/2000");
    }

    @Test
    void localDateDeserializerTest() throws IOException {
        String json = "{\"localDate\":\"26/05/2000\"}";

        Pojo example = mapper.readValue(json, Pojo.class);
        assertThat(LocalDate.of(2000, 5, 26)).isEqualTo(example.localDate);
    }

    @Test
    void localDateTimeSerializerTest() throws JsonProcessingException, JSONException {

        Pojo testClass = new Pojo();
        testClass.localDateTime = LocalDateTime.of(LocalDate.of(2000, 5, 26), LocalTime.of(12, 30, 0)).withNano(123456789);

        String jsonString = mapper.writeValueAsString(testClass);
        JSONObject jsonObject = new JSONObject(jsonString);

        assertThat(jsonObject.getString("localDateTime")).isEqualTo("26/05/2000 12:30:00.123456789");
    }

    @Test
    void localDateTimeDeserializerTest() throws IOException {
        String json = "{\"localDateTime\":\"26/05/2000 12:30:00.123456789\"}";

        Pojo example = mapper.readValue(json, Pojo.class);
        assertThat(example.localDateTime).isEqualTo(LocalDateTime.of(LocalDate.of(2000, 5, 26), LocalTime.of(12, 30, 0)).withNano(123456789));
    }

    @Test
    void timestampSerializerTest() throws JsonProcessingException, JSONException {

        Pojo testClass = new Pojo();
        testClass.timestamp = Timestamp.valueOf("2000-05-26 12:30:00.123456789");

        String jsonString = mapper.writeValueAsString(testClass);
        JSONObject jsonObject = new JSONObject(jsonString);

        assertThat(jsonObject.getString("timestamp")).isEqualTo("26/05/2000 12:30:00.123456789");
    }

    @Test
    void timestampDeserializerTest() throws IOException {
        String json = "{\"timestamp\":\"26/05/2000 12:30:00.123456789\"}";

        Pojo example = mapper.readValue(json, Pojo.class);
        assertThat(example.timestamp).isEqualTo(Timestamp.valueOf("2000-05-26 12:30:00.123456789"));
    }

}