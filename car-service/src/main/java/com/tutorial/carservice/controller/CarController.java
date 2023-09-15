package com.tutorial.carservice.controller;

import com.tutorial.carservice.model.Car;
import com.tutorial.carservice.service.CarService;
import com.tutorial.carservice.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarServiceImpl carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        List<Car> cars = carService.getAll();
        if(cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable("id") int idUser) {
        Car car = carService.getById(idUser);
        if(car == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @GetMapping("/byuser/{idUser}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("idUser") int idUser) {
        List<Car> cars = carService.findByIdUser(idUser);
        return ResponseEntity.ok(cars);
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car carNew = carService.createCar(car);
        return ResponseEntity.ok(car);
    }
}
