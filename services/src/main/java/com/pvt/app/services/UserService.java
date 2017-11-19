package com.pvt.app.services;

import com.pvt.app.pojos.User;
import com.pvt.app.repositories.UserPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yauheni Krasko on 18.11.2017.
 */

@Service
public class UserService implements IUserService {
    @Autowired
    private UserPagingRepository userPagingRepository;

    public User add(User user){
        if(user != null) {
            return userPagingRepository.save(user);
        }
        return user;
    }

    public User update(User user){
        if(user!=null){
            return userPagingRepository.save(user);
        }
        return user;
    }

    public User get(Serializable id){
        return userPagingRepository.findById(id).orElse(null);
    }

    public void delete(Serializable id){
        User user;
        if((user = userPagingRepository.findById(id).orElse(null)) != null){
            userPagingRepository.delete(user);
        }
    }

    public List<User> getAllUsers(Pageable pageable){
        return userPagingRepository.findAllBy(pageable).getContent();
    }

    public User getByEmail (String email){
        return userPagingRepository.findByEmail(email);
    }
}
