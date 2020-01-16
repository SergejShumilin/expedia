package com.epam.esm.service;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.entity.Tag;
import com.epam.esm.dao.exception.TagExistsException;
import com.epam.esm.dao.exception.TagNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagService {
    private final TagDao<Tag> tagDao;

    public TagService(TagDao<Tag> tagDao) {
        this.tagDao = tagDao;
    }

    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    public void save(Tag tag) {
        boolean existByName = isExistByName(tag.getName());
        if (existByName){
            throw new TagExistsException(tag.getName());
        }
        tagDao.save(tag);
    }

    public void delete(int id) {
        boolean exist = isExist(id);
        if (!exist){
            throw new TagNotFoundException(id);
        }
        tagDao.delete(id);
    }

    public Tag findById(int id){
        boolean exist = isExist(id);
        if (!exist){
            throw new TagNotFoundException(id);
        }
        return tagDao.findById(id);
    }

    public Tag findByName(String name){
        boolean existByName = isExistByName(name);
        if (!existByName){
            throw new TagNotFoundException(name);
        }
        return tagDao.findByName(name);
    }

    public boolean isExist(int id){
        return tagDao.isExistById(id);
    }

    public boolean isExistByName(String name){
        return tagDao.isExistByName(name);
    }

}
