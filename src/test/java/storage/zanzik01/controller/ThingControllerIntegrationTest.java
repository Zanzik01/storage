package storage.zanzik01.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import storage.zanzik01.BaseIntegrationTest;
import storage.zanzik01.dto.ThingDtoRequest;
import storage.zanzik01.dto.ThingDtoResponse;
import storage.zanzik01.model.Place;
import storage.zanzik01.model.Thing;

import java.time.OffsetDateTime;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;

class ThingControllerIntegrationTest extends BaseIntegrationTest {

    private static final String THING_URL = "/thing";

    @Test
    @DisplayName("Должна быть возвращена ошибка при отсутвии вещи")
    void shouldReturn500WithoutThing() {
        // given - when
        ResponseEntity<ThingDtoResponse> response = testRestTemplate
                .exchange(THING_URL + "/11", HttpMethod.GET, null, ThingDtoResponse.class);

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
                .exchange(THING_URL + "/" + thing.getId().toString(), HttpMethod.GET, null, ThingDtoResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ThingDtoResponse thingDtoResponse = response.getBody();
        assertThat(thingDtoResponse).isNotNull();
        assertThat(thingDtoResponse.getName()).isEqualTo("Arduino Nano");
        assertThat(thingDtoResponse.getCount()).isEqualTo(2L);
    }

    @Test
    @DisplayName("Должен успешно обработать запрос создания вещи")
    void shouldCreateThing() {
        // given
        Place place = placeRepository.save(Place.builder()
                .name("Ящик 2")
                .createdDate(OffsetDateTime.now(UTC))
                .build());

        ThingDtoRequest request = ThingDtoRequest.builder()
                .name("Arduino")
                .count(3L)
                .placeId(place.getId())
                .build();

        HttpEntity<Object> requestEntity = new HttpEntity<>(request);

        // when
        ResponseEntity<ThingDtoResponse> response = testRestTemplate
                .exchange(THING_URL, HttpMethod.POST, requestEntity, ThingDtoResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ThingDtoResponse thingDtoResponse = response.getBody();
        assertThat(thingDtoResponse).isNotNull();
        assertThat(thingDtoResponse.getName()).isEqualTo("Arduino");
        assertThat(thingDtoResponse.getCount()).isEqualTo(3L);

        Thing thing = thingRepository.findThingByName("Arduino").orElse(null);
        assertThat(thing).isNotNull();
    }
}