import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        int losowanie =(int)(Math.random()*15)+1;
        System.out.println("wylosowano zestaw nr: "+losowanie);
        FileReader fr = new FileReader("plecak.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        Plecak plecak = new Plecak();
        List<Przedmiot> listaP = new ArrayList<>();
        List<Integer> sizes = new ArrayList<>();
        List<Integer> vals = new ArrayList<>();
        int stop =0;
        int pocz = 0;
        int length=0;
        int pojemnosc1 = 0;
        int koniec=0;
        while ((line = br.readLine()) != null) {
            if (pocz == 0){
                String[] liniaPocz = line.replace(',', ' ').split(" ");
                if (liniaPocz[0].equals("length")) {
                    try {
                        length=Integer.parseInt(liniaPocz[2]);
                    }catch (Exception e){

                    }

                }
                if(liniaPocz[4].equals("capacity")){
                    try {
                        pojemnosc1=Integer.parseInt(liniaPocz[5]);
                        plecak.pojemnosc=pojemnosc1;
                    }catch (Exception e){

                    }
                }
                pocz=1;
            }
            if(line.equals("dataset "+String.valueOf(losowanie)+':')){
                stop=1;
            }

            if(stop==1) {
                line = line.replace(',', ' ').replace('{', ' ').replace('}', ' ');
                line.trim();
                line.replace(""," ");
                String[] linia = line.split(" ");
                if(linia[0].equals("sizes")){
                    for(int i = 2;i<linia.length;i++){
                        try {
                            sizes.add(Integer.parseInt(linia[i]));
                        }catch (Exception e){

                        }
                    }
                    koniec++;
                }
                if(linia[0].equals("vals")) {
                    for(int i = 2;i<linia.length;i++) {
                        try {
                            vals.add(Integer.parseInt(linia[i]));
                        } catch (Exception e) {

                        }
                    }
                    koniec++;
                }

                if(koniec==2){
                   break;
                }
            }
        }
        for(int i = 0;i<vals.size();i++){
            listaP.add(new Przedmiot(sizes.get(i),vals.get(i)));
        }
        String max1="";
        for(int i =0;i<length;i++){
            max1+="1";
        }
        long maxVal = 0;
        int bestVal=0;
        String best="";
        int index=0;
        for(int i = max1.length()-1;i>=0;i--){
            maxVal+=Math.pow(2,index);
            index++;
        }
        Long tmp = System.nanoTime();
        for(int i = 1;i<maxVal;i++){
            String kombinacja =new StringBuilder(Integer.toBinaryString(i)).reverse().toString();
            int allVal=0;
            int cap=0;
            for (int j = 0;j<kombinacja.length();j++){
                if(kombinacja.charAt(j)=='1'){
                    allVal+=listaP.get(j).val;
                    cap+=listaP.get(j).size;
                }
            }
            if(cap<=plecak.pojemnosc){
                if(allVal>bestVal){
                    bestVal=allVal;
                    best=kombinacja;
                }
            }

        }
        Long tmp2 = System.nanoTime();
        while (best.length()<26){
            best+=0;
        }
        System.out.println("najlepsza kombinacja: "+best);
        System.out.println("czas szukania = "+(double)(tmp2-tmp)/1000000000);
        int cap=0,val=0;
        for (int i = 0;i<best.length();i++){
            if(best.charAt(i)=='1'){
                System.out.print("["+ i+ " ");
                System.out.print((listaP.get(i).size));
                System.out.print(" ");
                System.out.print((listaP.get(i).val));
                System.out.print("]");
                System.out.println();
                cap+=(listaP.get(i).size);
                val+=listaP.get(i).val;
            }
        }
        System.out.println("Value: "+val+", pojemnosc: "+cap);
    }
}
