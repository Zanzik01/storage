package storage.zanzik01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PlaceDtoResponse {

    private String name;

    private String description;

    private Long id;
}
