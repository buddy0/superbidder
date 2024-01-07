package com.bidder.repo;

import com.bidder.pojo.Buyer;
import com.bidder.pojo.Seller;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SellerRepo {

    private static ConcurrentMap<String, Seller> sellers = new ConcurrentHashMap<>();

    public static boolean hasSeller(String name) {
        return sellers.containsKey(name);
    }

    public static void addSeller(Seller seller) {
        if(hasSeller(seller.getName())) {
            throw new RuntimeException("Buyer is already present");
        }
        sellers.put(seller.getName(), seller);
    }

    public static Seller getSeller(String name) {
        if(hasSeller(name)) {
            return sellers.get(name);
        } else {
            return null;
        }
    }
}
