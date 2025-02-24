package storage.zanzik01.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import storage.zanzik01.BaseIntegrationTest;
import storage.zanzik01.dto.ThingDtoResponse;
import storage.zanzik01.model.Place;
import storage.zanzik01.model.Thing;

import java.time.OffsetDateTime;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;

class ThingControllerIntegrationTest extends BaseIntegrationTest {


    @Test
    @DisplayName("Должна быть возвращена ошибка при отсутвии вещи")
    void shouldReturn500WithoutThing() {
        // given - when
        ResponseEntity<ThingDtoResponse> response = testRestTemplate
                .exchange("/11", HttpMethod.GET, null, ThingDtoResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Должен успешно обработать запрос для получения вещи по id")
    void shouldReturnThingById() {
        // given
        Place place = placeRepository.save(Place.builder()
                .name("Ящик 2")
                .createdDate(OffsetDateTime.now(UTC))
                .build());

        Thing thing = thingRepository.save(Thing.builder()
                .name("Arduino Nano")
                .count(2L)
                .placeId(place.getId())
                .createdDate(OffsetDateTime.now(UTC))
                .build());

        // when
        ResponseEntity<ThingDtoResponse> response = testRestTemplate
                .exchange("/" + thing.getId().toString(), HttpMethod.GET, null, ThingDtoResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ThingDtoResponse thingDtoResponse = response.getBody();
        assertThat(thingDtoResponse).isNotNull();
        assertThat(thingDtoResponse.getName()).isEqualTo("Arduino Nano");
        assertThat(thingDtoResponse.getCount()).isEqualTo(2L);
    }
}