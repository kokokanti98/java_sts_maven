package com.example.demo.rent;


import com.example.demo.person.Person;
import com.example.demo.vehicule.Vehicule;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Rent {

    private int id;

    public Vehicule vehicule;

    public Person person;

    private Date beginRent;

    private Date endRent;

    public Rent(){

    }

    //le JsonIgnore permet d'arreter la boucle entre les deux tables ManyToOne et OneToMany
    @JsonIgnore
    @ManyToOne
    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //@JsonSerialize(using=JsonSerializer.class)
    //@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a") //Pour que le format json de requestbody de Rent accept ce format en variable beginRent
    //@JsonFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date getBeginRent() {
        return beginRent;
    }

    public void setBeginRent(Date beginRent) {
        this.beginRent = beginRent;
    }

    //@JsonSerialize(using= JsonSerializer.class)
    //@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a") //Pour que le format json de requestbody de Rent accept ce format en variable endRent
    //@JsonFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date getEndRent() {
        return endRent;
    }

    public void setEndRent(Date endRent) {
        this.endRent = endRent;
    }
}
