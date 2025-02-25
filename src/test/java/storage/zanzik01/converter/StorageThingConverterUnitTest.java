package storage.zanzik01.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import storage.zanzik01.dto.StorageThingDtoResponse;
import storage.zanzik01.model.StorageThing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StorageThingConverterUnitTest {

    @Test
    @DisplayName("Должен конвертировать в StorageThingDtoResponse")
    void shouldConvertToStorageThingDtoResponse() {
        // given
        StorageThing storageThing = StorageThing.builder()
                .nameThing("Arduino Nano")
                .countThing(2L)
                .namePlace("1 система")
                .descriptionPlace("Смотри внимательно")
                .build();

        // when
        StorageThingDtoResponse response = StorageThingConverter.toThingDtoResponse(storageThing);

        // then
        assertNotNull(response);
        assertThat(response.getNameThing()).isEqualTo(storageThing.getNameThing());
        assertThat(response.getCountThing()).isEqualTo(storageThing.getCountThing());
        assertThat(response.getNamePlace()).isEqualTo(storageThing.getNamePlace());
        assertThat(response.getDescriptionPlace()).isEqualTo(storageThing.getDescriptionPlace());
    }
}
