package com.company;

public class fruit extends product {

    public fruit(int id, double price, double weight, String type, String name) {
        super(id, price, weight, type, name);
    }

    public fruit() {
        super(0, 0, 0, "none", "none");
    }
}
