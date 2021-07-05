import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Obliczenia {
    //List<Kwiat> kwiatek;
    public Obliczenia(String[] userData,int iloscDanych,List<Kwiat> listaKwiatow){
        try {
            FileReader fr = new FileReader("iris_training.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] linia = line.split("\\t");
                int lineLength = (linia.length - 2);
                double roznice = 0;
                if (iloscDanych > lineLength) {
                    int index = 0;
                    int roznica = iloscDanych - lineLength;
                    //double roznicaEuklidesowa;
                    for (int i = 0; i <= lineLength; i++) {
                        roznice += (Math.pow(Double.parseDouble(userData[i]) - Double.parseDouble(linia[i].replace(',', '.')), 2));
                        index++;
                    }
                    /*for (int i = 0; i <= roznica; i++) {
                        roznice += (Math.pow((Double.parseDouble(userData[index]) - 0), 2));
                    }*/
                    Kwiat kwiatek = new Kwiat(linia[linia.length - 1], Math.sqrt(roznice));
                    listaKwiatow.add(kwiatek);

                } else if (iloscDanych < lineLength) {
                    int index = 0;
                    int roznica = lineLength - iloscDanych;
                    for (int i = 0; i <= iloscDanych; i++) {
                        roznice += (Math.pow(Double.parseDouble(userData[i].replace(',', '.')) - Double.parseDouble(linia[i].replace(',', '.')), 2));
                        index++;
                    }
                   /* for (int i = 0; i <= roznica; i++) {
                        roznice += (Math.pow((0 - Double.parseDouble(linia[index].replace(',', '.'))), 2));
                    }*/

                    Kwiat kwiatek = new Kwiat(linia[linia.length - 1], Math.sqrt(roznice));
                    listaKwiatow.add(kwiatek);
                }
                else if (iloscDanych == lineLength) {

                    for (int i = 0; i <= iloscDanych; i++) {
                        roznice += (Math.pow(Double.parseDouble(userData[i].replace(',', '.')) - Double.parseDouble(linia[i].replace(',', '.')), 2));

                    }
                    Kwiat kwiatek = new Kwiat(linia[linia.length - 1], Math.sqrt(roznice));
                    listaKwiatow.add(kwiatek);
                }
                int index = linia.length - 2;
                double[] liczby = new double[linia.length - 1];
                for (int i = 0; i <= index; i++) {
                    liczby[i] = Double.parseDouble(linia[i].replace(',', '.'));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        Collections.sort(listaKwiatow, Kwiat::compareTo);
        /*for (Kwiat k:listaKwiatow
             ) {
            System.out.println(k.nazwa+" "+k.roznica);
        };*/
    }

}
