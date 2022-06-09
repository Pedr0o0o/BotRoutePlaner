package com.company;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String gridName = "grid-2.txt";
        String jobName = "job-2.txt";
       /* gridName = args[0];
        jobName = args[1];*/
        Controler cotroler = new Controler(gridName, jobName);
        cotroler.startProces();

    }
}
