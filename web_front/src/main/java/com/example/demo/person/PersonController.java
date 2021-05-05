package com.example.demo.person;

import com.example.demo.function.Function;
import com.example.demo.person.Person;
import com.fasterxml.jackson.core.JsonParser;
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
public class PersonController {

    private Function function = new Function();
    //String base_url ="http://localhost:8089/";

    //SEEALL
    @GetMapping("/users")
    public String Affichage(Model model) throws IOException {
        // Create a neat value object to hold the URL
        Person personne = new Person();
        model.addAttribute("personne", personne);
        //recuperer tous les données
        Object[] users = new Object[0];
        String url_after = "persons";
        String attrname = "users";
        function.Chargerdonner(model,users,url_after,attrname);

        return "person";
    }
    //CREATE
    @PostMapping("/users")
    public String CreateUser_Form(@ModelAttribute("personne") Person personne, Model model) throws IOException {
        System.out.println("ID: "+personne.id);
        System.out.println("Name: "+personne.name);
        String name_underscore = personne.name.replaceAll(" ", "_");
        System.out.println(name_underscore);
        // Create a neat value object to hold the URL
        URL url = new URL(function.base_url+"persons/create?id=" + personne.id + "&name=" + name_underscore);

        // Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestMethod("POST");


        // This line makes the request
        InputStream responseStream = connection.getInputStream();

        // Manually converting the response body InputStream to APOD using Jackson
        ObjectMapper mapper = new ObjectMapper();
        Person user = mapper.readValue(responseStream, Person.class);

        // Finally we have the response
        System.out.println("Nom de l utilisateur creer: " + user.name);

        return "redirect:/users";
    }
    //DELETE
    @PostMapping("/users/delete/{id}")
    public String DeleteUser(Model model,@PathVariable("id") int id) throws IOException {
        System.out.println("ID to delete: "+id);
        // Create a neat value object to hold the URL
        URL url = new URL(function.base_url+"persons/supprimer/"+id);

        // Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("accept", "application/x-www-form-urlencoded");
        connection.setRequestMethod("POST");


        // This line makes the request
        InputStream responseStream = connection.getInputStream();

        // Manually converting the response body InputStream to APOD using Jackson
        try {
            //  Block of code to try
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            var message = mapper.readValue(responseStream, Object.class);
            System.out.println(message);
            // Finally we have the response
            System.out.println("Message: " + message);
        }
        catch(Exception e) {
            //  Block of code to handle errors
        }


        return "redirect:/users";
    }
    //Pour voir un seul
    @GetMapping("/1")
    public String SeeOneUser1(Model model) throws IOException {
        // Create a neat value object to hold the URL
        URL url = new URL(function.base_url+"person?id=1");

        // Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestMethod("GET");

        // This line makes the request
        InputStream responseStream = connection.getInputStream();

        // Manually converting the response body InputStream to APOD using Jackson
        ObjectMapper mapper = new ObjectMapper();
        Person user = mapper.readValue(responseStream, Person.class);

        // Finally we have the response
        System.out.println("Nom del  utilisateur recherche: " + user.name);

        model.addAttribute("name",user.name);
        return "index";
    }

}
