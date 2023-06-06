package com.ban2.biblioteca.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrinterUtils {
    private PrinterUtils(){
        throw new IllegalStateException("Classe Utils");
    }

    static Logger logger = LoggerFactory.getLogger(PrinterUtils.class);


    public static void printHeader(String[] headers) {
        StringBuilder header = new StringBuilder();
        for (String h : headers) {
            header.append(String.format("| %-45s ", h));
            if(headers[headers.length - 1].equals(h))
                header.append("|");
        }
        int length = header.length();
        header.insert(0, printLine(length));
        header.append("\n").append(printLine(length));

        String retorno = header.toString();
        System.out.print(retorno);
    }

    public static String printLine(int size){
        return "-".repeat(size) + "\n";
    }
}
