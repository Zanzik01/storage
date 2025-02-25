package storage.zanzik01.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import storage.zanzik01.dto.PlaceDtoRequest;
import storage.zanzik01.dto.StorageThingDtoRequest;
import storage.zanzik01.dto.ThingDtoRequest;
import storage.zanzik01.model.Place;
import storage.zanzik01.model.StorageThing;
import storage.zanzik01.model.Thing;
import storage.zanzik01.repository.StorageThingRepository;

@Service
@RequiredArgsConstructor
public class StorageThingService {

    private final StorageThingRepository storageThingRepository;

    private final PlaceService placeService;

    private final ThingService thingService;

    public StorageThing getThingById(long id) {
        return storageThingRepository.findStorageThingById(id)
                .orElseThrow(() -> new IllegalArgumentException("Thing id not found: " + id));
    }

    public StorageThing createStorageThing(StorageThingDtoRequest storageThingDtoRequest) {
        Place place = placeService.getPlaceByName(storageThingDtoRequest.getNamePlace())
                .orElseGet(() -> placeService.createPlace(PlaceDtoRequest.builder()
                        .name(storageThingDtoRequest.getNamePlace())
                        .description(storageThingDtoRequest.getDescriptionPlace())
                        .build())
                );

        Thing thing = thingService.getThingByName(storageThingDtoRequest.getNameThing())
                .orElseGet(() -> thingService.createThing(ThingDtoRequest.builder()
                        .name(storageThingDtoRequest.getNameThing())
                        .count(storageThingDtoRequest.getCountThing())
                        .placeId(place.getId())
                        .build())
                );

        return StorageThing.builder()
                .nameThing(thing.getName())
                .countThing(thing.getCount())
                .namePlace(place.getName())
                .descriptionPlace(place.getDescription())
                .build();
    }
}
