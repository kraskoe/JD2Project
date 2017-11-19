package com.pvt.app.services;

import com.pvt.app.pojos.Product;
import com.pvt.app.pojos.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Yauheni Krasko on 18.11.2017.
 */
public interface IUserService extends IService<User> {
    List<User> getAllUsers(Pageable pageable);
    User getByEmail (String email);
}
