package com.tutorial.userservice.feignclients;

import com.tutorial.userservice.model.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
//Puedo sacar la url porque la tengo registrada en eureka, url = "http://localhost:8003/bike")
@FeignClient(name = "bike-service")
public interface BikeFeignClient {
    @PostMapping("/bike")
    Bike createBike(@RequestBody Bike bike);

    @GetMapping("bike/byuser/{idUser}")
    List<Bike> getBikes(@PathVariable("idUser") int idUser);
}
