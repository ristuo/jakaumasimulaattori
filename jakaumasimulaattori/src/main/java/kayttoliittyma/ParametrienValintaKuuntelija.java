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





public class ParametrienValintaKuuntelija implements ActionListener {
    
    JFrame parametrienValintaKehys;
    JFrame jakaumanValintaKehys;
    
    public ParametrienValintaKuuntelija(JFrame parametrienValintaKehys, JFrame paaohjelmaKehys) {
        this.parametrienValintaKehys=parametrienValintaKehys;
        this.jakaumanValintaKehys = paaohjelmaKehys;
    }
    
    public void actionPerformed(ActionEvent e){
        parametrienValintaKehys.setVisible(true);
        jakaumanValintaKehys.setVisible(false);
    }
    
}
