package com.library.batch_checkloan.service.impl;

import com.library.batch_checkloan.model.User;
import com.library.batch_checkloan.service.interf.UserService;
import org.springframework.web.client.RestTemplate;

public class UserServiceImpl extends AbstractService implements UserService {

    @Override
    public User getUser(int user_id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject( properties.getServiceURL() + "Users/" + user_id, User.class);
    }
}
