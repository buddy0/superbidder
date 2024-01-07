package com.bidder.pojo;

public class Buyer {
    private String name;
    private Amount balance;
    private int totalParticipatedAuctions;
    public boolean isPreferred;


    public Buyer(String name, Amount amount) {
        this.name = name;
        balance = amount;
        totalParticipatedAuctions = 0;
        isPreferred = false;
    }

    public String getName() {
        return name;
    }

    public synchronized void onCloseBid() {
        totalParticipatedAuctions+=1;
    }
}
