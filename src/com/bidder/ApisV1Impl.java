package com.bidder;

import com.bidder.pojo.Amount;
import com.bidder.pojo.Auction;
import com.bidder.pojo.Buyer;
import com.bidder.pojo.Seller;
import com.bidder.repo.AuctionRepo;
import com.bidder.repo.BuyerRepo;
import com.bidder.repo.SellerRepo;

public class ApisV1Impl implements ApisV1{

    public String addBuyer(String name) {
        Buyer buyer = new Buyer(name, new Amount(1000000, "INR"));
        BuyerRepo.addBuyer(buyer);
        return "Buyer "+name+" successfully added.";
    }

    public String addBuyer(String name, String balance) {
        Buyer buyer = new Buyer(name, new Amount(Double.parseDouble(balance), "INR"));
        BuyerRepo.addBuyer(buyer);
        return "Buyer "+name+" successfully added.";
    }

    public String addSeller(String name) {
        Seller seller = new Seller(name);
        SellerRepo.addSeller(seller);
        return "Seller "+name+" successfully added.";
    }

    public String createAuction(String auctionName, String minValue, String maxValue, String sellerName) {
        if(AuctionRepo.hasAuction(auctionName)) {
            return "Auction name already exists";
        }

        Seller seller = SellerRepo.getSeller(sellerName);

        if(seller==null) {
            return "Seller is not present in the system";
        }
        Amount minAmount = new Amount(Double.parseDouble(minValue), "INR");
        Amount maxAmount = new Amount(Double.parseDouble(maxValue), "INR");
        Auction auction = new Auction(auctionName, seller, minAmount, maxAmount);

        AuctionRepo.addAuction(auction);

        return "Auction "+ auctionName + " Successfully Added";
    }

    public String createBid(String buyerName, String auctionName, String value) {
        Buyer buyer = BuyerRepo.getBuyer(buyerName);
        Auction auction = AuctionRepo.getAuction(auctionName);

        if(buyer==null) {
            return "Buyer is not present in the system";
        }

        if(auction==null) {
            return "Auction is not present in the system";
        }

        Amount amount = new Amount(Double.parseDouble(value), "INR");
        return auction.addBids(buyer, amount);
    }

    public String updateBid(String buyerName, String auctionName, String value) {
        Buyer buyer = BuyerRepo.getBuyer(buyerName);
        Auction auction = AuctionRepo.getAuction(auctionName);

        if(buyer==null) {
            return "Buyer is not present in the system";
        }

        if(auction==null) {
            return "Auction is not present in the system";
        }


        Amount amount = new Amount(Double.parseDouble(value), "INR");
        return auction.updateBids(buyer, amount);
    }

    public String withdrawBid(String buyerName, String auctionName) {
        Buyer buyer = BuyerRepo.getBuyer(buyerName);
        Auction auction = AuctionRepo.getAuction(auctionName);

        if(buyer==null) {
            return "Buyer is not present in the system";
        }

        if(auction==null) {
            return "Auction is not present in the system";
        }
        return auction.withDrawBids(buyer);
    }

    public String closeAuction(String auctionName) {
        Auction auction = AuctionRepo.getAuction(auctionName);

        if(auction==null) {
            return "Auction is not present in the system";
        }

        return auction.closeAuction();
    }
}
