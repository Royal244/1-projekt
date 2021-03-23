package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    static File clientFile = new File("Clients.txt");

    void addClinet(client client) {

        try {
            Files.writeString(clientFile.toPath(), client.id + ";" + client.name + ";" + client.surname + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("nie istnieje plik");
        }

    }

    List<client> showClients(){

        List<client> listOfClients = new ArrayList<>();
        try {
            List<String> list = Files.readAllLines(clientFile.toPath());
            for (String line:list) {
                String[]splitedLine = line.split(";");
                int id = Integer.parseInt(splitedLine[0]);
                String name = splitedLine[1];
                String surname = splitedLine[2];
                listOfClients.add(new client(id,name,surname));
            }
        } catch (IOException e) {
            System.out.println("plik nie istnieje");;
        }
    return listOfClients;}

    void deleteClient(int id){

        List<client> listToDelete = showClients();
        List<String> listString = new ArrayList<>();

        listToDelete.removeIf(client -> client.id == id);
        for (client client:listToDelete) {
            String cl = client.id + ";" + client.name + ";" + client.surname;
            listString.add(cl);
        }

        try {
            Files.write(clientFile.toPath(),listString);
        } catch (IOException e) {
            System.out.println("błąd");
        }

    }

    List<client> searchClient(String looking){

        List<client> listOfClients = new ArrayList<>();
        try {
            List<String> list = Files.readAllLines(clientFile.toPath());
            for (String line:list) {
                String[]splitedLine = line.split(";");
                int id = Integer.parseInt(splitedLine[0]);
                String name = splitedLine[1];
                String surname = splitedLine[2];
                if(surname.contains(looking)) {
                    listOfClients.add(new client(id, name, surname));
                }
            }
        } catch (IOException e) {
            System.out.println("plik nie istnieje");;
        }
        return listOfClients;

    }

    client findByID (int id){

        client client = null;
        //product.id = id;
        List<client> clientList = showClients();
        for (client client1:clientList) {
            if (client1.id == id){
                client = client1;
            }
        }

        return client;}
}

