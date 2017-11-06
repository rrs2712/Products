package com.appexperts.products.data.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by rrs27 on 2017-11-06.
 */

public class Price {

    public Map<String, Double> getPrice() {
        return price;
    }

    public void setPrice(Map<String, Double> price) {
        this.price = price;
    }

    public Price(Map<String, Double> price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                '}';
    }

    private Map<String,Double> price;

    public static Price getFakePrice(){
        Random random = new Random();

        String name = "Other " + random.nextInt(50);
        Double money = random.nextDouble() * 1000;

        Map<String,Double> val = new HashMap<String, Double>();
        val.put(name,money);

        return new Price(val);
    }
}
