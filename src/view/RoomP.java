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
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import controller.roomP.ControllerAddingOptions;
import controller.roomP.ControllerAddingSingleRoom;
import controller.roomP.ControllerDeletingRoomP;
import model.DoubleRoom;
import model.Hotel;
import model.LuxuryRoom;
import model.Options;
import model.PresidentialSuite;
import model.Room;
import model.Simple;
import view.custom.EditOptionsDialog;
import view.custom.EditRoomDialog;
import view.custom.MultipleAddingDialog;
import view.ui.RoundedPanel;
import view.ui.theme;

public class RoomP extends JPanel {
	public Hotel h;
	public JTable JTableRoom;
	public DefaultTableModel TableModelRoom;
	public JPanel pnlMain = new JPanel();
	public JPanel pnlPageLevel = new JPanel();
	public JComboBox<String> cbRoomType;
	public Vector<Options> optionsVector = new Vector<>();
	public JTable JTableOptions;
	public DefaultTableModel TableModelOptions;



	public JLabel lblFloor;
	public JLabel lblRoomNumber;
	public JTextField txtRoomNumber;
	public JLabel lblRoomType;
	public JTextField txtFloor;
	public JButton btnAddRoom;
	public JButton btnMultipleAdding;




	public JTextField optionNameTextField;
	public JTextField optionPriceTextField;


