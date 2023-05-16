package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;

import controller.PageController;
import model.Hotel;
import view.custom.SettingDiag;
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

	//Ajout des icones pour les boutons du menu getRessource permet de recuperer les images dans le dossier resources issue avec l'excutable. 
	ImageIcon overviewIconA = new ImageIcon(getClass().getResource("/resources/overviewIconA.png"));
	ImageIcon overviewIcon = new ImageIcon(getClass().getResource("/resources/overviewIcon.png"));

	ImageIcon roomManagerIconA = new ImageIcon(getClass().getResource("/resources/roomManagerIconA.png"));
	ImageIcon roomManagerIcon = new ImageIcon(getClass().getResource("/resources/roomManagerIcon.png"));

	ImageIcon customerManagerIconA = new ImageIcon(getClass().getResource("/resources/customerManagerIconA.png"));
	ImageIcon customerManagerIcon = new ImageIcon(getClass().getResource("/resources/customerManagerIcon.png"));

	ImageIcon reservationManagerIconA = new ImageIcon(getClass().getResource("/resources/reservationManagerIconA.png"));
	ImageIcon reservationManagerIcon = new ImageIcon(getClass().getResource("/resources/reservationManagerIcon.png"));

	ImageIcon stayManagerIconA = new ImageIcon(getClass().getResource("/resources/stayManagerIconA.png"));
	ImageIcon stayManagerIcon = new ImageIcon(getClass().getResource("/resources/stayManagerIcon.png"));


	
	
	public App(Hotel hotel) {
		this.hotel = hotel;
		setBackground(theme.BACKGROUND);

		//Different panel pour chaque onglet du menu
		
		Overview = new OverviewP(hotel);
		CustomerManager = new CustomerP(hotel);
		StayManager = new StayP(hotel);
		ReservationManager = new ReservationP(hotel,StayManager);
		RoomManager = new RoomP(hotel,StayManager);
		hotel.addObserver(ReservationManager);
		
		// Creation de l'emplacemnt acceuil du des onglets
		MultiScreen = new JPanel();
		MultiScreen.setLayout(CdL);
		
		// Ajout des onglets dans le panel avec une clé pour les identifier
		
		MultiScreen.add(Overview,"Overview");
		MultiScreen.add(RoomManager,"Room Manager");
		MultiScreen.add(CustomerManager,"Customer Manager");
		MultiScreen.add(ReservationManager,"Reservation Manager");
		MultiScreen.add(StayManager,"Stay Manager");
		


		// Creation du menu lateral
		Menu = new JPanel();
		Menu.setLayout(new BoxLayout(Menu, BoxLayout.PAGE_AXIS));
		
		
		
		
		// Creation des boutons du menu et ajout d'une action pour chaque bouton enfin la clé pour identifier l'onglet
		
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
		
		
		// Ajout des boutons dans un tableau pour pouvoir les modifier plus facilement
		GroupButtons[0] = btnOverview;
        GroupButtons[1] = btnRoomManager;
        GroupButtons[2] = btnCustomerManager;
        GroupButtons[3] = btnReservationManager;
        GroupButtons[4] = btnStayManager;
        
        
        Color buttonBackground = theme.BACKGROUND;
        
        // Creation du bouton settings 
		JButton settings = new JButton();
		ImageIcon inactiveIcon = new ImageIcon(getClass().getResource("/resources/setting.png"));
		settings.setIcon(inactiveIcon);
		ImageIcon activeIcon = new ImageIcon(getClass().getResource("/resources/settingA.png"));
		settings.setPressedIcon(activeIcon);
		GroupButtons[5] = settings;
		

		// Modification des boutons du menu
        for (JButton button : GroupButtons) {
			//  Modification du style des boutons, couleur, taille, bordure, alignement
            button.setBackground(buttonBackground);
            button.setFocusPainted(false);
            button.setSize(new Dimension(25,25));
            button.setAlignmentX(CENTER_ALIGNMENT); 
			button.setBorderPainted(false);
            
        }

		
		// Ajout du page controller pour les boutons du menu qui permet de changer d'onglet
		// On lui passe le layout du panel qui contient les onglet, 
		// Et la classe app pour pouvoir modifier le bouton l'aspect du bouton actif et inactif avec la methode setStatusIcon
		PageController pgc = new PageController(CdL, MultiScreen,this);
		
		btnOverview.addActionListener(pgc);
		btnRoomManager.addActionListener(pgc);
		btnCustomerManager.addActionListener(pgc);
		btnReservationManager.addActionListener(pgc);
		btnStayManager.addActionListener(pgc);
		
		
	
		
		
	
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
		Menu.add(settings);

        
		
		
		
		
		getContentPane().setLayout(new BorderLayout());
	
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(MultiScreen, BorderLayout.CENTER);
		getContentPane().add(Menu, BorderLayout.WEST);
		App app = this;
		
		settings.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SettingDiag((JFrame) SwingUtilities.getWindowAncestor(app), hotel);
				
			}
		});
			
		
		
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
		
		Hotel hotel = new Hotel("N/A", "N/A");
		App frame = new App(hotel);
		frame.setTitle("HotelCopilot.io");
	    frame.setStatusIcon("Overview");
		frame.setMinimumSize(new Dimension(1024 ,800));
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
