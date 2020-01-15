package com.epam.esm.service;

import com.epam.esm.dao.GiftCertificatesDao;
import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.dao.exception.CertificateNotFoundException;
import com.epam.esm.dao.exception.TagNotFoundException;
import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.dao.impl.TagDaoImp;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.util.CertificateUpdater;
import com.epam.esm.util.TagVerification;
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
    private final CertificateUpdater certificateUpdater;
    private final TagVerification tagVerification;

    public GiftCertificateService(GiftCertificatesDao<GiftCertificate> giftCertificatesDao, TagService tagService, CertificateUpdater certificateUpdater, TagVerification tagVerification) {
        this.giftCertificatesDao = giftCertificatesDao;
        this.tagService = tagService;
        this.certificateUpdater = certificateUpdater;
        this.tagVerification = tagVerification;
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

    public List<GiftCertificate> findByDescription(String description) throws CertificateNotFoundException {
        boolean exist = giftCertificatesDao.isExistByDescription(description);
        if (!exist){
            throw new CertificateNotFoundException(description);
        }
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
        if (giftCertificate.getTag()!=null) {
            tagVerification.checkAndSaveTagIfNotExist(tagService, giftCertificate);
        }
        giftCertificate.setCreateDate(DATE_TIME);
        giftCertificate.setLastUpdateDate(DATE_TIME);
        giftCertificatesDao.save(giftCertificate);
    }

    public void update(GiftCertificate giftCertificate) throws TagNotFoundException {
        if (giftCertificate.getTag()!=null) {
            tagVerification.checkAndSaveTagIfNotExist(tagService, giftCertificate);
        }
        GiftCertificate certificateFromDb = findById(giftCertificate.getId());
        boolean equalsCertificates = certificateFromDb.equals(giftCertificate);
        if (!equalsCertificates) {
            certificateUpdater.changeCertificate(certificateFromDb, giftCertificate);
            giftCertificatesDao.update(certificateFromDb);
        }
    }

    public void delete(int id) {
        giftCertificatesDao.delete(id);
    }

    public List<GiftCertificate> sortByDate(String typeSort) {
        return giftCertificatesDao.sortByDate(typeSort);
    }
    public List<GiftCertificate> sortByName(String typeSort) {
        return giftCertificatesDao.sortByName(typeSort);
    }

    public List<GiftCertificate> sortByDateAndName(String typeSort) {
        return giftCertificatesDao.sortByDateAndName(typeSort);
    }
}
