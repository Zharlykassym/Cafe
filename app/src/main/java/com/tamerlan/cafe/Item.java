package com.tamerlan.cafe;

public class Item {

    private String product;
    private String additives;
    private String type;

    public Item(String product, String additives, String type) {
        this.product = product;
        this.additives = additives;
        this.type = type;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getAdditives() {
        return additives;
    }

    public void setAdditives(String additives) {
        this.additives = additives;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
