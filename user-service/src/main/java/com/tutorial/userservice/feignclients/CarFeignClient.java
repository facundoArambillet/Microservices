package com.tutorial.userservice.feignclients;

import com.tutorial.userservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Puedo sacar la url porque la tengo registrada en eureka url = "http://localhost:8002/car"
@FeignClient(name = "car-service")
public interface CarFeignClient {
    //Tengo que agregarle el /car(que es el RequestMapping del servicio) porque sino no los encuentra
    @PostMapping("/car")
    Car createCar(@RequestBody Car car);

    @GetMapping("car/byuser/{idUser}")
    List<Car> getCars(@PathVariable("idUser") int idUser);
}
