package com.example.planetarySystem.DAO;

import com.example.planetarySystem.model.CelestialBody;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres implementation")
public class CelestialBodyDataAccessService implements CelestialBodyDao {
    @Override
    public int insertCelestialBody(UUID id, CelestialBody celestialBody) {
        return 0;
    }

    @Override
    public List<CelestialBody> selectAllCelestialBody() {
        List<CelestialBody> cb = new ArrayList<>();
        cb.add(new CelestialBody(UUID.randomUUID(), "FROM POSTGRES DB"));
        return cb;
    }

    @Override
    public Optional<CelestialBody> selectCelestialBodyById(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deleteCelestialBodyId(UUID id) {
        return 0;
    }

    @Override
    public int updateCelestialBodyById(UUID id, CelestialBody celestialBody) {
        return 0;
    }
}
