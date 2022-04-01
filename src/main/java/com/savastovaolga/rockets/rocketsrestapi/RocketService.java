//FASE 3:
////
////Per la fase 3 modificarem els propulsors afegint també una potència actual.
//// Un propulsor tindrà una potència màxima (no la pot superar) i una potència actual (la potència que té el propulsor en aquell moment).
//// Tots els propulsors tindran una potència actual que començarà amb 0.
////
////El coet tindrà dos mètodes, accelerar o frenar i  augmentarà o es reduirà de 10 en 10 la potència de cada propulsor.
////
////La potencia actual del coet, és la suma de totes les potències de cada propulsor.
////
////Un cop aplicat el canvi:
////
////Modifiquem el main anterior per saber la potencia actual en un moment concret.
////
////Creem dos coets amb els codis “32WESSDS” I “LDSFJA32”.
//// El primer coet tindrà tres propulsors (potència màxima: 10,30,80) i el segon sis propulsors (potència màxima: 30,40,50,50,30,10).
////
////Mostrar a pantalla el codi dels coets, el nombre de propulsors que té i la potència màxima de cada propulsor.
////
////Accelerar amb els coets tres cops
////
////Mostrar a pantalla la potencia actual (suma de les potencies per propulsor)
////
////Frenar cinc cops amb el primer coet (“32WESSDS”) i accelerar set amb el segon coet.
////
////Mostrar a pantalla la potencia actual (suma de les potencies per propulsor)
////
////Accelerar 15 cops amb els dos coets.
////
////Mostrar a pantalla la velocitat actual


package com.savastovaolga.rockets.rocketsrestapi;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RocketService {
    private RocketRepository rocketRepository;
    private PropellerRepository propellerRepository;
    private static final String ACCELERATE = "accelerate";
    private static final String BRAKE = "brake";

    public RocketService(RocketRepository rocketRepository, PropellerRepository propellerRepository) {
        this.rocketRepository = rocketRepository;
        this.propellerRepository = propellerRepository;
    }

    //POST rockets
    public Rocket createRocket(Rocket rocket) {
        this.rocketRepository.save(rocket);
        return rocket;
    }

    //POST rockets/{rocketId}/propellers
    public Propeller addPropeller(String rocketId, Propeller propeller) throws Exception {
        Rocket rocket = findRocket(rocketId);
        propeller = rocket.addPropeller(propeller);
        rocket.addPropeller(propeller);
        propellerRepository.save(propeller);
        return propeller;
    }

    private Rocket findRocket(String rocketId) {
        return rocketRepository.findById(rocketId).get();
    }


    //GET rockets
    public List<Rocket> getRockets() {
        return (List<Rocket>) rocketRepository.findAll();
    }

    //GET rockets/rocketId
    public Rocket getRocket(String rocketId) {
        return rocketRepository.findById(rocketId).get();
    }

    //GET rockets/rocketId/propellers
    public List<Propeller> getPropellers(String rocketId) {
        Rocket rocket = findRocket(rocketId);
        List<Propeller> propellers = rocket.getPropellers();
        return propellers;
    }

    //GET rockets/rocketId/propellers/propellerId
    public Propeller getPropeller(String rocketId, String propellerId) {
        return propellerRepository.findById(propellerId).get();
    }

    //DELETE rockets
    public void removeAllRockets() {
        rocketRepository.deleteAll();
    }

    //DELETE rockets/rocketId
    public void removeRocket(String rocketId) {
        rocketRepository.deleteById(rocketId);
    }

    //DELETE rockets/rocketId/propellers
    public void removeAllPropellers(String rocketId) {
        Rocket rocket = findRocket(rocketId);
        propellerRepository.deleteAllByRocket(rocket);
    }

    //DELETE rockets/rocketId/propellers/propellerId
    public void removePropeller(String rocketId, String propellerId) {
        Propeller propeller = getPropeller(rocketId, propellerId);
        propellerRepository.delete(propeller);
    }

    //PUT rockets/rocketId
    public Rocket updateRocket(Rocket rocketToUpdate, String rocketId) throws Exception {
        Rocket rocket = findRocket(rocketId);
        rocket.setCodeOfRocket(rocketToUpdate.getCodeOfRocket());
        rocketRepository.save(rocket);
        return rocket;
    }

    //PUT rockets/rocketId/propellers/propellerId
    public Propeller updatePropeller(String rocketId, Propeller propellerToUpdate, String propellerId) throws Exception {
        Rocket rocket = findRocket(rocketId);
        for (Propeller propeller : rocket.getPropellers()
        ) {
            if (propeller.getPropellerId().equalsIgnoreCase(propellerId)) {
                propeller.setMaxLevelOfPower(propellerToUpdate.getMaxLevelOfPower());
                propellerRepository.save(propeller);
                return propeller;
            }
        }
        throw new Exception("Not found");
    }

    //POST rockets/rocketId/movement
    public void addMovement(String rocketId, Movement movement) throws Exception {
        Rocket rocket = findRocket(rocketId);

        if (movement.getTypeOfMovement().equalsIgnoreCase(Movement.ACCELERATE)) {
            rocket.accelerate(movement.getTimes());
        } else if (movement.getTypeOfMovement().equalsIgnoreCase(Movement.BRAKE)) {
            rocket.brake(movement.getTimes());
        }
        rocketRepository.save(rocket);
    }
}
