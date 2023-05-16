package view.custom;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.github.lgooddatepicker.components.DatePicker;

import model.Customer;
import model.Reservation;
import model.Room;
import view.ReservationP;

public class EditReservationDialog extends JDialog {

    public JComboBox<String> customerComboBox = new JComboBox<String>();;
    public JComboBox<String> roomComboBox = new JComboBox<String>();;
    public JButton saveButton ;
    public JButton cancelButton;
    public ReservationP Rview;
    public DatePicker startDate;
    public DatePicker endDate;

    public EditReservationDialog(Reservation r, ReservationP Rview) {
        
    	setTitle("Modifier les reservations");
        setModal(true);
        
        setLocationRelativeTo((JFrame) SwingUtilities.getWindowAncestor(Rview));
        this.Rview = Rview;
        // remplir les combobox avec les clients et les chambres pour la selection;
        FillRoomComboBox();
        FillCustomerComboBox(); 
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        
        if (r.getCustomer() != null) {
            String customerName = r.getCustomer().getLastName() + " " + r.getCustomer().getFirstName();
            customerComboBox.setSelectedItem(customerName);
        }

        
        if (r.getRoom() != null) {
            String room = r.getRoom().getFloor() + " - " + r.getRoom().getNum() + " - " + r.getRoom().getType();
            roomComboBox.setSelectedItem(room);
        }

        
        
        cs.fill = GridBagConstraints.HORIZONTAL;
        
        
        
        
        JLabel roomLabel = new JLabel("Room: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(roomLabel, cs);

        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(roomComboBox, cs);

        JLabel customerLabel = new JLabel("Clients: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(customerLabel, cs);

        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(customerComboBox, cs);
        
        JLabel startDateLabel = new JLabel("Début: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(startDateLabel, cs);

        startDate = new DatePicker();
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(startDate, cs);
        startDate.setDate(r.getDateStart());
        
        JLabel endDateLabel = new JLabel("Fin: ");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        panel.add(endDateLabel, cs);
        

        
        endDate = new DatePicker();
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        panel.add(endDate, cs);
        endDate.setDate(r.getDateEnd());

        saveButton = new JButton("Confirmer");
        // sauvegarder les changements
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	if(customerComboBox.getSelectedItem()!=null) {
            		String SelectedName = customerComboBox.getSelectedItem().toString();
            		// on a besoin de séparer le nom et le prénom pour pouvoir chercher le client dans la liste des clients
                    String[] Fullname = SelectedName.split(" ");
            		String FirstName = Fullname[1];
            		String LastName = Fullname[0];
                    Customer newc = Rview.h.CheckIn(FirstName, LastName);
                    
                    // si newc est null, ça veut dire que le client n'existe pas dans la liste des clients Note c'est impossible d'arriver à ce cas juste j'ai oublier de le supprimer
                    if(newc != null) {
                        // si le client n'est pas le même que celui de la réservation, on doit changer le client de la réservation
                        if(r.getCustomer()!=null) {
                        
                            Customer c = r.getCustomer();
                            if(c.getLastName()!=LastName||c.getFirstName()!=FirstName) {
                                // on doit supprimer la réservation de la liste des réservations du client actuel et l'ajouter à la liste des réservations du nouveau client
                                c.getListrsv_current_customer().remove(r);
                                newc.addReservation(r);
                                r.setCustomer(newc);	
                            }
                        }
                        // si le client est null, ça veut dire que la réservation n'a pas de client, donc on doit ajouter le client à la réservation
                        else {
                            newc.addReservation(r);
                            r.setCustomer(newc);
                        }
            		
            	} }
            	
            	
            	if(roomComboBox.getSelectedItem()!=null) {
            	    String selectedRoom = roomComboBox.getSelectedItem().toString();
            	    String[] roomDetails = selectedRoom.split(" - ");
            	    int floor = Integer.parseInt(roomDetails[0]);
            	    int num = Integer.parseInt(roomDetails[1]);
            	    Room newRoom = Rview.h.getRoom(floor, num);
            	    // si la chambre n'est pas la même que celle de la réservation, on doit changer la chambre de la réservation.
                    if(!newRoom.equals(r.getRoom())){
                        // si la nouvelle chambre est libre pendant la période spécifiée, on peut changer la chambre de la réservation
            	        if (newRoom.CheckisFree(startDate.getDate(), endDate.getDate())) {
            	        if(r.room !=null) {
            	        Room room = r.getRoom() ;
            	        if(room.getFloor()!=floor || room.getNum()!=num) {
            	        room.reservations.remove(r);
            	        newRoom.addReservation(r);
            	        r.setRoom(newRoom);   
            	        }
            	    
            	        }
            	        else{
            	    	newRoom.addReservation(r);
             	        r.setRoom(newRoom);  
            	        }
            	        }
            	        else {
            	    	JOptionPane.showMessageDialog(Rview, "La nouvelle chambre sélectionnée n'est pas libre pendant la période spécifiée.", "Erreur de réservation", JOptionPane.ERROR_MESSAGE);
                       
            	        }
            	}}
            	
            	if(startDate.getDate() == null || endDate.getDate() == null || endDate.getDate().isBefore(startDate.getDate())) {
					JOptionPane.showMessageDialog(Rview, "Veuillez spécifier une date de début et une date de fin.", "Erreur de réservation", JOptionPane.ERROR_MESSAGE);
					
            	}
            	else {
            	 r.setDateStart(startDate.getDate());
                 r.setDateEnd(endDate.getDate());
                 Rview.updateTableModel();
                 JOptionPane.showMessageDialog(Rview, "Les changements ont été appliqués.", "Message", JOptionPane.INFORMATION_MESSAGE);
            	
            	dispose();
            	}
            }
        });

        cancelButton = new JButton("Annuler");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel bp = new JPanel();
        bp.add(saveButton);
        bp.add(cancelButton);
        
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.SOUTH);
        setSize(new Dimension(400, 250));
        setVisible(true);
    }
    // remplir les combobox avec les clients et les chambres pour la selection;

    public void FillCustomerComboBox() {
        customerComboBox.removeAllItems();
        for (Customer customer : Rview.h.customers) {
            String Name = customer.getLastName()  + " " +  customer.getFirstName();
            customerComboBox.addItem(Name);
        }
        customerComboBox.setSelectedItem(null);
    }

    public void FillRoomComboBox() {
        roomComboBox.removeAllItems();
        for (Room room : Rview.h.rooms) {
            roomComboBox.addItem(room.getFloor() + " - " + room.getNum() + " - " + room.getType());
        }
        roomComboBox.setSelectedItem(null);
    }

}