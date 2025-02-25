package storage.zanzik01.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import storage.zanzik01.converter.PlaceConverter;
import storage.zanzik01.dto.PlaceDtoRequest;
import storage.zanzik01.dto.PlaceDtoResponse;
import storage.zanzik01.service.PlaceService;

@Tag(name = "API для работы с местами")
@RestController
@RequiredArgsConstructor
@RequestMapping(
        path = "/place",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class PlaceController {

    private final PlaceService placeService;

    @Operation(
            summary = "Запрос для поиска места по ID"
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlaceDtoResponse getPlaceById(@PathVariable long id) {
        return PlaceConverter.toPlaceDtoResponse(placeService.getPlaceById(id));
    }

    @Operation(
            summary = "Запрос для создания места"
    )
    @PostMapping
    public ResponseEntity<PlaceDtoResponse> createPlace(@RequestBody PlaceDtoRequest placeDtoRequest) {
        return ResponseEntity.ok(PlaceConverter.toPlaceDtoResponse(placeService.createPlace(placeDtoRequest)));
    }

    @Operation(
            summary = "Запрос для редактирование места"
    )
    @PatchMapping("/{id}/name")
    public ResponseEntity<PlaceDtoResponse> updatePlace(@PathVariable long id, @RequestBody PlaceDtoRequest placeDtoRequest) {
        return ResponseEntity.ok(PlaceConverter.toPlaceDtoResponse(placeService.updatePlace(id, placeDtoRequest)));
    }

    @Operation(
            summary = "Запрос для удаления места по ID"
    )
    @DeleteMapping("/{id}")
    public void deletePlace(@PathVariable long id) {
        placeService.deletePlace(id);
    }
}
