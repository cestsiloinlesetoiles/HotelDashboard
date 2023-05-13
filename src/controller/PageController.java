package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.App;

public class PageController implements ActionListener {
    
	public CardLayout cardLayout;
    public JPanel Mutliscren;
    public App app;
    JButton[] GroupButtons;
    public PageController(CardLayout cardLayout, JPanel parent, JButton[] buttons, App app) {
        this.cardLayout = cardLayout;
        this.Mutliscren = parent;
        this.GroupButtons = buttons;
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton b = (JButton)event.getSource();
        
        System.out.println(b.getActionCommand());
        app.setStatusIcon(b.getActionCommand());
    	cardLayout.show(Mutliscren, b.getActionCommand());
    }
}
