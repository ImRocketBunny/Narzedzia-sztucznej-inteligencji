import java.util.ArrayList;
import java.util.List;

public class Perceptron {
    List<Double> listaW = new ArrayList<>();
    int y;
    int d;
    double s;
    double prog;
    String jezyk;
    public Perceptron(List<Double> listaW,Double prog,double s,String jezyk){
        this.prog=prog;
        this.listaW=listaW;
        this.s=s;
        this.jezyk=jezyk;


    }
}
