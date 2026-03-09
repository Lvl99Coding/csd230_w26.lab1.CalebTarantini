package csd230.lab1.controllers;

public class AcousticGuitarNotFoundException extends RuntimeException {
    public AcousticGuitarNotFoundException(Long id) {

        super("Could not find AcousticGuitar with id " + id);
    }
}