	public RoomP(Hotel h, StayP stay) {
		this.h = h;

		// Configuration v2 de la structure de la page CustomerP ( lègrement différente de la v1 avec un panel en plus droite)
		setLayout(new BorderLayout());

		// Configuration du panel pnlPageLevel
		ImageIcon pgIco = new ImageIcon(getClass().getResource("/resources/pagelevel/2.png"));
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
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.30;
		gbc.insets = new Insets(15, 10, 5, 30);
		pnlMain.add(pnlTopDiv, gbc);

		RoundedPanel pnlJTable = new RoundedPanel();
		pnlJTable.setLayout(new BorderLayout());
		pnlJTable.setBackground(theme.BACKGROUND_PANEL);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.7; 
		gbc.weighty = 0.7;
		gbc.insets = new Insets(30, 10, 10, 10);
		pnlMain.add(pnlJTable, gbc);

		RoundedPanel pnlSide = new RoundedPanel();
		pnlSide.setBackground(theme.BACKGROUND_PANEL);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.3;
		gbc.weighty = 0.7;
		gbc.insets = new Insets(30, 0, 10, 30);
		pnlMain.add(pnlSide, gbc);



		//JTable pour les options
		// Ajout des options par défaut pour tester
		Options breakfastOption = new Options("Petit déjeuner", 10);
		Options dailyCleaningOption = new Options("Nettoyage quotidien", 20);
		Options roomServiceOption = new Options("Service de chambre", 15);
		Options wifiOption = new Options("Accès Wi-Fi", 5);
		optionsVector.add(breakfastOption);
		optionsVector.add(dailyCleaningOption);
		optionsVector.add(roomServiceOption);
		optionsVector.add(wifiOption);



		// Configuration du panel pnlTopDiv
		pnlTopDiv.setLayout(new GridBagLayout());

		String titleText = "<html>Rooms<br>Manager</html>";
		JLabel title = new JLabel(titleText);

		title.setFont(new Font("SansSerif", Font.BOLD, 36));
		title.setForeground(theme.BACKGROUND);
		title.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));

		JPanel formPanel = new JPanel();
		formPanel.setBackground(theme.BACKGROUND_PANEL);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.20;
		pnlTopDiv.add(title, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.8;
		pnlTopDiv.add(formPanel, gbc);

		JPanel subpanelJTables = new JPanel();
		subpanelJTables.setLayout(new BorderLayout());



		ImageIcon editIcon = new ImageIcon(getClass().getResource("/resources/table/edit.png"));
		ImageIcon editIconActive = new ImageIcon(getClass().getResource("/resources/table/editA.png"));
		JButton btnEdit = new JButton(editIcon);
		btnEdit.setPressedIcon(editIconActive);
		btnEdit.setContentAreaFilled(false);
		btnEdit.setBorderPainted(false);

		// NON IMPLEMENTE FAUTE DE TEMPS
		ImageIcon infoIcon = new ImageIcon(getClass().getResource("/resources/table/info.png"));
		ImageIcon infoIconActive = new ImageIcon(getClass().getResource("/resources/table/infoA.png"));
		JButton btnInfo = new JButton(infoIcon);
		btnInfo.setPressedIcon(infoIconActive);
		btnInfo.setContentAreaFilled(false);
		btnInfo.setBorderPainted(false);

		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/resources/table/delete.png"));
		ImageIcon deleteIconActive = new ImageIcon(getClass().getResource("/resources/table/deleteA.png"));
		JButton btnDelete = new JButton(deleteIcon);
		btnDelete.setPressedIcon(deleteIconActive);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setActionCommand("Room");

		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// Recherche feature surpimée faute de temps
		//JLabel searchLabel = new JLabel("Recherche:");
		//JTextField searchTextField = new JTextField(15);
		//searchPanel.add(searchLabel);
		//searchPanel.add(searchTextField);


		// jtable pour les chambres meme partern que pour les customers
		RoundedPanel buttonsPanel = new RoundedPanel();
		buttonsPanel.setBackground(theme.SECONDARY_BACKGROUND);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(btnEdit);
		buttonsPanel.add(btnInfo);
		buttonsPanel.add(btnDelete);

		JPanel searchAndButtonsPanel = new JPanel(new BorderLayout());
		searchAndButtonsPanel.add(searchPanel, BorderLayout.WEST);
		searchAndButtonsPanel.add(buttonsPanel, BorderLayout.EAST);

		CreateTableModel();
		JTableRoom = new JTable(TableModelRoom);
		
		JScrollPane tableScrollPane = new JScrollPane(JTableRoom);
		tableScrollPane.setPreferredSize(new Dimension(400, 300));
		

		
		JTableHeader header = JTableRoom.getTableHeader();
		header.setBackground(Color.decode("#232323"));
		header.setForeground(Color.WHITE);
		header.setFont(new Font("SansSerif", Font.BOLD, 14));
		header.setOpaque(false);
		header.setBorder(null);

		JTableRoom.setShowGrid(false);

		pnlJTable.add(searchAndButtonsPanel, BorderLayout.NORTH);
		pnlJTable.add(tableScrollPane, BorderLayout.CENTER);
		searchPanel.setBackground(theme.BACKGROUND_PANEL);
		buttonsPanel.setBackground(theme.BACKGROUND_PANEL);
		searchAndButtonsPanel.setBackground(theme.BACKGROUND_PANEL);
		pnlJTable.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));





		// Configuration du panel pnlSide et optionspanel

		pnlSide.setBackground(theme.BACKGROUND_PANEL);
		pnlSide.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));

		JLabel optionNameLabel = new JLabel("Options Nom :");
		optionNameTextField = new JTextField(15);

		JLabel optionPriceLabel = new JLabel("Options Prix :");
		optionPriceTextField = new JTextField(15);
		// JTable pour les options creation de la table en +
		CreateTableModelOptions();
		JTableOptions = new JTable(TableModelOptions);

		JScrollPane tableScrollPaneOptions = new JScrollPane(JTableOptions);
		tableScrollPaneOptions.setPreferredSize(new Dimension(300, 400));

		JTableHeader headerOptions = JTableOptions.getTableHeader();
		headerOptions.setBackground(Color.decode("#232323"));
		headerOptions.setForeground(Color.WHITE);
		headerOptions.setFont(new Font("SansSerif", Font.BOLD, 14));
		headerOptions.setOpaque(false);
		headerOptions.setBorder(null);
		JTableOptions.setShowGrid(false);

		RoundedPanel optionsPanel = new RoundedPanel();
		optionsPanel.setLayout(new GridBagLayout());
		optionsPanel.setBackground(theme.BACKGROUND_PANEL);
		JButton btnAddOption = new JButton("Ajouter");
		GridBagConstraints gbcOptions = new GridBagConstraints();

		gbcOptions.fill = GridBagConstraints.HORIZONTAL;
		gbcOptions.insets = new Insets(2, 2, 2, 2);

		gbcOptions.gridx = 0;
		gbcOptions.gridy = 0;
		optionsPanel.add(optionNameLabel, gbcOptions);

		gbcOptions.gridx = 1;
		gbcOptions.gridy = 0;

		optionsPanel.add(optionNameTextField, gbcOptions);

		gbcOptions.gridx = 0;
		gbcOptions.gridy = 1;


		optionsPanel.add(optionPriceLabel, gbcOptions);

		gbcOptions.gridx = 1;
		gbcOptions.gridy = 1;
		gbcOptions.weightx = 1.0;
		optionsPanel.add(optionPriceTextField, gbcOptions);

		gbcOptions.gridx = 0;

		gbcOptions.gridy = 2;
		gbcOptions.gridwidth = 2; 

		optionsPanel.add(btnAddOption, gbcOptions);



		// Ajout des boutons edit et delete pour les options
		RoundedPanel buttonsPanel2 = new RoundedPanel();
		buttonsPanel2.setBackground(theme.BACKGROUND_PANEL);
		buttonsPanel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton btnEdit2 = new JButton(editIcon);
		btnEdit2.setPressedIcon(editIconActive);
		btnEdit2.setContentAreaFilled(false);
		btnEdit2.setBorderPainted(false);
		buttonsPanel2.add(btnEdit2);

		JButton btnDelete2 = new JButton(deleteIcon);
		btnDelete2.setPressedIcon(deleteIconActive);
		btnDelete2.setContentAreaFilled(false);
		btnDelete2.setBorderPainted(false);
		buttonsPanel2.add(btnDelete2);


		JPanel ButtonAndTableOptions = new JPanel(new BorderLayout());

		ButtonAndTableOptions.add(buttonsPanel2,BorderLayout.NORTH);
		ButtonAndTableOptions.add(tableScrollPaneOptions,BorderLayout.CENTER);

		pnlSide.setLayout(new BorderLayout());
		pnlSide.add(optionsPanel,BorderLayout.SOUTH);
		pnlSide.add(ButtonAndTableOptions,BorderLayout.CENTER);



		// Formulair pour ajouter une chambre

		lblFloor = new JLabel("Étage:");
		lblRoomNumber = new JLabel("Numéro de chambre:");
		txtRoomNumber = new JTextField(5);
		lblRoomType = new JLabel("Type de chambre:");
		String[] comboBoxItems = {"Simple", "Double", "Luxury", "Presidential"};
		cbRoomType = new JComboBox<>(comboBoxItems);
		txtFloor = new JTextField(5);
		btnAddRoom = new JButton("Ajouter");

		JButton btnMultipleAdding = new JButton("Ajouts par lots");

		txtFloor.setPreferredSize(new Dimension(80, 20));
		txtRoomNumber.setPreferredSize(new Dimension(80, 20));
		formPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbcForm = new GridBagConstraints();
		gbcForm.insets = new Insets(2, 2, 2, 2);
		// Ligne 1: Titres
		gbcForm.gridx = 0;
		gbcForm.gridy = 0;
		formPanel.add(lblFloor, gbcForm);

		gbcForm.gridx = 1;
		formPanel.add(lblRoomNumber, gbcForm);

		gbcForm.gridx = 2;
		formPanel.add(lblRoomType, gbcForm);

		// Ligne 2: Champs texte et JComboBox
		gbcForm.fill = GridBagConstraints.HORIZONTAL;
		gbcForm.gridx = 0;
		gbcForm.gridy = 1;
		gbcForm.weightx = 0.4; 
		formPanel.add(txtFloor, gbcForm);

		gbcForm.gridx = 1;
		gbcForm.weightx = 0.4; 
		formPanel.add(txtRoomNumber, gbcForm);

		gbcForm.gridx = 2;
		gbcForm.weightx = 0.2; 
		formPanel.add(cbRoomType, gbcForm);

		// Ligne 3: Boutons
		gbcForm.gridx = 0;
		gbcForm.gridy = 2;
		gbcForm.gridwidth = 2;
		gbcForm.weightx = 0.8;
		formPanel.add(btnAddRoom, gbcForm);


		gbcForm.gridx = 2;
		gbcForm.weightx = 0.2;
		formPanel.add(btnMultipleAdding, gbcForm);



		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0,0));
		formPanel.setPreferredSize(new Dimension(200,100));




		// Configuration des boutons avec les controllers : 
		// this stocke la référence de la classe RoomP dans une variable locale pour pouvoir l'utiliser dans les controllers
		RoomP r = this;

		btnAddRoom.addActionListener(new ControllerAddingSingleRoom(this));
		btnAddOption.addActionListener( new ControllerAddingOptions(this));

		btnMultipleAdding.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MultipleAddingDialog(r);

			}
		});

		btnDelete.addActionListener(new ControllerDeletingRoomP(this));
		btnDelete2.addActionListener(new ControllerDeletingRoomP(this));
		
		
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selectedRow = JTableRoom.getSelectedRow();
				if(selectedRow>-1) {
				int floor = (int) r.JTableRoom.getValueAt(selectedRow, 0);
				int num =  (int) r.JTableRoom.getValueAt(selectedRow, 1);
				// On prend la ligne sélectionnée dans la JTable et on récupère l'étage et le numéro de chambre pour choper la ref de la chambre
				Room room = r.h.getRoom(floor, num);
				new EditRoomDialog(room, r);
				stay.updateTableModel();
				}
				else {
					JOptionPane.showMessageDialog(r, "Aucune ligne sélectionnée dans la liste des chambres.", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		// Bouton edit pour les options meme principe que pour les chambres
		btnEdit2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = JTableOptions.getSelectedRow();
				if(selectedRow>-1) {
					String name =   (String) r.JTableOptions.getValueAt(selectedRow, 0);
					Options opt = getOptionByName(name);
					new EditOptionsDialog(r, opt);
					
				}
				else {
					JOptionPane.showMessageDialog(r, "Aucune ligne sélectionnée dans les options.", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
			
		
		
		

	
	} 


	
	// Meme principe que pour customerP
	public void CreateTableModel() {

		String[] columnNames = {"Etage", "Numero", "Type"};

		Object[][] data = new Object[h.rooms.size()][4];

		for (int i = 0; i < h.rooms.size(); i++) {
			Room room = h.rooms.get(i);

			data[i][0] = room.getFloor(); 

			data[i][1] = room.getNum();

			if (room instanceof Simple) {
				data[i][2] = "Simple";
			} else if (room instanceof DoubleRoom) {
				data[i][2] = "Double";
			} else if (room instanceof LuxuryRoom) {
				data[i][2] = "Luxury";
			} else if (room instanceof PresidentialSuite) {
				data[i][2] = "Presidential";
			} else {
				data[i][2] = "Erreur";
			}
		}

		TableModelRoom = new DefaultTableModel(data, columnNames){
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };
		

	}



	public void updateTableModel() {
		CreateTableModel();
		JTableRoom.setModel(TableModelRoom);
		TableModelRoom.fireTableDataChanged();
	}


	public void CreateTableModelOptions() {
		String[] columnNames = {"Nom de l'option", "Prix de l'option"};

		Object[][] data = new Object[optionsVector.size()][2];

		for (int i = 0; i < optionsVector.size(); i++) {
			Options options = optionsVector.get(i);

			data[i][0] = options.getName(); 

			data[i][1] = options.getCost();
		}
		
		
		TableModelOptions = new DefaultTableModel(data, columnNames){
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };
		;
		
	}

	public void updateTableModelOptions() {
		CreateTableModelOptions();
		JTableOptions.setModel(TableModelOptions);
		TableModelOptions.fireTableDataChanged();
	} 



	// Fonction pour remove une option car le tableau d'options possible presente dans la vue et pas dans le modele. 
	public void removeOption(String name) {
		for (int i = 0; i < optionsVector.size(); i++) {
			if (optionsVector.get(i).getName().equals(name)) {
				optionsVector.remove(i);
				h.removeOptionFromRooms(name);
				updateTableModelOptions(); 
				break; 
			}
		
	
		}
	}





	public Options getOptionByName(String name) {
		for (Options option : optionsVector) {
			if (option.getName().equals(name)) {
				return option;
			}
		}
		return null;
	}





}
