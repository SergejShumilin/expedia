package com.epam.esm.dao;

import java.util.List;

public interface GiftCertificatesDao<T> extends Dao<T> {
    void update(T t);
    List<T> sortByDate(String type);
    List<T> sortByDateAndName(String type);
    List<T> sortByName(String type);
    List<T> findByPartName(String name);
    List<T> findByDescription(String description);
    List<T> findByTag(String tagName);
    boolean isExistByDescription(String description);
    boolean isExistByTagName(String tagName);

}
