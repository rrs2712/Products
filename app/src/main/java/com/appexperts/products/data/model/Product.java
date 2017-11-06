package com.appexperts.products.data.model;

import android.util.Log;

import com.appexperts.products.app.APIutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class to represent a Java bean of Product objects
 *
 * @author Raul RS
 * @version 1.0
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private String prices;
    private String photo;
    private String colors;
    private String stores;

    /**
     * Class constructor
     * @param id
     * @param name
     * @param description
     * @param prices
     * @param photo
     * @param colors
     * @param stores
     */
    public Product(int id, String name, String description, String prices, String photo, String colors, String stores) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.prices = prices;
        this.photo = photo;
        this.colors = colors;
        this.stores = stores;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getStores() {
        return stores;
    }

    public void setStores(String stores) {
        this.stores = stores;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", prices='" + prices + '\'' +
                ", photo='" + photo + '\'' +
                ", colors='" + colors + '\'' +
                ", stores='" + stores + '\'' +
                '}';
    }

    /**
     * Method to retrieve a fake random-generated Product object
     * @return Product
     */
    public static Product getFakeProduct(){
        Random random = new Random();
        APIutils api = new APIutils();
        Store fkStore = Store.getFakeStore();

        int min = 3;
        int max = 10;
        int noStores = random.nextInt(max-min) + min;
        List<Store> stores = new ArrayList<Store>();

        for (int i = 1; i <= noStores; i++) {
            Store store = Store.getFakeStore();
            stores.add(store);
            Log.d("RRS Product",store.toString());
        }

        Map<String,Double> map = new HashMap<String,Double>();
        map.put("Regular price",random.nextDouble() * 1000 );
        map.put("Sale price",random.nextDouble() * 1000 );
        Price prices = new Price(map);

        String name = "Product name " + random.nextInt(1000);
        String desc = "Description " + random.nextInt(1000);
        String price = api.pricesToJson(prices);
        String photo = "";
        String colors = "";
        String store = api.storesToJson(stores);

        return new Product(0,name,desc,price,photo,colors,store);
    }
}
