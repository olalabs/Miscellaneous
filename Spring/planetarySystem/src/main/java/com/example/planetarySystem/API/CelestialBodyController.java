package com.example.planetarySystem.API;

import com.example.planetarySystem.model.CelestialBody;
import com.example.planetarySystem.service.CelestialBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public List<CelestialBody> getAllCelestialBody(){
        return celestialBodyService.getAllCelestialBody();
    }

    @GetMapping(path = "{id}")
    public CelestialBody getCelestialBodyById(@PathVariable("id") UUID id){
        return celestialBodyService.getCelestialBodyById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCelestialBodyById(@PathVariable("id") UUID id){
        celestialBodyService.deleteCeletialBody(id);
    }

    @PutMapping(path = "{id}")
    public void updateCelestialBody(@PathVariable("id") UUID id, @RequestBody CelestialBody celestialBodyToUpdate){
        celestialBodyService.updateCelestialBody(id, celestialBodyToUpdate);
    }
}
