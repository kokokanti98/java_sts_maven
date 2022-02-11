package com.example.demo.vehicule;

import com.example.demo.rent.Rent;

import java.util.ArrayList;
import java.util.Collection;

public class Vehicule {
    private Collection<Rent> rents = new ArrayList<Rent>();
    public Collection<Rent> getRents() {
        return rents;
    }

    public void setRents(Collection<Rent> rents) {
        this.rents = rents;
    }
    private String plateNumber;
    private String brand;
    private int price;

    public void addRent_Vehicule(Rent rent ){
        this.getRents().add( rent );
        rent.vehicule = this;
    }

    public Vehicule() {
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
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
}
