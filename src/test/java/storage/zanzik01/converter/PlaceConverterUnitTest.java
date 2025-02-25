package storage.zanzik01.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import storage.zanzik01.dto.PlaceDtoResponse;
import storage.zanzik01.model.Place;

import java.time.OffsetDateTime;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlaceConverterUnitTest {

    @Test
    @DisplayName("Должен конвертировать в PlaceDtoResponse")
    void shouldConvertToPlaceDtoResponse() {
        // given
        Place place = Place.builder()
                .id(11L)
                .name("1 система")
                .description("Смотри внимательно")
                .createdDate(OffsetDateTime.now(UTC))
                .build();

        // when
        PlaceDtoResponse placeDtoResponse = PlaceConverter.toPlaceDtoResponse(place);

        // then
        assertNotNull(placeDtoResponse);
        assertThat(placeDtoResponse.getName()).isEqualTo(place.getName());
        assertThat(placeDtoResponse.getDescription()).isEqualTo(place.getDescription());
    }
}
