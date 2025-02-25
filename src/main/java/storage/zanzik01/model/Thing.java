package storage.zanzik01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@Builder(toBuilder = true)
@Entity(name = "thing")
@NoArgsConstructor
@AllArgsConstructor
public class Thing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long count;

    private Long placeId;

    private OffsetDateTime createdDate;

    private OffsetDateTime lastUpdatedDate;
}
