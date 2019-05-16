package pers.ownsky.trafficalert.dataaccess.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ownsky.trafficalert.dataaccess.datasource.TargetDataSource;
import pers.ownsky.trafficalert.dataaccess.model.CarPlate;
import pers.ownsky.trafficalert.dataaccess.repository.CarPlateRepository;
import pers.ownsky.trafficalert.publicutils.json.RestException;

@RestController
@RequestMapping("/carPlate")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarPlateController {
    private final CarPlateRepository carPlateRepository;

    @TargetDataSource("read")
    @GetMapping("/{place}/{num}")
    public ResponseEntity<CarPlate> getByNum(@PathVariable Integer place, @PathVariable String num) {
        CarPlate carPlate = carPlateRepository.findByPlaceAndNum(place, num);
        if (carPlate == null) {
            throw new RestException(404, "CarPlate not found.");
        }
        return new ResponseEntity<>(carPlate, HttpStatus.OK);
    }

}
