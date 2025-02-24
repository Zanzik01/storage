package storage.zanzik01.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import storage.zanzik01.model.StorageThing;
import storage.zanzik01.repository.StorageThingRepository;

@Service
@RequiredArgsConstructor
public class StorageThingService {

    private final StorageThingRepository storageThingRepository;

    public StorageThing getThingById(long id) {
        return storageThingRepository.findStorageThingById(id)
                .orElseThrow(() -> new IllegalArgumentException("Thing id not found: " + id));
    }
}
