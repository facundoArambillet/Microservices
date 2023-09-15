package com.tutorial.userservice.controller;

import com.tutorial.userservice.model.Bike;
import com.tutorial.userservice.model.Car;
import com.tutorial.userservice.model.UserModel;
import com.tutorial.userservice.service.UserService;
import com.tutorial.userservice.service.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallbackGetCars")
    @GetMapping("/cars/{idUser}")
    public ResponseEntity<List<Car>> getCarsByIdUser(@PathVariable("idUser") int idUser) {
        UserModel user = userService.getById(idUser);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = userService.getCars(idUser);
        return ResponseEntity.ok(cars);
    }
    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallbackGetBikes")
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
    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallbackSaveCar")
    @PostMapping("/savecar/{idUser}")
    public ResponseEntity<Car> createCar(@PathVariable("idUser") int idUser, @RequestBody Car car) {
        Car carNew = userService.createCar(idUser, car);
        return ResponseEntity.ok(carNew);
    }
    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallbackSaveBike")
    @PostMapping("/savebike/{idUser}")
    public ResponseEntity<Bike> createBike(@PathVariable("idUser") int idUser, @RequestBody Bike bike) {
        Bike bikeNew = userService.createBike(idUser, bike);
        return ResponseEntity.ok(bikeNew);
    }
    @CircuitBreaker(name = "allCB", fallbackMethod = "fallbackGetAll")
    @GetMapping("/getAll/{idUser}")
    public ResponseEntity<Map<String,Object>> getAllVehicles(@PathVariable("idUser") int idUser) {
        Map<String,Object> result = userService.getUserAndVehicles(idUser);
        return ResponseEntity.ok(result);
    }

    //Metodos de FallBacks que me sirve para devolver algo cuando alguno de los servicios que uso esta caido
    //Esto me permite que se me rompa la aplicacion si se cae alguno

    private ResponseEntity<List<Car>> fallbackGetCars(@PathVariable("idUser") int idUser, RuntimeException e) {
        return new ResponseEntity("El usuario " + idUser + " tiene los autos en el mecanico", HttpStatus.OK);
    }
    private ResponseEntity<List<Bike>> fallbackGetBikes(@PathVariable("idUser") int idUser, RuntimeException e) {
        return new ResponseEntity("El usuario " + idUser + " tiene las motos en el mecanico", HttpStatus.OK);
    }
    private ResponseEntity<Car> fallbackSaveCar(@PathVariable("idUser") int idUser, @RequestBody Car car, RuntimeException e) {
        return new ResponseEntity("El usuario " + idUser + " no tiene dinero para autos", HttpStatus.OK);
    }
    private ResponseEntity<Bike> fallbackSaveBike(@PathVariable("idUser") int idUser, @RequestBody Bike bike, RuntimeException e) {
        return new ResponseEntity("El usuario " + idUser + " no tiene dinero para motos", HttpStatus.OK);
    }
    private ResponseEntity<Map<String,Object>> fallbackGetAll(@PathVariable("idUser") int idUser, RuntimeException e) {
        return new ResponseEntity("El usuario " + idUser + " tiene los vehiculos en el taller", HttpStatus.OK);
    }
}
