package storage.zanzik01.converter;

import lombok.experimental.UtilityClass;
import storage.zanzik01.dto.StorageThingDtoResponse;
import storage.zanzik01.model.StorageThing;

@UtilityClass
public class StorageThingConverter {

    public StorageThingDtoResponse toThingDtoResponse(StorageThing thing) {
        return StorageThingDtoResponse.builder()
                .nameThing(thing.getNameThing())
                .countThing(thing.getCountThing())
                .namePlace(thing.getNamePlace())
                .descriptionPlace(thing.getDescriptionPlace())
                .build();
    }
}
