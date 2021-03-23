package com.company;

public abstract class product {

    int id;
    double price, weight;
    String type, name;


    public product(int id, double price, double weight, String type, String name){
        this.id = id;
        this.price = price;
        this.weight = weight;
        this.type = type;
        this.name = name;

    }

    @Override
    public String toString() {
        return "\nproduct{" +
                "id=" + id +
                ", price=" + price +
                ", weight=" + weight +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                "}";
    }
}
