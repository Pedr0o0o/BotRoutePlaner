package com.company;

public class Grid {
    //klasa będąca przestrzenia roboczą dla robiota
    //składa sie z wymiarów x,y oraz głębokości kontenerów N oraz samych kontenerów

    int xSize;
    int ySize;
    int N;
    Conteiner[][] conteiner;



    //konstruuje przetrzeń o wymiarach (x,y)
    public Grid(int x, int y, int N) {
        this.xSize = x;
        this.ySize = y;
        this.N = N;

        //tworzę kontenery na produkty o głębokości N
        //każdemu kotenerowi przyporządkowuje unikalny numer który będzie jego reprezentacją w grafie
        int numer=0;
        conteiner  = new Conteiner[x][y];
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                conteiner[i][j] = new Conteiner(N);
                conteiner[i][j].number = numer;
                numer++;
            }
        }
    }

    public Conteiner[][] getConteiner() { return conteiner; }

    public int getxSize() { return xSize; }

    public int getySize() { return ySize; }
}
