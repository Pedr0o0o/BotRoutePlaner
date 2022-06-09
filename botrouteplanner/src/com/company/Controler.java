package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Controler {
    //klasa sterująca procesem

//klasa zawiera nazwy plików z króych mają zostać pobrane dane
    String gridName = "grid-1.txt";
    String jobName = "job-1.txt";

    public Controler(String gridName, String jobName) {
        this.gridName = gridName;
        this.jobName = jobName;
    }


    //rozpoczyna proces
    void startProces() throws FileNotFoundException {

        ImputData imp = new ImputData();

        //tworzę przestrzen roboczą
        Grid hala = null;
        hala = imp.scanGrid(hala, gridName);

        //tworzę bota do pracy
        BotMovment bot = null;
        bot = imp.scanJob(bot, jobName);
        bot.setGrid(hala);

        //szukam najszybszej trasy i zwracam ją jako liste krawędzi
        List<Edge> path = bot.findPath();


        //przystępuje do analizy wyników
        //czasu przejazdu
        //liczby potrzebnych ruchów robota

        float totalTime =0;
        int numberOfMoves =path.size();
        for(int i=0;i<path.size();i++){
            totalTime+=path.get(i).getWeight(); //czas przejazdu składa się z czasów na kązdej z krawędzi
        }
        totalTime+= bot.getRemovalTime();//dodatkowo oprócz czasu przejzadu dodaje czas wychągniecia produktu z kontenera


        //liste krawdzi zamieniam na koordynaty natepnych kontenerow na trasie robota
        List<Coordinate> coordinates = new ArrayList<>();
        for(int i = 0;i< path.size();i++){
            coordinates.add( new Coordinate(path.get(i).from()/hala.ySize ,path.get(i).from()%hala.ySize ));
        }
        coordinates.add(new Coordinate( path.get(path.size()-1).to()/hala.ySize,path.get(path.size()-1).to()%hala.ySize));



        results(coordinates, totalTime, numberOfMoves);
    }

    //drukuje wyniki na standardowe wyjście
    public void results(List<Coordinate> coordinates, float time, int numberOfMoves){

        System.out.println(numberOfMoves);//liczba kroków
        System.out.format( "%.1f \n",time);//czas
        for(int i=0;i<coordinates.size();i++){//koordynaty
            System.out.println(coordinates.get(i).x + " " + coordinates.get(i).y);
        }

    }

}
