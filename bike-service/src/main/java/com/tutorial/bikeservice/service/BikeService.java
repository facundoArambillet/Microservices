package com.tutorial.bikeservice.service;

import com.tutorial.bikeservice.model.Bike;

import java.util.List;

public interface BikeService {

    List<Bike> getAll();
    List<Bike> findByIdUser(int idUser);
    Bike getById(int idBike);
    Bike createBike(Bike bike);

}
