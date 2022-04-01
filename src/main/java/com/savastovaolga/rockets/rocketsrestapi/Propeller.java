package com.savastovaolga.rockets.rocketsrestapi;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "propellers")
public class Propeller {
    @Id
    private String propellerId = UUID.randomUUID().toString();
    private int maxLevelOfPower;
    private static final int POWER_TO_CHANGE = 10;
    private int currentLevelOfPower = 0;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "rocket_id")
    @JsonBackReference
    private Rocket rocket;

    public Propeller() {
    }

    public Propeller(int maxLevelOfPower) throws Exception {
        checkMaxLevelOfPower(maxLevelOfPower);
        this.maxLevelOfPower = maxLevelOfPower;
    }

    public int getCurrentLevelOfPower() {
        return currentLevelOfPower;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public int getMaxLevelOfPower() {
        return maxLevelOfPower;
    }

    private void checkMaxLevelOfPower(int maxLevelOfPower) throws Exception {
        if (maxLevelOfPower <= 0)
            throw new Exception("Error. Lavel of power cannot be 0 o negative");
    }

    public void setMaxLevelOfPower(int maxLevelOfPower) throws Exception {
        checkMaxLevelOfPower(maxLevelOfPower);
        this.maxLevelOfPower = maxLevelOfPower;
    }

    public void setPropellerId(String propellerId) {
        this.propellerId = propellerId;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public void setCurrentLevelOfPower(int currentLevelOfPower) {
        this.currentLevelOfPower = currentLevelOfPower;
    }

    public void acceleratePropeller() {
        this.currentLevelOfPower += POWER_TO_CHANGE;
        if (this.currentLevelOfPower > maxLevelOfPower) {
            this.currentLevelOfPower = maxLevelOfPower;
        }
    }

    public void brakePropeller() {
        this.currentLevelOfPower -= POWER_TO_CHANGE;
        if (this.currentLevelOfPower < 0) {
            this.currentLevelOfPower = 0;
        }
    }

    public String getPropellerId() {
        return propellerId;
    }

    @Override
    public String toString() {
        return "\n" + "Propeller" +
                " max Level Of Power = " + maxLevelOfPower + ";" +
                " current Level Of Power=" + currentLevelOfPower;
    }


}









