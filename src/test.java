import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Menaga on 17/04/2017.
 */
public class test {


    public static void main(String args[]){
}
public void TestGraphs(){

            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:test.db");
                c.setAutoCommit(false);
                System.out.println("Opened database successfully");

                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery( "SELECT * FROM NODES;" );
                while ( rs.next() ) {

                    String  name = rs.getString("GRAPHNAME");

                    System.out.println( " name " + name );


                }
                rs.close();
                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Operation done successfully");
        }
    public void TestG(){
        ArrayList<Nodes> no= new ArrayList<>();
        Nodes n = new Nodes("g",1,1,"n",true);
        no.add(n);
        Graphs g = new Graphs("g", no);
        System.out.println( g.getName()+g.getArray().get(0).getName() );
    }
    }



