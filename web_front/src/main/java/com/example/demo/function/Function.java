package com.example.demo.function;

import com.example.demo.person.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Function {

    public String base_url ="http://localhost:8089/";

    public Function() {
    }

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public void Chargerdonner(Model model, Object[] liste_objet, String url_after, String AttrName) throws IOException {
        // Create a neat value object to hold the URL
        URL url = new URL(base_url+url_after);
        //System.out.println(base_url+url_after);
        // Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestMethod("GET");

        // This line makes the request
        InputStream responseStream = connection.getInputStream();

        // Manually converting the response body InputStream to APOD using Jackson
        ObjectMapper mapper = new ObjectMapper();
        liste_objet = mapper.readValue(responseStream, Object[].class);

        model.addAttribute(AttrName,liste_objet);
    }
    public String Date_toString(Date date) throws ParseException {
        //date from form to old format
        //format
        final String OLD_FORMAT = "yyyy-MM-dd";
        final String NEW_FORMAT = "dd-MM-yyyy";
        //date format old one from form
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = formatter.format(date);
        System.out.println(format);
        String newDateString;


        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        //Change to new one format
        Date d = sdf.parse(format);
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);
        //System.out.println("String begin date conv dd/MM/yyyy string: "+newDateString);
        return newDateString;
    }
    public Date Add_one_Day(Date date) throws ParseException {
        //Date today = new Date();
        System.out.println(date);      //Sat Jul 14 22:25:03 IST 2018

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        // manipulate date
        //cal.add(Calendar.YEAR, 1);
        //cal.add(Calendar.MONTH, 1);
        //cal.add(Calendar.DATE, 1);
        cal.add(Calendar.DAY_OF_MONTH, 1); //Plus one day

        // convert calendar to date
        Date modifiedDate = cal.getTime();
        System.out.println("Original date plus one day is:" + modifiedDate);       //Fri Aug 16 22:25:03 IST 2019
        return modifiedDate;
    }

    public static long Soustraction_Date(Date date1, Date date2) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date firstDate = sdf.parse(String.valueOf(date1));
        Date secondDate = sdf.parse(String.valueOf(date2));

        long diff = secondDate.getTime() - firstDate.getTime();

        TimeUnit time = TimeUnit.DAYS;
        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
        System.out.println("The difference in days is : "+diffrence);

        return diffrence;
    }
    public Person[]  Chargerdonner_login(Person[] liste_objet) throws IOException {
        String url_after = "persons";
        // Create a neat value object to hold the URL
        URL url = new URL(base_url+url_after);
        //System.out.println(base_url+url_after);
        // Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestMethod("GET");

        // This line makes the request
        InputStream responseStream = connection.getInputStream();

        // Manually converting the response body InputStream to APOD using Jackson
        ObjectMapper mapper = new ObjectMapper();
        liste_objet = mapper.readValue(responseStream, Person[].class);

        return liste_objet;

    }
}
