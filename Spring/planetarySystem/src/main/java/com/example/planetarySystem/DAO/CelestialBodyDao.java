package com.example.planetarySystem.DAO;

import com.example.planetarySystem.model.CelestialBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CelestialBodyDao {
    int insertCelestialBody(UUID id, CelestialBody celestialBody);

    default int insertCelestialBody(CelestialBody celestialBody){
        UUID id = UUID.randomUUID();
        return insertCelestialBody(id, celestialBody);
    }

    List<CelestialBody> selectAllCelestialBody();

    Optional<CelestialBody> selectCelestialBodyById(UUID id);

    int deleteCeletialBodyId(UUID id);

    int updateCelestialBodyById(UUID id, CelestialBody celestialBody);
}
