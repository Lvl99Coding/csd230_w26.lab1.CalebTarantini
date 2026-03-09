package csd230.lab1.controllers;

public class ElectricGuitarNotFoundException extends RuntimeException {
    public ElectricGuitarNotFoundException(Long id) {

        super("Could not find ElectricGuitar with id " + id);
    }
}
