package com.bidder.repo;

import com.bidder.pojo.Auction;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AuctionRepo {

    private static ConcurrentMap<String, Auction> auctions = new ConcurrentHashMap<>();


    public static boolean hasAuction(String name) {
        return auctions.containsKey(name);
    }

    public static Auction getAuction(String name) {
        if(hasAuction(name)) {
            return auctions.get(name);
        } else {
            return null;
        }
    }

    public static void addAuction(Auction auction) {
        auctions.put(auction.getAuctionName(), auction);
    }
}
