package com.ban2.biblioteca.controller;

import java.util.Scanner;

public abstract class MainController {
    final Scanner scanner = new Scanner(System.in);
    public abstract void listAll();
    public abstract void findById();
    public abstract void save();
    public abstract void update();
    public abstract void delete();
}
