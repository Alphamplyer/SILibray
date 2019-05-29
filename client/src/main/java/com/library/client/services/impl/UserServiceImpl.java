package com.library.client.services.impl;

import com.library.client.model.User;
import com.library.client.model.UserLogin;
import com.library.client.services.AbstractService;
import com.library.client.services.interf.UserService;
import org.springframework.web.client.RestTemplate;

public class UserServiceImpl extends AbstractService implements UserService {

    @Override
    public User getUser(int user_id) {
        return restTemplate.getForObject( properties.getServiceURL() + "Users/" + user_id, User.class);
    }

    @Override
    public User register(User user) {
        return restTemplate.postForObject(properties.getServiceURL() + "Users", user, User.class);
    }

    @Override
    public User identify(UserLogin userLogin) {
        String url = properties.getServiceURL() + "Users/Identification";
        return restTemplate.postForObject(url, userLogin, User.class);
    }
}
