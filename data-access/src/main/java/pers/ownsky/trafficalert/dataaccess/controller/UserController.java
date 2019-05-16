package pers.ownsky.trafficalert.dataaccess.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.ownsky.trafficalert.dataaccess.model.CarPlate;
import pers.ownsky.trafficalert.dataaccess.model.User;
import pers.ownsky.trafficalert.dataaccess.repository.CarPlateRepository;
import pers.ownsky.trafficalert.dataaccess.repository.UserRepository;
import pers.ownsky.trafficalert.publicutils.json.IllegalParameterException;
import pers.ownsky.trafficalert.publicutils.json.UserNotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserRepository userRepository;
    private final CarPlateRepository carPlateRepository;

    @GetMapping("/findByPhone")
    public ResponseEntity<User> getUserByPhone(@RequestParam String phone) {
        User user = userRepository.findByPhone(phone);
        if (user == null) {
            throw new UserNotFoundException(phone);
        }
        user.setPassword(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/validate")
    public ResponseEntity<User> validateUser(@RequestParam String phone,
                             @RequestParam String password) {
        User user = userRepository.findByPhoneAndPassword(phone, password);
        if (user == null) {
            throw new UserNotFoundException(phone, password);
        }
        user.setPassword(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    // TODO: to solve null update problem, use @ModelAttribute
    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        if (user == null || user.getPhone() == null) {
            throw new IllegalParameterException();
        }
        List<CarPlate> cars = user.getCars();
        user = userRepository.save(user);
        if (cars != null) {
            for (CarPlate carPlate : cars) {
                carPlate.setOwner(user);
                carPlateRepository.save(carPlate);
            }
        }
        user.setPassword(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
