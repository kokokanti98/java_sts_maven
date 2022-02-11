package com.example.demo.vehicule.car;
import com.example.demo.vehicule.Vehicule;
import com.example.demo.vehicule.VehiculeRepository;
import com.example.demo.vehicule.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CarController {
    //retoureve moi CardRepository et donne moi accès
    @Autowired
    private VehiculeRepository carrepo;
    @Autowired
    public CarController(VehiculeRepository carrepo){
        System.out.println(carrepo);
        this.carrepo = carrepo;
        Car car1 = new Car(10);
        car1.setBrand("ferrari");
        car1.setPlateNumber("221EBXC");
        car1.setPrice(10300);
        Car car2 = new Car(20);
        car2.setBrand("ferrari");
        car2.setPlateNumber("221EBXA");
        car2.setPrice(20400);
        carrepo.save(car1);
        carrepo.save(car2);
    }
    //Retourne un String Hello World! dans la page web
    @GetMapping("/hello")
    public String HelloWorld() {
        return "Hello World!";
    }
    //Regarder tous les cars au lancement de l'application la bdd est vide alors tout d'abord créer une bdd à l aide
    @GetMapping("/cars")
    @ResponseBody
    public List<Vehicule> findAllCar() {
        var it = carrepo.findAllCar();
        var cars = new ArrayList<Vehicule>();
        it.forEach(e -> cars.add(e));
        return cars;
    }
    @GetMapping(value = "/car")
    @ResponseBody
    public Optional<Vehicule> findCarById(@RequestParam String plateNumber) {
        var it = carrepo.findCarById(plateNumber);
        if(it == null){
            System.out.println("L automobile car n'existe pas donc la valeur trouvé est null");
        }
        else{
            System.out.println("L automobile car existe et trouvé dans le navigateur web!");
        }
        return it;
    }
    //{
    //    "plateNumber" : "221EBXAXD",
    //    "brand" : "ferrari",
    //    "numberofseats" : 12,
    //    "price" : 14000
    //}
    //creer une car a l aide d un fichier json
    @PostMapping(value = "/cars/creer")
    @ResponseBody
    public  Vehicule car_CreatOrModify(@RequestBody Car car_created) {
        Vehicule vehicule_enregistrer = carrepo.save(car_created);
        if(carrepo != null){
            System.out.println("Car creer avec succès");
        }
        else{
            System.out.println("Creation impossible!! Erreur!");
        }
        return car_created;
    }
    //{
    //    "plateNumber" : "221EBXAXD",
    //    "brand" : "ferrari",
    //    "numberofseats" : 12,
    //    "price" : 14000
    //}
    //supprimer une car a l aide d un fichier json
    @DeleteMapping(value = "/cars/supprimer")
    @ResponseBody
    public  void  car_Delete(@RequestBody Vehicule car_created) {
        if(carrepo != null){
            carrepo.deleteById(car_created.getPlateNumber());
            System.out.println("Car supprimer avec succès");
        }
        else{
            System.out.println("Suppresion impossible!! Erreur!");
        }
    }
    @PostMapping(value = "/cars/creer/{platenumber}/{brand}/{price}/{numberofseats}")
    public  Vehicule car_CreatOrModify_HTTP(@PathVariable (value = "platenumber") String platenumber,
                                            @PathVariable (value = "brand") String brand,
                                            @PathVariable (value = "price") int price,
                                            @PathVariable (value = "numberofseats") int numberofseats) {
        Car car_created = new Car();
        car_created.setPlateNumber(platenumber);
        car_created.setBrand(brand);
        car_created.setPrice(price);

        car_created.setNumberofseats(numberofseats);

        Vehicule vehicule_enregistrer = carrepo.save(car_created);
        if(carrepo != null){
            System.out.println("Car creer avec succès");
        }
        else{
            System.out.println("Creation impossible!! Erreur!");
        }
        return car_created;
    }
    //supprimer une car a l aide http
    @PostMapping(value = "/cars/supprimer/{platenumber}")
    public  void  car_Delete_http(@PathVariable (value = "platenumber") String platenumber) {
        if(carrepo != null){
            carrepo.deleteById(platenumber);
            System.out.println("Car supprimer avec succès");
        }
        else{
            System.out.println("Suppresion impossible!! Erreur!");
        }
    }
}
