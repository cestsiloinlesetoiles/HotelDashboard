package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.github.lgooddatepicker.components.DatePicker;

import controller.reservationP.ControllerAddReseravtions;
import controller.reservationP.ControllerDelReservations;
import model.Customer;
import model.DoubleRoom;
import model.Hotel;
import model.LuxuryRoom;
import model.PresidentialSuite;
import model.Reservation;
import model.Room;
import model.Simple;
import view.custom.EditReservationDialog;
import view.ui.RoundedPanel;
import view.ui.theme;


public class ReservationP extends JPanel implements Observer {
	public Hotel h;
	public JTable JTableReserv;
	public DefaultTableModel TableModelReserv;
	public JPanel pnlMain = new JPanel();
	public JPanel pnlPageLevel = new JPanel();


	public DatePicker startDate; 
	public DatePicker endDate; 
	public JLabel roomLabel;
	public JComboBox<String> roomComboBox;
	public JTextField firstName;
	public JTextField lastName; 

	public ReservationP(Hotel h) {
		this.h = h;
		
		
		// Mise en place du Pagelevel et du cadran Main
		

        setLayout(new BorderLayout());
        ImageIcon pgIco = new ImageIcon("resources/pagelevel/4.png");
        JLabel pg = new JLabel(pgIco);
        pg.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        pnlPageLevel.setLayout(new BoxLayout(pnlPageLevel, BoxLayout.Y_AXIS));
        pnlPageLevel.add(Box.createVerticalGlue());
        pnlPageLevel.add(pg);
        pnlPageLevel.add(Box.createVerticalGlue());
        add(pnlPageLevel, BorderLayout.EAST);

        add(pnlMain, BorderLayout.CENTER);
        pnlMain.setBackground(theme.SECONDARY_BACKGROUND);
        pnlPageLevel.setBackground(theme.SECONDARY_BACKGROUND);

        pnlMain.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        RoundedPanel pnlTopDiv = new RoundedPanel();
        pnlTopDiv.setBackground(theme.BACKGROUND_PANEL);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.30;
        gbc.insets = new Insets(15, 10, 5, 30);
        pnlMain.add(pnlTopDiv, gbc);

        RoundedPanel pnlJTable = new RoundedPanel();
        pnlJTable.setBackground(theme.BACKGROUND_PANEL);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.70;
        gbc.insets = new Insets(30, 10, 10, 30);
        pnlMain.add(pnlJTable, gbc);

        pnlTopDiv.setLayout(new GridBagLayout());

        String titleText = "<html>Reservation<br>Manager</html>";
        JLabel title = new JLabel(titleText);

        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        title.setForeground(theme.BACKGROUND);
        title.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));

        JPanel formPanel = new JPanel();
        
        formPanel.setBackground(theme.BACKGROUND_PANEL);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.20;
  
    
        pnlTopDiv.add(title, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
    
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.8;
        pnlTopDiv.add(formPanel, gbc);


		// [Emplacement Titre Page et du form] 


		// Title

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.20;
		pnlTopDiv.add(title, gbc);

		// Form
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.8;


		pnlTopDiv.add(formPanel, gbc);


	
		JLabel firstNameLabel = new JLabel("Prénom :");
		firstName = new JTextField(10);

		JLabel lastNameLabel = new JLabel("Nom :");
		 lastName = new JTextField(10);


		JLabel DateEndLabel = new JLabel("Début  :");
		JLabel DateStartLabel = new JLabel("Fin :");
	    JLabel InterTxt = new JLabel("au");
	    InterTxt.setHorizontalAlignment(SwingConstants.CENTER);
		startDate = new DatePicker();
		endDate = new DatePicker();
		
		roomLabel = new JLabel("Chambre :");
		roomComboBox = new JComboBox<>();
		
		JButton AddButton = new JButton("Ajouter");
		JButton SearchPeriod = new JButton("Recherche par Periode");
		
		formPanel.setLayout(new GridBagLayout());
		formPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);

		// Ligne 1: Titres
		c.gridx = 0;
		c.gridy = 0;
		formPanel.add(lastNameLabel, c);

		c.gridx = 1;
		formPanel.add(firstNameLabel, c);
		
		c.gridx = 2;
		formPanel.add(roomLabel, c);
		
		// Ligne 2: Champs texte
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.5;
		formPanel.add(lastName, c);

		c.gridx = 1;
		formPanel.add(firstName, c);

		c.gridx = 2;
		formPanel.add(roomComboBox, c);
		
		// Ligne 3: Champs texte
		
		c.gridx = 0;
		c.gridy = 2;
		formPanel.add(DateStartLabel, c);
		c.gridx = 2;
		formPanel.add(DateEndLabel, c);
		
		// Ligne 4: Champs texte
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0.5;
		formPanel.add(startDate, c);

		c.gridx = 1;
		formPanel.add(InterTxt, c);
		
		c.gridx = 2;
		formPanel.add(endDate, c);
	
		// Ligne 5: Boutons

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.weightx = 0.7;
		formPanel.add(AddButton, c);
		
		c.gridx = 2;
		
		
		c.weightx = 0.3;
		formPanel.add(SearchPeriod, c);

		formPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
		formPanel.setPreferredSize(new Dimension(200, 150));
        pnlJTable.setPreferredSize(new Dimension(870, 500));
		
        
        // Jtable 
		
        pnlJTable.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
		
        ImageIcon editIcon = new ImageIcon("resources/table/edit.png");
        ImageIcon editIconActive = new ImageIcon("resources/table/editA.png");
        JButton btnEdit = new JButton(editIcon);
        btnEdit.setPressedIcon(editIconActive);
        btnEdit.setContentAreaFilled(false);
        btnEdit.setBorderPainted(false);

        ImageIcon infoIcon = new ImageIcon("resources/table/info.png");
        ImageIcon infoIconActive = new ImageIcon("resources/table/infoA.png");
        JButton btnInfo = new JButton(infoIcon);
        btnInfo.setPressedIcon(infoIconActive);
        btnInfo.setContentAreaFilled(false);
        btnInfo.setBorderPainted(false);

        ImageIcon deleteIcon = new ImageIcon("resources/table/delete.png");
        ImageIcon deleteIconActive = new ImageIcon("resources/table/deleteA.png");
        JButton btnDelete = new JButton(deleteIcon);
        btnDelete.setPressedIcon(deleteIconActive);
        btnDelete.setContentAreaFilled(false);
        btnDelete.setBorderPainted(false);
        
        
        RoundedPanel buttonsPanel = new RoundedPanel();
        buttonsPanel.setBackground(theme.SECONDARY_BACKGROUND);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(btnEdit);
        buttonsPanel.add(btnInfo);
        buttonsPanel.add(btnDelete);

       
        pnlJTable.setLayout(new BorderLayout());
        pnlJTable.add(buttonsPanel, BorderLayout.NORTH);

        CreateTableModel();
        JTableReserv = new JTable(TableModelReserv);
        
        JScrollPane tableScrollPane = new JScrollPane(JTableReserv);
        tableScrollPane.setPreferredSize(new Dimension(870, 450));

        JTableHeader header = JTableReserv.getTableHeader();
        header.setForeground(Color.decode("#232323"));
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBorder(BorderFactory.createEmptyBorder());
        header.setBackground(null);
        JTableReserv.setShowGrid(false);

        pnlJTable.add(tableScrollPane, BorderLayout.CENTER);
        
        buttonsPanel.setBackground(theme.BACKGROUND_PANEL);
        ReservationP resP = this;

        // Controller o
        AddButton.addActionListener(new ControllerAddReseravtions(this));
        btnDelete.addActionListener(new ControllerDelReservations(this));
        btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = JTableReserv.getSelectedRow();
				if(selectedRow>-1) {
					int id = Integer.parseInt(JTableReserv.getValueAt(selectedRow, 0).toString());
					new EditReservationDialog(h.getReservationById(id), resP);
					
				}
				else{
					JOptionPane.showMessageDialog(resP, "Aucune ligne sélectionnée.", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});

        SearchPeriod.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 Vector<Room> freeRooms = new Vector<Room>();
				 
				 if(startDate.getDate() == null || endDate.getDate() == null || endDate.getDate().isBefore(startDate.getDate())) {
						JOptionPane.showMessageDialog(resP, "Veuillez spécifier une date de début et une date de fin valide", "Erreur de réservation", JOptionPane.ERROR_MESSAGE);
						return;
				 }

				 
				 
				 for(Room room : h.rooms) {
					 if(room.CheckisFree(startDate.getDate(), endDate.getDate())) {
						 freeRooms.add(room);
					 }
				 }
				 
				 
				 if (freeRooms.isEmpty()) {
			            JOptionPane.showMessageDialog(resP, "Aucune chambre n'est disponible pendant la période sélectionnée.", "Information", JOptionPane.INFORMATION_MESSAGE);
			            return;
				 }
				 
				 JPanel pnl = new JPanel();
				 pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
				 
				 
				 for (Room room : freeRooms) {
		                pnl.add(new JLabel(room.getFloor() + " - " + room.getNum() + " - " + room.getType()));
		         }
				 
				 JScrollPane scrollFrame = new JScrollPane(pnl);
		         scrollFrame.setPreferredSize(new Dimension(300, 200));
				
		         JOptionPane.showMessageDialog(resP, scrollFrame, "Chambres disponibles", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
        
        
	}
	

		

	   public void CreateTableModel() {

	    	String[] columnNames = {"N°","Date de debut","Date de fin","Type de Chambre","Client","Status"}
	    	;

			Object[][] data = new Object[h.reservations.size()][6];

			for (int i = 0; i < h.reservations.size(); i++) {
				Reservation res = h.reservations.get(i);

				data[i][0] = Integer.toString(res.getId());
				data[i][1] = res.getDateStart().toString();
				data[i][2] = res.getDateEnd().toString();
				
				Room room = res.getRoom();
				
				if (room instanceof Simple) {
					data[i][3] = "Simple";
				} else if (room instanceof DoubleRoom) {
					data[i][3] = "Double";
				} else if (room instanceof LuxuryRoom) {
					data[i][3] = "Luxury";
				} else if (room instanceof PresidentialSuite) {
					data[i][3] = "Presidential";
				} else {
					data[i][3] = "N/A";
				}
				
				Customer c = res.getCustomer();
				if(c==null){
					data[i][4] = "N/A";
				}
				else {
					data[i][4] = c.getFirstName()+" "+ c.getLastName();
					
				}
				LocalDate currentDate = LocalDate.now();
				

		 
		        String status;

		        if (currentDate.isEqual(res.getDateStart())) {
		            status = "Check-in";
		        } else if (currentDate.isEqual(res.getDateEnd())) {
		            status = "Check-out";
		        } else if (currentDate.isAfter(res.getDateStart()) && currentDate.isBefore(res.getDateEnd())) {
		            status = "En cours";
		        } else if (currentDate.isBefore(res.getDateStart())) {
		            status = "Réservé";
		        } else {
		            status = "Outdated";
		        }

		        data[i][5] = status;
			}
		
			TableModelReserv = new DefaultTableModel(data, columnNames);
			
		}
	   
	   
	   
		public void updateTableModel() {
			CreateTableModel();
			JTableReserv.setModel(TableModelReserv);
			TableModelReserv.fireTableDataChanged();
		}
		
	   
	   
	   
	   public void FillRoomComboBox() {
		   roomComboBox.removeAllItems();
		   for(Room room : h.rooms) {
			   roomComboBox.addItem(room.getFloor() + " - " +room.getNum()+ " - " + room.getType());
			   
		   }
		   roomComboBox.setSelectedItem(null);
	   }

	@Override
	public void update(Observable o, Object arg) {
		FillRoomComboBox();
		
	}
	   
	   
	 
}
