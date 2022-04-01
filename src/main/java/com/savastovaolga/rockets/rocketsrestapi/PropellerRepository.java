package com.savastovaolga.rockets.rocketsrestapi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PropellerRepository extends CrudRepository<Propeller, String> {
    @Transactional
    List<Propeller> deleteAllByRocket(Rocket rocket);
    Propeller getByRocket(Rocket rocket);
}
