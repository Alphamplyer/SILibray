package com.library.client.services.interf;

import com.library.client.model.User;
import com.library.client.model.UserLogin;

public interface UserService {

    User getUser(int user_id);

    User register(User user);

    User identify(UserLogin userLogin);
}
