package com.epam.esm.dao;

import java.util.List;

public interface GiftCertificatesDao<T> extends Dao<T> {
    void update(T t);
    List<T> sort(String type);
    List<T> sortByName(String type);
    List<T> findByName(String name);
    T findByDescription(String description);
    List<T> findByTag(String tagName);
}
