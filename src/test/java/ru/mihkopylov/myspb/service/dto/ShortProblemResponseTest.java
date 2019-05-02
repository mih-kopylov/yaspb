package ru.mihkopylov.myspb.service.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDateTime;
import org.junit.Test;
import ru.mihkopylov.myspb.config.ObjectMapperConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

public class ShortProblemResponseTest {
    private final ObjectMapper mapper = new ObjectMapperConfiguration().objectMapper();

    @Test
    public void testDateTimeDeserialization() throws IOException {
        ShortProblemResponse deserialized =
                mapper.readValue( "{\"dt\":\"2019-04-22T20:20:07.927806\"}", ShortProblemResponse.class );
        assertThat( deserialized.getDt() ).isEqualTo( LocalDateTime.of( 2019, 4, 22, 20, 20, 7, 927806000 ) );

        String serialized = mapper.writeValueAsString( deserialized );
        assertThat( mapper.readTree( serialized ).get( "dt" ).asText() ).isEqualTo( "2019-04-22T20:20:07.927806" );
    }
}