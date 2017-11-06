package com.appexperts.products.data.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rrs27 on 2017-11-06.
 */

public class StoreTest {

    @Test
    public void test() {
        List<Store> stores = new ArrayList<Store>();
        for (int i = 0; i < 10; i++) {
            String name = "Name " + i;
            String address = "Address " + i;

            Map<String,String> val = new HashMap<String, String>();
            val.put(name,address);

            Store store = new Store(val);
            stores.add(store);
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Store>>() {}.getType();
        String json = gson.toJson(stores, type);
        System.out.println(json);
        List<Store> fromJson = gson.fromJson(json, type);

        for (Store store : fromJson) {
            System.out.println(store);
        }
    }
}
