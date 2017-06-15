import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.ArrayList;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Created by Menaga on 02/11/2016.
 */
public class MainWindow  {
    JFrame frame = new JFrame("Graph display");
    JPanel mainpanel = new JPanel();
    CardLayout layout = new CardLayout();
    JPanel card1 = new JPanel();
    JPanel newGraph = new JPanel();
    JPanel selectGraph = new JPanel();
    JPanel displayGraph= new JPanel();
    JPanel editGraph= new JPanel();
    JTextField write = new JTextField(20);
    JButton set = new JButton("Create");
    JButton eback = new JButton("Go back");
    JButton nback = new JButton("Go back");
    JButton dback = new JButton("Go back");
    JButton sback = new JButton("Go back");
    JButton ok =new JButton("Ok");
    JButton nGraph = new JButton("Create new Graph");
    JButton eGraph = new JButton("Edit Graphs");
    JButton dGraph = new JButton("Show Graphs");
    
    JButton aNode = new JButton("Add Node");
    JButton dNode = new JButton("Delete Node");
    JTextField NodeN = new JTextField(20);
    JTextField NodeX = new JTextField(2);
    JTextField NodeY = new JTextField(2);
    JButton zoomin= new JButton("+");
    JButton zoomout = new JButton("-");
    JButton reset = new JButton("reset");
    JButton NN = new JButton("NN");
    JButton FF = new JButton("FF");
    JLabel tour = new JLabel();
    JLabel time = new JLabel();
    Graph first;
    SqliteClass n = new SqliteClass();
    Viewer viewer ;
    ViewPanel view ;
    ArrayList<Graphs> gra;
    Graphs gh;
    JLabel l = new JLabel();

    public void createGui(){


        frame.setLayout(new FlowLayout());
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(mainpanel);


        mainpanel.setLayout(layout);


        card1.add(nGraph);
        card1.add(eGraph);
        card1.add(dGraph);
// code to set name of a new graph
        newGraph.add(write);
        newGraph.add(nback);
        newGraph.add(set);
// code to select graph to edit
        System.out.println("going to look at graphs");
         gra=n.GetGraphs();
        int s = gra.size();

        String[] list = new String[s];
        for(int b=0;b<s;++b){
            String c =gra.get(b).getName();
            list[b]= c;

        }
        System.out.println("list of things" + s);
        final JComboBox gList = new JComboBox(list);
        selectGraph.add(gList);
        selectGraph.add(ok);
        selectGraph.add(sback);

// code to display graphs
        first = new MultiGraph("first");

        viewer = new Viewer(first, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        view = viewer.addDefaultView(false);   // false indicates "no JFrame".
        view.setPreferredSize(new Dimension(400, 400));
        final JComboBox dList = new JComboBox(list);
        displayGraph.add(dList);
        displayGraph.add(dback);
        displayGraph.add(zoomin);
        displayGraph.add(zoomout);
        displayGraph.add(reset);
        displayGraph.add(view);
        displayGraph.add(NN);
        displayGraph.add(FF);
        displayGraph.add(tour);
        displayGraph.add(time);
        // code to edit graphs

        editGraph.add(eback);
        editGraph.add(NodeN);
        editGraph.add(NodeX);
        editGraph.add(NodeY);
        editGraph.add(aNode);
        editGraph.add(dNode);


// adding various panels to the container
        mainpanel.add(card1,"Start");
        mainpanel.add(newGraph, "New Graph");
        mainpanel.add(selectGraph,"Select Graph");
        mainpanel.add(displayGraph,"Display Graph");
        mainpanel.add(editGraph,"Edit Graph");

        nGraph.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {

                layout.show(mainpanel,"New Graph");
            }
        });
        dGraph.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {

                layout.show(mainpanel,"Display Graph");


            }
        });
        eGraph.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {

                layout.show(mainpanel,"Select Graph");
            }
        });
        aNode.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {

                    int x = Integer.parseInt(NodeX.getText());
                    int y = Integer.parseInt(NodeY.getText());
                    Nodes z = new Nodes(l.getText(),x,y,NodeN.getText(),true);

                    n.addNode(z);


            }
        });
        dNode.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                n.removeNode(NodeN.getText());

            }
        });
        gList.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                layout.show(mainpanel,"Edit Graph");
                l.setText(gList.getSelectedItem().toString());

            }
        });
        NN.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                Calc cal = new Calc();
                Data data= cal.NN(gh);
                time.setText(String.valueOf(data.getTime()));
                tour.setText(String.valueOf(data.getTour()));
                ArrayList<Nodes> no = data.getNo();
                int s= no.size();
                for(int d=0;d<s;++d){
                    String a =no.get(d).getName();
                    String b =no.get(d+1).getName();
                    first.addEdge(a+b,a,b);
                }

            }
        });
        FF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                Calc cal = new Calc();
                Data data= cal.FI(gh);
                time.setText(String.valueOf(data.getTime()));
                tour.setText(String.valueOf(data.getTour()));
                ArrayList<Nodes> no = data.getNo();
                int s= no.size();
                for(int d=0;d<s;++d){
                    String a =no.get(d).getName();
                    String b =no.get(d+1).getName();
                    first.addEdge(a+b,a,b);
                }

            }
        });


        class GoBack implements ActionListener{

            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout)(mainpanel.getLayout());
                cardLayout.show(mainpanel,"Start");
            }
        }
        GoBack back = new GoBack();
        nback.addActionListener(back);
        eback.addActionListener(back);
        dback.addActionListener(back);
        sback.addActionListener(back);

        zoomin.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                view.getCamera().setViewPercent(0.5);
            }
        });
        zoomout.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                view.getCamera().setViewPercent(1);
            }
        });
        reset.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                view.getCamera().resetView();
            }
        });
        dList.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                int size =gra.size();
                String ca = dList.getSelectedItem().toString();
                first.display();
                for(int b=0;b<size;++b){

                    if (ca.equals(gra.get(b).getName())){
                        gh= gra.get(b);
                    }
                    int k=gh.getArray().size();
                    for(int d=0;d<k;++d){
                        Nodes sn= gh.getArray().get(d);
                        first.addNode(sn.getName());
                    }
                }
            }
        });
        set.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                if(write.getText().equals("")){}
                else{
                    String name =write.getText();
                    n.addGraph(name);
                }

            }
        });


        frame.add(mainpanel);

    }

    public static void main(String[] args) {
        // write your code here
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                SqliteClass n = new SqliteClass();
                if (n.checkGraph()==0){
                    n.createTable();
                }

                if (n.checkNode()==0){
                    n.createNodeTable();
                }
                MainWindow m = new MainWindow();
                m.createGui();



            }
        });

    }
}


