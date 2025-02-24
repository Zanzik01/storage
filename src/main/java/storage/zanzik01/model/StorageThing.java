package storage.zanzik01.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class StorageThing {

    private String nameThing;

    private Long countThing;

    private String namePlace;

    private String descriptionPlace;
}
