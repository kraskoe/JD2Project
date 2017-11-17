package com.pvt.app.repositories;

import com.pvt.app.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Yauheni Krasko on 17.11.2017.
 */
public interface UserJpaRepository extends JpaRepository<User, Integer> {
    List<User> findByName(String name);
    List<User> findByNameContaining(String pattern);
    List<User> findByNameLike(String pattern);
}
