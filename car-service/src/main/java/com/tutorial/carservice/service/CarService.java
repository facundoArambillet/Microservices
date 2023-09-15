package com.tutorial.carservice.service;

import com.tutorial.carservice.model.Car;
import java.util.List;

public interface CarService {

    List<Car> getAll();
    List<Car> findByIdUser(int idUser);
    Car getById(int idCar);
    Car createCar(Car car);

}
