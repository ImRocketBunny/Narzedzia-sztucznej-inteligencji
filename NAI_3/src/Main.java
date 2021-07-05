import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        double s2 = 0;
        File f = new File("jezyki\\");
        String[] lista = f.list();
        int znaki = 25;
        List<Perceptron> listaP = new ArrayList<>();
        for (int i = 0; i < lista.length; i++) {
            double Prog = 0;
            List<Double> listaW4 = new ArrayList<>();
            for (int j = 0; j < znaki; j++) {
                listaW4.add(0.0);
            }
            listaP.add(new Perceptron(listaW4, Prog, s2, lista[i]));
            System.out.println(listaP.get(i).jezyk);
        }

        System.out.println("Uczenie Perceptorow..");


        double dokladnosc = 0.0;
        while (dokladnosc != 1000.0) {


            for (int i = 0; i < lista.length; i++) {
                File f1 = new File(f.getName() + "\\" + lista[i]);
                String[] lista2 = f1.list();
                for (int j = 0; j < lista2.length; j++) {
                    for (int x = 0; x < listaP.size(); x++) {
                        //double s=0;
                        boolean zgodnosc = false;
                        List<Double> listaW2 = new ArrayList<>();
                        for (int m = 0; m < znaki; m++) {
                            listaW2.add(0.0);
                        }
                        int d;
                        int y;
                        if (lista[i].split(" ")[0].equals(listaP.get(x).jezyk)) {
                            d = 1;
                        } else {
                            d = 0;
                        }
                        FileReader fr = new FileReader(f.getName() + "\\" + lista[i] + "\\" + lista2[j]);

                        BufferedReader br = new BufferedReader(fr);
                        String line = "";
                        String txt = "";

                        while ((line = br.readLine()) != null) {
                            txt += line;
                        }
                        String txtAsci = "";
                        for (int k = 0; k < txt.length(); k++) {
                            for (int l = 0; l < znaki; l++) {
                                if (txt.charAt(k) == l + 97) {
                                    txtAsci += txt.charAt(k);
                                    //listaW2.set(l,(listaW2.get(l)+1));
                                }
                            }
                        }
                        for (int k = 0; k < txtAsci.length(); k++) {
                            for (int l = 0; l < znaki; l++) {
                                if (txtAsci.charAt(k) == l + 97) {
                                    listaW2.set(l, (listaW2.get(l) + 1));
                                }
                            }
                        }

                        while (zgodnosc == false) {
                            double s = 0;
                            for (int o = 0; o < listaW2.size(); o++) {
                                s += (listaP.get(x).listaW.get(o) * (listaW2.get(o) / txtAsci.length()));
                            }
                            if (s >= listaP.get(x).prog) {
                                y = 1;
                            } else {
                                y = 0;
                            }
                            if (d != y) {
                                for (int p = 0; p < znaki; p++) {
                                    listaP.get(x).listaW.set(p, listaP.get(x).listaW.get(p) + (d - y) * 1 * (listaW2.get(p) / txtAsci.length()));
                                }
                                listaP.get(x).prog = listaP.get(x).prog - (d - y) * 1;
                            } else {
                                zgodnosc = true;
                            }

                        }
                    }


                }


            }

            dokladnosc++;


        }

        System.out.println("Zakonczono nauczanie " + dokladnosc + " generacji ");
        boolean chcesz = true;
        while (chcesz == true) {

            double jakijezyk = Math.random() - 1000000;
            System.out.println("Rozpoczynam test generacji " + dokladnosc + "\n" + "podaj tekst");
            String tekst = "";
            Scanner sc1 = new Scanner(System.in);

            tekst += sc1.nextLine();


            List<Double> listaW3 = new ArrayList<>();
            for (int m = 0; m < znaki; m++) {
                listaW3.add(0.0);
            }
            String txtAsci = "";
            for (int k = 0; k < tekst.length(); k++) {
                for (int l = 0; l < znaki; l++) {
                    if (tekst.charAt(k) == l + 97) {
                        txtAsci += tekst.charAt(k);
                    }
                }
            }

            for (int k = 0; k < txtAsci.length(); k++) {
                for (int l = 0; l < znaki; l++) {
                    if (txtAsci.charAt(k) == l + 97) {
                        listaW3.set(l, (listaW3.get(l) + 1));
                    }
                }
            }
            for (int i = 0; i < listaP.size(); i++) {
                double s1 = 0;
                for (int o = 0; o < listaW3.size(); o++) {
                    s1 += (listaP.get(i).listaW.get(o) * (listaW3.get(o) / txtAsci.length()));
                }

                listaP.get(i).s = s1- listaP.get(i).prog;
            }


            for (int i = 0; i < listaP.size(); i++) {
                if (listaP.get(i).s > jakijezyk) {
                    jakijezyk = listaP.get(i).s;
                }
            }

            for (int i = 0; i < listaP.size(); i++) {
                if (listaP.get(i).s == jakijezyk) {
                    System.out.println("To jest " + listaP.get(i).jezyk);
                }
            }

            System.out.println("dalej? y/n");

            boolean decyzja = false;
            while (decyzja == false) {
                Scanner sc2 = new Scanner(System.in);
                String yup = "";
                if (sc2.hasNext())
                    yup = sc2.nextLine();
                if (yup.equals("n")) {
                    chcesz = false;
                    decyzja = true;
                } else if (yup.equals("y")) {
                    chcesz = true;
                    decyzja = true;
                } else {
                    System.out.println("Try again");
                }
            }

        }
    }
}
