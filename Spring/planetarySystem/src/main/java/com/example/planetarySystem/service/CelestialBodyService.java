package com.example.planetarySystem.service;

import com.example.planetarySystem.DAO.CelestialBodyDao;
import com.example.planetarySystem.model.CelestialBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CelestialBodyService {

    private final CelestialBodyDao celestialBodyDao;

    @Autowired
    public CelestialBodyService(@Qualifier("fakeDao") CelestialBodyDao celestialBodyDao) {
        this.celestialBodyDao = celestialBodyDao;
    }

    public int addCelestialBody(CelestialBody celestialBody){
        return celestialBodyDao.insertCelestialBody(celestialBody);
    }

    public List<CelestialBody> getAllCelestialBody(){
        return celestialBodyDao.selectAllCelestialBody();
    }
}
