package com.bidder;

public interface ApisV1 {

    public String addBuyer(String name);

    public String addSeller(String name);

    public String createAuction(String auctionName, String minValue, String maxValue, String sellerName);

    public String createBid(String buyerName, String auctionName, String value);

    public String updateBid(String buyerName, String auctionName, String value);

    public String withdrawBid(String buyerName, String auctionName);

    public String closeAuction(String auctionName);

}
