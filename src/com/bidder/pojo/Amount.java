package com.bidder.pojo;

public class Amount implements Comparable<Amount>{
    private double value;
    private String currency;

    public Amount(double value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int compareTo(Amount o) {
        return (int)(this.value - o.value);
        // TODO Currency conversion and return value
    }

    boolean isGreaterThan(Amount other) {
        return this.compareTo(other) > 0;
    }

    boolean isLessThan(Amount other) {
        return this.compareTo(other) < 0;
    }
}
