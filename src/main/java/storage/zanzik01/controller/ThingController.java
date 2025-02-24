package storage.zanzik01.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import storage.zanzik01.converter.ThingConverter;
import storage.zanzik01.dto.ThingDtoRequest;
import storage.zanzik01.dto.ThingDtoResponse;
import storage.zanzik01.service.ThingService;

@Tag(name = "API для работы с вещами")
@RestController
@RequiredArgsConstructor
public class ThingController {

    private final ThingService thingService;

    @Operation(
            summary = "Запрос для поиска вещи по ID"
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ThingDtoResponse getThingById(@PathVariable long id) {
        return ThingConverter.toThingDtoResponse(thingService.getThingById(id));
    }

    @Operation(
            summary = "Запрос для создания вещи"
    )
    @PostMapping
    public ResponseEntity<ThingDtoResponse> createThing(@RequestBody ThingDtoRequest thingDtoRequest) {
        return ResponseEntity.ok(ThingConverter.toThingDtoResponse(thingService.createThing(thingDtoRequest)));
    }

    @Operation(
            summary = "Запрос для редактирования вещи"
    )
    @PatchMapping("/{id}/name")
    public ResponseEntity<ThingDtoResponse> updateThingName(@PathVariable long id, @RequestBody ThingDtoRequest thingDtoRequest) {
        return ResponseEntity.ok(ThingConverter.toThingDtoResponse(thingService.updateThing(id, thingDtoRequest)));
    }

    @Operation(
            summary = "Запрос для удаления вещи по ID"
    )
    @DeleteMapping("/{id}")
    public void deleteThing(@PathVariable long id) {
        thingService.deleteThing(id);
    }
}
