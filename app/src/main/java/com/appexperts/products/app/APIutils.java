package com.appexperts.products.app;

import com.appexperts.products.data.model.Price;
import com.appexperts.products.data.model.Store;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 *
 * Class to handle common operations, for instance, data validations and values conversion.
 *
 * @author Raul RS
 * @version 1.0
 */
public class APIutils {

    // Log
    private final String developer = "RRS";
    private final String TAG = developer + " APIutils";

    /**
     * Method to convert Store objects to it's Json equivalent
     * @param stores - An array of Store objects
     * @return String - Json equivalent of the array received
     */
    public String storesToJson(Store[] stores){
        Gson gson = new Gson();
        String json = gson.toJson(stores);

        return json;
    }

    /**
     * Method to convert Store objects to it's Json equivalent
     * @param stores - A list of Store objects
     * @return String - Json equivalent of the array received
     */
    public String storesToJson(List<Store> stores){
        Gson gson = new Gson();
        String json = gson.toJson(stores);

        return json;
    }

    /**
     * Method to convert a Json String to it's Java object equivalent.
     * @param json - A string representing a list of Store Objects
     * @return List<Store> - A list of Store objects
     */
    public List<Store> storesFromJson(String json){
        Gson gson = new Gson();
        List<Store> list = gson.fromJson(json, new TypeToken<List<Store>>(){}.getType());
        return list;
    }

    /**
     * Method to convert a list of Price objects to it's Json representation
     * @param prices - a list of Price objects
     * @return json - A string representation of the object received
     */
    public String pricesToJson(List<Price> prices){
        Gson gson = new Gson();
        String json = gson.toJson(prices);

        return json;
    }

    /**
     * Method to convert a Price object to it's Json representation
     * @param prices - Price object
     * @return json - A string representation of the object received
     */
    public String pricesToJson(Price prices){
        Gson gson = new Gson();
        String json = gson.toJson(prices);

        return json;
    }
}
