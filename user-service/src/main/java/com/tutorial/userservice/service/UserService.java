package com.tutorial.userservice.service;

import com.tutorial.userservice.model.Bike;
import com.tutorial.userservice.model.Car;
import com.tutorial.userservice.model.UserModel;

import java.util.List;

public interface UserService {

    List<UserModel> getAll();
    List<Car> getCars(int idUser);
    List<Bike> getBikes(int idUser);
    UserModel getById(int idUser);
    UserModel createUser(UserModel user);

}
