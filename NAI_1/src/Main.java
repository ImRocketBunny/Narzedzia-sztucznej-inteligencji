import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        int sukces=0;
        int wszytskie=0;
        int parametrK=0;
        boolean chceszDalej = true;
        int good2=0;
        while (good2==0){
            try {
                System.out.println("Podaj wartosc Parametru K");
                Scanner licz = new Scanner(System.in);
                parametrK = Integer.parseInt(licz.nextLine());
                good2=1;
            }catch (NumberFormatException e){
                System.out.println("Podena dane sa niepoprawne sprobuj ponownie");
            }
        }
        FileReader fr = new FileReader("iris_test.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while ((line = br.readLine()) != null) {
            List<Kwiat> kwiatek=new ArrayList<>();
            String[] linia = line.split("\\t");
            String nazwa = linia[linia.length-1];
            int lineLength = (linia.length - 2);
            new Obliczenia(linia,lineLength,kwiatek);
            Wynik wynik1 = new Wynik(kwiatek,parametrK);
            //System.out.println(wynik1.nazwa);
            //System.out.println(nazwa);
            if(nazwa.equals(wynik1.nazwa)){
                sukces++;
            }
            wszytskie++;


        }

        double suk=sukces;
        double all=wszytskie;
        double wyrazenie = 0;
        //System.out.println(suk);
        //System.out.println(all);
        wyrazenie=suk/all;
        System.out.println("Dookladnosc eksperymentu to "+wyrazenie*100.0+'%');
        System.out.println("Poprawnie sklasyfikowane "+sukces);

        int good4=0;
        String[] decyzja=null;
        while(good4==0){
            System.out.println("Chcesz dalej wprowadzac kwiaty? tak/nie");
            Scanner bool = new Scanner(System.in);
            decyzja = bool.nextLine().split(",");
            if (decyzja[0].equals("nie")) {
                good4=1;
                chceszDalej = false;
                System.exit(0);
            }
            else if(decyzja[0].equals("tak")){
                good4=1;
                chceszDalej=true;
            }
            else{

            }
        }
        while (chceszDalej == true) {
            List<Kwiat> kwiatek=new ArrayList<Kwiat>();

            //System.out.println("Podaj liczby kwiatka oddzielone tabem");
            Scanner sc;
            String[] userData=null;
            int iloscdanych=0;
            int good=1;
            while(good==1) {
                try {
                    System.out.println("Podaj wektory kwiatka w formacie double oddzielone tabem");
                    sc = new Scanner(System.in);
                    userData = sc.nextLine().split("\\t");
                    for (int i = 0; i < userData.length; i++) {
                        Double.parseDouble(userData[i]);
                    }
                    iloscdanych=userData.length-1;
                    good=0;
                } catch (NumberFormatException e) {
                    System.out.println("Podena dane sa niepoprawne sprobuj ponownie");
                }
            }
            int probki =0;
            new Obliczenia(userData, iloscdanych,kwiatek);
            Wynik wynik1 =new Wynik(kwiatek,parametrK);
            System.out.println("Wynik eksperymentu to: ");
            System.out.println(wynik1.nazwa);
            //kwiatek=Obliczenia(userData,iloscDanych);
            //Obliczenia(userData,iloscDanych);*/
            int good3=0;
            String[] decyzja1=null;
            while(good3==0){
                    System.out.println("Chcesz dalej wprowadzac kwiaty? tak/nie");
                    Scanner bool = new Scanner(System.in);
                    decyzja1 = bool.nextLine().split(",");
                if (decyzja1[0].equals("nie")) {
                    good3=1;
                    chceszDalej = false;
                }
                else if(decyzja[0].equals("tak")){
                    good3=1;
                    chceszDalej=true;
                }
                else{

                }
            }


        }

    }
}

