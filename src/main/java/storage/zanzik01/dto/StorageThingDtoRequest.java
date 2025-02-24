package storage.zanzik01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StorageThingDtoRequest {

    private String nameThing;

    private Long countThing;

    private String namePlace;

    private String descriptionPlace;
}
