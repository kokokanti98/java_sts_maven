package com.example.demo.vehicule.van;
import com.example.demo.vehicule.Vehicule;
import com.example.demo.vehicule.VehiculeRepository;
import com.example.demo.vehicule.van.Van;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class VanController {

    //retoureve moi CardRepository et donne moi accès
    @Autowired
    private VehiculeRepository vanrepo;
    @Autowired
    public VanController(VehiculeRepository vanrepo){
        System.out.println(vanrepo);
        this.vanrepo = vanrepo;
        Van van1 = new Van(1000);
        van1.setBrand("fiat");
        van1.setPlateNumber("1124AAC");
        van1.setPrice(10300);
        Van van2 = new Van(2000);
        van2.setBrand("fiat");
        van2.setPlateNumber("1124ABC");
        van2.setPrice(20400);
        vanrepo.save(van1);
        vanrepo.save(van2);
    }
    //Regarder tous les vans au lancement de l'application la bdd est vide alors tout d'abord créer une bdd à l aide
    @GetMapping("/vans")
    @ResponseBody
    public List<Vehicule> findAllVan() {
        var it = vanrepo.findAllVan();
        var vans = new ArrayList<Vehicule>();
        it.forEach(e -> vans.add(e));
        return vans;
    }
    @GetMapping(value = "/van")
    @ResponseBody
    public Optional<Vehicule> findVanById(@RequestParam String plateNumber) {
        var it = vanrepo.findVanById(plateNumber);
        if(it == null){
            System.out.println("La automobile van n'existe pas donc la valeur trouvé est null");
        }
        else{
            System.out.println("La automobile van existe et trouvé dans le navigateur web!");
        }
        return it;
    }
    //{
    //    "plateNumber" : "A16X22EXC",
    //    "brand" : "fiat",
    //    "maxweight" : 1200,
    //    "price" : 14000
    //}
    //creer une van a l aide d un fichier json
    @PostMapping(value = "/vans/creer")
    @ResponseBody
    public  Vehicule Van_CreatOrModify(@RequestBody Van Van_created) {
        Vehicule vehicule_enregistrer = vanrepo.save(Van_created);
        if(vanrepo != null){
            System.out.println("Van creer avec succès");
        }
        else{
            System.out.println("Creation impossible!! Erreur!");
        }
        return Van_created;
    }
    //{
    //    "plateNumber" : "A16X22EXC",
    //    "brand" : "fiat",
    //    "maxweight" : 1200,
    //    "price" : 14000
    //}
    //supprimer une van a l aide d un fichier json
    @DeleteMapping(value = "/vans/supprimer")
    @ResponseBody
    public  void  Van_Delete(@RequestBody Vehicule Van_created) {
        if(vanrepo != null){
            vanrepo.deleteById(Van_created.getPlateNumber());
            System.out.println("Van supprimer avec succès");
        }
        else{
            System.out.println("Suppresion impossible!! Erreur!");
        }
    }

    @PostMapping(value = "/vans/creer/{platenumber}/{brand}/{price}/{maxweight}")
    public  Vehicule van_CreatOrModify_HTTP(@PathVariable (value = "platenumber") String platenumber,
                                            @PathVariable (value = "brand") String brand,
                                            @PathVariable (value = "price") int price,
                                            @PathVariable (value = "maxweight") float maxweight) {
        Van car_created = new Van();
        car_created.setPlateNumber(platenumber);
        car_created.setBrand(brand);
        car_created.setPrice(price);

        car_created.setMaxweight(maxweight);

        Vehicule vehicule_enregistrer = vanrepo.save(car_created);
        if(vanrepo != null){
            System.out.println("Van creer avec succès");
        }
        else{
            System.out.println("Creation impossible!! Erreur!");
        }
        return car_created;
    }

    //supprimer une car a l aide http
    @PostMapping(value = "/vans/supprimer/{platenumber}")
    public  void  van_Delete_http(@PathVariable (value = "platenumber") String platenumber) {
        if(vanrepo != null){
            vanrepo.deleteById(platenumber);
            System.out.println("Van supprimer avec succès");
        }
        else{
            System.out.println("Suppresion impossible!! Erreur!");
        }
    }
}
