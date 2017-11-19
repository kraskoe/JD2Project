package com.pvt.app.services;

import java.io.Serializable;

/**
 * Created by Yauheni Krasko on 18.11.2017.
 */
public interface IService<T> {
    T add(T t);

    T update(T t);

    T get(Serializable id);

    void delete(Serializable id);
}
