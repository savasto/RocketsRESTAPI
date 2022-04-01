package com.savastovaolga.rockets.rocketsrestapi;

public class Movement {
    private String typeOfMovement;
    private int times;
    public static final String ACCELERATE = "accelerate";
    public static final String BRAKE = "brake";

    public Movement() {
    }

    public Movement(String typeOfMovement, int times) throws Exception {
        checkTypeOfMovement(typeOfMovement);
        checkTimes(times);
        this.typeOfMovement = typeOfMovement;
        this.times = times;
    }

    public void checkTypeOfMovement(String typeOfMovement) throws Exception {//?
        if (!typeOfMovement.equalsIgnoreCase(ACCELERATE) && !typeOfMovement.equalsIgnoreCase(BRAKE))
            throw new Exception("Wrong type of movement");
    }

    public int getTimes() {
        return times;
    }

    public String getTypeOfMovement() {
        return typeOfMovement;
    }

    public void checkTimes(int times) throws Exception {
        if (times <= 0)
            throw new Exception("Error. Times cannot be negative");
    }


    public void setTimes(int times) throws Exception {
        checkTimes(times);
        this.times = times;
    }

    public void setTypeOfMovement(String typeOfMovement) throws Exception {
        checkTypeOfMovement(typeOfMovement);
        this.typeOfMovement = typeOfMovement;
    }
}
