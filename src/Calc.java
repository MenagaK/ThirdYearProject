import java.util.ArrayList;

/**
 * Created by Menaga on 18/04/2017.
 */
public class Calc {

    public double calcDis(Nodes n,Nodes e){
        int y = n.getY()-e.getY();
        int x = n.getX()-e.getX();
        y= y*y;
        x = x*x;
        int z= y+x;
        double distance= Math.sqrt(z);
        return distance;
    }
    public ArrayList<TravelP> findDist(Graphs g,Nodes n){
        ArrayList<TravelP> list = new ArrayList<>();
        int s = g.getArray().size();
        for(int b= 0;b<s;++b){
            if(g.getArray().get(b).isAvailable){
                double d =calcDis( n,g.getArray().get(b));
                TravelP h= new TravelP(n,d);
                list.add(h);
            }
        }
        return list;
    }

    public int findLow(ArrayList<TravelP> t){
        int s= t.size();
        double k= 150;
        int o=0;
        for(int b=0;b<s;++b){
            double r =t.get(b).getD();
           if (r<k) {
             k=r;
               o=b;
           }
        }
            return o;
    }
    public int findHigh(ArrayList<TravelP> t){
        int s= t.size();
        double k= 0;
        int o=0;
        for(int b=0;b<s;++b){
            double r =t.get(b).getD();
            if (r>k) {
                k=r;
                o=b;
            }
        }
        return o;
    }
    public Data NN(Graphs g){
        Nodes NextNode=g.getArray().get(0);
        ArrayList<Nodes> no = new ArrayList<>();
        no.add(NextNode);
        double tour = 0;
        int s = g.getArray().size();
        long startTime = System.currentTimeMillis();
        for(int b=0;b<s;++b){
            ArrayList<TravelP> t = findDist(g,NextNode);
            System.out.println(t.get(0).getN());
            NextNode.setIsAvailable(false);
            int f = findHigh(t);
            double i = t.get(f).getD();
            tour = tour +i;
            NextNode=t.get(f).getN();
            no.add(NextNode);
        }

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        Data data = new Data(totalTime,tour,no);
        return data;
    }
    public Data FI(Graphs g){
        Nodes NextNode=g.getArray().get(0);
        ArrayList<Nodes> no = new ArrayList<>();
        no.add(NextNode);
        double tour = 0;
        long startTime = System.currentTimeMillis();
        int s = g.getArray().size();
        for(int b=0;b<s;++b){
            ArrayList<TravelP> t = findDist(g,NextNode);

            int f = findLow(t);
            double i = t.get(f).getD();
            tour = tour +i;
            NextNode=t.get(f).getN();
            NextNode.setIsAvailable(false);
            no.add(NextNode);
        }
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        Data data = new Data(totalTime,tour,no);
        return data;

    }

}
