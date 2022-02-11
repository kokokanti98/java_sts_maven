package com.example.demo.person;

import com.example.demo.rent.Rent;

import java.util.ArrayList;
import java.util.Collection;

public class Person {
    int id;
    String name;

    private Collection<Rent> rents = new ArrayList<Rent>();

    public Collection<Rent> getRents() {
        return rents;
    }

    public void addRent_Person(Rent rent ){
        this.getRents().add( rent );
        rent.person = this;
    }
    public void setRents(Collection<Rent> rents) {
        this.rents = rents;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
