package com.company;

import java.util.ArrayList;
import java.util.List;

public class BotMovment {

    //współżędne startowe robota
    Coordinate botStart;

    //współżędne stacji odbiorczej
    Coordinate station;

    //nazwa produktu który ma przewieżć bot
    String produkt;

    //współżędne produktu
    Coordinate productCoord;
    int productContainerNumber;
    //przestrzeń po której porusza się robot
    Grid grid;


    //tworzę robota
    public BotMovment(Coordinate botStart, Coordinate station, String produkt) {
        this.botStart = botStart;
        this.station = station;
        this.produkt = produkt;
    }

    //nadaje robotoiwi przestrzeń po której moze się poruszać
    public void setGrid(Grid grid) {
        this.grid = grid;
    }



    //metoda odnajduje ścieżkę
    public List<Edge> findPath(){


        //odszukuje produkt który chę dostarczyć
        //wprowadzam zmienną dystans aby bot transportował produkt najbliżej niego
        int distance =Integer.MAX_VALUE;
        for(int i=0;i< grid.getxSize();i++){
            for(int j=0;j< grid.getySize();j++){

                Conteiner con = grid.conteiner[i][j];
                for(int z=0;z<con.contener.length;z++){

                    if(con.contener[z]!=null && con.contener[z].equals(produkt)){
                        //sptawdzam czy produkt jest bliżej bota niż praodukt znaleziony wcześniej
                        if(Math.abs(botStart.x-i)+Math.abs(botStart.y-j)<distance) {
                            //jeśli znajduje się blizej zmieniam wartość dystansu i koordynaty dostarczanego produktu
                            distance = Math.abs(botStart.x-i)+Math.abs(botStart.y-j);
                            productCoord = new Coordinate(i, j);
                            productContainerNumber = con.number;
                        }
                    }

                }

            }

        }

        //tworzę listy kraędzi po których będzie poruszał sie robot
        //pathStarttoProduct - trasa od punktu startowego do produktu
        //pathProducttoRecive - trasa od produktu do stacji odbiorczej
        List<Edge> pathStarttoProduct = new ArrayList<>();
        List<Edge> pathProducttoRecive = new ArrayList<>();
        List<Edge> totalPath = new ArrayList<>();


        //odszukuje pierwszą część trasy metodą najkrutszej ścieżki w graafie
        DijkstraShortestPath dijkstraPathSP = new DijkstraShortestPath();
        pathStarttoProduct = dijkstraPathSP.setGraph(grid, grid.conteiner[botStart.x][botStart.y].number ,productContainerNumber);

        //odszukuje pierwszą część trasy metodą najkrutszej ścieżki w graafie
        DijkstraShortestPath dijkstraPathPO = new DijkstraShortestPath();
        pathProducttoRecive = dijkstraPathPO.setGraph(grid,productContainerNumber, grid.conteiner[station.x][station.y].number);

        //tworzę ścieżkę całkowitą składającą się z wcześniej odnalezionych ścieżek
        totalPath.addAll(pathStarttoProduct);
        totalPath.addAll(pathProducttoRecive);

        //zwracam całkowitą ścieżkę
        return totalPath;


    }

    public double getRemovalTime(){
        return grid.conteiner[productCoord.x][productCoord.y].getRemovalTime(produkt);
    }



}
