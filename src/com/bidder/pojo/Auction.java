package com.bidder.pojo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Auction {

    public static enum AuctionStatus {
        OPEN,
        CLOSED
    }

    String auctionName;
    Buyer winner;
    AuctionStatus status;
    private Seller seller;
    private Amount minAuctionAmount;
    private Amount maxAuctionAmount;
    private ConcurrentMap<Buyer, Amount> bids;

    public Auction(String auctionName, Seller seller, Amount minAuctionAmount, Amount maxAuctionAmount) {
        this.auctionName = auctionName;
        this.status = AuctionStatus.OPEN;
        this.seller = seller;
        this.minAuctionAmount = minAuctionAmount;
        this.maxAuctionAmount = maxAuctionAmount;
        this.bids = new ConcurrentHashMap<>();
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public AuctionStatus getStatus() {
        return status;
    }

    public void setStatus(AuctionStatus status) {
        this.status = status;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Amount getMinAuctionAmount() {
        return minAuctionAmount;
    }

    public void setMinAuctionAmount(Amount minAuctionAmount) {
        this.minAuctionAmount = minAuctionAmount;
    }

    public Amount getMaxAuctionAmount() {
        return maxAuctionAmount;
    }

    public void setMaxAuctionAmount(Amount maxAuctionAmount) {
        this.maxAuctionAmount = maxAuctionAmount;
    }

    public ConcurrentMap<Buyer, Amount> getBids() {
        return bids;
    }

    public void setBids(ConcurrentMap<Buyer, Amount> bids) {
        this.bids = bids;
    }

    public String addBids(Buyer buyer, Amount amount){
        if(amount.isGreaterThan(maxAuctionAmount)  || amount.isLessThan(minAuctionAmount)) {
            return "Auction amount invalid";
        }

        if(bids.containsKey(buyer)) {
            bids.put(buyer, amount);
            return "Buyer bid updated";
        } else {
            bids.put(buyer, amount);
            return "Buyer bid added";
        }
    }

    public String updateBids(Buyer buyer, Amount amount){
        if(amount.isGreaterThan(maxAuctionAmount)  || amount.isLessThan(minAuctionAmount)) {
            return "Auction amount invalid";
        }

        if(bids.containsKey(buyer)) {
            bids.put(buyer, amount);
            return "Buyer bid updated";
        } else {
            return "Buyer bid not present for this auction";
        }
    }

    public String withDrawBids(Buyer buyer){
        if(bids.containsKey(buyer)) {
            bids.remove(buyer);
            return "Buyer bid updated";
        } else {
            return "Buyer bid not present for this auction";
        }
    }

    public String closeAuction() {
        if(bids.isEmpty()) {
            return "No Winner Found";
        }
        status = AuctionStatus.CLOSED;
        synchronized (bids) {
            NavigableSet<Double> amounts = new TreeSet<Double>();
            Set<Double> duplicateAmounts = new TreeSet<>();

            for(Map.Entry<Buyer, Amount> v: bids.entrySet()) {
                if(amounts.contains(v.getValue().getValue())) {
                    amounts.remove(v.getValue().getValue());
                    duplicateAmounts.add(v.getValue().getValue());
                } else if(!duplicateAmounts.contains(v.getValue().getValue())) {
                    amounts.add(v.getValue().getValue());
                }
            }

            if(amounts.isEmpty()) {
                return "No Winner Found";
            }

            Double minValue = amounts.pollFirst();
            for(Map.Entry<Buyer, Amount> v: bids.entrySet()) {
                if(v.getValue().getValue() == minValue) {
                    return " Winner is"+v.getKey().getName();
                }
            }
        }
        return "No Winner Found";
    }
}
