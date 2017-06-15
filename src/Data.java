import java.util.ArrayList;

/**
 * Created by Menaga on 18/04/2017.
 */
public class Data {
    long time;
    double tour;
    ArrayList<Nodes> no;
    public Data( long time,double tour,ArrayList<Nodes> no){
        this.time=time;
        this.tour=tour;
        this.no=no;
    }

    public double getTour() {
        return tour;
    }

    public long getTime() {
        return time;
    }

    public ArrayList<Nodes> getNo() {
        return no;
    }
}
