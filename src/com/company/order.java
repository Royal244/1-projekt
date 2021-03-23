package com.company;

import java.util.ArrayList;
import java.util.List;

public class order {

    int orderId;
    client client;
    List<product> productList = new ArrayList<>();

    public order(){
        this.orderId = 0;
        this.client = null;
        this.productList = null;
    }

    public order(int orderId, client newClient, List<product> aa){
        this.orderId = orderId;
        this.client = newClient;
        this.productList = aa;
    }

    @Override
    public String toString(){
        return "orderId:" + orderId +
        " {clientId: " + client.id + ", Name: " + client.name + ", Surname: " + client.surname + "}"
        + "\nLista produktów: " + productList.toString()+'\'' + "}\n";
    }

}
