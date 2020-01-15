package com.epam.esm.util;

import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.dao.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagVerification {

    private final TagService tagService;

    public TagVerification(TagService tagService) {
        this.tagService = tagService;
    }

    public void checkAndSaveTagIfNotExist(GiftCertificate giftCertificate){
        Tag tag = giftCertificate.getTag();
        if (!tagService.isExistByName(tag.getName())) {
            tagService.save(tag);
        }
        tag = tagService.findByName(tag.getName());
        giftCertificate.setTag(tag);
    }
}
