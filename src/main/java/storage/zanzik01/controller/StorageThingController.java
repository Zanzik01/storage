package storage.zanzik01.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import storage.zanzik01.converter.StorageThingConverter;
import storage.zanzik01.dto.StorageThingDtoRequest;
import storage.zanzik01.dto.StorageThingDtoResponse;
import storage.zanzik01.service.StorageThingService;

@Tag(name = "API для работы с вещами на местах")
@RestController
@RequiredArgsConstructor
@RequestMapping(
        path = "/storage",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class StorageThingController {

    private final StorageThingService storageThingService;

    @Operation(
            summary = "Запрос для поиска вещи по ID"
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StorageThingDtoResponse getStorageThingById(@PathVariable long id) {
        return StorageThingConverter.toThingDtoResponse(storageThingService.getThingById(id));
    }

    @Operation(
            summary = "Запрос для создания вещи на месте"
    )
    @PostMapping
    public ResponseEntity<StorageThingDtoResponse> createStorageThing(@RequestBody StorageThingDtoRequest storageThingDtoRequest) {
        return ResponseEntity.ok(StorageThingConverter.toThingDtoResponse(storageThingService.createStorageThing(storageThingDtoRequest)));
    }
}
