package com.digipolitan.sample.retrofit.domain.user.service;

import com.digipolitan.sample.retrofit.domain.user.model.User;
import com.digipolitan.sample.retrofit.platform.common.IServiceResultListener;


/**
 * @author Julien BACZYNSKI http://www.digipolitan.com on 10/04/2017.
 */

public interface IUserService {

    void create(User user, IServiceResultListener<String> resultListener);
    void read(String userID, IServiceResultListener<User> resultListener);
}
