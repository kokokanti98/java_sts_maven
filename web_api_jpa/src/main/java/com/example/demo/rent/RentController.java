package com.example.demo.rent;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.example.demo.person.Person;
import com.example.demo.person.PersonRepository;
import com.example.demo.vehicule.Vehicule;
import com.example.demo.vehicule.VehiculeRepository;
import com.example.demo.vehicule.van.Van;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
public class RentController {
    //retoureve moi CardRepository et donne moi accès
    @Autowired
    private VehiculeRepository vehiculerepo;
    //retoureve moi CardRepository et donne moi accès
    @Autowired
    private PersonRepository persrepo;
    //retoureve moi CardRepository et donne moi accès
    @Autowired
    private RentRepository rentrepo;

    @Autowired
    public RentController(VehiculeRepository vehiculerepo) throws ParseException {
        System.out.println(vehiculerepo);
        this.vehiculerepo = vehiculerepo;
        //Creation de la classe Van fille de classe Vehicule
        Van van1 = new Van(1000);
        //Attribuer les valeurs de la classe Van
        van1.setBrand("fiat");
        van1.setPlateNumber("1124AEGG");
        van1.setPrice(10450);
        //Creation de la classe Rent
        Rent rent1 = new Rent();
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
        //Creation de la classe Personne
        Person per4 = new Person(5,"jojo");
        //Set du Vehicule sur la classe Rent
        rent1.setVehicule(van1);
        //Set du Rent dans la classe Vehicule
        van1.addRent_Vehicule(rent1);
        //Set du Person sur la classe Rent
        rent1.setPerson(per4);
        //Set du Rent sur la classe Person
        per4.addRent_Person(rent1);
        //Ici on créer le vehicule seulement mais grâce aux Cascade ca crée aussi le Rent avec le vehicule
        vehiculerepo.save(van1);
    }
    //Regarder tous les voitures au lancement de l'application la bdd est vide alors tout d'abord créer une bdd à l aide
    //de http://localhost:8088/voitures/create/1122AB/feri/2400 par exemple avant de lancer http://localhost:8088/voitures
    @GetMapping("/rents/car/{platenumber}")
    @ResponseBody
    public Collection<Rent> findAllRentByPlatenumber(@PathVariable (value = "platenumber") String plateNumber) {
        Vehicule it = vehiculerepo.findById(plateNumber).get();
        return it.getRents();
    }
    //Regarder tous les vans au lancement de l'application la bdd est vide alors tout d'abord créer une bdd à l aide
    @GetMapping("/rents")
    @ResponseBody
    public List<Rent> findAllRent() {
        var it = rentrepo.findAll();
        var rents = new ArrayList<Rent>();
        it.forEach(e -> rents.add(e));
        return rents;
    }
    //Regarder tous les vans au lancement de l'application la bdd est vide alors tout d'abord créer une bdd à l aide
    @GetMapping("/rents_all")
    @ResponseBody
    public List<RentJoin> findAllWithPersonAndVehicule() {
        var it = rentrepo.findAllWithPersonAndVehicule();
        var rents = new ArrayList<RentJoin>();
        it.forEach(e -> rents.add(e));
        return rents;
    }

    //   {
    //      "id":2,
    //      "beginRent":"31/12/2019",
    //      "endRent":"24/12/2019",
    //      "vehicule":{
    //         "plateNumber":"221EBXC",
    //         "brand":"ferrari",
    //         "price":10300
    //      },
    //      "person":{
    //         "id":3,
    //         "name":"Maryse"
    //      }
    //   }

    //OU

    //   {
    //      "beginRent":"31/12/2019",
    //      "endRent":"24/12/2019"
    //   }

    //http://host.docker.internal:8089/rents/creer/221EBXC/3

