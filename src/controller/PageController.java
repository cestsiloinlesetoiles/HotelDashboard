package controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PageController implements ActionListener {
    
	public CardLayout cardLayout;
    public JPanel parent;
    JButton[] GroupButtons;
    public PageController(CardLayout cardLayout, JPanel parent, JButton[] buttons) {
        this.cardLayout = cardLayout;
        this.parent = parent;
        this.GroupButtons = buttons;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton b = (JButton)event.getSource();
        for (JButton button : GroupButtons) {
            if (button == b) {
                button.setBackground(Color.gray); // ou une autre couleur de votre choix
            } else {
                button.setBackground(null); // ou la couleur par défaut
            }
        }
        System.out.println(b.getText());
    	cardLayout.show(parent, b.getText());
    }
}
