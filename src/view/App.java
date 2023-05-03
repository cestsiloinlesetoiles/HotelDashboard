package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;

import controller.PageController;
import controller.ShowDataMetaController;
import model.Hotel;

public class App extends JFrame {


	JPanel Menu;
   
	JPanel MultiScreen;
	OverviewP Overview;
	RoomP RoomManager;
	CustomerP CustomerManager;
	ReservationP ReservationManager;
	StayP StayManager;
	CardLayout cLM = new CardLayout();
	Hotel hotel;
	JButton[] GroupButtons = new JButton[5];
	
	public App(Hotel hotel) {
		

		MultiScreen = new JPanel();
		Menu = new JPanel();


		MultiScreen.setLayout(cLM);
		Menu.setLayout(new BoxLayout(Menu, BoxLayout.PAGE_AXIS));

		Overview = new OverviewP(hotel);
		RoomManager = new RoomP(hotel);
		CustomerManager = new CustomerP(hotel);
		ReservationManager = new ReservationP(hotel);
		StayManager = new StayP(hotel);

		MultiScreen.add(Overview,"Overview");
		MultiScreen.add(RoomManager,"Room Manager");
		MultiScreen.add(CustomerManager,"Customer Manager");
		MultiScreen.add(ReservationManager,"Reservation Manager");
		MultiScreen.add(StayManager,"Stay Manager");
		
		
		ShowDataMetaController sd = new ShowDataMetaController(hotel);
		JButton Demo = new JButton("Data Console");
		Demo.addActionListener(sd);
		
		JButton btnOverview = new JButton("Overview");
		JButton btnRoomManager = new JButton("Room Manager");
		JButton btnCustomerManager = new JButton("Customer Manager");
		JButton btnReservationManager = new JButton("Reservation Manager");
		JButton btnStayManager = new JButton("Stay Manager");
		
		// Permet de gerer la couleur du bouton selon l'onglet voir pgc
		GroupButtons[0] = btnOverview;
        GroupButtons[1] = btnRoomManager;
        GroupButtons[2] = btnCustomerManager;
        GroupButtons[3] = btnReservationManager;
        GroupButtons[4] = btnStayManager;
        Color buttonBackground = Color.BLACK;
        Color buttonText = Color.WHITE;

        for (JButton button : GroupButtons) {
        	button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            button.setBackground(buttonBackground);
            button.setForeground(buttonText);
            button.setFocusPainted(false);
            /*En combinant ces deux éléments, cette instruction définit 
             * la bordure du bouton à une bordure vide, ce qui a pour effet de 
             * supprimer tout style ou décoration de bordure du bouton.*/
            button.setBorder(BorderFactory.createEmptyBorder());
        }

		
		
		PageController pgc = new PageController(cLM, MultiScreen,GroupButtons);
		
		btnOverview.addActionListener(pgc);
		btnRoomManager.addActionListener(pgc);
		btnCustomerManager.addActionListener(pgc);
		btnReservationManager.addActionListener(pgc);
		btnStayManager.addActionListener(pgc);
		
		Menu.add(Box.createRigidArea(new Dimension(0, 25)));
		Menu.add(btnOverview);
		Menu.add(Box.createRigidArea(new Dimension(0, 25)));
		Menu.add(btnRoomManager);
		Menu.add(Box.createRigidArea(new Dimension(0, 25)));
		Menu.add(btnCustomerManager);
		Menu.add(Box.createRigidArea(new Dimension(0, 25)));
		Menu.add(btnReservationManager);
		Menu.add(Box.createRigidArea(new Dimension(0, 25)));
		Menu.add(btnStayManager);
		Menu.add(Box.createRigidArea(new Dimension(0, 25)));
		Menu.add(Demo);
		Menu.setBackground(Color.BLACK);

        

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(MultiScreen, BorderLayout.CENTER);
		getContentPane().add(Menu, BorderLayout.WEST);
	}


	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatArcIJTheme());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		Hotel hotel = new Hotel("Fanti", "28 rue Parisien");
		App frame = new App(hotel);
		frame.setMinimumSize(new Dimension(1200,720));

		frame.setVisible(true);

	}
}
