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
    
    public PageController(CardLayout cardLayout, JPanel parent, App app) {
        this.cardLayout = cardLayout;
        this.Mutliscren = parent;
    
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton b = (JButton)event.getSource();
        // verification
        System.out.println(b.getActionCommand());
        // change l'état de l'icone de la barre de tache en fonction de la page passe la page courante en icone active et le reste en icone inactive.
        app.setStatusIcon(b.getActionCommand());

        // change la page en fonction du bouton cliqué avec le cardLayout
        // L'aide de la clé qui est la actionCommand du bouton setup dans app.java
    	cardLayout.show(Mutliscren, b.getActionCommand());
    }
}