    //creer un nouvelle rent a l aide d un fichier json
    @PostMapping(value = "/rents/creer/{platenumber}/{id}")
    @ResponseBody
    public  Vehicule Rents_CreatOrModify(@RequestBody Rent rent,@PathVariable (value = "platenumber") String plateNumber,@PathVariable (value = "id") int id) {
        //Attribution à une classe Vehicule : grace à la recherche de vehiculerepo de findbyId pour recuperer le vehicule par son Id
        Vehicule vehicule = vehiculerepo.findById(plateNumber).get();
        //Attribution à une classe Person : grace à la recherche de persrepo de findbyId pour recuperer la personne par son Id
        Person person = persrepo.findById(id).get();
        //Set du Vehicule sur la classe Rent
        rent.setVehicule(vehicule);
        //Set du Rent dans la classe Vehicule
        vehicule.addRent_Vehicule(rent);
        //Set du Person sur la classe Rent
        rent.setPerson(person);
        //Set du Rent sur la classe Person
        person.addRent_Person(rent);
        //Ici on créer le vehicule seulement mais grâce aux Cascade ca crée aussi le Rent avec le vehicule
        vehiculerepo.save(vehicule);
        if(vehiculerepo != null){
            System.out.println("Rent creer avec succès");
        }
        else{
            System.out.println("Creation impossible!! Erreur!");
        }
        return vehiculerepo.save(vehicule);
    }

    //creer un nouvelle rent et ratache l utilisateur et vehicule concerne
    @PostMapping(value = "/rents/creer/{id}/{date_debut_location}/{date_fin_location}/{id_of_user}/{platenumber}")
    @ResponseBody
    public  Vehicule Rents_CreatOrModifyWith_userandcar(@PathVariable (value = "id") int id,
                                     @PathVariable (value = "date_debut_location") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date_debut_location,
                                     @PathVariable (value = "date_fin_location") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date_fin_location,
                                                    @PathVariable (value = "id_of_user") int id_of_user,
                                                    @PathVariable (value = "platenumber") String platenumber) {
        //Create Rent(vide)
        Rent rent1 = new Rent();
        rent1.setId(id);
        rent1.setBeginRent(date_debut_location);
        rent1.setEndRent(date_fin_location);
        //Search User by id
        Person person = persrepo.findById(id_of_user).get();
        //Search Car by id
        Vehicule vehicule = vehiculerepo.findById(platenumber).get();
        //Make the relation between car and rent
        //Set du Vehicule sur la classe Rent
        rent1.setVehicule(vehicule);
        //Set du Rent dans la classe Vehicule
        vehicule.addRent_Vehicule(rent1);
        //Make the relation between use and rent
        //Set du Person sur la classe Rent
        rent1.setPerson(person);
        //Set du Rent sur la classe Person
        person.addRent_Person(rent1);
        //Ici on créer le vehicule seulement mais grâce aux Cascade ca crée aussi le Rent avec le vehicule
        vehiculerepo.save(vehicule);
        if(vehiculerepo != null){
            System.out.println("Rent creer avec succès");
        }
        else{
            System.out.println("Creation impossible!! Erreur!");
        }
        return vehiculerepo.save(vehicule);
    }
    //   {
    //      "id":6
    //   }

    //http://host.docker.internal:8089/rents/supprimer

    //supprimer une rent a l aide d un fichier json
    @DeleteMapping(value = "/rents/supprimer")
    @ResponseBody
    public  void  Rent_Delete(@RequestBody Rent rent_created) {
        if(rentrepo != null){
            rentrepo.deleteById(rent_created.getId());
            System.out.println("Rent supprimer avec succès");
        }
        else{
            System.out.println("Suppresion impossible!! Erreur!");
        }
    }
    //supprimer une rent a l aide http
    @PostMapping(value = "/rents/supprimer/{id}")
    public  void  Delete_Rent(@PathVariable (value = "id") int id) {
        if(rentrepo != null){
            rentrepo.deleteById(id);
            System.out.println("Rent supprimer avec succès");
        }
        else{
            System.out.println("Suppresion impossible!! Erreur!");
        }
    }
}
