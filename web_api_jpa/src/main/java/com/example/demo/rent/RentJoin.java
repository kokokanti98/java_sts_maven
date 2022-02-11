package com.example.demo.rent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class RentJoin {
    private int id;

    //public Vehicule vehicule;
    public String platenumber;
    public String brand;
    public int price;


    //public Person person;
    public int id_personne;
    public String name;

    private Date beginRent;

    private Date endRent;

    public long totalRent;

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

    public long getTotalRent() throws ParseException {
        //difference de jour
        long numberofdays = Soustraction_Date(this.beginRent,this.endRent);
        //afficher
        System.out.println("Number of days difference is: "+numberofdays);
        //calcul du total en euros par jour
        totalRent = numberofdays * this.price;
        //retourne la valeur
        return totalRent;
    }

    public void setTotalRent(long totalRent) {
        this.totalRent = totalRent;
    }

    public RentJoin(int id, String platenumber, String brand, int price, int id_personne, String name, Date beginRent, Date endRent) throws ParseException {
        this.id = id;
        this.platenumber = platenumber;
        this.brand = brand;
        this.price = price;
        this.id_personne = id_personne;
        this.name = name;
        this.beginRent = beginRent;
        this.endRent = endRent;
        this.totalRent = this.getTotalRent();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlatenumber() {
        return platenumber;
    }

    public void setPlatenumber(String platenumber) {
        this.platenumber = platenumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId_personne() {
        return id_personne;
    }

    public void setId_personne(int id_personne) {
        this.id_personne = id_personne;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginRent() {
        return beginRent;
    }

    public void setBeginRent(Date beginRent) {
        this.beginRent = beginRent;
    }

    public Date getEndRent() {
        return endRent;
    }

    public void setEndRent(Date endRent) {
        this.endRent = endRent;
    }
}
