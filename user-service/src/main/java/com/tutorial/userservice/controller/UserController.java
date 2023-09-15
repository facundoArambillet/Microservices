package com.tutorial.userservice.controller;

import com.tutorial.userservice.model.Bike;
import com.tutorial.userservice.model.Car;
import com.tutorial.userservice.model.UserModel;
import com.tutorial.userservice.service.UserService;
import com.tutorial.userservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAll() {
        List<UserModel> users = userService.getAll();
        if(users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/cars/{idUser}")
    public ResponseEntity<List<Car>> getCarsByIdUser(@PathVariable("idUser") int idUser) {
        UserModel user = userService.getById(idUser);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = userService.getCars(idUser);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{idUser}")
    public ResponseEntity<List<Bike>> getBikesByIdUser(@PathVariable("idUser") int idUser) {
        UserModel user = userService.getById(idUser);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Bike> bikes = userService.getBikes(idUser);
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getById(@PathVariable("id") int idUser) {
        UserModel user = userService.getById(idUser);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
        UserModel userNew = userService.createUser(user);
        return ResponseEntity.ok(userNew);
    }

    @PostMapping("/savecar/{idUser}")
    public ResponseEntity<Car> createCar(@PathVariable("idUser") int idUser, @RequestBody Car car) {
        Car carNew = userService.createCar(idUser, car);
        return ResponseEntity.ok(carNew);
    }

    @PostMapping("/savebike/{idUser}")
    public ResponseEntity<Bike> createBike(@PathVariable("idUser") int idUser, @RequestBody Bike bike) {
        Bike bikeNew = userService.createBike(idUser, bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/getAll/{idUser}")
    public ResponseEntity<Map<String,Object>> getAllVehicles(@PathVariable("idUser") int idUser) {
        Map<String,Object> result = userService.getUserAndVehicles(idUser);
        return ResponseEntity.ok(result);
    }
}
