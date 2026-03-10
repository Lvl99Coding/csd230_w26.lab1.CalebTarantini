package csd230.lab1.controllers;

import csd230.lab1.entities.AcousticGuitarEntity;
import csd230.lab1.repositories.AcousticGuitarEntityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "AcousticGuitar REST API", description = "JSON API for managing AcousticGuitars")
@RestController
@RequestMapping("/api/rest/acousticguitars")
@CrossOrigin(origins = "*")
public class AcousticGuitarRestController {
    private final AcousticGuitarEntityRepository repository;

    public AcousticGuitarRestController(AcousticGuitarEntityRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get all AcousticGuitars", description = "Returns a list of all AcousticGuitars in the database")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of AcousticGuitars")
    @GetMapping
    public List<AcousticGuitarEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new AcousticGuitar", description = "Creates a new AcousticGuitar in the database with the provided JSON data")
    @ApiResponse(responseCode = "200", description = "Successfully created a new AcousticGuitar")
    @PostMapping
    public AcousticGuitarEntity newAcousticGuitar(@RequestBody AcousticGuitarEntity newAcousticGuitar) {
        return repository.save(newAcousticGuitar);
    }

    @Operation(summary = "Get a AcousticGuitar by ID", description = "Returns a single AcousticGuitar with the specified ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the AcousticGuitar with the specified ID")
    @GetMapping("/{id}")
    public AcousticGuitarEntity one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new AcousticGuitarNotFoundException(id));
    }

    @Operation(summary = "Replace a AcousticGuitar by ID", description = "Replaces the AcousticGuitar with the specified ID with the provided JSON data")
    @ApiResponse(responseCode = "200", description = "Successfully replaced the AcousticGuitar with the specified ID")
    @PutMapping("/{id}")
    public AcousticGuitarEntity replaceAcousticGuitar(@RequestBody AcousticGuitarEntity newAcousticGuitar, @PathVariable Long id) {
        return repository.findById(id)
                .map(AcousticGuitar -> {
                    AcousticGuitar.setBrand(newAcousticGuitar.getBrand());
                    AcousticGuitar.setModel(newAcousticGuitar.getModel());
                    AcousticGuitar.setNumberOfStrings(newAcousticGuitar.getNumberOfStrings());
                    AcousticGuitar.setHasCutaway(newAcousticGuitar.getHasCutaway());
                    AcousticGuitar.setPrice(newAcousticGuitar.getPrice());
                    return repository.save(AcousticGuitar);
                })
                .orElseThrow(() -> new AcousticGuitarNotFoundException(id));
    }

    @Operation(summary = "Delete a AcousticGuitar by ID", description = "Deletes the AcousticGuitar with the specified ID from the database")
    @ApiResponse(responseCode = "200", description = "Successfully deleted the AcousticGuitar with the specified ID")
    @DeleteMapping("/{id}")
    public void deleteAcousticGuitar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}