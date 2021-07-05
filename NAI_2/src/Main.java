import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        List<Double> listaW = new ArrayList<>();
        double Prog = Math.random()*2-1;
        int generacje = 0;
        //System.out.println(Math.random()*2-1);
        System.out.println("Uczenie perceptorona....");
        int pierwszyRaz = 1;
        double dokladnosc=0.0;
        double wszystkie=0;
        double sukces=0;
        while(dokladnosc!=100.00) {
            generacje++;
            wszystkie=0;
            sukces=0;
            FileReader fr = new FileReader("iris_training.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                boolean zgodnosc = false;
                int d;
                int y;
                String[] values = line.split("\t");
                if (values[values.length - 1].equals("Iris-setosa")) {
                    d = 1;
                } else {
                    d = 0;
                }
                int dlugosclinii = values.length - 2;
                if (pierwszyRaz == 1) {
                    for (int i = 0; i <= dlugosclinii; i++) {
                        listaW.add(Math.random()*2-1);
                    }
                    pierwszyRaz = 0;
                }
                int roznica=0;
                roznica=listaW.size()-1 - dlugosclinii;
                if (dlugosclinii > listaW.size() - 1) {
                    for(int i = 0;i<roznica;i++) {
                        listaW.add( Math.random() * 2 - 1);
                    }
                }
                while (zgodnosc == false) {
                    double s = 0;
                    for (int i = 0; i < values.length - 1; i++) {
                        s += (listaW.get(i) * Double.parseDouble(values[i].replace(",", ".")));
                    }
                    if (s >= Prog) {
                        y = 1;
                    } else {
                        y = 0;
                    }
                    if (d != y) {
                        for (int i = 0; i < values.length - 1; i++) {
                            listaW.set(i, listaW.get(i) + (d - y) * 1 * Double.parseDouble(values[i].replace(",", ".")));
                        }
                        Prog = Prog - (d - y) * 1;
                    } else {
                        zgodnosc = true;
                    }
                }
            }

            System.out.println("Zakonczono nauczanie generacji "+ generacje);
            System.out.println("Rozpoczynam test generacji "+generacje);
            FileReader fr2 = new FileReader("iris_test.txt");
            BufferedReader br2 = new BufferedReader(fr2);
            String line2 = "";
            while ((line2 = br2.readLine()) != null) {
                int d;
                int y;
                String[] values2 = line2.split("\t");
                int dlugoscLinii2 = values2.length;
                if (dlugoscLinii2 - 1 > listaW.size() - 1) {
                    listaW.add(0.0);
                }
                if (values2[values2.length - 1].equals("Iris-setosa")) {
                    d = 1;
                } else {
                    d = 0;
                }
                double s = 0;
                for (int i = 0; i < values2.length - 1; i++) {
                    //System.out.println(values2.length);
                    s += (listaW.get(i) * Double.parseDouble(values2[i].replace(",", ".")));
                }
                if (s >= Prog) {
                    y = 1;
                } else {
                    y = 0;
                }
                if (d == y) {
                    sukces++;
                }
                wszystkie++;
            }
            dokladnosc = sukces / wszystkie*100;
            //System.out.println("Dokladnosc generacji "+generacje+" to "+dokladnosc+'%');
        }
        for (Double wagi: listaW
        ) {
            System.out.println(wagi);
        }
        System.out.println("przydzielono dobrze " + sukces + " na " + wszystkie);
        System.out.println("Dokladnosc eksperymentu: " + dokladnosc+ '%');
        System.out.println("chcesz wprowadzic wlasne dane? tak/nie");
        String decyzja[] = null;
        int good4 = 0;
        while (good4 == 0) {
            Scanner bool = new Scanner(System.in);
            decyzja = bool.nextLine().split(",");
            if (decyzja[0].equals("nie")) {
                System.exit(0);
            } else if (decyzja[0].equals("tak")) {
                good4 = 1;
            } else {
            }
        }
        boolean chceszDalej = true;
        while (chceszDalej == true) {
            Scanner sc;
            String[] userData = null;
            int iloscdanych = 0;
            int good = 1;
            while (good == 1) {
                try {
                    System.out.println("Podaj wektory kwiatka w formacie double oddzielone tabem");
                    sc = new Scanner(System.in);
                    userData = sc.nextLine().split("\\t");
                    for (int i = 0; i < userData.length; i++) {
                        Double.parseDouble(userData[i]);
                    }
                    iloscdanych = userData.length-1;
                    if (iloscdanych > listaW.size() - 1) {
                        listaW.add((double) ((int)Math.random()*2-1));
                    }
                    double s = 0;
                    for (int i = 0; i < iloscdanych; i++) {
                        s += (listaW.get(i) * Double.parseDouble(userData[i].replace(",", ".")));
                    }
                    if (s >= Prog) {

                        System.out.println("Iris-setosa");
                    } else {

                        System.out.println("nie Iris-setosa");
                    }
                    good = 0;
                } catch (NumberFormatException e) {
                    System.out.println("Podena dane sa niepoprawne sprobuj ponownie");
                }
            }
            int probki = 0;
            //System.out.println("Wynik eksperymentu to: ");
            //kwiatek=Obliczenia(userData,iloscDanych);
            //Obliczenia(userData,iloscDanych);*/
            int good3 = 0;
            String[] decyzja1 = null;
            while (good3 == 0) {
                System.out.println("Chcesz dalej wprowadzac kwiaty? tak/nie");
                Scanner bool = new Scanner(System.in);
                decyzja1 = bool.nextLine().split(",");
                if (decyzja1[0].equals("nie")) {
                    good3 = 1;
                    chceszDalej = false;
                } else if (decyzja[0].equals("tak")) {
                    good3 = 1;
                    chceszDalej = true;
                } else {

                }
            }
        }
    }
}
