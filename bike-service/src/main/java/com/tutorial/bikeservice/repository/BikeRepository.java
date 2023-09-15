package com.tutorial.bikeservice.repository;

import com.tutorial.bikeservice.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike,Integer> {

    List<Bike> findByIdUser(int idUser);
}
