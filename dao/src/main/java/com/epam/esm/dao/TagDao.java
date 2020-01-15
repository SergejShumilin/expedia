package com.epam.esm.dao;

public interface TagDao<T> extends Dao<T> {
    T findByName(String name);
}
