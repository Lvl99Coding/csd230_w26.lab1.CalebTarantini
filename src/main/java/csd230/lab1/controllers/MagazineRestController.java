package csd230.lab1.controllers;

import csd230.lab1.entities.MagazineEntity;
import csd230.lab1.repositories.MagazineEntityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Magazine REST API", description = "JSON API for managing magazines")
@RestController
@RequestMapping("/api/rest/magazines")
@CrossOrigin(origins = "*")
public class MagazineRestController {
    private final MagazineEntityRepository repository;

    public MagazineRestController(MagazineEntityRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get all magazines", description = "Returns a list of all magazines in the database")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of magazines")
    @GetMapping
    public List<MagazineEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new magazine", description = "Adds a new magazine to the database")
    @ApiResponse(responseCode = "201", description = "Magazine created successfully")
    @PostMapping
    public MagazineEntity newMagazine(@RequestBody MagazineEntity newMagazine) {
        return repository.save(newMagazine);
    }

    @Operation(summary = "Get a magazine by ID", description = "Returns a single magazine based on its ID")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of magazine")
    @GetMapping("/{id}")
    public MagazineEntity one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new MagazineNotFoundException(id));
    }

    @Operation(summary = "Replace a magazine", description = "Updates an existing magazine with new data")
    @ApiResponse(responseCode = "200", description = "Magazine updated successfully")
    @PutMapping("/{id}")
    public MagazineEntity replaceMagazine(@RequestBody MagazineEntity newMagazine, @PathVariable Long id) {
        return repository.findById(id)
                .map(magazine -> {
                    magazine.setTitle(newMagazine.getTitle());
                    magazine.setPrice(newMagazine.getPrice());
                    magazine.setCopies(newMagazine.getCopies());
                    magazine.setOrderQty(newMagazine.getOrderQty());
                    magazine.setCurrentIssue(newMagazine.getCurrentIssue());
                    return repository.save(magazine);
                })
                .orElseThrow(() -> new MagazineNotFoundException(id));
    }

    @Operation(summary = "Delete a magazine", description = "Removes a magazine from the database based on its ID")
    @ApiResponse(responseCode = "204", description = "Magazine deleted successfully")
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }
}