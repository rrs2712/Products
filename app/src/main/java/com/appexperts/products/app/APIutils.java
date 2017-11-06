package com.appexperts.products.app;

import com.appexperts.products.data.model.Price;
import com.appexperts.products.data.model.Store;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by rrs27 on 2017-11-06.
 */

public class APIutils {

    // Log
    private final String developer = "RRS";
    private final String TAG = developer + " APIutils";

    public String storesToJson(Store[] stores){
        Gson gson = new Gson();
        String json = gson.toJson(stores);

        return json;
    }

    public String storesToJson(List<Store> stores){
        Gson gson = new Gson();
        String json = gson.toJson(stores);

        return json;
    }

    public List<Store> storesFromJson(String json){
        Gson gson = new Gson();
        List<Store> list = gson.fromJson(json, new TypeToken<List<Store>>(){}.getType());
        return list;
    }

    public String pricesToJson(List<Price> prices){
        Gson gson = new Gson();
        String json = gson.toJson(prices);

        return json;
    }

    public String pricesToJson(Price prices){
        Gson gson = new Gson();
        String json = gson.toJson(prices);

        return json;
    }
}
