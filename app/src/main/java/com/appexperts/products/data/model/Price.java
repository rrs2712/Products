package com.appexperts.products.data.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Class to represent a Java bean of Price objects
 *
 * @author Raul RS
 * @version 1.0
 */
public class Price {

    private Map<String,Double> price;

    /**
     * Getter method
     * @return Price - Map<String, Double>
     */
    public Map<String, Double> getPrice() {
        return price;
    }

    /**
     * Setter method
     * @param price - Price object
     */
    public void setPrice(Map<String, Double> price) {
        this.price = price;
    }

    /**
     * Class constructor
     * @param price - Map<String, Double>
     */
    public Price(Map<String, Double> price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                '}';
    }

    /**
     * Method to retrieve a fake random-generated Price object
     * @return price - Price object
     */
    public static Price getFakePrice(){
        Random random = new Random();

        String name = "Other " + random.nextInt(50);
        Double money = random.nextDouble() * 1000;

        Map<String,Double> val = new HashMap<String, Double>();
        val.put(name,money);

        return new Price(val);
    }
}
