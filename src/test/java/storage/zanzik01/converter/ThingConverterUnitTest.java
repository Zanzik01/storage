package storage.zanzik01.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import storage.zanzik01.dto.ThingDtoResponse;
import storage.zanzik01.model.Place;
import storage.zanzik01.model.Thing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ThingConverterUnitTest {

    @Test
    @DisplayName("Должен конвертировать в ThingDtoResponse")
    void shouldConvertToThingDtoResponse() {
        // given
        Place place = Place.builder()
                .id(11L)
                .name("1 система")
                .build();
        Thing thing = Thing.builder()
                .id(1L)
                .name("Arduino Nano")
                .count(2L)
                .placeId(place.getId())
                .build();

        // when
        ThingDtoResponse thingDto= ThingConverter.toThingDtoResponse(thing);

        // then
        assertNotNull(thingDto);
        assertThat(thingDto.getName()).isEqualTo(thing.getName());
        assertThat(thingDto.getCount()).isEqualTo(thing.getCount());
    }
}