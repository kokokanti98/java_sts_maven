package com.example.demo;

import com.example.demo.person.Person;
import com.example.demo.person.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class PersonTest {
    //retoureve moi repository et donne moi accès
    @Autowired
    private PersonRepository personrepo;

    @Test
    void Create_Class() {
        //Arrange
        int id = 22;
        String name = "Mams";
        //Act
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        //Assert
        assert (person != null && person.getName() != null && person instanceof Person) : "La classe de la variable n\'est pas une classe Person";
    }
    //Test on the Controller class
    @Test
    void Test_see_all() {
        //Arrange
        var it = personrepo.findAll();
        var persons = new ArrayList<Person>();
        boolean result = false;
        //Act
        it.forEach(e -> persons.add(e));
        for(Person pers : persons){
            if(pers instanceof Person){
                result = true;
            }
            else{
                result = false;
                break;
            }
        }
        //Assert
        assert (persons != null && result == true): "La classe de la variable n\'est pas une liste classe Person";
    }
    @Test
    void Test_see_one_byid() {
        //Arrange
        int id = 1;
        //Act
        var it = personrepo.findById(id);
        //Assert
        assert (it != null && it.get() instanceof Person) : "La personne n'existe pas donc la valeur trouvé est null";
    }

    @Test
    void Test_delete_one_byid() {
        //Arrange
        int  id = 1;
        boolean result = false;
        //Act
        if(personrepo != null){
            personrepo.deleteById(id);
            result = true;
        }
        else{
            result = false;
        }
        //Assert
        assert (personrepo != null && result == true) : "La personne n'a pas été supprimer car le donnée n'existe pas dans la bdd";
    }
    @Test
    void Test_create_or_modify_one() {
        //Arrange
        int id = 22;
        String name = "Mams";
        boolean result = false;
        //Act
        Person person_enregistrer = personrepo.save(new Person(id,name));
        if(personrepo != null){
            result = true;
        }
        else{
            result = false;
        }
        //Assert
        assert (person_enregistrer != null && result == true) : "La personne n'existe pas donc la valeur trouvé est null";
    }
}
