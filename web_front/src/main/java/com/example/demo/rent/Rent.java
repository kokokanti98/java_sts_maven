package com.example.demo.rent;

import com.example.demo.vehicule.Vehicule;
import com.example.demo.person.Person;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Rent {
    private int id;

    public Vehicule vehicule;

    public Person person;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginRent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endRent;


    public Rent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    //@JsonFormat(pattern = "dd/MM/yyyy")
    //@JsonFormat(pattern = "yyyy-MM-dd")
    public Date getBeginRent() {
        return beginRent;
    }

    public void setBeginRent(Date beginRent) {
        this.beginRent = beginRent;
    }
    //@JsonFormat(pattern = "dd/MM/yyyy")
    public Date getEndRent() {
        return endRent;
    }

    public void setEndRent(Date endRent) {
        this.endRent = endRent;
    }
}
