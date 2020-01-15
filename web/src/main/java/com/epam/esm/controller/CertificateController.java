package com.epam.esm.controller;

import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {
    private final GiftCertificateService giftCertificateService;

    public CertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping
    public List<GiftCertificate> findAll() {
        return giftCertificateService.findAll();
    }

    @GetMapping(value = "/{name}")
    public List<GiftCertificate> findByPartName(@PathVariable String name)  {
        return giftCertificateService.findByPartName(name);
    }
    @GetMapping(value = "tag_name/{name}")
    public List<GiftCertificate> findByTag(@PathVariable String tagName) {
        return giftCertificateService.findByTag(tagName);
    }

    @GetMapping(value = "description/{description}")
    public List<GiftCertificate> findByDescription(@PathVariable String description) {
        return giftCertificateService.findByDescription(description);
    }

    @PostMapping
    public List<GiftCertificate> save(@RequestBody GiftCertificate giftCertificate) {
        giftCertificateService.save(giftCertificate);
        return giftCertificateService.findAll();
    }

    @DeleteMapping(value = "/{id}")
    public List<GiftCertificate> delete(@PathVariable int id) {
        giftCertificateService.delete(id);
        return giftCertificateService.findAll();
    }

    @GetMapping(value = "/date_sort/{type}")
    public List<GiftCertificate> sortByDate(@PathVariable String type) {
        return giftCertificateService.sortByDate(type);
    }

    @GetMapping(value = "/name_sort/{type}")
    public List<GiftCertificate> sortByName(@PathVariable String type) {
        return giftCertificateService.sortByName(type);
    }

    @GetMapping(value = "/sort_date_name/{type}")
    public List<GiftCertificate> sortByDateAndName(@PathVariable String type) {
        return giftCertificateService.sortByDateAndName(type);
    }

    @PutMapping
    public List<GiftCertificate> update(@RequestBody GiftCertificate giftCertificate) {
        giftCertificateService.update(giftCertificate);
        return giftCertificateService.findAll();
    }

}
