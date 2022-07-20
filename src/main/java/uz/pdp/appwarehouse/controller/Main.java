package uz.pdp.appwarehouse.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Timestamp start = new Timestamp(timestamp.getYear(),timestamp.getMonth(), timestamp.getDate(),0,0,0,0);
        Timestamp end = new Timestamp(timestamp.getYear(),timestamp.getMonth(), timestamp.getDate(),23,59,59,1000);
        System.out.println(start);
        System.out.println(end);
        System.out.println(timestamp);
    }

}
