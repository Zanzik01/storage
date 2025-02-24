package storage.zanzik01.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import storage.zanzik01.model.StorageThing;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StorageThingRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String STORAGE_THING_QUERY =
            "SELECT th.name, th.count, pl.name, pl.description " +
                    "FROM thing AS th " +
                    "JOIN place AS pl ON th.place_id = pl.id " +
                    "WHERE th.id = ?";

    @Transactional(readOnly = true)
    public Optional<StorageThing> findStorageThingById(Long id) {
        return Optional.ofNullable(queryForStorageThing(STORAGE_THING_QUERY, id).get(0));
    }

    private List<StorageThing> queryForStorageThing(String query, Object param) {
        return jdbcTemplate.query(query,
                (rs, rowNum) -> StorageThing.builder()
                        .nameThing(rs.getString(1))
                        .countThing(rs.getLong(2))
                        .namePlace(rs.getString(3))
                        .descriptionPlace(rs.getString(4))
                        .build(),
                param);
    }
}
