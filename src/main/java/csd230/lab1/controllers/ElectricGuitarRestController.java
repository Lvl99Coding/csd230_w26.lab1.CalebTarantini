package csd230.lab1.controllers;

import csd230.lab1.entities.ElectricGuitarEntity;
import csd230.lab1.repositories.ElectricGuitarEntityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "ElectricGuitar REST API", description = "JSON API for managing ElectricGuitars")
@RestController
@RequestMapping("/api/rest/ElectricGuitars")
@CrossOrigin(origins = "*")
public class ElectricGuitarRestController {
    private final ElectricGuitarEntityRepository repository;

    public ElectricGuitarRestController(ElectricGuitarEntityRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get all ElectricGuitars", description = "Returns a list of all ElectricGuitars in the database")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of ElectricGuitars")
    @GetMapping
    public List<ElectricGuitarEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new ElectricGuitar", description = "Adds a new ElectricGuitar to the database")
    @ApiResponse(responseCode = "200", description = "Successfully created a new ElectricGuitar")
    @PostMapping
    public ElectricGuitarEntity newElectricGuitar(@RequestBody ElectricGuitarEntity newElectricGuitar) {
        return repository.save(newElectricGuitar);
    }

    @Operation(summary = "Get a specific ElectricGuitar by ID", description = "Returns the ElectricGuitar with the specified ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the ElectricGuitar")
    @GetMapping("/{id}")
    public ElectricGuitarEntity one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ElectricGuitarNotFoundException(id));
    }

    @Operation(summary = "Replace an existing ElectricGuitar", description = "Updates the ElectricGuitar with the specified ID")
    @ApiResponse(responseCode = "200", description = "Successfully updated the ElectricGuitar")
    @PutMapping("/{id}")
    public ElectricGuitarEntity replaceElectricGuitar(@RequestBody ElectricGuitarEntity newElectricGuitar, @PathVariable Long id) {
        return repository.findById(id)
                .map(ElectricGuitar -> {
                    ElectricGuitar.setBrand(newElectricGuitar.getBrand());
                    ElectricGuitar.setModel(newElectricGuitar.getModel());
                    ElectricGuitar.setNumberOfStrings(newElectricGuitar.getNumberOfStrings());
                    ElectricGuitar.setNumberOfPickups(newElectricGuitar.getNumberOfPickups());
                    ElectricGuitar.setPrice(newElectricGuitar.getPrice());
                    return repository.save(ElectricGuitar);
                })
                .orElseThrow(() -> new ElectricGuitarNotFoundException(id));
    }

    @Operation(summary = "Delete an ElectricGuitar", description = "Removes the ElectricGuitar with the specified ID from the database")
    @ApiResponse(responseCode = "200", description = "Successfully deleted the ElectricGuitar")
    @DeleteMapping("/{id}")
    public void deleteElectricGuitar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}