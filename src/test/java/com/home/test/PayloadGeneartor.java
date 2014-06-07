package com.home.test;

import com.home.dto.Item;
import com.home.service.JacksonUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author ashar61
 */
public class PayloadGeneartor {
    @Test
    public void generateItem() throws IOException {

        Item item = new Item();
        item.setItemId(UUID.randomUUID().toString());
        item.setEndDate(new Date());
        item.setStartDate(new Date());
        item.setItemDescription("This is sample description");
        item.setItemName("iPod");

        Map<String, String> map = new HashMap<String, String>();
        map.put("age_restriction", "none");
        map.put("category", "electronics");
        item.setItemAttributes(map);


        System.out.println(JacksonUtil.toJson(item));

    }
}
