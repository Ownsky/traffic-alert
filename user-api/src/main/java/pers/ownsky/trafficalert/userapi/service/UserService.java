package pers.ownsky.trafficalert.userapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import pers.ownsky.trafficalert.publicutils.json.UserNotFoundException;
import pers.ownsky.trafficalert.publicutils.model.Record;
import pers.ownsky.trafficalert.publicutils.model.User;
import pers.ownsky.trafficalert.userapi.remoteservice.UserAuthService;
import pers.ownsky.trafficalert.userapi.remoteservice.UserDataAccessService;
import pers.ownsky.trafficalert.userapi.remoteservice.UserOSSService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    public String login(String phone, String password) {
        return userAuthService.login(phone, password).getBody();
    }

    public String logout(String token) {
        return userAuthService.logout(token).getBody();
    }

    public Map<String, Object> preUpload(String phone, Double lat, Double lng) {

        User user = userDataAccessService.getUserByPhone(phone).getBody();
        if (user == null) {
            throw new UserNotFoundException(phone);
        }
        Record record = new Record();
        record.setUploader(user);
        record.setChecked(false);
        record.setLat(lat);
        record.setLng(lng);

        Long rid = userDataAccessService.newRecord(record).getBody();
        String token = userOSSService.getUploadToken().getBody();

        Map<String, Object> response = new HashMap<>();
        response.put("rid", rid);
        response.put("token", token);

        return response;
    }
}
