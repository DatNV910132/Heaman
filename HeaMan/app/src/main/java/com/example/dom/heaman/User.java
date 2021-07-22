package com.example.dom.heaman;


public class User {
    int Id;
    String Name;
    String Gen;
    int Chieucao;
    int Cannang;
    int Age;
    String Phone;
    int Calorie;

    public User() {

    }

    public User(int id, String name, String gen, int chieucao, int cannang, int age, String phone, int calorie) {
        Id = id;
        Name = name;
        Gen = gen;
        Chieucao = chieucao;
        Cannang = cannang;
        Age = age;
        Phone = phone;
        Calorie = calorie;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Gen='" + Gen + '\'' +
                ", Chieucao=" + Chieucao +
                ", Cannang=" + Cannang +
                ", Age=" + Age +
                ", Phone='" + Phone + '\'' +
                ", Calorie=" + Calorie +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGen() {
        return Gen;
    }

    public void setGen(String gen) {
        Gen = gen;
    }

    public int getChieucao() {
        return Chieucao;
    }

    public void setChieucao(int chieucao) {
        Chieucao = chieucao;
    }

    public int getCannang() {
        return Cannang;
    }

    public void setCannang(int cannang) {
        Cannang = cannang;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getCalorie() {
        return Calorie;
    }

    public void setCalorie(int calorie) {
        Calorie = calorie;
    }
}

