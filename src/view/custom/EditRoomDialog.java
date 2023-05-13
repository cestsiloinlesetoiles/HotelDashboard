package view.custom;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import model.LuxuryRoom;
import model.Options;
import model.PresidentialSuite;
import model.Room;
import view.RoomP;

public class EditRoomDialog extends JDialog {
    public Room room;
    public RoomP r;
    public JTextField txtFloor;
    public JTextField txtRoomNumber;
    public Vector<JCheckBox> checkBoxes = new Vector<JCheckBox>() ;
    public Vector<String> selectedNames = new Vector<String>();
    
    public EditRoomDialog(Room room, RoomP r) {
    	setTitle("Modifier Chambre");
        setModal(true);
        setLocationRelativeTo(r);
    	this.room = room;
        this.r = r;
        
       
      

       

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Étage:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        txtFloor = new JTextField(5);
        txtFloor.setText(Integer.toString(room.getFloor()));
        panel.add(txtFloor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Numéro de chambre:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        txtRoomNumber = new JTextField(5);
        txtRoomNumber.setText(Integer.toString(room.getNum()));
        panel.add(txtRoomNumber, gbc);

        contentPanel.add(panel);
        
        if (room instanceof PresidentialSuite || room instanceof LuxuryRoom) {
        	checkBoxes = new Vector<JCheckBox>();
            JPanel panelCheckBox = new JPanel(new GridBagLayout());
            GridBagConstraints checkBoxGbc = new GridBagConstraints();
            checkBoxGbc.insets = new Insets(5, 5, 5, 5);
            int Totcolumns = 3; 
            int currentColumn = 0;
            int currentRow = 0;
         
            int i = 0;
            while (i < r.optionsVector.size()) {
            	 	checkBoxGbc.gridx = currentColumn;
            	    checkBoxGbc.gridy = currentRow;
                Options opt = r.optionsVector.get(i);
                JCheckBox checkBox = new JCheckBox(opt.name);
                
                
                if (room instanceof PresidentialSuite) {
                    PresidentialSuite presidentialSuite = (PresidentialSuite) room;
                    if (presidentialSuite.optionExists(opt.name)) {
                        checkBox.setSelected(true);
                    }
                }

               
                if (room instanceof LuxuryRoom && !(room instanceof PresidentialSuite)) {
                    LuxuryRoom luxaryRoom = (LuxuryRoom) room;
                    if (luxaryRoom.optionExists(opt.name)) {
                        checkBox.setSelected(true);
                    }
                }

                
                checkBoxes.add(checkBox);
                
                panelCheckBox.add(checkBox, checkBoxGbc);
                currentColumn++;
                if (currentColumn >= Totcolumns) {
                    currentColumn = 0;
                    currentRow++;
                }
                i++;
            }

            contentPanel.add(panelCheckBox);
        }
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton confirmButton = new JButton("Confirmer");
        JButton cancelButton = new JButton("Annuler");
        
        
        JDialog d = this;
        confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				initSelectedCheckBoxNames();
				if((txtFloor.getText().isEmpty())||(txtRoomNumber.getText().isEmpty())) {
					JOptionPane.showMessageDialog(r, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
					
					return;
				}
				
				else if(!txtFloor.getText().matches("\\d+")&&!txtFloor.getText().matches("\\d+")) {
		        	JOptionPane.showMessageDialog(r, "Veuillez entrer des nombres valides pour le prix", "Erreur", JOptionPane.ERROR_MESSAGE);
		        
		        	return;
		        }
				else {
					int floor = Integer.parseInt(txtFloor.getText());
					int roomNumber = Integer.parseInt(txtRoomNumber.getText());
					
					 if (r.h.isRoomExist(floor, roomNumber) && (floor != room.getFloor() || roomNumber != room.getNum())) {
						 JOptionPane.showMessageDialog(d, "Cet emplacement est déjà occupé, veuillez sélectionner un autre emplacement.",
			                        "Erreur d'emplacement", JOptionPane.ERROR_MESSAGE);
			         }
					 else {
						 	room.setFloor(floor);
							room.setNum(roomNumber);
							Vector<Options> listOptions = new Vector<>();
							if (room instanceof PresidentialSuite) {
								PresidentialSuite roomPred = (PresidentialSuite)room;
								
								for(String name :selectedNames ) {
									Options opt  = r.getOptionByName(name);
									 listOptions.add(opt);
									
		
								}
								roomPred.setlistOptions(listOptions);
							}
							if(room instanceof LuxuryRoom){
								LuxuryRoom roomLux = (LuxuryRoom)room;

								for(String name : selectedNames ) {
									Options opt  = r.getOptionByName(name);
									listOptions.add(opt);
								}
								roomLux.setlistOptions(listOptions);
							}
							
							
							r.updateTableModel();
							d.dispose(); 
					 }
					
					 
				}
				
				
				
				
			}
		});
        
        
        
        cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				d.dispose();
				
			}
		});
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        contentPanel.add(buttonPanel);
        
        getContentPane().add(contentPanel);
        pack(); 
        setVisible(true);
    }
    
    
    public void initSelectedCheckBoxNames() {

        for (JCheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                selectedNames.add(checkBox.getText());
            }
        }

     
    }
}

