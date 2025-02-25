package storage.zanzik01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ThingDto {

    private Long id;

    private String name;

    private Long count;
}
