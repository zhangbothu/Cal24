package com.example.mycal24;

public class Card {

    private String name; //the number in the card

    private int imageId;

    private String kind;

    public Card(String name, int imageId, String kind) {
        this.name = name;
        this.imageId = imageId;
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getKind() {
        return kind;
    }

    ;

}
