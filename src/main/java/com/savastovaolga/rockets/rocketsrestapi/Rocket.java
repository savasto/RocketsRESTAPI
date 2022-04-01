package com.savastovaolga.rockets.rocketsrestapi;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.savastovaolga.rockets.rocketsrestapi.Propeller;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "rockets")

public class Rocket {
    @Id
    private String rocketId = UUID.randomUUID().toString();
    private String codeOfRocket;

    @OneToMany(mappedBy = "rocket")
    @JsonManagedReference
    private List<Propeller> propellers = new ArrayList<>();

    public Rocket() {
    }

    public Rocket(String codeOfRocket) throws Exception {
        checkCodeOfRocket(codeOfRocket);
        this.codeOfRocket = codeOfRocket;
    }

    private void checkCodeOfRocket(String codeOfRocket) throws Exception {
        if (!codeOfRocket.toUpperCase().matches("[0-9]{2}[A-Z]{6}")) {
            throw new Exception("Code of rocket should have 2 numbers and 6 characters");
        }
    }

    public String getRocketId() {
        return rocketId;
    }

    public String getCodeOfRocket() {
        return codeOfRocket;
    }

    public List<Propeller> getPropellers() {
        return propellers;
    }

    public void setCodeOfRocket(String codeOfRocket) throws Exception {
        checkCodeOfRocket(codeOfRocket);
        this.codeOfRocket = codeOfRocket;
    }

    public void setPropellers(List<Propeller> propellers) {
        this.propellers = propellers;
    }

    public void accelerate(int times) {
        for (int i = 0; i < times; i++) {
            for (Propeller propeller : propellers) {
                propeller.acceleratePropeller();
            }
        }
    }

    public void brake(int times) {
        for (int i = 0; i < times; i++) {
            for (Propeller propeller : propellers) {
                propeller.brakePropeller();
            }
        }
    }

    public void setRocketId(String rocketId) {
        this.rocketId = rocketId;
    }

    public Propeller addPropeller(Propeller propeller) throws Exception {
        propeller.setRocket(this);
        this.propellers.add(propeller);
        return propeller;
    }



    @JsonProperty("currentPower")
    public int currentPower() {
        return propellers.stream()
                .mapToInt(Propeller::getCurrentLevelOfPower)
                .sum();
    }

    @Override
    public String toString() {
        return
                "code Of Rocket : " + codeOfRocket +
                        " propellers" + propellers;
    }
}