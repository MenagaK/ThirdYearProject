/**
 * Created by Menaga on 14/04/2017.
 */
public class Nodes {
    String name;
    int x;
    int y;
    String graph;
    Boolean isAvailable;



    public Nodes( String graph, int x,int y, String name,Boolean isAvailable){
        this.name= name;
        this.x=x;
        this.y=y;
        this.graph=graph;
        this.isAvailable=isAvailable;

    }

    public String getName() {
        return name;
    }
    public void setName(String n){
        this.name=n;
    }

    public String getGraph(){
        return graph;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }
    public void setIsAvailable(boolean t){
        this.isAvailable=t;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


}
