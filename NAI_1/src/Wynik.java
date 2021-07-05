import java.util.*;

public class Wynik {
    String nazwa ="";
    public Wynik(List<Kwiat> listaK, int probki){
        String nazwa="";
        int[] tab=new int[3];
        int setosa = 0;
        int versicolor = 0;
        int virginica=0;
        for (int i = 0; i < probki; i++) {
            //System.out.println(listaK.get(i).nazwa);
            if (listaK.get(i).nazwa.equals("Iris-setosa"))
                setosa++;
            else if (listaK.get(i).nazwa.equals("Iris-versicolor")){
                versicolor++;
            }
            else
                virginica++;
        }
        int finalSetosa = setosa;
        int finalVersicolor = versicolor;
        int finalVirginica = virginica;
        /*Map<String,Integer> mapaKwaitow = new HashMap<>(){
            {
                put(" Iris-setosa ",finalSetosa);
                put(" Iris-versicolor ",finalVersicolor);
                put(" Iris-virginica ",finalVirginica);
            }
        };
        mapaKwaitow.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue())
                .forEach(System.out::println);
        */
        tab[0]=setosa;
        tab[1]=versicolor;
        tab[2]=virginica;
        Arrays.sort(tab);
            if (tab[1] == tab[0] && tab[1] == tab[2]) {
                int random = (int) (Math.random() * 3);
                if (random == 1) {
                    //System.out.println(("Iris-setosa"));
                    this.nazwa="Iris-setosa";
                } else if (random == 2) {
                    //System.out.println((" Iris-versicolor "));
                    this.nazwa="Iris-versicolor";
                } else {
                    //System.out.println(" Iris-virginica ");
                    this.nazwa="Iris-virginica";
                }
                double liczba = Double.parseDouble(String.valueOf(tab[2]));
                double dokladnoscProc = ((double) (liczba / probki));
                dokladnoscProc = dokladnoscProc * 100.0;
                //System.out.println("Dokladnosc Elksperymetu to " + dokladnoscProc + '%');
            } else if (tab[2] > tab[1]) {
                if (tab[2] == setosa) {
                    //System.out.println(("Iris-setosa"));
                    this.nazwa="Iris-setosa";
                } else if (tab[2] == versicolor) {
                    //System.out.println((" Iris-versicolor "));
                    this.nazwa="Iris-versicolor";
                } else {
                    //System.out.println(" Iris-virginica ");
                    this.nazwa="Iris-virginica";
                }
                double liczba = Double.parseDouble(String.valueOf(tab[2]));
                double dokladnoscProc = ((double) (liczba / probki));
                dokladnoscProc = dokladnoscProc * 100.0;
                //System.out.println("Dokladnosc Elksperymetu to " + dokladnoscProc + '%');
            } else if (tab[2] == tab[1] && tab[1] > tab[0]) {
                //int random = (int) (Math.random() * 2);
                if(tab[0]==setosa){
                    int random = (int) (Math.random() * 2);
                    if(random==1){
                        //System.out.println(("Iris-versicolor"));
                        this.nazwa="Iris-versicolor";
                    }else {
                        //.out.println("Iris-virginica");
                        this.nazwa=" Iris-virginica ";
                    }
                }else if(tab[0]==versicolor){
                    int random = (int) (Math.random() * 2);
                    if(random==1){
                        //System.out.println((" Iris-setosa "));
                        this.nazwa="Iris-setosa";
                    }else {
                        //System.out.println("Iris-virginica");
                        this.nazwa="Iris-virginica";
                    }
                }else{
                    int random = (int) (Math.random() * 2);
                    if(random==1){
                        //System.out.println(("Iris-setosa"));
                        this.nazwa="Iris-setosa";
                    }else {
                        //System.out.println(("Iris-versicolor"));
                        this.nazwa="Iris-versicolor";
                    }
                }
                double liczba = Double.parseDouble(String.valueOf(tab[2]));
                double dokladnoscProc = ((double) (liczba / probki));
                dokladnoscProc = dokladnoscProc * 100.0;
                //System.out.println("Dokladnosc Elksperymetu to " + dokladnoscProc + '%');
            }

    }
}
