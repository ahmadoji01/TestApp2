package com.tanyadong.testapp2;

/**
 * Created by michaelknight123 on 1/29/2016.
 */
public class User
{
    String name, username, password;
    int age;

    public User(String name, int age, String username, String password)
    {
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.name = "";
        this.age = -1;
    }
}
