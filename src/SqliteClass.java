/**
 * Created by Menaga on 14/04/2017.
 */
import java.sql.*;
import java.util.ArrayList;

public class SqliteClass {

    public static void createTable(  )
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE GRAPHS " +
                    "( NAME TEXT  NOT NULL)"
                    ;

            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public static void createNodeTable(  )
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "CREATE TABLE NODES " +
                    "( GRAPHNAME TEXT  NOT NULL," +
                    " X   INT   NOT NULL, " +
                    " Y   INT   NOT NULL, " +
                    " NAME  TEXT NULL )"
                    ;
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
    public static void addGraph(String name){
        Connection c = null;
        Statement stmt = null;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:test.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");
            System.out.println( "Error in update no"  );
        stmt = c.createStatement();

        String sql = "INSERT INTO GRAPHS (NAME) " +
                "VALUES ('"+name+"');";
        stmt.executeUpdate(sql);

        stmt.close();
        c.commit();
        c.close();
    } catch ( Exception e ) {
        System.err.println( "Error in update" + e.getMessage() );
        System.exit(0);
    }
    System.out.println("Update Sucessful");
}

    public static void addNode(Nodes node){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO NODES (GRAPHNAME,X,Y,NAME) " +
                    "VALUES ('"+node.getGraph()+"','"+node.getX()+"','"+node.getY()+"','"+node.getName()+"');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( "Error in update" + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Addition Sucessful");
    }
    public static void removeNode(String name){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "DELETE from NODES where NAME="+name+";";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( "Error in update" + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Addition Sucessful");
    }


    public ArrayList<Graphs> GetGraphs(){
        ArrayList<Graphs> graphs = new ArrayList<Graphs>();


        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM GRAPHS;" );
            while ( rs.next() ) {

                String  name = rs.getString("NAME");

                ArrayList<Nodes> nodes = new ArrayList<Nodes>();
                Graphs g=new Graphs(name,nodes);
                graphs.add(g);
                System.out.println("Added graph");
            }
            ResultSet nod = stmt.executeQuery( "SELECT * FROM NODES;" );
            while ( nod.next() ) {

                String n = nod.getString("NAME");


                    String gn = nod.getString("GRAPHNAME");
                    int x= nod.getInt("X");
                    int y= nod.getInt("Y");
                    Nodes nn = new Nodes(gn,x,y,n,true);
                    int s= graphs.size();
                for(int b=0;b<s;++b){
                    if(graphs.get(b).getName().equals(gn)){
                        graphs.get(b).addNodes(nn);
                    }
                }


            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return graphs;
    }
    public int checkGraph(){
       int s =0;


        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT count(*) FROM sqlite_master WHERE type='table' AND name='GRAPHS';" );

            s =rs.getInt(1);
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return s;
    }
    public int checkNode(){
        int s =0;


        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT count(*) FROM sqlite_master WHERE type='table' AND name='NODES';" );

            s =rs.getInt(1);
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return s;
    }

    }

