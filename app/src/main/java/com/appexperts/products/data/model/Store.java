package com.appexperts.products.data.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by rrs27 on 2017-11-06.
 */

public class Store {
    private Map<String,String> store;

    public Map<String, String> getStore() {
        return store;
    }

    public void setStore(Map<String, String> store) {
        this.store = store;
    }

    public Store(Map<String, String> store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Store{" +
                "store=" + store +
                '}';
    }

    public static Store getFakeStore(){
        Random random = new Random();

        String name = "Store " + random.nextInt(1000);
        String address = "" + random.nextInt(50) + " Street, SW" + random.nextInt(50) + ", London.";

        Map<String,String> val = new HashMap<String, String>();
        val.put(name,address);

        return new Store(val);
    }


}
