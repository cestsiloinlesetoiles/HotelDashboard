package view.custom;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.roomP.ControllerAddMultiRooms;
import model.Hotel;
import view.RoomP;

public class MultipleAddingDialog extends JDialog {
	public  JTextField txtNumberOfRooms;
    public  JTextField txtStartingFloor;
    public JTextField txtStartingRoomNumber;
    public JComboBox<String> cbRoomType;
    public JTextField txtCapacityPerFloor;
    public Hotel h;
    private RoomP r; 

    public MultipleAddingDialog(RoomP r) {
    	this.h = r.h;
    	this.r = r;
        // Structure  classique : 
    	setTitle("Ajout de chambres multiples");
        setModal(true);
        setLayout(new GridBagLayout());
        setLocationRelativeTo((JFrame) SwingUtilities.getWindowAncestor(r));
        setSize(400, 300);
        
        
        JPanel contentPanel = new JPanel(new GridBagLayout());

        JLabel lblNumberOfRooms = new JLabel("Nombre de chambres:");
        txtNumberOfRooms = new JTextField(5);

        JLabel lblStartingFloor = new JLabel("Étage de départ:");
        txtStartingFloor = new JTextField(5);

        JLabel lblStartingRoomNumber = new JLabel("Numéro de chambre de départ:");
        txtStartingRoomNumber = new JTextField(5);

        JLabel lblRoomType = new JLabel("Type de chambre:");
        String[] comboBoxItems = {"Simple", "Double", "Luxury", "Presidential"};
        cbRoomType = new JComboBox<>(comboBoxItems);

        JLabel lblCapacityPerFloor = new JLabel("Capacité par étage:");
        txtCapacityPerFloor = new JTextField(5);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(lblNumberOfRooms, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPanel.add(txtNumberOfRooms, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(lblStartingFloor, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPanel.add(txtStartingFloor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(lblStartingRoomNumber, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPanel.add(txtStartingRoomNumber, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPanel.add(lblRoomType, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPanel.add(cbRoomType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPanel.add(lblCapacityPerFloor, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        contentPanel.add(txtCapacityPerFloor, gbc);

        JButton btnSave = new JButton("Enregistrer");
        
        btnSave.addActionListener(new ControllerAddMultiRooms(r,this));
        JButton btnCancel = new JButton("Annuler");

        gbc.gridx = 0;
        gbc.gridy = 5;
        contentPanel.add(btnSave, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        contentPanel.add(btnCancel, gbc);

        add(contentPanel);
        
        GridBagConstraints gbcContent = new GridBagConstraints();
        gbcContent.gridx = 0;
        gbcContent.gridy = 0;
        gbcContent.gridwidth = 2;
        gbcContent.gridheight = 1;
        gbcContent.fill = GridBagConstraints.BOTH;
        gbcContent.anchor = GridBagConstraints.CENTER;
        gbcContent.weightx = 1;
        gbcContent.weighty = 1;
        getContentPane().add(contentPanel, gbcContent);
        this.setVisible(true);
    }
}
