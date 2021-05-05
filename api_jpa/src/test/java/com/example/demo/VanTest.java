package com.example.demo;

import com.example.demo.vehicule.Vehicule;
import com.example.demo.vehicule.VehiculeRepository;
import com.example.demo.vehicule.van.Van;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class VanTest {
    //retoureve moi repository et donne moi accès
    @Autowired
    private VehiculeRepository vanrepo;

    @Test
    void Create_Class() {
        //Arrange
        Van van1 = new Van(1000);
        //Act
        van1.setBrand("fiat");
        van1.setPlateNumber("1124AAC");
        van1.setPrice(10300);
        //Assert
        assert (van1 != null && van1.getPlateNumber() != null && van1 instanceof Van) : "La classe de la variable n\'est pas une classe Van";
    }
    //Test on the Controller class
    @Test
    void Test_see_all() {
        //Arrange
        var it = vanrepo.findAllVan();
        var vans = new ArrayList<Vehicule>();
        boolean result = false;
        //Act
        it.forEach(e -> vans.add(e));
        for(Vehicule van : vans){
            if(van instanceof Van){
                result = true;
            }
            else{
                result = false;
                break;
            }
        }
        //Assert
        assert (vans != null && result == true): "La classe de la variable n\'est pas une liste classe Van";
    }
    @Test
    void Test_see_one_byid() {
        //Arrange
        String plateNumber = "A12X34RBB";
        boolean result = false;
        //Act
        var it = vanrepo.findVanById(plateNumber);
        if(it == null){
            result = false;
        }
        else{
            result = true;
        }
        //Assert
        assert (it != null && result == true) : "Le Van n'existe pas donc la valeur trouvé est null";
    }

    @Test
    void Test_delete_one_byid() {
        //Arrange
        String plateNumber = "1124AAC";
        boolean result = false;
        //Act
        if(vanrepo != null){
            vanrepo.deleteById(plateNumber);
            result = true;
        }
        else{
            result = false;
        }
        //Assert
        assert (vanrepo != null && result == true) : "Le Van n'a pas été supprimer car le donnée n'existe pas dans la bdd";
    }
}
