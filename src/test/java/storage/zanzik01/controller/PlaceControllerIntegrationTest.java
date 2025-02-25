package storage.zanzik01.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import storage.zanzik01.BaseIntegrationTest;
import storage.zanzik01.dto.PlaceDtoRequest;
import storage.zanzik01.dto.PlaceDtoResponse;
import storage.zanzik01.model.Place;

import java.time.OffsetDateTime;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;

public class PlaceControllerIntegrationTest extends BaseIntegrationTest {

    private static final String PLACE_URL = "/place";

    @Test
    @DisplayName("Должен успешно обработать запрос изменения места")
    void shouldUpdatePlace() {
        // given
        Place place = placeRepository.save(Place.builder()
                .name("Ящик 2")
                .createdDate(OffsetDateTime.now(UTC))
                .build());

        PlaceDtoRequest placeDtoRequest = PlaceDtoRequest.builder()
                .name("Ящик 3")
                .description("Он переехал")
                .build();

        HttpEntity<Object> requestEntity = new HttpEntity<>(placeDtoRequest);

        // when
        ResponseEntity<PlaceDtoResponse> response = testRestTemplate
                .exchange(PLACE_URL + "/" + place.getId().toString() + "/name", HttpMethod.PATCH, requestEntity, PlaceDtoResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        assertThat(placeRepository.existsById(place.getId())).isTrue();
        assertThat(placeRepository.findAll()).hasSize(1);

        Place placeUpdated = placeRepository.findById(place.getId()).get();
        assertThat(placeUpdated.getName()).isEqualTo(placeDtoRequest.getName());
        assertThat(placeUpdated.getDescription()).isEqualTo(placeDtoRequest.getDescription());
    }

    @Test
    @DisplayName("Должен успешно обработать запрос удаления места")
    void shouldDeletePlace() {
        // given
        Place place = placeRepository.save(Place.builder()
                .name("Ящик 2")
                .createdDate(OffsetDateTime.now(UTC))
                .build());

        // when
        ResponseEntity<Void> response = testRestTemplate
                .exchange(PLACE_URL + "/" + place.getId(), HttpMethod.DELETE, null, Void.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(placeRepository.existsById(place.getId())).isFalse();
    }
}
