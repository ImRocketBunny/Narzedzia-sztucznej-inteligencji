import java.util.Collections;
import java.util.Comparator;

public class Kwiat {
    double roznica;
    String nazwa;
    public Kwiat(String nazwa, double roznica){
        this.roznica=roznica;
        this.nazwa=nazwa;
    }


    public int compareTo(Kwiat o) {
        Double irozn = roznica;
        Double orizn = o.roznica;
        return irozn.compareTo(orizn);
    }


}
