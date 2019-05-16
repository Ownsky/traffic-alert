package pers.ownsky.trafficalert.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.ownsky.trafficalert.dataaccess.model.CarPlate;

@Repository
public interface CarPlateRepository extends JpaRepository<CarPlate, Long> {
    CarPlate findByPlaceAndNum(Integer place, String num);
}
