package com.ban2.biblioteca.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public abstract class MainController {
    final Scanner scanner = new Scanner(System.in);
    Logger logger = LoggerFactory.getLogger(MainController.class);

    public abstract String listAll();
    public abstract String findById();
    public abstract void save();
    public abstract void update();
    public abstract void delete();
}
