package storage.zanzik01.converter;

import lombok.experimental.UtilityClass;
import storage.zanzik01.dto.ThingDtoResponse;
import storage.zanzik01.model.Thing;

@UtilityClass
public class ThingConverter {

    public ThingDtoResponse toThingDtoResponse(Thing thing) {
        return ThingDtoResponse.builder()
                .name(thing.getName())
                .count(thing.getCount())
                .build();
    }
}
