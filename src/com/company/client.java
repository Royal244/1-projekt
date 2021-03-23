package com.company;

public class client {

    int id;
    String name, surname;

   public client(int id, String s, String s1){
       this.id = id;
       this.name = s;
       this.surname = s1;
   }

    public client(){
        this.id = 0;
        this.name = "";
        this.surname = "";
    }

    @Override
    public String toString() {
        return "client{" +
                "id=" + id +
                ", name='" + name +
                ", surname=" + surname +'\'' +
                '}';
    }
}

