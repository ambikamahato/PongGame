import javax.swing.*;
import java.awt.*;

public class GameFrame {

    JFrame frame;


    Gamepannel panel;


    GameFrame(){ // GameFrame Constructer
        frame=new JFrame("PongGame");   //Initialize with my Frame
        //frame.setLayout(null);    //Take "null" for Default Layout
        panel = new Gamepannel();  //Initialize with panel
        frame.add(panel);
        frame.setBackground(Color.black);
        panel.setBounds(0,0,1000,555);  // for panel colour
       /* panel.setBackground(Color.black);*/  // set Colour for Jpanel BackGround


        frame.setSize(1000,555);    //for set size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //if click on Cross Button close the progame
        frame.setVisible(true);      // for Visible my Frame
        frame.setResizable(false);   // for fix the size
        frame.setLocationRelativeTo(null);  // same as setLayout

    }
}
