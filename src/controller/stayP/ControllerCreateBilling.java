package controller.stayP;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.Consumption;
import model.Customer;
import model.DoubleRoom;
import model.GuestStay;
import model.LuxuryRoom;
import model.Options;
import model.PresidentialSuite;
import model.Room;
import model.Simple;
import view.StayP;

public class ControllerCreateBilling  implements ActionListener {
	StayP st;
	public ControllerCreateBilling(StayP st) {
		this.st = st;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			
		int selectedRow = st.JTableStay.getSelectedRow();
		if (selectedRow != -1) {
            st.invoiceArea.setText("");
			int id = (int) st.JTableStay.getValueAt(selectedRow, 0);
			GuestStay stay = st.h.getGuestStayById(id); 
		
			
			System.out.println("On est rentré de dans it's k");

			Room room = stay.reservation.getRoom();
			Customer c = stay.reservation.customer;

			Vector<Options> listopt = new Vector<Options>();
			String roomType = "Non disponible";
			int roomPrice = 0;
			int roomBed = 0;

			if(room != null) {
				if(room instanceof Simple) {
					roomType = "Simple";
					roomPrice = Simple.spec_price;
					roomBed = Simple.spec_bed;
				} else if(room instanceof DoubleRoom) {
					roomType = "Double";
					roomPrice = DoubleRoom.spec_price;
					roomBed = DoubleRoom.spec_bed;
				} else if(room instanceof LuxuryRoom) {
					roomType = "De luxe";
					roomPrice = LuxuryRoom.spec_price;
					roomBed = LuxuryRoom.spec_bed;
					listopt = ((LuxuryRoom) room).getListOptions();  
				} else if(room instanceof PresidentialSuite) {
					roomType = "Présidentielle";
					roomPrice = PresidentialSuite.spec_price;;
					roomBed = PresidentialSuite.spec_bed;
					listopt = ((PresidentialSuite) room).getListOptions();    
				}
			}

			Vector<Consumption> listConsumptions = stay.listconspt;
			stay.calculateTotalCost();
			int total = stay.totalCost;
			String name = stay.hotel.name; 
			String add  = stay.hotel.address;
			long numberOfDays = ChronoUnit.DAYS.between(stay.reservation.getDateStart(), stay.reservation.getDateEnd());	
			st.invoiceArea.append("================= HOTEL INFO =================\n");
			st.invoiceArea.append("Nom de l'hotel : " + name + "\n");
			st.invoiceArea.append("Adresse: " + add + "\n");
			st.invoiceArea.append("================= STAY INFO =================\n");
			st.invoiceArea.append("Date d'arrivée : " + stay.reservation.getDateEnd() + "\n");
			st.invoiceArea.append("Date de départ : " + stay.reservation.getDateStart() + "\n\n");
			st.invoiceArea.append("Nombre de jours : " + numberOfDays + "\n\n");
			
			if(c != null) {
				st.invoiceArea.append("================= CLIENT INFO =================\n");
				st.invoiceArea.append("Client: " + c.getFirstName() + " " + c.getLastName() + "\n\n");
			}
			
			st.invoiceArea.append("================= ROOM INFO =================\n");
			st.invoiceArea.append("Type de chambre: " + roomType + "\n");
			st.invoiceArea.append("Prix par jour: " + roomPrice+"€" + "\n");
			st.invoiceArea.append("Nombre de lits: " + roomBed + "\n\n");
			st.invoiceArea.append("Consommations: \n");
			
			
			
			st.invoiceArea.append("================= CONSUMPTION INFO =================\n");
			for (Consumption cons : listConsumptions) {
				st.invoiceArea.append(cons.getName() + " - " + cons.product.getPrice()+"€"+ " - "+ cons.qt + "\n");
			}
			st.invoiceArea.append("\n================= ROOM OPTIONS =================\n");
			st.invoiceArea.append("\nOptions de chambre : \n");
			for (Options opt : listopt) {
				st.invoiceArea.append(opt.getName() + " - " + opt.price+"€" + "\n");
			}
			
			st.invoiceArea.append("\n================= TOTAL =================");
			st.invoiceArea.append("\nTotal : " + total+"€" + "\n");
			st.invoiceArea.append("===========================================\n");

			st.invoiceArea.setEditable(false);

			
		}
		else {
			JOptionPane.showMessageDialog(st, "Veuillez sélectionner un séjour", "Erreur", JOptionPane.ERROR_MESSAGE);
		}

	}

}
