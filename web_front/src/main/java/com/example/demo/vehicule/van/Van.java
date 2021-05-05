package com.example.demo.vehicule.van;

import com.example.demo.vehicule.Vehicule;

public class Van extends Vehicule {
    float maxweight;

    public Van(float maxweight) {
        super();
        this.maxweight = maxweight;
    }

    public Van() {

    }
    public float getMaxweight() {
        return maxweight;
    }

    public void setMaxweight(float maxweight) {
        this.maxweight = maxweight;
    }
}
