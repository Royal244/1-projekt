package com.company;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static int menu() {
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *                 MENU                 *");
        System.out.println("     ****************************************");
        System.out.println("     1.  Dodaj produkt");
        System.out.println("     2.  Usun produkt");
        System.out.println("     3.  Przegladaj produkty");
        System.out.println("     4.  Wyszukaj produkt");
        System.out.println("     5.  Dodaj Klienta");
        System.out.println("     6.  Usun klienta");
        System.out.println("     7.  Przegladaj klientów");
        System.out.println("     8.  Wyszukaj Klienta");
        System.out.println("     9.  Dodaj zamówienie");
        System.out.println("     10. Przegladaj zamówienia");
        System.out.println("     0.  Koniec");

        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        return w;

    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int wybor = menu();

        ProductService service = new ProductService();
        ClientService clientService = new ClientService();
        IdGenerator generator = new IdGenerator();
        OrderService orderService = new OrderService();
        File orderFile = new File("Orders.txt");

        while (wybor != 0) {
            switch (wybor) {
                case 1:
                    System.out.println("Podaj typ(warzywo, owoc, nasiona) ");
                    in = new Scanner(System.in);
                    String type = in.nextLine();
                    switch (type) {
                        case "warzywo":
                            in = new Scanner(System.in);
                            System.out.println("Podaj nazwe");
                            product veg = new vegetable();
                            veg.name = in.nextLine();
                            veg.type = "warzywo";
                            System.out.println("Podaj cene");
                            veg.price = in.nextDouble();
                            System.out.println("Podaj wagę");
                            veg.weight = in.nextDouble();
                            veg.id = generator.generateIdFromFile(ProductService.productFile);
                            service.addProduct(veg);
                            break;
                        case "owoc":
                            in = new Scanner(System.in);
                            System.out.println("Podaj nazwe");
                            product fruit = new fruit();
                            fruit.name = in.nextLine();
                            fruit.type = "owoc";
                            System.out.println("Podaj cene");
                            fruit.price = in.nextDouble();
                            System.out.println("Podaj wage(dkg)");
                            fruit.weight = in.nextDouble();
                            fruit.id = generator.generateIdFromFile(ProductService.productFile);
                            service.addProduct(fruit);
                            break;
                        case "nasiona":
                            in = new Scanner(System.in);
                            System.out.println("Podaj nazwe");
                            product seed = new seed();
                            seed.name = in.nextLine();
                            seed.type = "nasiona";
                            System.out.println("Podaj cene");
                            seed.price = in.nextDouble();
                            System.out.println("Podaj wage(dkg)");
                            seed.weight = in.nextDouble();
                            seed.id = generator.generateIdFromFile(ProductService.productFile);
                            service.addProduct(seed);
                            break;
                    }
                    break;

                case 2:
                    in = new Scanner(System.in);
                    service.showProducts().forEach(product -> System.out.println(product));
                    System.out.println("Podaj nr id produktu który chcesz usunąć");
                    int id = in.nextInt();
                    service.deleteProduct(id);
                    break;

                case 3:

                    service.showProducts().forEach(product -> System.out.println(product));


                    break;

                case 4:
                    in = new Scanner(System.in);
                    System.out.println("Podaj nazwe(czesc nazwy) produktu który chcesz wyszukac");
                    String name = in.nextLine();
                    //service.SearchProduct(name).forEach(product -> System.out.println(product));
                    List<product> searchedList = service.searchProduct(name);
                    if(searchedList.isEmpty()){
                        System.out.println("Nie znaleziono produktów");
                    }else searchedList.forEach(product -> System.out.println(product));
                    break;

                case 5:
                    in = new Scanner(System.in);
                    client client = new client();
                    client.id = generator.generateIdFromFile(ClientService.clientFile);
                    System.out.println("Podaj imie klienta");
                    client.name = in.nextLine();
                    System.out.println("Podaj nazwisko klienta");
                    client.surname = in.nextLine();
                    clientService.addClinet(client);
                    break;

                case 6:
                    in = new Scanner(System.in);
                    clientService.showClients().forEach(product -> System.out.println(product));
                    System.out.println("Podaj nr id klienta którego chcesz usunąć");
                    int id2 = in.nextInt();
                    clientService.deleteClient(id2);
                    break;

                case 7:


                    clientService.showClients().forEach(client1 -> System.out.println(client1));
                    /*List<client> aa = clientService.showClients();
                    client client3 = new client();
                    //strings = new ArrayList<>();
                    List<String> strings = aa.stream().map(client1 -> Objects.toString(client3,null)).collect(Collectors.toList());
                    System.out.println(strings.toString());*/


                    break;

                case 8:

                    in = new Scanner(System.in);
                    System.out.println("Podaj nazwisko(czesc nazwiska) klienta którego chcesz wyszukac");
                    String surn = in.nextLine();
                    //service.SearchProduct(name).forEach(product -> System.out.println(product));
                    List<client> searchedListOfClients = clientService.searchClient(surn);
                    if(searchedListOfClients.isEmpty()){
                        System.out.println("Nie znaleziono klienta");
                    }else searchedListOfClients.forEach(client1 -> System.out.println(client1));

                    break;

                case 9:
                    clientService.showClients().forEach(client1 -> System.out.println(client1));
                    in = new Scanner(System.in);
                    System.out.println("Podaj nr id klienta");
                    int x = in.nextInt();
                    client newClient = clientService.findByID(x);
                    List<product> listOrder = orderService.addOrder();
                    order order = new order();
                    order.orderId = generator.generateIdFromFile(orderFile);
                    order.client = newClient;
                    order.productList = listOrder;
                    orderService.addOrderToFile(order,orderFile);
                    break;

                case 10:

                    orderService.showOrders(orderFile).forEach(order1 -> System.out.println(order1));

                    break;
            }

            System.out.println("\nWciśnij Enter, aby kontynuować...");
            System.in.read();

            wybor = menu();


        }

        System.out.println("     ****************************************");
        System.out.println("\n     Koniec programu\n\n");
    }
}