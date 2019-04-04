package pers.ownsky.trafficalert.userapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RestController;
import pers.ownsky.trafficalert.publicutils.model.User;
import pers.ownsky.trafficalert.userapi.service.UserDataAccessService;
import pers.ownsky.trafficalert.userapi.service.UserOSSService;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceController {
    private final UserDataAccessService userDataAccessService;
    private final UserOSSService userOSSService;

    public RequestEntity<String> login(String phone, String password) {

    }

    public RequestEntity<String> logout(String phone, String token) {

    }

    public RequestEntity<String> register(User user) {

    }

    public RequestEntity<String> upload() {

    }

    public RequestEntity<>
}
