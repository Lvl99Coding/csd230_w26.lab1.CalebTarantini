package csd230.lab1.controllers;

import csd230.lab1.entities.CartEntity;
import csd230.lab1.repositories.CartEntityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Cart REST API", description = "JSON API for managing Carts")
@RestController
@RequestMapping("/api/rest/Carts")
@CrossOrigin(origins = "*")
public class CartRestController {
    private final CartEntityRepository repository;

    public CartRestController(CartEntityRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get all carts", description = "Returns a list of all carts in the system")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of carts")
    @GetMapping
    public List<CartEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new cart", description = "Creates a new cart with the provided details")
    @ApiResponse(responseCode = "201", description = "Successfully created a new cart")
    @PostMapping
    public CartEntity newCart(@RequestBody CartEntity newCart) {
        return repository.save(newCart);
    }

    @Operation(summary = "Get a cart by ID", description = "Returns the cart with the specified ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the cart")
    @GetMapping("/{id}")
    public CartEntity one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new CartNotFoundException(id));
    }

    @Operation(summary = "Update a cart", description = "Updates the cart with the specified ID using the provided details")
    @ApiResponse(responseCode = "200", description = "Successfully updated the cart")
    @PutMapping("/{id}")
    public CartEntity replaceCart(@RequestBody CartEntity newCart, @PathVariable Long id) {
        return repository.findById(id)
                .map(Cart -> {
                    Cart.setUser(newCart.getUser());
                    Cart.setProducts(newCart.getProducts());
                    return repository.save(Cart);
                })
                .orElseThrow(() -> new CartNotFoundException(id));
    }

    @Operation(summary = "Delete a cart", description = "Deletes the cart with the specified ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted the cart")
    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id) {
        repository.deleteById(id);
    }
}