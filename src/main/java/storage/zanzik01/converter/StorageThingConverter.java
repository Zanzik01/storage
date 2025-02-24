package storage.zanzik01.converter;

import lombok.experimental.UtilityClass;
import storage.zanzik01.dto.StorageThingDtoResponse;
import storage.zanzik01.model.Thing;

@UtilityClass
public class StorageThingConverter {

    public StorageThingDtoResponse toThingDtoResponse(Thing thing) {
        return StorageThingDtoResponse.builder()
                .nameThing(thing.getName())
                .countThing(thing.getCount())
                .build();
    }
}
