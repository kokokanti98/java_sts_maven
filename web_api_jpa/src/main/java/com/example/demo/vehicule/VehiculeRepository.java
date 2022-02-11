package com.example.demo.vehicule;

import com.example.demo.vehicule.Vehicule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

//le parametre de CRUD(CReate,Update,Delete) repository sont <la classe correspondant, type de la clé primaire de cette classe>
//Comme classe est Car ici alors son clé primaire est platenumber qui est un string
public interface VehiculeRepository extends CrudRepository<Vehicule, String> {
    List<Vehicule> findByBrandOrderByPriceDesc(String brand);

    List<Vehicule> findDistinctByBrand(String brand);

    Optional<Vehicule> findByBrandAndPlateNumber(String brand, String plateNumber);

    List<Vehicule> findByOrderByPlateNumberAsc();

    @Query("SELECT ve FROM Vehicule ve WHERE ve.brand=:brand and ve.price=:price")
    List<Vehicule> fetchVehiculeByBrandAndPrice(@Param("brand") String brand, @Param("price") int price);

    @Query("SELECT va FROM Van va")
    List<Vehicule> findAllVan();

    @Query("SELECT va FROM Van va WHERE va.plateNumber=:plateNumber")
    Optional<Vehicule> findVanById(@Param("plateNumber") String plateNumber);


    @Query("SELECT ca FROM Car ca")
    List<Vehicule> findAllCar();

    @Query("SELECT ca FROM Car ca WHERE ca.plateNumber=:plateNumber")
    Optional<Vehicule> findCarById(@Param("plateNumber") String plateNumber);

}
