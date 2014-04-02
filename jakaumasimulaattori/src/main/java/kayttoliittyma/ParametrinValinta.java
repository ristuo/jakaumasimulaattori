package kayttoliittyma;
import java.util.*;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.io.*;



public class ParametrinValinta implements Runnable {
    
    
    
    private JFrame parametrinValintaKehys;
    
    
    
    @Override
    public void run() {
        this.luoKehykset();
        parametrinValintaKehys.setVisible(true);
    }
    
    private void luoKehykset() {
        this.parametrinValintaKehys = new JFrame();
        parametrinValintaKehys.setPreferredSize( new Dimension( 200, 400));
        parametrinValintaKehys.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    
    }
}
