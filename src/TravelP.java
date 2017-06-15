import java.util.ArrayList;

/**
 * Created by Menaga on 18/04/2017.
 */
public class TravelP {
    Nodes n;
    double s;
    public TravelP(Nodes n, double s){
        this.n = n;
        this.s=s;
    }

    public double getD() {
        return s;
    }

    public Nodes getN() {
        return n;
    }
}
