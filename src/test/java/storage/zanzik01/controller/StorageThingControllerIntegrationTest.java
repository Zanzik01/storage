package storage.zanzik01.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import storage.zanzik01.BaseIntegrationTest;
import storage.zanzik01.dto.StorageThingDtoRequest;
import storage.zanzik01.dto.StorageThingDtoResponse;
import storage.zanzik01.model.Place;
import storage.zanzik01.model.Thing;

import static org.assertj.core.api.Assertions.assertThat;

public class StorageThingControllerIntegrationTest extends BaseIntegrationTest {

    private static final String STORAGE_URL = "/storage";

    @Test
    @DisplayName("Должен успешно обработать запрос создания вещи на месте")
    void shouldCreateStorageThing() {
        // given
        StorageThingDtoRequest request = StorageThingDtoRequest.builder()
                .nameThing("Arduino Mega")
                .countThing(3L)
                .namePlace("Ящик стола")
                .descriptionPlace("Вторая полка")
                .build();

        HttpEntity<Object> requestEntity = new HttpEntity<>(request);

        // when
        ResponseEntity<StorageThingDtoResponse> response = testRestTemplate
                .exchange(STORAGE_URL, HttpMethod.POST, requestEntity, StorageThingDtoResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        StorageThingDtoResponse storageThingDtoResponse = response.getBody();
        assertThat(storageThingDtoResponse).isNotNull();
        assertThat(storageThingDtoResponse.getNameThing()).isEqualTo("Arduino Mega");
        assertThat(storageThingDtoResponse.getCountThing()).isEqualTo(3L);
        assertThat(storageThingDtoResponse.getNamePlace()).isEqualTo("Ящик стола");
        assertThat(storageThingDtoResponse.getDescriptionPlace()).isEqualTo("Вторая полка");

        Thing thing = thingRepository.findThingByName("Arduino Mega").orElse(null);
        assertThat(thing).isNotNull();

        Place place = placeRepository.findPlaceByName("Ящик стола").orElse(null);
        assertThat(place).isNotNull();
    }
}
