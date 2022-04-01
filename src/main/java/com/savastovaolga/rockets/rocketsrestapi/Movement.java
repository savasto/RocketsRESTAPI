package com.savastovaolga.rockets.rocketsrestapi;

public class Movement {
    private String typeOfMovement;
    private int times;
    public static final String ACCELERATE = "accelerate";
    public static final String BRAKE = "brake";

    public Movement() {
    }

    public Movement(String typeOfMovement, int times) throws Exception {
      // checkTypeOfMovement(typeOfMovement);//?
        this.typeOfMovement = typeOfMovement;
        this.times = times;
    }

//    public void checkTypeOfMovement(String typeOfMovement) throws Exception {//?
//        if (typeOfMovement != ACCELERATE && typeOfMovement != BRAKE)
//            throw new Exception("Wrong type of movement");
//    }
//
//    public void checkTypeOfMovement() throws Exception {//?
//        checkTypeOfMovement(this.typeOfMovement);
//    }

    public int getTimes() {
        return times;
    }

    public String getTypeOfMovement() {
        return typeOfMovement;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setTypeOfMovement(String typeOfMovement) throws Exception {
      // checkTypeOfMovement(typeOfMovement);//?
        this.typeOfMovement = typeOfMovement;
    }
}
