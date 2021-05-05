package com.example.demo.vehicule.van;

import com.example.demo.function.Function;
import com.example.demo.vehicule.van.Van;
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
public class VanController {
    private Function function = new Function();

    //SEEALL
    @GetMapping("/vans")
    public String SeeAllVan(Model model) throws IOException {
        // Create a neat value object to hold the URL
        Van van = new Van();
        model.addAttribute("van", van);
        //recuperer tous les données
        Object[] users = new Object[0];
        String url_after = "vans";
        String attrname = "vans";
        function.Chargerdonner(model,users,url_after,attrname);
        return "van";
    }
    //CREATE
    @PostMapping("/vans")
    public String CreateUser_Form(@ModelAttribute("van") Van van, Model model) throws IOException {

        System.out.println("Poids max: "+van.maxweight);

        System.out.println("Numero de serie: "+van.getPlateNumber());
        System.out.println("Prix: "+ van.getPrice());
        System.out.println("Marque: "+ van.getBrand());

        //Au cas ou il y a de l espacement dans les variables String
        String platenumber_underscore = van.getPlateNumber().replaceAll(" ", "_");
        System.out.println(platenumber_underscore);
        String brand_underscore = van.getBrand().replaceAll(" ", "_");
        System.out.println(brand_underscore);
        // Create a neat value object to hold the URL
        URL url = new URL(function.base_url+"vans/creer/" + platenumber_underscore + "/" + brand_underscore + "/" + van.getPrice() + "/" + van.maxweight);
        System.out.println(function.base_url+"vans/creer/" + platenumber_underscore + "/" + brand_underscore + "/" + van.getPrice() + "/" + van.maxweight);

        // Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestMethod("POST");


        // This line makes the request
        InputStream responseStream = connection.getInputStream();

        // Manually converting the response body InputStream to APOD using Jackson
        ObjectMapper mapper = new ObjectMapper();
        Van van1 = mapper.readValue(responseStream, Van.class);

        // Finally we have the response
        System.out.println("Platenumber du Van créer: " + van1.getPlateNumber());

        return "redirect:/vans";
    }
    //DELETE
    @PostMapping("/vans/delete/{platenumber}")
    public String delete_van(Model model,@PathVariable("platenumber") String platenumber) throws IOException {

        System.out.println("Platenumber of van to delete: "+platenumber);
        // Create a neat value object to hold the URL
        URL url = new URL(function.base_url+"vans/supprimer/"+platenumber);
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


        return "redirect:/vans";
    }
}
