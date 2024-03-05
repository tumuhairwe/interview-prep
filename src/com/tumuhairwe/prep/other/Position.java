package com.tumuhairwe.prep.other;

public class Position {
    private int source;
    private int destination;
    private Integer redirectTo;
    public Position(Integer source, Integer destination, Integer redirectTo){
        this.source = source;
        this.destination = destination;
        this.redirectTo = redirectTo;
    }

    public int getDestination() {
        return destination;
    }

    public Integer getRedirectTo() {
        return redirectTo;
    }
}
