package com.example.demo.rent;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentRepository extends CrudRepository<Rent, Integer> {
    @Query("SELECT new com.example.demo.rent.RentJoin(rent.id, rent.vehicule.plateNumber, rent.vehicule.brand,rent.vehicule.price,rent.person.id,rent.person.name,rent.beginRent,rent.endRent) FROM Rent rent")
    List<RentJoin> findAllWithPersonAndVehicule();
}
