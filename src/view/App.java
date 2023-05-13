package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;

import controller.PageController;
import controller.ShowDataMetaController;
import model.Hotel;
import view.ui.theme;

public class App extends JFrame {


	JPanel Menu;
	JPanel MultiScreen;
	OverviewP Overview;
	RoomP RoomManager;
	CustomerP CustomerManager;
	ReservationP ReservationManager;
	StayP StayManager;
	CardLayout CdL = new CardLayout();
	Hotel hotel;
	JButton[] GroupButtons = new JButton[6];
	
	JButton btnOverview;
	JButton btnRoomManager;
	JButton btnCustomerManager;
	JButton btnReservationManager;
	JButton btnStayManager;
	
	ImageIcon overviewIconA = new ImageIcon("resources/overviewIconA.png");
	ImageIcon overviewIcon = new ImageIcon("resources/overviewIcon.png");
	
	ImageIcon roomManagerIconA = new ImageIcon("resources/roomManagerIconA.png");
	ImageIcon roomManagerIcon = new ImageIcon("resources/roomManagerIcon.png");
	
	ImageIcon customerManagerIconA = new ImageIcon("resources/customerManagerIconA.png");
	ImageIcon customerManagerIcon = new ImageIcon("resources/customerManagerIcon.png");
	
	ImageIcon reservationManagerIconA = new ImageIcon("resources/reservationManagerIconA.png");
	ImageIcon reservationManagerIcon = new ImageIcon("resources/reservationManagerIcon.png");
	
	ImageIcon stayManagerIconA = new ImageIcon("resources/stayManagerIconA.png");
	ImageIcon stayManagerIcon = new ImageIcon("resources/stayManagerIcon.png");

	
	
