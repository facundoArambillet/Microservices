package com.tutorial.userservice.service;

import com.tutorial.userservice.feignclients.BikeFeignClient;
import com.tutorial.userservice.feignclients.CarFeignClient;
import com.tutorial.userservice.model.Bike;
import com.tutorial.userservice.model.Car;
import com.tutorial.userservice.model.UserModel;
import com.tutorial.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;
    @Autowired
    BikeFeignClient bikeFeignClient;

    @Override
    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Car> getCars(int idUser) {
        List<Car> cars = this.restTemplate.getForObject("http://car-service/car/byuser/" + idUser, List.class);
        return cars;
    }

    @Override
    public List<Bike> getBikes(int idUser) {
        List<Bike> bikes = this.restTemplate.getForObject("http://bike-service/bike/byuser/" + idUser, List.class);
        return bikes;
    }

    @Override
    public UserModel getById(int idUser) {
        return userRepository.findById(idUser).orElse(null);
    }

    @Override
    public UserModel createUser(UserModel user) {
        UserModel userNew = userRepository.save(user);
        return userNew;
    }

    public Car createCar(int idUser,Car car) {
        car.setIdUser(idUser);
        Car carNew = carFeignClient.createCar(car);
        return carNew;
    }

    public Bike createBike(int idUser,Bike bike) {
        bike.setIdUser(idUser);
        Bike bikeNew = bikeFeignClient.createBike(bike);
        return bikeNew;
    }

    public Map<String, Object> getUserAndVehicles(int idUser) {
        Map<String, Object> result = new HashMap<>();
        UserModel user = userRepository.findById(idUser).orElse(null);
        if(user == null) {
            result.put("Mensaje","No existe el usuario");
            return result;
        }
        result.put("User",user);
        List<Car> cars = carFeignClient.getCars(idUser);
        if(cars.isEmpty()) {
            result.put("Cars","Ese user no posee coches");
        } else {
            result.put("Cars", cars);
        }
        List<Bike> bikes = bikeFeignClient.getBikes(idUser);
        if(bikes.isEmpty()) {
            result.put("Bikes", "Ese user no posee bikes");
        } else {
            result.put("Bikes", bikes);
        }
        return result;
    }
}
