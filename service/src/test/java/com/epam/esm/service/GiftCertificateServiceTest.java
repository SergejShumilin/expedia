package com.epam.esm.service;

//import com.epam.esm.dao.entity.GiftCertificate;
//import com.epam.esm.dao.entity.Tag;
//import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
//import com.epam.esm.dao.exception.CertificateNotFoundException;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(MockitoJUnitRunner.class)
//public class GiftCertificateServiceTest {
//    @Mock
//    private GiftCertificateDaoImpl giftCertificateDao;
//    private List<GiftCertificate> list;
//
//    @Autowired
//    @InjectMocks
//    private GiftCertificateService service;
//
//    @Before
//    public void setup() {
//        list = new ArrayList<>();
//        GiftCertificate giftCertificate1 = new GiftCertificate();
//        giftCertificate1.setName("a");
//        GiftCertificate giftCertificate2 = new GiftCertificate();
//        giftCertificate2.setName("c");
//        GiftCertificate giftCertificate3 = new GiftCertificate();
//        giftCertificate3.setName("b");
//        list.add(giftCertificate1);
//        list.add(giftCertificate2);
//        list.add(giftCertificate3);
//    }
//
//    @Test
//    public void testDeleteShouldGiftCertificateDaoCallMethodDelete() {
//        service.delete(1);
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).delete(1);
//    }
//
//    @Test
//    public void testFindAllShouldGiftCertificateDaoCallMethodFindAll() {
//        service.findAll();
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).findAll();
//    }
//
//    @Test
//    public void testFindByIdShouldGiftCertificateDaoCallMethodFindById() throws CertificateNotFoundException {
//        int id = 1;
//        Mockito.when(giftCertificateDao.isExist(id)).thenReturn(true);
//        service.findById(id);
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).findById(id);
//    }
//
//    @Test(expected = CertificateNotFoundException.class)
//    public void testFindByIdShouldGiftCertificateDaoReturnExceptionWhenCallMethodFindById() throws CertificateNotFoundException {
//        int id = 1;
//        Mockito.when(giftCertificateDao.isExist(id)).thenReturn(false);
//        service.findById(id);
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).findById(id);
//    }
//
//    @Test
//    public void testFindByNameShouldGiftCertificateDaoCallMethodFindByName() {
//        service.findByName("asd");
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).findByName("asd");
//    }
//
//    @Test(expected = CertificateNotFoundException.class)
//    public void testUpdateGiftCertificateDaoReturnExceptionWhenCallMethodUpdate() throws CertificateNotFoundException {
//        GiftCertificate giftCertificate = new GiftCertificate();
//        service.update(giftCertificate);
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).update(giftCertificate);
//    }
//
//    @Test
//    public void testSortByNameShouldGiftCertificateDaoReturnSortedByAscListCertificates() {
//        String type = "asc";
//        Mockito.when(giftCertificateDao.findAll()).thenReturn(list);
//        List<GiftCertificate> sortList = service.sortByName(type);
//        Assert.assertEquals("b", sortList.get(1).getName());
//    }
//
//    @Test
//    public void changeCertificate() {
//        GiftCertificate giftCertificate1 = new GiftCertificate();
//        giftCertificate1.setName("a");
//        giftCertificate1.setDescription("a");
//        giftCertificate1.setTag(new Tag());
//        GiftCertificate giftCertificate2 = new GiftCertificate();
//        giftCertificate2.setName("c");
//        giftCertificate2.setDescription("a");
//        giftCertificate2.setTag(new Tag());
//
//        service.changeCertificate(giftCertificate1, giftCertificate2);
//        giftCertificate2.setLastUpdateDate(giftCertificate1.getLastUpdateDate());
//        Assert.assertEquals(giftCertificate1, giftCertificate2);
//    }
//}