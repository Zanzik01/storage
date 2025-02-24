package storage.zanzik01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ThingDtoResponse {

    private String name;

    private Long count;
}
