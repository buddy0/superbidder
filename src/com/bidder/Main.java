package com.bidder;

import com.bidder.repo.AuctionRepo;

public class Main {

    public static void main(String[] args) {

        ApisV1 apisV1 =  new ApisV1Impl();

        // Test case 1
        /*
        ADD_BUYER(“buyer1”)
● ADD_BUYER(“buyer2”)
● ADD_BUYER(“buyer3”)
● ADD_SELLER(“seller1”)
● CREATE_AUCTION(“A1”, “10”, “50”,  “seller1”)
● CREATE_BID(“buyer1”, “A1”, “17”)
● CREATE_BID(“buyer2”, “A1”, “15”)
● UPDATE_BID(“buyer2”, “A1”, “19”)
● CREATE_BID(“buyer3”, “A1”, “19”)
● CLOSE_AUCTION(“A1”) // Should give Buyer1 as winner

         */

        System.out.println(apisV1.addBuyer("buyer1"));
        System.out.println(apisV1.addBuyer("buyer2"));
        System.out.println(apisV1.addBuyer("buyer3"));
        System.out.println(apisV1.addSeller("seller1"));
        System.out.println(apisV1.createAuction("A1", "10", "50", "seller1"));
        System.out.println(apisV1.createBid("buyer1", "A1", "17"));
        System.out.println(apisV1.createBid("buyer2", "A1", "15"));
        System.out.println(apisV1.updateBid("buyer2", "A1", "19"));
        System.out.println(apisV1.createBid("buyer3", "A1", "19"));
        System.out.println(apisV1.closeAuction("A1"));

        //Test 2
        /*
        ADD_SELLER(“seller2”)
● CREATE_AUCTION(“A2”, “5”, “20”, “seller2”)
● CREATE_BID(“buyer3”, ”A2”, 25) //This should fail as highest bid limit is 20 for A2
● CREATE_BID(“buyer2, ”A2”, 5)
● WITHDRAW_BID(“buyer2”, “A2”)
● CLOSE_AUCTION(“A2”) // No winner

         */

        System.out.println(apisV1.addSeller("seller2"));
        System.out.println(apisV1.createAuction("A2", "5", "20", "seller2"));
        System.out.println(apisV1.createBid("buyer3", "A2", "25"));
        System.out.println(apisV1.createBid("buyer2", "A2", "5"));
        System.out.println(apisV1.withdrawBid("buyer2", "A2"));
        System.out.println(apisV1.closeAuction("A2"));


        // Test 3
        /**
         * ADD_BUYER(“buyer1”, 20)
         * ● ADD_BUYER(“buyer2”, 20)
         * ● ADD_BUYER(“buyer3”, 20)
         * ● ADD_SELLER(“seller1”)
         * ● ADD_SELLER(“seller2”)
         * ● CREATE_AUCTION(“A1”, “10”, “50”, “seller1”)
         * ● CREATE_AUCTION(“A2”, “5”, “20”, “seller2”)
         * ● CREATE_BID(“buyer1”, “A1”, “17”)
         * ● CREATE_BID(“buyer2”, “A1”, “15”)
         * ● UPDATE_BID(“buyer2”, “A1”, “19”)
         * ● CREATE_BID(“buyer3”, “A1”, “19”)
         * ● CLOSE_AUCTION(“A1”) // Should give Buyer1 as winner
         * ● CREATE_BID(“buyer1, ”A1”, 5) //This should fail as budget exceeds for buyer1
         * ● CREATE_BID(“buyer3”, ”A2”, 25) //This should fail as highest bid limit is 20 for A2
         * ● CREATE_BID(“buyer2, ”A2”, 5)
         * ● WITHDRAW_BID(“buyer2”, “A2”)
         * ● CLOSE_AUCTION(“A2”) // No winner
         */


    }
}
