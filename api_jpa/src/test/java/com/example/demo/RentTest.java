package com.example.demo;

import com.example.demo.person.PersonRepository;
import com.example.demo.rent.Rent;
import com.example.demo.rent.RentRepository;
import com.example.demo.vehicule.VehiculeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
public class RentTest {
    //retoureve moi repository et donne moi accès
    @Autowired
    private VehiculeRepository vehiculerepo;
    //retoureve moi repository et donne moi accès
    @Autowired
    private PersonRepository persrepo;
    //retoureve moi repository et donne moi accès
    @Autowired
    private RentRepository rentrepo;
    @Test
    void Create_Class() throws ParseException {
        //Arrange
        Rent rent1 = new Rent();
        //Act
        rent1.setId(1);
        //Creation des 2 variables date
        String s = "22/09/2006";
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = sd.parse(s);
        String st = "23/09/2006";
        SimpleDateFormat sd1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date2 = sd1.parse(st);
        //Set des 2 dates sur la classe Rent Créer
        rent1.setBeginRent(date1);
        rent1.setEndRent(date2);
        //Assert
        assert (rent1 != null && rent1.getBeginRent() != null && rent1 instanceof Rent) : "La classe de la variable n\'est pas une classe Rent";
    }
    //Test on the Controller class
    @Test
    void Test_see_all() {
        //Arrange
        var it = rentrepo.findAll();
        var rents = new ArrayList<Rent>();
        boolean result = false;
        //Act
        it.forEach(e -> rents.add(e));
        //Assert
        assert (rents != null): "La classe de la variable est vide";
    }
    @Test
    void Test_see_one_byid() {
        //Arrange
        int id = 1;
        //Act
        var it = rentrepo.findById(id);
        //Assert
        assert (it != null && it.get() instanceof Rent) : "La location n'existe pas donc la valeur trouvé est null";
    }
    @Test
    void Test_delete_one_byid() {
        //Arrange
        int  id = 1;
        boolean result = false;
        //Act
        if(rentrepo != null){
            rentrepo.deleteById(id);
            result = true;
        }
        else{
            result = false;
        }
        //Assert
        assert (rentrepo != null && result == true) : "La location n'a pas été supprimer car le donnée n'existe pas dans la bdd";
    }
}
