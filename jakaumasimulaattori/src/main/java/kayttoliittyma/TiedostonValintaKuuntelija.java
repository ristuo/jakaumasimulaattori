package kayttoliittyma;
import java.awt.event.*;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.io.*;


public class TiedostonValintaKuuntelija implements ActionListener {
    
    private JFrame ohjelmaKehys;
    private File tallennusTiedosto;
    private boolean onkoTiedostoa = false;
    
    public TiedostonValintaKuuntelija(JFrame frame) {
        this.ohjelmaKehys = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
              
        
    }
    
    
    public File gettallennusTiedosto() {
        return this.tallennusTiedosto;
    }
    
    public boolean onkoTiedostoa() {
        return onkoTiedostoa;
    }
}
