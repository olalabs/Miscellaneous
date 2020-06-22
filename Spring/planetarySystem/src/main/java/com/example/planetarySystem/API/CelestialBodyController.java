package com.example.planetarySystem.API;

import com.example.planetarySystem.model.CelestialBody;
import com.example.planetarySystem.service.CelestialBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/celestialBody")
@RestController
public class CelestialBodyController {
    private final CelestialBodyService celestialBodyService;

    @Autowired
    public CelestialBodyController(CelestialBodyService celestialBodyService) {
        this.celestialBodyService = celestialBodyService;
    }

    @PostMapping
    public void addCelestialBody(@RequestBody CelestialBody celestialBody){
        celestialBodyService.addCelestialBody(celestialBody);
    }
}
