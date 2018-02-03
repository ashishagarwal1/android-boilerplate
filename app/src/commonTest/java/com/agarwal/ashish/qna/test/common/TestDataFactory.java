package com.agarwal.ashish.qna.test.common;

import com.agarwal.ashish.qna.room.entities.RibotProfile;
import com.agarwal.ashish.qna.room.model.RibotName;
import com.agarwal.ashish.qna.room.model.Ribot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
public class TestDataFactory {

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    public static RibotProfile makeRibot(String uniqueSuffix) {
        RibotProfile ribotProfile =  new RibotProfile();
        RibotName name = new RibotName();
        name.setFirst("Name-" + uniqueSuffix);
        name.setLast("Surname-" + uniqueSuffix);
        ribotProfile.setName(name);
        ribotProfile.setEmail("email" + uniqueSuffix + "@ribotProfile.co.uk");
        ribotProfile.setDob(new Date());
        ribotProfile.setHexColor("#0066FF");
        ribotProfile.setAvatar("http://api.ribotProfile.io/images/" + uniqueSuffix);
        ribotProfile.setBio(randomUuid());
        return ribotProfile;
    }

    public static Ribot makeRibotProfile(String uniqueSuffix) {
        Ribot ribot = new Ribot();
        ribot.setRibotProfile(makeRibot(uniqueSuffix));
        return ribot;
    }


    public static List<RibotProfile> makeListRibots(int number) {
        List<RibotProfile> ribotProfiles = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            ribotProfiles.add(makeRibot(String.valueOf(i)));
        }
        return ribotProfiles;
    }
}