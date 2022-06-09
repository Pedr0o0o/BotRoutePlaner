package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ImputData {
    //klasa przeznaczona do odczytu plików

    //metoda przeznaczona do odczytu pliku z danymi domeny obliczeniowej (magazynu)
    // metoda pobiera domenę (grid) oraz nazwę pliku z którego mają zostać odczytane dane
    Grid scanGrid(Grid grid, String filename ) throws FileNotFoundException {

        File file= new File(filename);

        Scanner scanner = new Scanner(file);

        int x,y,N;
        x = scanner.nextInt();
        y = scanner.nextInt();
        N = scanner.nextInt();

        grid = new Grid(x,y,N);

        scanner.nextLine();

        for(int i=0;i<y;i++){
            String line = scanner.nextLine();

            for(int j=0;j<x;j++){

                char state = line.charAt(j);
                grid.conteiner[j][i].setState(state);

            }

        }

        while (scanner.hasNextLine()){
            String product = scanner.next();
            int tmpX = scanner.nextInt();
            int tmpY = scanner.nextInt();
            int tmpN = scanner.nextInt();
            grid.conteiner[tmpX][tmpY].contener[tmpN] = product;
        }

        return grid;
    }



    //metoda przeznaczona do odczytu pliku z danymi zadania do wykonania przez robota
    // metoda pobiera robota który ma wykonać pracę oraz nazwę pliku z danymi
    BotMovment scanJob(BotMovment bot, String filename) throws FileNotFoundException {
        File file= new File(filename);
        Scanner scanner = new Scanner(file);

        int xS, yS;
        int xO, yO;
        String productName;

        xS = scanner.nextInt(); yS = scanner.nextInt();
        Coordinate start = new Coordinate(xS, yS);
        xO = scanner.nextInt(); yO = scanner.nextInt();
        Coordinate recive = new Coordinate(xO, yO);
        productName = scanner.next();


        bot = new BotMovment(start, recive, productName);
        return bot;
    }



}
