package com.appexperts.products.app;

import com.appexperts.products.data.model.Price;
import com.appexperts.products.data.model.Store;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by rrs27 on 2017-11-06.
 */

public class APIutilsTest {

    @Test
    public void testStores(){
        APIutils api = new APIutils();
        Store storeA = Store.getFakeStore();
        Store storeB = Store.getFakeStore();
        Store storeC = Store.getFakeStore();

        System.out.println("POJO = " + storeA.toString());
        System.out.println("POJO = " + storeB.toString());
        System.out.println("POJO = " + storeC.toString());

        List<Store> stores = new ArrayList<Store>();
        stores.add(storeA);
        stores.add(storeB);
        stores.add(storeC);

        Store[] storesArray = new Store[]{storeA,storeB,storeC};

        String json = api.storesToJson(stores);
        System.out.println("JSON List = " + json);

        json = api.storesToJson(storesArray);
        System.out.println("JSON Array = " + json);

//        List<Store> erots = api.storesFromJson(json);
//        System.out.println("POJO after convesion is the same = " + stores.equals(erots));
    }

    @Test
    public void testPrices(){
        APIutils api = new APIutils();
        Random random = new Random();

        Map<String,Double> map = new HashMap<String,Double>();
        map.put("Regular price",random.nextDouble() * 1000 );
        map.put("Sale price",random.nextDouble() * 1000 );
        Price price = new Price(map);

        System.out.println("POJO = " + price.toString());

        String json = api.pricesToJson(price);
        System.out.println("JSON = " + json);
    }
}
