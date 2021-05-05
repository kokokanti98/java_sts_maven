package com.example.demo.rent;

import com.example.demo.function.Function;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class RentController {

    private Function function = new Function();

    //SEEALL
    @GetMapping("/rents")
    public String SeeAllRent(Model model) throws IOException {
        // Create a neat value object to hold the URL
        Rent rent = new Rent();
        model.addAttribute("rent", rent);
        //recuperer tous les données
        Object[] users = new Object[0];
        String url_after = "rents_all";
        String attrname = "rents";
        function.Chargerdonner(model,users,url_after,attrname);

        return "rent";
    }
    //CREATE
    @PostMapping("/rents")
    public String CreateRent_With_User_And_Car(@ModelAttribute("rent") Rent rent, Model model) throws IOException, ParseException {
        System.out.println("ID: "+rent.getId());

        //Begin date
        String newDateString = function.Date_toString(rent.getBeginRent());
        System.out.println("String begin date conv dd/MM/yyyy string: "+newDateString);
        System.out.println("Begin date: "+rent.getBeginRent());

        //platenumber
        System.out.println("Platenumber: "+rent.vehicule.getPlateNumber());
        //id du locateur
        System.out.println("ID of user: "+rent.person.getId());

        //End date
        String newDateString1 = function.Date_toString(rent.getEndRent());
        System.out.println("String end date conv dd/MM/yyyy string: "+newDateString1);
        System.out.println("End date: "+rent.getEndRent());

        // Create a neat value object to hold the URL
        URL url = new URL(function.base_url+"rents/creer/" + rent.getId() + "/" + newDateString + "/" + newDateString1 + "/"+rent.person.getId()+"/"+rent.vehicule.getPlateNumber());
        //System.out.println(function.base_url+"rents/creer/" + rent.getId() + "/" + newDateString + "/" + newDateString1 + "/"+rent.person.getId()+"/"+rent.vehicule.getPlateNumber());

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

        // Finally we have the response
        //System.out.println("rent creer: " + car.getPlateNumber());


        return "redirect:/rents";
    }
    //DELETE
    @PostMapping("/rents/delete/{id}")
    public String delete_rent(Model model,@PathVariable("id") int id) throws IOException {
        System.out.println("ID to delete: "+id);
        // Create a neat value object to hold the URL
        URL url = new URL(function.base_url+"rents/supprimer/"+id);
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


        return "redirect:/rents";
    }
}
