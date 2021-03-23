package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderService {

    ProductService service = new ProductService();
    ClientService clientService = new ClientService();
    //IdGenerator generator = new IdGenerator();
    Scanner scanner = new Scanner(System.in);

    static File orderFile = new File("Orders.txt");

    //List<order> orderList = new ArrayList<>();



    List<product> addOrder(){

        List<product> productList = new ArrayList<>();

        /*clientService.showClients().forEach(client -> System.out.println(client));

        System.out.println("podaj numer id klienta");
        int idClient = scanner.nextInt();*/

        service.showProducts().forEach(product -> System.out.println(product));


        char continued = 't';
        while (continued == 't'){
            System.out.println("podaj numer id produktu");
            int idProduct = scanner.nextInt();
            productList.add(service.findByID(idProduct));
            scanner = new Scanner(System.in);
            System.out.println("czy chcesz dodać kolejny produkt? t/n");
            continued = scanner.next().charAt(0);
        }

    return productList;}

    void addOrderToFile(order order, File aa){

        try {

            for (product product:order.productList) {

                Files.writeString(aa.toPath(),order.orderId + ";" + order.client.id +";"+ order.client.name+";"+order.client.surname+";"+product.id+ "\n", StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println("błąd");
        }
    }

    List<order> showOrders(File aa){

        List<order> listOfOrders = new ArrayList<>();
        List<product> productHelp = new ArrayList<>();
        //List<product> productHelp2 = new ArrayList<>();
        try {
            List<String> list = Files.readAllLines(aa.toPath());
            int newId=-1; //jest tutaj ustawione -1, ponieważ id nie może być ujemne
            for (String line:list) {
                //productHelp.clear();
                String[]splitedLine = line.split(";");
                int idOrder = Integer.parseInt(splitedLine[0]);
                int idClient = Integer.parseInt(splitedLine[1]);
                client clientHelp = clientService.findByID(idClient);
                //int idProduct = Integer.parseInt(splitedLine[4]);
                productHelp = productListToOrder(idOrder);
                if(idOrder != newId ){
                    order or = new order(idOrder,clientHelp,productHelp);
                    listOfOrders.add(or);
                }
                newId = idOrder;
            }
        } catch (IOException e) {
            System.out.println("plik nie istnieje");;
        }
        return listOfOrders;}

        private List<product> productListToOrder(int orderId){
            List<product> productList = new ArrayList<>();
            //productList.add(service.findByID(orderId));
            List<String> aa = null;
            //int orderID = orderId;
            try {
                aa = Files.readAllLines(orderFile.toPath());
                for (String line:aa) {
                    String[] splitedLine = line.split(";");
                    int orderIdToAdd = Integer.parseInt(splitedLine[0]);
                    int productId = Integer.parseInt(splitedLine[4]);
                    if(orderId == orderIdToAdd){
                        productList.add(service.findByID(productId));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        return productList;}
}
