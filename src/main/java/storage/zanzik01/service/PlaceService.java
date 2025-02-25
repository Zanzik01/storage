package storage.zanzik01.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import storage.zanzik01.dto.PlaceDtoRequest;
import storage.zanzik01.model.Place;
import storage.zanzik01.repository.PlaceRepository;

import java.time.OffsetDateTime;
import java.util.Optional;

import static java.time.ZoneOffset.UTC;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public Place createPlace(PlaceDtoRequest placeDtoRequest) {
        return placeRepository.save(Place.builder()
                .name(placeDtoRequest.getName())
                .description(placeDtoRequest.getDescription())
                .createdDate(OffsetDateTime.now(UTC))
                .build());
    }

    public Place getPlaceById(long id) {
        return placeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Place id not found: " + id));
    }

    public Optional<Place> getPlaceByName(String name) {
        return placeRepository.findPlaceByName(name);
    }

    public Place updatePlace(long id, PlaceDtoRequest placeDtoRequest) {
        return placeRepository.save(getPlaceById(id).toBuilder()
                .name(placeDtoRequest.getName())
                .description(placeDtoRequest.getDescription())
                .lastUpdatedDate(OffsetDateTime.now(UTC))
                .build());
    }

    public void deletePlace(long id) {
        Place place = getPlaceById(id);
        placeRepository.delete(place);
    }
}
