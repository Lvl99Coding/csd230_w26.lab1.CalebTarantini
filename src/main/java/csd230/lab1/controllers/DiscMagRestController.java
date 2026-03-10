package csd230.lab1.controllers;

import csd230.lab1.entities.DiscMagEntity;
import csd230.lab1.repositories.DiscMagEntityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "discMag REST API", description = "JSON API for managing discMags")
@RestController
@RequestMapping("/api/rest/discmags")
@CrossOrigin(origins = "*")
public class DiscMagRestController {
    private final DiscMagEntityRepository repository;

    public DiscMagRestController(DiscMagEntityRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get all discMags", description = "Returns a list of all discMags in the database")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping
    public List<DiscMagEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new discMag", description = "Creates a new discMag in the database")
    @ApiResponse(responseCode = "201", description = "DiscMag created successfully")
    @PostMapping
    public DiscMagEntity newDiscMag(@RequestBody DiscMagEntity newDiscMag) {
        return repository.save(newDiscMag);
    }

    @Operation(summary = "Get a discMag by ID", description = "Returns a single discMag by its ID")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping("/{id}")
    public DiscMagEntity one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new DiscMagNotFoundException(id));
    }

    @Operation(summary = "Replace a discMag", description = "Replaces an existing discMag with new data")
    @ApiResponse(responseCode = "200", description = "DiscMag replaced successfully")
    @PutMapping("/{id}")
    public DiscMagEntity replacediscMag(@RequestBody DiscMagEntity newDiscMag, @PathVariable Long id) {
        return repository.findById(id)
                .map(discMag -> {
                    discMag.setTitle(newDiscMag.getTitle());
                    discMag.setPrice(newDiscMag.getPrice());
                    discMag.setCopies(newDiscMag.getCopies());
                    discMag.setOrderQty(newDiscMag.getOrderQty());
                    discMag.setHasDisc(newDiscMag.getHasDisc());
                    return repository.save(discMag);
                })
                .orElseThrow(() -> new DiscMagNotFoundException(id));
    }

    @Operation(summary = "Delete a discMag", description = "Deletes a discMag by its ID")
    @ApiResponse(responseCode = "204", description = "DiscMag deleted successfully")
    @DeleteMapping("/{id}")
    public void deletediscMag(@PathVariable Long id) {
        repository.deleteById(id);
    }
}