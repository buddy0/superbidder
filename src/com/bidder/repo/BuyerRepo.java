package com.bidder.repo;

import com.bidder.pojo.Buyer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BuyerRepo {

    private static ConcurrentMap<String, Buyer> buyers = new ConcurrentHashMap<>();

    public static boolean hasBuyer(String name) {
        return buyers.containsKey(name);
    }

    public static void addBuyer(Buyer buyer) {
        if(hasBuyer(buyer.getName())) {
            throw new RuntimeException("Buyer is already present");
        }
        buyers.put(buyer.getName(), buyer);
    }

    public static Buyer getBuyer(String name) {
        if(hasBuyer(name)) {
            return buyers.get(name);
        } else {
            return null;
        }
    }
}
