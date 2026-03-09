package csd230.lab1.controllers;

import csd230.lab1.entities.TicketEntity;
import csd230.lab1.repositories.TicketEntityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Ticket REST API", description = "JSON API for managing Tickets")
@RestController
@RequestMapping("/api/rest/tickets")
@CrossOrigin(origins = "*")
public class TicketRestController {
    private final TicketEntityRepository repository;

    public TicketRestController(TicketEntityRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get all tickets", description = "Returns a list of all tickets in the system")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of tickets")
    @GetMapping
    public List<TicketEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new ticket", description = "Creates) a new ticket with the provided details")
    @ApiResponse(responseCode = "201", description = "Ticket successfully created")
    @PostMapping
    public TicketEntity newTicket(@RequestBody TicketEntity newTicket) {
        return repository.save(newTicket);
    }

    @Operation(summary = "Get a ticket by ID", description = "Returns the ticket with the specified ID")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of the ticket")
    @GetMapping("/{id}")
    public TicketEntity one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new TicketNotFoundException(id));
    }

    @Operation(summary = "Update a ticket", description = "Updates) the ticket with the specified ID using the provided details")
    @ApiResponse(responseCode = "200", description = "Ticket successfully updated")
    @PutMapping("/{id}")
    public TicketEntity replaceTicket(@RequestBody TicketEntity newTicket, @PathVariable Long id) {
        return repository.findById(id)
                .map(Ticket -> {
                    Ticket.setDescription(newTicket.getDescription());
                    Ticket.setPrice(newTicket.getPrice());
                    return repository.save(Ticket);
                })
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    @Operation(summary = "Delete a ticket", description = "Deletes the ticket with the specified ID")
    @ApiResponse(responseCode = "204", description = "Ticket successfully deleted")
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        repository.deleteById(id);
    }
}