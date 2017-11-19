package com.pvt.app.repositories;

import com.pvt.app.pojos.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yauheni Krasko on 17.11.2017.
 */
public interface UserPagingRepository extends PagingAndSortingRepository<User, Serializable> {
    Page<User> findAllBy(Pageable pageable);
    Page<User> findByName(String name, Pageable pageable);
    Page<User> findBySurname(String surname, Pageable pageable);
    Page<User> findBySurnameAndName(String surname, String name, Pageable pageable);
    Page<User> findBySurnameContaining(String pattern, Pageable pageable);
    Page<User> findBySurnameLike(String pattern, Pageable pageable);
    Page<User> findByPhoneContaining(String pattern, Pageable pageable);
    User findByEmail(String email);
}
