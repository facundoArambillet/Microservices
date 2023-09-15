package com.tutorial.bikeservice.service;

import com.tutorial.bikeservice.model.Bike;
import com.tutorial.bikeservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeServiceImpl implements BikeService {
    @Autowired
    BikeRepository bikeRepository;

    @Override
    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    @Override
    public List<Bike> findByIdUser(int idUser) {
        return bikeRepository.findByIdUser(idUser);
    }

    @Override
    public Bike getById(int idBike) {
        return bikeRepository.findById(idBike).orElse(null);
    }

    @Override
    public Bike createBike(Bike bike) {
        Bike bikeNew = bikeRepository.save(bike);
        return bikeNew;
    }
}
