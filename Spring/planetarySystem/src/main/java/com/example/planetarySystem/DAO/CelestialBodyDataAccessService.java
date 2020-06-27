package com.example.planetarySystem.DAO;

import com.example.planetarySystem.model.CelestialBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres implementation")
public class CelestialBodyDataAccessService implements CelestialBodyDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CelestialBodyDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertCelestialBody(UUID id, CelestialBody celestialBody) {
        return 0;
    }

    @Override
    public List<CelestialBody> selectAllCelestialBody() {
        final String sql = "SELECT id, name FROM celestialbody";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new CelestialBody(id, name);
        });
    }

    @Override
    public Optional<CelestialBody> selectCelestialBodyById(UUID id) {
        final String sql = "SELECT id, name FROM celestialbody WHERE id = ?";

        CelestialBody celestialBody = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
            UUID celestialBodyId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new CelestialBody(celestialBodyId, name);
        });
        return Optional.ofNullable(celestialBody);
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
