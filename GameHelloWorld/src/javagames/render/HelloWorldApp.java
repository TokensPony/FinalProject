package javagames.render;

import java.awt.*;
import javax.swing.*;
import javagames.util.*;

public class HelloWorldApp extends JFrame {
   
   private FrameRate frameRate;
   int sizex = 640;
   int sizey = 480;
   String fps = "";
   FontMetrics metrics;
   
   public HelloWorldApp() {
      frameRate = new FrameRate();
   }
   
   protected void createAndShowGUI() {
      GamePanel gamePanel = new GamePanel();
      gamePanel.setBackground( Color.BLACK );
      //320,240
      gamePanel.setPreferredSize( new Dimension( sizex, sizey ) );
      getContentPane().add( gamePanel );
      setDefaultCloseOperation( EXIT_ON_CLOSE );
      setTitle( "Hello World!" );
      pack();
      frameRate.initialize();
      setVisible( true );
   }
   
   private class GamePanel extends JPanel {
       public void paint( Graphics g ) {
         super.paint( g );
         metrics = g.getFontMetrics();
         onPaint( g );
      }
   }
   
   protected void onPaint( Graphics g ) {
      frameRate.calculate();
      g.setColor( Color.WHITE );
      //30,30
      fps = frameRate.getFrameRate();
      g.drawString( fps , (sizex/2)-metrics.stringWidth(fps)/2, sizey/2 );
      repaint();
   }

   public static void main( String[] args ) {
      final HelloWorldApp app = new HelloWorldApp();
      SwingUtilities.invokeLater( new Runnable() {
         public void run() {
            app.createAndShowGUI();
         }
      });
   }
}
