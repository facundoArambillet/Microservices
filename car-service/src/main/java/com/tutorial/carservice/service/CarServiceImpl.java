package com.tutorial.carservice.service;

import com.tutorial.carservice.model.Car;
import com.tutorial.carservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository carRepository;

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> findByIdUser(int idUser) {
        return carRepository.findByIdUser(idUser);
    }

    @Override
    public Car getById(int idCar) {
        return carRepository.findById(idCar).orElse(null);
    }

    @Override
    public Car createCar(Car car) {
        Car carNew = carRepository.save(car);
        return carNew;
    }
}
