package pers.ownsky.trafficalert.userapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import pers.ownsky.trafficalert.publicutils.json.RestException;
import pers.ownsky.trafficalert.publicutils.json.UserNotFoundException;
import pers.ownsky.trafficalert.publicutils.model.CarPlate;
import pers.ownsky.trafficalert.publicutils.model.Record;
import pers.ownsky.trafficalert.publicutils.model.User;
import pers.ownsky.trafficalert.userapi.remoteservice.UserAuthService;
import pers.ownsky.trafficalert.userapi.remoteservice.UserDataAccessService;
import pers.ownsky.trafficalert.userapi.remoteservice.UserOSSService;

import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserOSSService userOSSService;
    private final UserDataAccessService userDataAccessService;
    private final UserAuthService userAuthService;

    public String register(User user) {
        user.setRegDate(new Date());
        userDataAccessService.saveUser(user).getBody();
        return "register success";
    }

    public Map<String, Object> login(String phone, String password) {
        String token = userAuthService.login(phone, password).getBody();
        User user = userDataAccessService.getUserByPhone(phone).getBody();
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("user", user);
        return result;
    }

    public String logout(String token) {
        return userAuthService.logout(token).getBody();
    }

    public Map<String, Object> preUpload(String phone, Double lat, Double lng) {

//        User user = userDataAccessService.getUserByPhone(phone).getBody();
//        if (user == null) {
//            throw new UserNotFoundException(phone);
//        }
        User user = new User();
        user.setPhone(phone);
        Record record = new Record();
        record.setUploader(user);
        record.setChecked(false);
        record.setPushed(false);
        record.setLat(lat);
        record.setLng(lng);
        record.setDate(new Date());

        Long rid = userDataAccessService.newRecord(record).getBody();
        String token = userOSSService.getUploadToken().getBody();

        Map<String, Object> response = new HashMap<>();
        response.put("rid", rid);
        response.put("token", token);

        return response;
    }

    public List<Record> notPushed(String phone) {
        User user = userDataAccessService.getUserByPhone(phone).getBody();
        List<Record> result = new ArrayList<>();
        for (CarPlate carPlate : user.getCars()) {
            List<Record> rs = userDataAccessService.notPushedToCar(carPlate.getId()).getBody();
            if (rs == null) throw new RestException("...");
            for (Record r : rs) {
                r.setUploader(null);
                result.add(r);
            }
        }
        return result;
    }
}
