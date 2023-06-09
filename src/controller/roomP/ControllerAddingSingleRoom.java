package controller.roomP;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Hotel;
import model.Roomtype;
import view.RoomP;

public class ControllerAddingSingleRoom implements ActionListener {

	public RoomP r;

	public ControllerAddingSingleRoom(RoomP r) {
		this.r = r ;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ajout d'une chambre simple
		// On vérifie que tous les champs sont remplis
		if (r.txtFloor.getText().isEmpty() || r.txtRoomNumber.getText().isEmpty()) {
			JOptionPane.showMessageDialog(r, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
		} else {
			String floorStr = r.txtFloor.getText();
			String roomNumberStr = r.txtRoomNumber.getText();
			// On vérifie que les champs sont des nombres
			if (!floorStr.matches("\\d+") || !roomNumberStr.matches("\\d+")) {
				JOptionPane.showMessageDialog(r, "Veuillez entrer des nombres valides pour l'étage et le numéro de chambre.", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				// On récupère les valeurs et on les convertit en int
				int floor = Integer.parseInt(floorStr);
				int roomNumber = Integer.parseInt(roomNumberStr);
				// On vérifie que les nombres sont positifs
				if (floor < 0 || roomNumber < 0) {
					JOptionPane.showMessageDialog(r, "Le numéro d'étage et de chambre doivent être positifs.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					// On récupère le type de chambre dans le comboBox
					String roomTypeStr = (String) r.cbRoomType.getSelectedItem();
					
					
					// On convertit le type de chambre en Roomtype
					Roomtype roomType = Roomtype.SIMPLE;
					switch (roomTypeStr) {
					case "Double":
						roomType = Roomtype.DOUBLE;
						break;
					case "Luxury":
						roomType = Roomtype.LUXURY;
						break;
					case "Presidential":
						roomType = Roomtype.PRESIDENTIAL;
						break;
					default:
						break;
					}
					// On vérifie que la chambre n'existe pas déjà
					if (r.h.isRoomExist(floor, roomNumber)) {
						JOptionPane.showMessageDialog(r, "La chambre existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
					} else {
						// On ajoute la chambre
						r.h.addRoom(floor, roomNumber, roomType);
						r.updateTableModel();
						JOptionPane.showMessageDialog(r, "La chambre a été ajoutée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
						r.txtFloor.setText("");
						r.txtRoomNumber.setText("");
					}
				}
			}
		}
	}
}
