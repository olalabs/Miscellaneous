package com.example.planetarySystem.service;

import com.example.planetarySystem.DAO.CelestialBodyDao;
import com.example.planetarySystem.model.CelestialBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CelestialBodyService {

    private final CelestialBodyDao celestialBodyDao;

    @Autowired
    public CelestialBodyService(@Qualifier("postgres implementation") CelestialBodyDao celestialBodyDao) {
        this.celestialBodyDao = celestialBodyDao;
    }

    public int addCelestialBody(CelestialBody celestialBody){
        return celestialBodyDao.insertCelestialBody(celestialBody);
    }

    public List<CelestialBody> getAllCelestialBody(){
        return celestialBodyDao.selectAllCelestialBody();
    }

    public Optional<CelestialBody> getCelestialBodyById(UUID id){
        return celestialBodyDao.selectCelestialBodyById(id);
    }

    public int deleteCeletialBody(UUID id){
        return celestialBodyDao.deleteCelestialBodyId(id);
    }

    public int updateCelestialBody(UUID id, CelestialBody newCelestialBody){
        return celestialBodyDao.updateCelestialBodyById(id, newCelestialBody);
    }
}
