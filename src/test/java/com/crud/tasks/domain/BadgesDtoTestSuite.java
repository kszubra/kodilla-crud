package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BadgesDtoTestSuite {

    @Test
    public void testBadgesDtoGetters() {
        //Given
        AttachmentsByTypeDto attachments = new AttachmentsByTypeDto(new TrelloDto());
        BadgesDto testBadges = new BadgesDto(3, attachments);

        //Then
        Assert.assertEquals(3, testBadges.getVotes());
        Assert.assertEquals(attachments, testBadges.getAttachmentsByType());
    }
}
