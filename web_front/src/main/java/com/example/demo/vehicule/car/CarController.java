package com.example.demo.vehicule.car;

import com.example.demo.function.Function;
import com.example.demo.vehicule.car.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class CarController {
    private Function function = new Function();

    //SEEALL
    @GetMapping("/cars")
    public String SeeAllCar(Model model) throws IOException {
        // Create a neat value object to hold the URL
        Car car = new Car();
        model.addAttribute("car", car);
        //recuperer tous les données
        Object[] users = new Object[0];
        String url_after = "cars";
        String attrname = "cars";
        function.Chargerdonner(model,users,url_after,attrname);
        return "car";
    }

    //CREATE
    @PostMapping("/cars")
    public String CreateUser_Form(@ModelAttribute("personne") Car car, Model model) throws IOException {
        System.out.println("Nombre de place max: "+car.numberofseats);

        System.out.println("Numero de serie: "+car.getPlateNumber());
        System.out.println("Prix: "+ car.getPrice());
        System.out.println("Marque: "+ car.getBrand());

        //Au cas ou il y a de l espacement dans les variables String
        String platenumber_underscore = car.getPlateNumber().replaceAll(" ", "_");
        System.out.println(platenumber_underscore);
        String brand_underscore = car.getBrand().replaceAll(" ", "_");
        System.out.println(brand_underscore);
        // Create a neat value object to hold the URL
        URL url = new URL(function.base_url+"cars/creer/" + platenumber_underscore + "/" + brand_underscore + "/" + car.getPrice() + "/" + car.numberofseats);
        System.out.println(function.base_url+"cars/creer/" + platenumber_underscore + "/" + brand_underscore + "/" + car.getPrice() + "/" + car.numberofseats);

        // Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestMethod("POST");


        // This line makes the request
        InputStream responseStream = connection.getInputStream();

        // Manually converting the response body InputStream to APOD using Jackson
        ObjectMapper mapper = new ObjectMapper();
        Car car1 = mapper.readValue(responseStream, Car.class);

        // Finally we have the response
        System.out.println("Platenumber du Voiture créer: " + car1.getPlateNumber());

        return "redirect:/cars";
    }
    //DELETE
    @PostMapping("/cars/delete/{platenumber}")
    public String delete_car(Model model,@PathVariable("platenumber") String platenumber) throws IOException {

        System.out.println("Platenumber of car to delete: "+platenumber);
        // Create a neat value object to hold the URL
        URL url = new URL(function.base_url+"cars/supprimer/"+platenumber);
        //System.out.println(function.base_url+"persons/supprimer/"+id);

        // Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestMethod("POST");


        // This line makes the request
        InputStream responseStream = connection.getInputStream();
        System.out.println(responseStream);

        // Manually converting the response body InputStream to APOD using Jackson
        ObjectMapper mapper = new ObjectMapper();
        //Vehicule car = mapper.readValue(responseStream, Vehicule.class);


        return "redirect:/cars";
    }
}
