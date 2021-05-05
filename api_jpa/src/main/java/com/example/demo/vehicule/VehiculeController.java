package com.example.demo.vehicule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class VehiculeController {
    //retoureve moi VehiculedRepository et donne moi accès vehiculerepo
    @Autowired
    private VehiculeRepository vehiculerepo;

    public VehiculeController(VehiculeRepository vehiculerepo){
        System.out.println(vehiculerepo);
        this.vehiculerepo = vehiculerepo;
        Vehicule Vehicule1 = new Vehicule("1123AA","ferrari",10000);
        Vehicule Vehicule2 = new Vehicule("1123AB","ferrari",20000);
        Vehicule Vehicule3 = new Vehicule("1124AA","fiat",10300);
        Vehicule Vehicule4 = new Vehicule("1124AB","fiat",20400);
        vehiculerepo.save(Vehicule1);
        vehiculerepo.save(Vehicule2);
        vehiculerepo.save(Vehicule3);
        vehiculerepo.save(Vehicule4);
    }
    //Regarder tous les vehicules au lancement de l'application la bdd est vide alors tout d'abord créer une bdd à l aide
    //de http://localhost:8088/vehicules/create/1122AB/feri/2400 par exemple avant de lancer http://localhost:8088/vehicules
    @GetMapping("/vehicules")
    @ResponseBody
    public List<Vehicule> findAll() {
        var it = vehiculerepo.findAll();
        var Vehicules = new ArrayList<Vehicule>();
        it.forEach(e -> Vehicules.add(e));
        return Vehicules;
    }
    //Pour voir les vehicules avec prix descendant grace au order by DESC
    @GetMapping("/vehicules/BrandOrderByPriceDESC")
    @ResponseBody
    public List<Vehicule> findByBrandOrderByPriceDesc(@RequestParam String brand) {

        var it = vehiculerepo.findByBrandOrderByPriceDesc(brand);

        var Vehicules = new ArrayList<Vehicule>();
        it.forEach(e -> Vehicules.add(e));

        return Vehicules;
    }
    //Pour voir les vehicules avec sa marque et son prix dont ici on utilise le code requete(Select) avec les parametres
    @GetMapping("/vehicules/fetchVehiculeByBrandAndPrice")
    @ResponseBody
    public List<Vehicule> fetchVehiculeByBrandAndPrice(@RequestParam String brand, @RequestParam int price) {

        var it = vehiculerepo.fetchVehiculeByBrandAndPrice(brand,price);

        var Vehicules = new ArrayList<Vehicule>();
        it.forEach(e -> Vehicules.add(e));

        return Vehicules;
    }
    //Pour voir les vehicules par sa marque avec la commande Distinct en langage de bdd
    @GetMapping("/vehicules/findDistinctByBrand")
    @ResponseBody
    public List<Vehicule> findDistinctByBrand(@RequestParam String brand) {

        var it = vehiculerepo.findDistinctByBrand(brand);

        var Vehicules = new ArrayList<Vehicule>();
        it.forEach(e -> Vehicules.add(e));

        return Vehicules;
    }
    //Pour voir les vehicules tout en les classant par ordre ASC ou ascending(plus petit au plus grand) de sa marque
    @GetMapping("/vehicules/findByOrderByPlateNumberAsc")
    @ResponseBody
    public List<Vehicule> findByOrderByPlateNumberAsc() {

        var it = vehiculerepo.findByOrderByPlateNumberAsc();

        var Vehicules = new ArrayList<Vehicule>();
        it.forEach(e -> Vehicules.add(e));

        return Vehicules;
    }
    //Pour voir les vehicules par sa marque et sa plaque d immatriculation
    @GetMapping("/vehicules/findByBrandAndPlateNumber")
    @ResponseBody
    public Optional<Vehicule> findByBrandAndPlateNumber(@RequestParam String brand, @RequestParam String plateNumber) {

        var it = vehiculerepo.findByBrandAndPlateNumber(brand,plateNumber);
        if(it == null){
            System.out.println("La vehicule n'existe pas donc la valeur trouvé est null");
        }
        else{
            System.out.println("La vehicule existe et trouvé dans le navigateur web!");
        }
        return it;
    }
    //@RequestHeader("my-number")
    //@RequestParam String p_plateNumber
    //Appel HTTP GET via param
    @GetMapping(value = "/vehicule")
    @ResponseBody
    public Optional<Vehicule> Vehicule_SeeByIds(@RequestParam String plateNumber) {
        var it = vehiculerepo.findById(plateNumber);
        if(it == null){
            System.out.println("La vehicule n'existe pas donc la valeur trouvé est null");
        }
        else{
            System.out.println("La vehicule existe et trouvé dans le navigateur web!");
        }
        return it;
    }
    //n oublier pas de mettre dans headers dans postman le Content-type et value = application/json
    // et dans body la valeur de chaque ligne(ligne pour les donnees pour creer la classe Vehicule) en json bien sur
    //{
    //    "plateNumber" : "A16X22E",
    //        "brand" : "ferrari",
    //        "price" : 14000
    //}
    //creer une vehicule a l aide d un fichier json
    @PostMapping(value = "/vehicules/creer")
    @ResponseBody
    public  Vehicule Vehicule_CreatOrModify(@RequestBody Vehicule Vehicule_created) {
        Vehicule vehicule_enregistrer = vehiculerepo.save(Vehicule_created);
        if(vehiculerepo != null){
            System.out.println("vehicule creer avec succès");
        }
        else{
            System.out.println("Creation impossible!! Erreur!");
        }
        return vehicule_enregistrer;
    }
    //n oublier pas de mettre dans headers dans postman le Content-type et value = application/json
    // et dans body la valeur de chaque ligne(ligne pour les donnees pour creer la classe Vehicule) en json bien sur
    //{
    //    "plateNumber" : "A16X22E",
    //        "brand" : "ferrari",
    //        "price" : 14000
    //}
    //supprimer une vehicule a l aide d un fichier json
    @DeleteMapping(value = "/vehicules/supprimer")
    @ResponseBody
    public  void  Vehicule_Delete(@RequestBody Vehicule Vehicule_created) {
        if(vehiculerepo != null){
            vehiculerepo.deleteById(Vehicule_created.getPlateNumber());
            System.out.println("vehicule supprimer avec succès");
        }
        else{
            System.out.println("Suppresion impossible!! Erreur!");
        }
    }
}
