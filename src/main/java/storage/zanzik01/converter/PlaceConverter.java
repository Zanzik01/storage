package storage.zanzik01.converter;

import lombok.experimental.UtilityClass;
import storage.zanzik01.dto.PlaceDtoResponse;
import storage.zanzik01.model.Place;

@UtilityClass
public class PlaceConverter {

    public PlaceDtoResponse toPlaceDtoResponse(Place place) {
        return PlaceDtoResponse.builder()
                .name(place.getName())
                .description(place.getDescription())
                .id(place.getId())
                .build();
    }
}
