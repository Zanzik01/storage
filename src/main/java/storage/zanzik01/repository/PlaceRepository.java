package storage.zanzik01.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import storage.zanzik01.model.Place;

import java.util.Optional;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Long> {

    Optional<Place> findPlaceByName(String name);
}
