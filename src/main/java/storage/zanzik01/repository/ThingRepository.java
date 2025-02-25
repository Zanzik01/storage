package storage.zanzik01.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import storage.zanzik01.model.Thing;

import java.util.Optional;

@Repository
public interface ThingRepository extends CrudRepository<Thing, Long> {

    Optional<Thing> findThingByName(String name);
}
