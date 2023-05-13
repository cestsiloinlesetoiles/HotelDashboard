package controller.reservationP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import model.Reservation;
import model.Room;
import view.ReservationP;

public class ControllerAddReseravtions implements ActionListener {
	ReservationP r ; 

	public ControllerAddReseravtions(ReservationP r ) {
		this.r = r;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		LocalDate startDate = r.startDate.getDate();
		LocalDate endDate = r.endDate.getDate();
		String firstName = r.firstName.getText();
		String lastName = r.lastName.getText();
		Room room = null;

		if(startDate == null || endDate == null || endDate.isBefore(startDate)) {
			JOptionPane.showMessageDialog(r, "Veuillez spécifier une date de début et une date de fin.", "Erreur de réservation", JOptionPane.ERROR_MESSAGE);
			return;
		}


		
		if(r.roomComboBox.getSelectedItem()!=null) {
			String selectedRoom = r.roomComboBox.getSelectedItem().toString();

		
			String[] roomInfo = selectedRoom.split(" - ");
			int floor = Integer.parseInt(roomInfo[0]);
			int num = Integer.parseInt(roomInfo[1]);
			room = r.h.getRoom(floor,num);

			if (!room.CheckisFree(startDate, endDate)) {
				JOptionPane.showMessageDialog(r, "La chambre sélectionnée n'est pas libre pendant la spécifiées.", "Erreur de réservation", JOptionPane.ERROR_MESSAGE);
				room = null;
			}


		}


		Reservation NewReservations = new Reservation(startDate, endDate);
		if(room != null) {

			NewReservations.setRoom(room);
			
			
		}
		else {
			JOptionPane.showMessageDialog(r, "Aucune chambre sélectionnée. La réservation sera créée sans client. Vous pouvez ajouter une chambre plus tard", "Avertissement", JOptionPane.WARNING_MESSAGE);
		}


		if(r.h.CheckIn(firstName, lastName)!=null) {

			NewReservations.setCustomer(r.h.CheckIn(firstName, lastName));
			
		}
		else{
			JOptionPane.showMessageDialog(r, "Le client n'est pas enregistré. La réservation sera crée sans client. Vous pouvez ajouter un client plus tard.", "Avertissement", JOptionPane.WARNING_MESSAGE);	
		}
		r.startDate.setDate(null);
		r.endDate.setDate(null);
		r.firstName.setText("");
		r.lastName.setText("");
		
		if (r.roomComboBox.getItemCount() > 0) {
		    r.roomComboBox.setSelectedIndex(0);
		} else {
		    r.roomComboBox.setSelectedIndex(-1);
		}

		r.h.addReservation(NewReservations);
		r.roomComboBox.setSelectedItem(null);
		r.updateTableModel();
		JOptionPane.showMessageDialog(r, "Réservation ajoutée avec succès.", "Réservation réussie", JOptionPane.INFORMATION_MESSAGE);
		
	}




}