	public App(Hotel hotel) {
		this.hotel = hotel;
		setBackground(theme.BACKGROUND);
		Overview = new OverviewP(hotel);
		RoomManager = new RoomP(hotel);
		CustomerManager = new CustomerP(hotel);
		ReservationManager = new ReservationP(hotel);
		StayManager = new StayP(hotel);
		hotel.addObserver(ReservationManager);
		
		
		MultiScreen = new JPanel();
		MultiScreen.setLayout(CdL);
		

		MultiScreen.add(Overview,"Overview");
		MultiScreen.add(RoomManager,"Room Manager");
		MultiScreen.add(CustomerManager,"Customer Manager");
		MultiScreen.add(ReservationManager,"Reservation Manager");
		MultiScreen.add(StayManager,"Stay Manager");
		
		Menu = new JPanel();
		Menu.setLayout(new BoxLayout(Menu, BoxLayout.PAGE_AXIS));
		
		
		
		
		
		btnOverview = new JButton();
		
		btnOverview.setActionCommand("Overview");
		
		btnRoomManager = new JButton();
		btnRoomManager.setActionCommand("Room Manager");

		btnCustomerManager = new JButton();
		btnCustomerManager.setActionCommand("Customer Manager");

		btnReservationManager = new JButton();
		btnReservationManager.setActionCommand("Reservation Manager");
		
		btnStayManager = new JButton();
		btnStayManager.setActionCommand("Stay Manager");
		
		setStatusIcon("");
		
		
		// Permet de gerer la couleur du bouton selon l'onglet voir pgc
		GroupButtons[0] = btnOverview;
        GroupButtons[1] = btnRoomManager;
        GroupButtons[2] = btnCustomerManager;
        GroupButtons[3] = btnReservationManager;
        GroupButtons[4] = btnStayManager;
        
        
        Color buttonBackground = theme.BACKGROUND;
        
        ShowDataMetaController sd = new ShowDataMetaController(hotel);
		JButton Demo = new JButton("t");
		
		
		Demo.addActionListener(sd);
		ImageIcon inactiveIcon = new ImageIcon("resources/Autres/Data.png");
		Demo.setIcon(inactiveIcon);
		ImageIcon activeIcon = new ImageIcon("resources/Autres/DataA.png");
		Demo.setPressedIcon(activeIcon);
		GroupButtons[5] = Demo;
		
        for (JButton button : GroupButtons) {
        	
            button.setBackground(buttonBackground);
            button.setFocusPainted(false);
            button.setSize(new Dimension(25,25));
            button.setAlignmentX(CENTER_ALIGNMENT); 
            button.setBorder(BorderFactory.createEmptyBorder());
        }

		
		
		PageController pgc = new PageController(CdL, MultiScreen,GroupButtons,this);
		
		btnOverview.addActionListener(pgc);
		btnRoomManager.addActionListener(pgc);
		btnCustomerManager.addActionListener(pgc);
		btnReservationManager.addActionListener(pgc);
		btnStayManager.addActionListener(pgc);
		
		Demo.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel titleLabel = new JLabel("");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
		titleLabel.setForeground(theme.SECONDARY_TEXT);
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		Menu.add(Box.createRigidArea(new Dimension(0, 15)));
		Menu.add(titleLabel);
		Menu.add(Box.createVerticalGlue());
		Menu.add(btnOverview);
		Menu.add(Box.createRigidArea(new Dimension(0, 60)));
		Menu.add(btnRoomManager);
		Menu.add(Box.createRigidArea(new Dimension(0, 60)));
		Menu.add(btnCustomerManager);
		Menu.add(Box.createRigidArea(new Dimension(0, 60)));
		Menu.add(btnReservationManager);
		Menu.add(Box.createRigidArea(new Dimension(0, 60)));
		Menu.add(btnStayManager);
		Menu.add(Box.createRigidArea(new Dimension(0, 60)));
		Menu.setBackground(Color.decode("#232323"));
		Menu.add(Box.createVerticalGlue());
		Menu.add(Demo);

        
		
		
		
		
		getContentPane().setLayout(new BorderLayout());
	
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(MultiScreen, BorderLayout.CENTER);
		getContentPane().add(Menu, BorderLayout.WEST);
	}
	
	
	public void setStatusIcon(String actionCommand) {
	    switch (actionCommand) {
	        case "Overview":
	            btnOverview.setIcon(overviewIconA);
	            btnRoomManager.setIcon(roomManagerIcon);
	            btnReservationManager.setIcon(reservationManagerIcon);
	            btnCustomerManager.setIcon(customerManagerIcon);
	            btnStayManager.setIcon(stayManagerIcon);
	            break;
	        case "Room Manager":
	            btnRoomManager.setIcon(roomManagerIconA);
	            btnOverview.setIcon(overviewIcon);
	            btnReservationManager.setIcon(reservationManagerIcon);
	            btnCustomerManager.setIcon(customerManagerIcon);
	            btnStayManager.setIcon(stayManagerIcon);

	            break;
	        case "Customer Manager":
	            btnCustomerManager.setIcon(customerManagerIconA);
	            btnRoomManager.setIcon(roomManagerIcon);
	            btnOverview.setIcon(overviewIcon);
	            btnReservationManager.setIcon(reservationManagerIcon);
	            btnStayManager.setIcon(stayManagerIcon);
	            break;
	        case "Reservation Manager":
	            btnReservationManager.setIcon(reservationManagerIconA);
	            btnCustomerManager.setIcon(customerManagerIcon);
	            btnRoomManager.setIcon(roomManagerIcon);
	            btnOverview.setIcon(overviewIcon);
	            btnStayManager.setIcon(stayManagerIcon);
	            break;
	        case "Stay Manager":
	            btnStayManager.setIcon(stayManagerIconA);
	            btnCustomerManager.setIcon(customerManagerIcon);
	            btnRoomManager.setIcon(roomManagerIcon);
	            btnOverview.setIcon(overviewIcon);
	            btnReservationManager.setIcon(reservationManagerIcon);
	            break;
	        default:
	            btnStayManager.setIcon(stayManagerIcon);
	            btnCustomerManager.setIcon(customerManagerIcon);
	            btnRoomManager.setIcon(roomManagerIcon);
	            btnOverview.setIcon(overviewIcon);
	            btnReservationManager.setIcon(reservationManagerIcon);
	            break;
	    }
	}
	

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatArcIJTheme());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		Hotel hotel = new Hotel("Fanti", "28 rue Parisien");
		App frame = new App(hotel);
		frame.setMinimumSize(new Dimension(1024 ,800));
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
