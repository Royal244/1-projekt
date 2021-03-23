package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    static File productFile = new File("Products.txt");

    void addProduct(product product)  {

        try {
            Files.writeString(productFile.toPath(),product.id + ";" + product.price + ";" + product.weight + ";" + product.type + ";" + product.name + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("nie istnieje plik ");
        }

    }

    List<product> showProducts(){

        List<product> listOfProducts = new ArrayList<>();
        try {
            List<String> aa = Files.readAllLines(productFile.toPath());
            for (String line:aa) {
                String[]splitedLine = line.split(";");
                String type = splitedLine[3];
                int id = Integer.parseInt(splitedLine[0]);
                double price = Double.parseDouble(splitedLine[1]);
                double weight = Double.parseDouble(splitedLine[2]);
                switch (type){

                    case "warzywo":
                        listOfProducts.add(new vegetable(id,price,weight,splitedLine[3],splitedLine[4]));
                        break;

                    case "owoc":
                        listOfProducts.add(new fruit(id,price,weight,splitedLine[3],splitedLine[4]));
                        break;

                    case "nasiona":
                        listOfProducts.add(new seed(id,price,weight,splitedLine[3],splitedLine[4]));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("nie istnie plik");
        }
        return listOfProducts;
    }


    void deleteProduct(int id) {

        List<product> listToDelete = showProducts();
        List<String> listString = new ArrayList<>();

        listToDelete.removeIf(product -> product.id == id);
        for (product product:listToDelete) {
            String prod = product.id + ";" + product.price + ";" + product.weight + ";" + product.type + ";" + product.name;
            listString.add(prod);
        }

        try {
            Files.write(productFile.toPath(),listString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<product> searchProduct(String name){

        List<product> listOfProducts = new ArrayList<>();
        try {
            List<String> aa = Files.readAllLines(productFile.toPath());
            for (String line:aa) {
                String[]splitedLine = line.split(";");
                String type = splitedLine[3];
                String searched = splitedLine[4];
                int id = Integer.parseInt(splitedLine[0]);
                double price = Double.parseDouble(splitedLine[1]);
                double weight = Double.parseDouble(splitedLine[2]);
                if(searched.contains(name)) {
                    switch (type) {

                        case "warzywo":
                            listOfProducts.add(new vegetable(id, price, weight, splitedLine[3], splitedLine[4]));
                            break;

                        case "owoc":
                            listOfProducts.add(new fruit(id, price, weight, splitedLine[3], splitedLine[4]));
                            break;

                        case "nasiona":
                            listOfProducts.add(new seed(id, price, weight, splitedLine[3], splitedLine[4]));
                            break;
                    }
                }//else {return List.of();}
            }
        } catch (IOException e) {
            System.out.println("nie istnie plik");
        }
        return listOfProducts;
    }
    
    product findByID (int id){
        
        product product = null;
        //product.id = id;
        List<product> productList = showProducts();
        for (product prod:productList) {
            if (prod.id == id){
                product = prod;
            }
        }

        return product;}
}
