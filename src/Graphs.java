import java.util.ArrayList;

/**
 * Created by Menaga on 14/04/2017.
 */
public class Graphs {

    String name;
    ArrayList<Nodes> nodes = new ArrayList<Nodes>();


    public Graphs(String n, ArrayList nodes){
    this.name = n;
        this.nodes=nodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addNodes(Nodes node) {
       nodes.add(node);
    }
    public void removeNodes(Nodes node) {
        nodes.remove(node);
    }

    public Nodes getNode(int index){
        Nodes rnode= nodes.get(index);
        return rnode;
    }

    public void setNodesX(Nodes node,int x) {

        Nodes set = node;
        set.setX(x);
    }

    public ArrayList<Nodes> getArray() {
        return nodes;
    }

    public void setNodesY(Nodes node, int y) {

        Nodes set = node;
        set.setY(y);
    }
}
