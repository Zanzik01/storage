package storage.zanzik01.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;;
import storage.zanzik01.dto.ThingDtoRequest;
import storage.zanzik01.model.Thing;
import storage.zanzik01.repository.ThingRepository;

import java.time.OffsetDateTime;

import static java.time.ZoneOffset.UTC;

@Service
@RequiredArgsConstructor
public class ThingService {

    private final ThingRepository thingRepository;

    public Thing createThing(ThingDtoRequest thingDtoRequest) {
        return thingRepository.save(Thing.builder()
                .name(thingDtoRequest.getName())
                .count(thingDtoRequest.getCount())
                .createdDate(OffsetDateTime.now())
                .placeId(thingDtoRequest.getPlaceId())
                .build());
    }

    public Thing getThingById(long id) {
        return thingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Thing id not found: " + id));
    }

    public Thing updateThing(long id, ThingDtoRequest thingDtoRequest) {
        return thingRepository.save(getThingById(id).builder()
                .name(thingDtoRequest.getName())
                .count(thingDtoRequest.getCount())
                .placeId(thingDtoRequest.getPlaceId())
                .lastUpdatedDate(OffsetDateTime.now(UTC))
                .build());
    }

    public void deleteThing(long id) {
        Thing thing = getThingById(id);
        thingRepository.delete(thing);
    }
}
