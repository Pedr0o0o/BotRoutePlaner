package com.company;

public class Conteiner {
    //klasa będąca odpowiednikiem kontenerów na produkty
    //przetrzymuje informacje o stanie danego kontenera state domyślnie ustawionego jako nieczynny
    //zawiera tablice wielkości N przechowujacą nazwy produktów przetrzymywanych w danym kontenerze,
    // indeks tablicy w którym zapisany jes produkt odpowiada warstwe na której znajduje się produkt
    //tablica pozwala na potencjalne przechowywanie więcej niż jednego produktu w danym kontenerze
    char state = 'O';
    String[] contener;
    int number;


    public Conteiner(int N, char state) {
        this.state = state;
        contener = new String[N];
    }

    public Conteiner(int N) {
        contener = new String[N];
    }

    public void setState(char state) {
        this.state = state;
    }

    //metoda zwraca czas wyjmowania produktu z kontenera
    public double getRemovalTime(String product){
        //metoda odnajduje głębokość na której znajduje się dany produkt
        int n=0;
        for(int i =0;i<contener.length;i++){
            if(contener[i]!=null && contener[i].equals(product))n=i;
        }

        //metoda zwraca czas jaki zajmie wyciągnięcie produktu
        switch (state){
            case 'H': return 3*n+4;
            case 'B': return 2*n+2;
            case 'S': return n+1;
            default: return Integer.MAX_VALUE;
        }
    }


    //metoda zwraca czas poruszania sie po module w zależności od swojego stanu
    //jeśli zdaży sie iż jest to kontener "O" zwraca Integer.MAX_VALUE co sprawia iż jest to z punktu widzenia optymalizacji nieosiągalna trasa
    public float getMoveTime(){
        switch (state){
            case 'H': return 0.5F;
            case 'B': return 1;
            case 'S': return 2;
            default: return Integer.MAX_VALUE;
        }
    }
}
