package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import controller.Room.AddOptionController;
import controller.Room.AddingRoomController;
import controller.Room.DeleteRoomController;
import controller.Room.OptionModifyController;
import controller.Room.RomfieldCheckController;

import model.DoubleRoom;
import model.Hotel;
import model.LuxaryRoom;
import model.Options;
import model.PresidentialSuite;
import model.Room;
import model.Simple;
import view.Dialogs.MultipleAddingDialog;

public class RoomP extends JPanel {
	public Hotel hotel;
	public JTable tableRoom;
	public DefaultTableModel tableModelRoom;
	public JPanel pnlTable = new JPanel();
	public JPanel pnlTopBoard = new JPanel();
	public JPanel pnlOptions = new JPanel();
	public JLabel lblError;
	public Vector<Options> optionsVector = new Vector<>();
	public JTable tableOptions;
	public DefaultTableModel tableModelOptions;
	
	public RoomP(Hotel h) {
		setBackground(new Color(255, 17, 17));
		setLayout(new GridBagLayout());
		
		 // Ajout des options classique
		Options breakfastOption = new Options("Petit déjeuner", 10);
	    Options dailyCleaningOption = new Options("Nettoyage quotidien", 20);
	    Options roomServiceOption = new Options("Service de chambre", 15);
	    Options wifiOption = new Options("Accès Wi-Fi", 5);

	    optionsVector.add(breakfastOption);
	    optionsVector.add(dailyCleaningOption);
	    optionsVector.add(roomServiceOption);
	    optionsVector.add(wifiOption);
		
		
		// top board panel
		pnlTopBoard.setLayout(new GridBagLayout());
		pnlTopBoard.setBackground(new Color(240, 240, 240));
		Border topBorder = BorderFactory.createTitledBorder("Ajout Unitaires");
		pnlTopBoard.setBorder(topBorder);

		JLabel lblFloor = new JLabel("Étage:");
		JLabel lblRoomNumber = new JLabel("Numéro de chambre:");
		JTextField txtRoomNumber = new JTextField(5);
		JLabel lblRoomType = new JLabel("Type de chambre:");
		String[] comboBoxItems = {"Simple", "Double", "Luxury", "Presidential"};
		JComboBox<String> cbRoomType = new JComboBox<>(comboBoxItems);
		JTextField txtFloor = new JTextField(5);
		
		

		JButton btnAddRoom = new JButton("Ajouter Chambre");
		AddingRoomController addR = new AddingRoomController(txtFloor, txtRoomNumber, cbRoomType,h,  this);
		btnAddRoom.addActionListener(addR);
		RoomP r = this;
		JButton btnMultipleAdding = new JButton("Ajouts Multiple");
        btnMultipleAdding.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MultipleAddingDialog(h, r);
			}
		});
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		pnlTopBoard.add(lblFloor, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		pnlTopBoard.add(txtFloor, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		pnlTopBoard.add(lblRoomNumber, gbc);

		gbc.gridx = 3;
		gbc.gridy = 0;
		pnlTopBoard.add(txtRoomNumber, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		pnlTopBoard.add(lblRoomType, gbc);

		gbc.gridx = 1; gbc.gridy = 1; pnlTopBoard.add(cbRoomType, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		pnlTopBoard.add(btnAddRoom, gbc);

		gbc.gridx = 3;
		gbc.gridy = 1;
		pnlTopBoard.add(btnMultipleAdding, gbc);

	
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		RomfieldCheckController rfc = new RomfieldCheckController(txtFloor, txtRoomNumber, lblError , h);
        txtFloor.addFocusListener(rfc);
        txtRoomNumber.addFocusListener(rfc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		pnlTopBoard.add(lblError, gbc);

		// options panel
		// options panel
		pnlOptions.setLayout(new GridBagLayout());
		pnlOptions.setBackground(new Color(240, 240, 240));
		Border optionsBorder = BorderFactory.createTitledBorder("Options");
		pnlOptions.setBorder(optionsBorder);

		JTextField txtOptionName;
		JTextField txtOptionPrice;
		JButton btnAddOption;
		JButton btnRemoveOption; // Nouveau bouton

		JLabel lblOptionName = new JLabel("Nom de l'option:");
		txtOptionName = new JTextField(10);
		JLabel lblOptionPrice = new JLabel("Prix de l'option:");
		txtOptionPrice = new JTextField(10);
		btnAddOption = new JButton("Ajouter Option");

		btnAddOption.addActionListener(new AddOptionController(txtOptionName, txtOptionPrice, this));
		
		btnRemoveOption = new JButton("Supprimer Option");
		btnRemoveOption.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableOptions.getSelectedRow();
		        if (selectedRow != -1) {
		            optionsVector.remove(selectedRow);
		            updateTableModelOptions(optionsVector);
		            // Console Check
		            System.out.println("**************");
		            for(int i = 0; i<optionsVector.size();i++) {
		            	Options options = optionsVector.get(i);
		            	System.out.println("| "+options.getName()+" |");
		            	
		            }
		        }
		    }
		});
		
		JButton btnEditOption = new JButton("Modifier Option");
		btnEditOption.addActionListener(new OptionModifyController(txtOptionName, txtOptionPrice, r));
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		pnlOptions.add(btnEditOption, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		pnlOptions.add(lblOptionName, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		pnlOptions.add(txtOptionName, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		pnlOptions.add(lblOptionPrice, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		pnlOptions.add(txtOptionPrice, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		pnlOptions.add(btnAddOption, gbc);

		// Ajoutez le nouveau bouton
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		pnlOptions.add(btnRemoveOption, gbc);

		// Ajout de la JTable pour les options
		tableModelOptions = CreateTableModelOptions(optionsVector);
		tableOptions = new JTable(tableModelOptions);
		JScrollPane scrollPaneOptions = new JScrollPane(tableOptions);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		pnlOptions.add(scrollPaneOptions, gbc);
		// table panel
		pnlTable.setLayout(new GridBagLayout());
		pnlTable.setBackground(new Color(240, 240, 240));
		Border tableBorder = BorderFactory.createTitledBorder("Liste des chambres");
		pnlTable.setBorder(tableBorder);

		tableModelRoom = CreateTableModelRoom(h);
		tableRoom = new JTable(tableModelRoom);
		JScrollPane scrollPane = new JScrollPane(tableRoom);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2; 
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1; 
		pnlTable.add(scrollPane, gbc);

		JButton btnEditRoom = new JButton("Modifier");
		JButton btnDeleteRoom = new JButton("Supprimer");
		
		DeleteRoomController dRoomC = new DeleteRoomController(h, tableRoom, r);
		btnDeleteRoom.addActionListener(dRoomC);
		

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 0;
		pnlTable.add(btnEditRoom, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		pnlTable.add(btnDeleteRoom, gbc);


		// main panel layout
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0.2;
		add(pnlTopBoard, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.2;
		gbc.weighty = 0.6;
		add(pnlTable, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.2;
		gbc.weighty = 0.6;
		add(pnlOptions, gbc);
	}

	 public void updateTableModel(Hotel hotel) {
	        tableModelRoom = CreateTableModelRoom(hotel);
	        tableRoom.setModel(tableModelRoom);
	        tableModelRoom.fireTableDataChanged();
	    }
	
	public DefaultTableModel CreateTableModelRoom(Hotel hotel) {
		String[] columnNames = {"Etage", "Numero", "Type", "Options"};

		Object[][] data = new Object[hotel.rooms.size()][4];

		for (int i = 0; i < hotel.rooms.size(); i++) {
			Room room = hotel.rooms.get(i);

			data[i][0] = room.getFloor(); 
					
			data[i][1] = room.getNum();

			if (room instanceof Simple) {
				data[i][2] = "Simple";
			} else if (room instanceof DoubleRoom) {
				data[i][2] = "Double";
			} else if (room instanceof LuxaryRoom) {
				data[i][2] = "Luxury";
			} else if (room instanceof PresidentialSuite) {
				data[i][2] = "Presidential";
			} else {
				data[i][2] = "Erreur";
			}

			if (room instanceof DoubleRoom || room instanceof Simple) {
				data[i][3] = "Aucune option disponible sur chambre";
			}
		}
		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
		return tableModel;
	}
	
	public DefaultTableModel CreateTableModelOptions(Vector<Options> optionsVector) {
		String[] columnNames = {"Nom de l'option", "Prix de l'option"};

		Object[][] data = new Object[optionsVector.size()][2];

		for (int i = 0; i < optionsVector.size(); i++) {
			Options options = optionsVector.get(i);

			data[i][0] = options.getName(); 
					
			data[i][1] = options.getCost();
		}
		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
		return tableModel;
	}
	
	 public void updateTableModelOptions(Vector<Options> optionsVector) {
	        tableModelOptions = CreateTableModelOptions(optionsVector);
	        tableOptions.setModel(tableModelOptions);
	        tableModelOptions.fireTableDataChanged();
	    } 
}    
