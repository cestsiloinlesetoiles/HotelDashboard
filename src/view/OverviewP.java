package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Hotel;
import view.ui.theme;

public class OverviewP extends JPanel {
	public Hotel h;
	public JTable tableCustomer;
	public DefaultTableModel tableModelCustomer;
	public JPanel pnlMain = new JPanel();
	public JPanel pnlPageLevel = new JPanel();
	public OverviewP(Hotel h){
		
		this.h = h;
		// Mise en place du Pagelevel et du cadran Main
		setLayout(new BorderLayout());
		ImageIcon pgIco = new ImageIcon("resources/pagelevel/1.png");
		JLabel pg = new JLabel(pgIco);
		pg.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		pnlPageLevel.setLayout(new BoxLayout(pnlPageLevel, BoxLayout.Y_AXIS));
		pnlPageLevel.add(Box.createVerticalGlue());
		pnlPageLevel.add(pg);
		pnlPageLevel.add(Box.createVerticalGlue());
		add(pnlPageLevel,BorderLayout.EAST);
		
		add(pnlMain,BorderLayout.CENTER);
		pnlMain.setBackground(theme.SECONDARY_BACKGROUND);
		pnlPageLevel.setBackground(theme.SECONDARY_BACKGROUND);
		
		


	}
}
