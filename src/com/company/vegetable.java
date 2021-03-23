package com.company;

public class vegetable extends product {


    public vegetable(int id, double price, double weight, String type, String name) {
        super(id, price, weight, type, name);
    }

    public vegetable() {
        super(0, 0, 0, "none", "none");
    }
}
