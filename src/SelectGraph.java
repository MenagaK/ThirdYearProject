import javax.swing.*;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Created by Menaga on 14/04/2017.
 */
public class SelectGraph {


        public static void SelectGraph(){
            JFrame frame = new JFrame();
            frame.setLayout(new BorderLayout());

            frame.setSize(600, 450);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

            JScrollBar gList = new JScrollBar();
            JButton set = new JButton("Create");
            JButton back = new JButton("Go back");


        }}

