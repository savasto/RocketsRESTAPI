package com.savastovaolga.rockets.rocketsrestapi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RocketRestController {
    private RocketService rocketService;

    public RocketRestController(RocketService rocketService) {
        this.rocketService = rocketService;
    }


    @PostMapping("/rockets")
    public Rocket createRocket(@RequestBody Rocket rocketToCreate) {
        return rocketService.createRocket(rocketToCreate);
    }

    @PostMapping("/rockets/{rocketId}/movement")
    public void addMovement(@PathVariable String rocketId, @RequestBody Movement movement) throws Exception {
        rocketService.addMovement(rocketId, movement);
    }

    @PostMapping("/rockets/{rocketId}/propellers")
    public void assignPowerToPropeller(@PathVariable String rocketId, @RequestBody Propeller propeller) throws Exception {
        rocketService.addPropeller(rocketId, propeller);
    }


    @GetMapping("/rockets")
    public List<Rocket> getRockets() throws Exception {
        return rocketService.getRockets();
    }

    @GetMapping("/rockets/{rocketId}")
    public Rocket getRocket(@PathVariable String rocketId) throws Exception {
        return rocketService.getRocket(rocketId);
    }

    @GetMapping("rockets/{rocketId}/propellers")
    public List<Propeller> getPropellers(@PathVariable String rocketId) throws Exception {
        return rocketService.getPropellers(rocketId);
    }

    @GetMapping("rockets/{rocketId}/propellers/{propellerId}")
    public Propeller getPropeller(@PathVariable String rocketId, @PathVariable String propellerId) throws Exception {
        return rocketService.getPropeller(rocketId, propellerId);
    }

    @DeleteMapping("/rockets")
    public void removeAllRockets() {
        rocketService.removeAllRockets();
    }

    @DeleteMapping("/rockets/{rocketId}")
    public void removeRocket(@PathVariable String rocketId) {
        rocketService.removeRocket(rocketId);
    }

    @DeleteMapping("/rockets/{rocketId}/propellers")
    public void removeAllPropellers(@PathVariable String rocketId) throws Exception {
        rocketService.removeAllPropellers(rocketId);
    }

    @DeleteMapping("/rockets/{rocketId}/propellers/{propellerId}")
    public void removePropeller(@PathVariable String rocketId, @PathVariable String propellerId) throws Exception {
        rocketService.removePropeller(rocketId, propellerId);
    }


    @PutMapping("/rockets/{rocketId}")
    public void updateRocket(@RequestBody Rocket rocketToUpdate, @PathVariable String rocketId) throws Exception {
        rocketService.updateRocket(rocketToUpdate, rocketId);
    }

    @PutMapping("/rockets/{rocketId}/propellers/{propellerId}")
    public void updatePropeller(@RequestBody Propeller propellerToUpdate, @PathVariable String rocketId, @PathVariable String propellerId) throws Exception {
        rocketService.updatePropeller(rocketId, propellerToUpdate, propellerId);
    }
}
