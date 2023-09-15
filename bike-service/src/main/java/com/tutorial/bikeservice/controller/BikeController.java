package com.tutorial.bikeservice.controller;

import com.tutorial.bikeservice.model.Bike;
import com.tutorial.bikeservice.service.BikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    private BikeServiceImpl bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> users = bikeService.getAll();
        if(users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable("id") int idBike) {
        Bike bike = bikeService.getById(idBike);
        if(bike == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    @GetMapping("/byuser/{idUser}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("idUser") int idUser) {
        List<Bike> bikes = bikeService.findByIdUser(idUser);
        return ResponseEntity.ok(bikes);
    }
    @PostMapping
    public ResponseEntity<Bike> createBike(@RequestBody Bike bike) {
        Bike bikeNew = bikeService.createBike(bike);
        return ResponseEntity.ok(bikeNew);
    }
}
