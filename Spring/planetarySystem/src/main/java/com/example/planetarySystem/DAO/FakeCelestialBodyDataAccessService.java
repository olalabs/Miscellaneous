package com.example.planetarySystem.DAO;

import com.example.planetarySystem.model.CelestialBody;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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
}
