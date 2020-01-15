package com.epam.esm.service;

import com.epam.esm.dao.GiftCertificatesDao;
import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.dao.entity.Tag;
import com.epam.esm.dao.exception.CertificateNotFoundException;
import com.epam.esm.dao.exception.TagNotFoundException;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class GiftCertificateService {

    private final static String DATE_TIME = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    private final GiftCertificatesDao<GiftCertificate> giftCertificatesDao;
    private final TagService tagService;

    public GiftCertificateService(GiftCertificatesDao<GiftCertificate> giftCertificatesDao, TagService tagService) {
        this.giftCertificatesDao = giftCertificatesDao;
        this.tagService = tagService;
    }

    public List<GiftCertificate> findAll() {
        return giftCertificatesDao.findAll();
    }

    public GiftCertificate findById(int id) throws TagNotFoundException {
        boolean exist = giftCertificatesDao.isExist(id);
        if (!exist) {
            throw new TagNotFoundException(id);
        }
        return giftCertificatesDao.findById(id);
    }

    public List<GiftCertificate> findByName(String name) throws CertificateNotFoundException {
        boolean exist = giftCertificatesDao.isExistByName(name);
        if (!exist) {
            throw new CertificateNotFoundException(name);
        }
        return giftCertificatesDao.findByName(name);
    }

    public GiftCertificate findByDescription(String description){
        return giftCertificatesDao.findByDescription(description);
    }

    public List<GiftCertificate> findByTag(String tagName) throws CertificateNotFoundException {
        boolean existByName = tagService.isExistByName(tagName);
        if (!existByName){
            throw new CertificateNotFoundException(tagName);
        }
        return giftCertificatesDao.findByTag(tagName);
    }
    public void save(GiftCertificate giftCertificate) {

        checkAndSaveTagIfNotExist(giftCertificate);
        giftCertificate.setCreateDate(DATE_TIME);
        giftCertificate.setLastUpdateDate(DATE_TIME);
        giftCertificatesDao.save(giftCertificate);
    }

    public void update(GiftCertificate giftCertificate) throws TagNotFoundException {
        checkAndSaveTagIfNotExist(giftCertificate);
        GiftCertificate certificateFromDb = findById(giftCertificate.getId());
        boolean equalsCertificates = certificateFromDb.equals(giftCertificate);
        if (!equalsCertificates) {
            changeCertificate(certificateFromDb, giftCertificate);
            giftCertificatesDao.update(certificateFromDb);
        }
    }

    public void delete(int id) {
        giftCertificatesDao.delete(id);
    }

    public List<GiftCertificate> sortByDate(String typeSort) {
        return giftCertificatesDao.sort(typeSort);
    }
    public List<GiftCertificate> sortByName(String typeSort) {
        return giftCertificatesDao.sort(typeSort);
    }

    private void checkAndSaveTagIfNotExist(GiftCertificate giftCertificate){
        Tag tag = giftCertificate.getTag();
        if (!tagService.isExistByName(tag.getName())) {
            tagService.save(tag);
        }
        tag = tagService.findByName(tag.getName());
        giftCertificate.setTag(tag);
    }

    private void changeCertificate(GiftCertificate certificateFromDb, GiftCertificate certificate) {
        if (!certificate.getName().equals(certificateFromDb.getName())) {
            certificateFromDb.setName(certificate.getName());
        }
        if (certificate.getPrice() != certificateFromDb.getPrice()) {
            certificateFromDb.setPrice(certificate.getPrice());
        }
        if (!certificate.getDescription().equals(certificateFromDb.getDescription())) {
            certificateFromDb.setDescription(certificate.getDescription());
        }
        if (!certificate.getTag().equals(certificateFromDb.getTag())) {
            certificateFromDb.setTag(certificate.getTag());
        }
        if (certificate.getDuration() != certificateFromDb.getDuration()) {
            certificateFromDb.setDuration(certificate.getDuration());
        }
        certificateFromDb.setLastUpdateDate(DATE_TIME);
    }
}
