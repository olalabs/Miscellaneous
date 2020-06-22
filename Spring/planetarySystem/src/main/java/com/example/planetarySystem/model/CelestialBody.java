package com.example.planetarySystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CelestialBody {
    private final UUID id;
    private final String name;

    public CelestialBody(@JsonProperty("id") UUID id,
                         @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
