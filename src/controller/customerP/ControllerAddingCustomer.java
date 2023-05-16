package controller.customerP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Customer;
import model.Hotel;
import view.CustomerP;

//Name regex (FirstName and LastName) source:
//https://stackoverflow.com/questions/2385701/regular-expression-for-first-and-last-name

//Email regex source:
//https://stackoverflow.com/questions/201323/how-to-validate-an-email-address-using-a-regular-expression

//Phone regex source: 
//https://ihateregex.io/expr/phone/

public class ControllerAddingCustomer implements ActionListener {
    CustomerP c;
	
	public ControllerAddingCustomer(CustomerP c) {
		this.c = c;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Hotel hotel = c.h;
		// Regex pour vérifier que les noms et prénoms sont valides lien dans le commentaire au dessus des sources de regex
		String nameRegex = "([a-zA-Z',.-]+( [a-zA-Z',.-]+)*){2,30}";
		String emailRegex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
		String phoneRegex = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
		
		// On récupère les informations entrées par l'utilisateur
		String LastName = c.txtLastName.getText();
		String FirstName = c.txtFirstName.getText();
		String Email = c.txtEmail.getText();
		String PhoneNumber = c.txtPhone.getText();
		// On vérifie que tous les champs sont remplis et que les regex sont respectés
		if (FirstName.isEmpty() || LastName.isEmpty() || Email.isEmpty() || PhoneNumber.isEmpty()) {
			JOptionPane.showMessageDialog(c, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
		} else if(!FirstName.matches(nameRegex) || !LastName.matches(nameRegex)) {
			JOptionPane.showMessageDialog(c, "Veuillez entrer un nom et un prénom valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
		} else if (!Email.matches(emailRegex)) {
			JOptionPane.showMessageDialog(c, "Veuillez entrer une adresse email valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
		} else if (!PhoneNumber.matches(phoneRegex)) {
			JOptionPane.showMessageDialog(c, "Veuillez entrer un numéro de téléphone valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
		} else if((hotel.CheckIn(FirstName, LastName)!=null)) {
			JOptionPane.showMessageDialog(c, "Le client existe déjà dans la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
		} else {
			// si tout est bon on crée le client et on l'ajoute à la base de données
			Customer newCustomer = new Customer(LastName, FirstName, Email, PhoneNumber);
			newCustomer.setHotel(c.h);
			hotel.addCustomer(newCustomer);
			c.updateTableModel();
			JOptionPane.showMessageDialog(c, "Le client a été ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
			// On vide les champs
			c.txtLastName.setText("");
			c.txtFirstName.setText("");
			c.txtEmail.setText("");
		    c.txtPhone.setText("");
		
		}
	}
}
