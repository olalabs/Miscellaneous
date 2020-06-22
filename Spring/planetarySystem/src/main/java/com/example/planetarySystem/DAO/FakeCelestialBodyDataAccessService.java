package com.example.planetarySystem.DAO;

import com.example.planetarySystem.model.CelestialBody;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeCelestialBodyDataAccessService implements CelestialBodyDao {

    private static List<CelestialBody> DB = new ArrayList<>();

    @Override
    public int insertCelestialBody(UUID id, CelestialBody celestialBody) {
        DB.add(new CelestialBody(id, celestialBody.getName()));
        return 1;
    }

    @Override
    public List<CelestialBody> selectAllCelestialBody() {
        return DB;
    }

    @Override
    public Optional<CelestialBody> selectCelestialBodyById(UUID id) {
        return DB.stream()
                .filter(celestialBody -> celestialBody.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteCeletialBodyId(UUID id) {
        return 0;
    }

    @Override
    public int updateCelestialBodyById(UUID id, CelestialBody celestialBody) {
        return 0;
    }
}
