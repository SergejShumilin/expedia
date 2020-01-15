package com.epam.esm.service;

import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.dao.exception.TagNotFoundException;
import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.dao.exception.CertificateNotFoundException;
import com.epam.esm.dao.impl.TagDaoImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;


@RunWith(MockitoJUnitRunner.class)
public class GiftCertificateServiceTest {
    @Mock
    private GiftCertificateDaoImpl giftCertificateDao;
    @Autowired
    @InjectMocks
    private GiftCertificateService service;

    @Test
    public void testDeleteShouldGiftCertificateDaoCallMethodDelete() {
        service.delete(1);
        Mockito.verify(giftCertificateDao, Mockito.times(1)).delete(1);
    }

    @Test
    public void testFindAllShouldGiftCertificateDaoCallMethodFindAll() {
        service.findAll();
        Mockito.verify(giftCertificateDao, Mockito.times(1)).findAll();
    }

    @Test
    public void testFindByIdShouldGiftCertificateDaoCallMethodFindById() throws TagNotFoundException {
        int id = 1;
        Mockito.when(giftCertificateDao.isExist(id)).thenReturn(true);
        service.findById(id);
        Mockito.verify(giftCertificateDao, Mockito.times(1)).findById(id);
    }

    @Test(expected = TagNotFoundException.class)
    public void testFindByIdShouldGiftCertificateDaoReturnExceptionWhenCallMethodFindById() throws TagNotFoundException {
        int id = 1;
        Mockito.when(giftCertificateDao.isExist(id)).thenReturn(false);
        service.findById(id);
        Mockito.verify(giftCertificateDao, Mockito.times(1)).findById(id);
    }


    @Test(expected = CertificateNotFoundException.class)
    public void testFindByNameShouldGiftCertificateDaoThrowExceptionWhenCallMethodFindByName() throws CertificateNotFoundException {
        service.findByName("asd");
        Mockito.verify(giftCertificateDao, Mockito.times(1)).findByName("asd");
    }

    @Test
    public void testFindByNameShouldGiftCertificateDaoCallMethodFindByName() throws CertificateNotFoundException {
        Mockito.when(giftCertificateDao.isExistByName("asd")).thenReturn(true);
        service.findByName("asd");
        Mockito.verify(giftCertificateDao, Mockito.times(1)).findByName("asd");
    }

    @Test(expected = CertificateNotFoundException.class)
    public void testFindByDescriptionShouldGiftCertificateDaoCallFindByDescription() throws CertificateNotFoundException {
        service.findByDescription("description");
        Mockito.verify(giftCertificateDao, Mockito.times(1)).findByDescription("description");
    }

    @Test
    public void testFindByDescriptionShouldGiftCertificateDaoCallMethodFindByDescription() throws CertificateNotFoundException {
        Mockito.when(giftCertificateDao.isExistByDescription("asd")).thenReturn(true);
        service.findByDescription("asd");
        Mockito.verify(giftCertificateDao, Mockito.times(1)).findByDescription("asd");
    }

    @Test(expected = CertificateNotFoundException.class)
    public void testFindByDescriptionShouldGiftCertificateDaoCallFindByDate() throws CertificateNotFoundException {
        service.findByDescription("2020-01-15");
        Mockito.verify(giftCertificateDao, Mockito.times(1)).findByDescription("2020-01-15");
    }

    @Test(expected = CertificateNotFoundException.class)
    public void testFindByTagShouldGiftCertificateDaoCallFindByTag() throws CertificateNotFoundException {
        service.findByDescription("2020-01-15");
        Mockito.verify(giftCertificateDao, Mockito.times(1)).findByDescription("2020-01-15");
    }

    @Test
    public void testSortByDateShouldGiftCertificateDaoCallMethodSortByDate() {
        service.sortByDate("asc");
        Mockito.verify(giftCertificateDao, Mockito.times(1)).sortByDate("asc");
    }

    @Test
    public void testSortByNameShouldGiftCertificateDaoCallMethodSortByName() {
        service.sortByName("name");
        Mockito.verify(giftCertificateDao, Mockito.times(1)).sortByName("name");
    }

    @Test
    public void testSortByDateAndNameShouldGiftCertificateDaoCallMethodSortByDateAndName() {
        service.sortByDateAndName("asc");
        Mockito.verify(giftCertificateDao, Mockito.times(1)).sortByDateAndName("asc");
    }

    @Test
    public void testSaveShouldGiftCertificateDaoCallMethodSave() {
        GiftCertificate giftCertificate = new GiftCertificate();
        service.save(giftCertificate);
        Mockito.verify(giftCertificateDao, Mockito.times(1)).save(giftCertificate);
    }
}