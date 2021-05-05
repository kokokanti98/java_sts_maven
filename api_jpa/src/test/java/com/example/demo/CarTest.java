package com.example.demo;

import com.example.demo.vehicule.Vehicule;
import com.example.demo.vehicule.VehiculeRepository;
import com.example.demo.vehicule.car.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class CarTest {
    //retoureve moi repository et donne moi accès
    @Autowired
    private VehiculeRepository carrepo;

    @Test
    void Create_Class() {
        //Arrange
        Car car1 = new Car(10);
        //Act
        car1.setBrand("ferrari");
        car1.setPlateNumber("221EBXC");
        car1.setPrice(10300);
        //Assert
        assert (car1 != null && car1.getPlateNumber() != null && car1 instanceof Car) : "La classe de la variable n\'est pas une classe Car";
    }
    //Test on the Controller class
    @Test
    void Test_see_all() {
        //Arrange
        var it = carrepo.findAllCar();
        var cars = new ArrayList<Vehicule>();
        boolean result = false;
        //Act
        it.forEach(e -> cars.add(e));
        for(Vehicule car : cars){
            if(car instanceof Car){
                result = true;
            }
            else{
                result = false;
                break;
            }
        }
        //Assert
        assert (cars != null && result == true): "La classe de la variable n\'est pas une liste classe Car";
    }
    @Test
    void Test_see_one_byid() {
        //Arrange
        String plateNumber = "A12X34RBB";
        boolean result = false;
        //Act
        var it = carrepo.findCarById(plateNumber);
        if(it == null){
            result = false;
        }
        else{
            result = true;
        }
        //Assert
        assert (it != null && result == true) : "Le Car n'existe pas donc la valeur trouvé est null";
    }
    @Test
    void Test_delete_one_byid() {
        //Arrange
        String plateNumber = "1124AAC";
        boolean result = false;
        //Act
        if(carrepo != null){
            carrepo.deleteById(plateNumber);
            result = true;
        }
        else{
            result = false;
        }
        //Assert
        assert (carrepo != null && result == true) : "Le Car n'a pas été supprimer car le donnée n'existe pas dans la bdd";
    }
}
