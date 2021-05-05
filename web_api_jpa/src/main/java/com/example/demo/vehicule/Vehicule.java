package com.example.demo.vehicule;

import com.example.demo.rent.Rent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Vehicule {

    private Collection<Rent> rents = new ArrayList<Rent>();

    @OneToMany(mappedBy = "vehicule", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    public Collection<Rent> getRents() {
        return rents;
    }

    public void setRents(Collection<Rent> rents) {
        this.rents = rents;
    }

    //@Column(name="plateNumber")
    @Column(nullable = false)
    private String plateNumber;
    //@Column(name="brand")
    @Basic(optional = false)
    @Column(nullable = false)
    private String brand;
    //@Column(name="price")
    @Basic(optional = false)
    @Column(nullable = false)
    private int price;

    public Vehicule(String p_platenumber,String brand,int price) {
        super();
        this.plateNumber = p_platenumber;
        this.brand = brand;
        this.price = price;
    }

    public Vehicule() {

    }
    public void addRent_Vehicule(Rent rent ){
        this.getRents().add( rent );
        rent.vehicule = this;
    }

    @Id
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
