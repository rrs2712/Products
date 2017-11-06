package com.appexperts.products.data.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Class to represent a Java bean of Store objects
 *
 * @author Raul RS
 * @version 1.0
 */
public class Store {
    private Map<String,String> store;

    /**
     * Getter method
     * @return Store - Map<String, String>
     */
    public Map<String, String> getStore() {
        return store;
    }

    /**
     * Setter method
     * @param store - Map<String, String>
     */
    public void setStore(Map<String, String> store) {
        this.store = store;
    }

    /**
     * Class constructor
     * @param store - Map<String, String>
     */
    public Store(Map<String, String> store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Store{" +
                "store=" + store +
                '}';
    }

    /**
     * Method to retrieve a fake random-generated Store object
     * @return store - Store Object
     */
    public static Store getFakeStore(){
        Random random = new Random();

        String name = "Store " + random.nextInt(1000);
        String address = "" + random.nextInt(50) + " Street, SW" + random.nextInt(50) + ", London.";

        Map<String,String> val = new HashMap<String, String>();
        val.put(name,address);

        return new Store(val);
    }


}
