package com.epam.esm.dao;

import java.util.List;

public interface GiftCertificatesDao<T> extends Dao<T> {
    void update(T t);
    List<T> findByName(String name);
}
