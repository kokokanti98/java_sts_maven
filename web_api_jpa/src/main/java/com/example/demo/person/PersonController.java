package com.example.demo.person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class PersonController {
    //retoureve moi CardRepository et donne moi accès
    @Autowired
    private PersonRepository personrepo;

    public PersonController(PersonRepository personrepo){
        System.out.println(personrepo);
        this.personrepo = personrepo;
        Person pers1 = new Person(1,"Mike");
        Person pers2 = new Person(2,"Kanty");
        Person pers3 = new Person(3,"Maryse");
        Person pers4 = new Person(4,"John");
        personrepo.save(pers1);
        personrepo.save(pers2);
        personrepo.save(pers3);
        personrepo.save(pers4);
    }
    //Regarder tous les voitures au lancement de l'application la bdd est vide alors tout d'abord créer une bdd à l aide
    //de http://localhost:8088/voitures/create/1122AB/feri/2400 par exemple avant de lancer http://localhost:8088/voitures
    @GetMapping("/persons")
    @ResponseBody
    public ArrayList<Person> findAll() {
        var it = personrepo.findAll();
        var persons = new ArrayList<Person>();
        it.forEach(e -> persons.add(e));
        return persons;
    }
    //@RequestHeader("my-number")
    //@RequestParam String p_plateNumber
    //Appel HTTP GET via param
    @GetMapping(value = "/person")
    @ResponseBody
    public Optional<Person> Person_SeeByIds(@RequestParam int id) {
        var it = personrepo.findById(id);
        if(it == null){
            System.out.println("La personne n'existe pas donc la valeur trouvé est null");
        }
        else{
            System.out.println("La personne existe et trouvé dans le navigateur web!");
        }
        return it;
    }
    //{
    //    "name" : "Alfred",
    //    "password" : "good game"
    //}
    //creer une voiture a l aide d un fichier json
    @PostMapping(value = "/persons/creer")
    @ResponseBody
    public  Person Person_CreatOrModify(@RequestBody Person person_created) {
        Person person_enregistrer = personrepo.save(person_created);
        if(personrepo != null){
            System.out.println("Personne creer avec succès");
        }
        else{
            System.out.println("Creation impossible!! Erreur!");
        }
        return person_enregistrer;
    }
    //{
    //    "id" : "5",
    //    "name" : "Alfred",
    //    "password" : "good game"
    //}
    //supprimer une voiture a l aide d un fichier json
    @DeleteMapping(value = "/persons/supprimer")
    @ResponseBody
    public  void  Person_Delete(@RequestBody Person person_created) {
        if(personrepo != null){
            personrepo.deleteById(person_created.getId());
            System.out.println("Personne supprimer avec succès");
        }
        else{
            System.out.println("Suppresion impossible!! Erreur!");
        }
    }
    //supprimer via requesparam
    @PostMapping(value = "/persons/supprimer/{id}")
    public  void  Person_Delete_param(@PathVariable (value = "id") int p_id) {
        if(personrepo != null){
            personrepo.deleteById(p_id);
            System.out.println("Personne supprimer avec succès");
        }
        else{
            System.out.println("Suppresion impossible!! Erreur!");
        }
    }
    //creer une voiture a l aide http
    @PostMapping(value = "/persons/creer/{id}/{name}")
    @ResponseBody
    public  Person Person_CreatOrModify2(@PathVariable("id") int id,@PathVariable("name") String name) {
        Person person_enregistrer = personrepo.save(new Person(id,name));
        if(personrepo != null){
            System.out.println("Personne creer avec succès");
        }
        else{
            System.out.println("Creation impossible!! Erreur!");
        }
        return person_enregistrer;
    }
    //creer une voiture a l aide d un fichier json
    @PostMapping(value = "/persons/create")
    @ResponseBody
    public  Person Person_CreatOrModify3(@RequestParam int id,@RequestParam String name) {

        String name_space = name.replaceAll("_", " ");

        Person person_enregistrer = personrepo.save(new Person(id,name_space));
        if(personrepo != null){
            System.out.println("Personne creer avec succès");
        }
        else{
            System.out.println("Creation impossible!! Erreur!");
        }
        return person_enregistrer;
    }
}
