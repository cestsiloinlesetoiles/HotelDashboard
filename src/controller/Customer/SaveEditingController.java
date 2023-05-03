package controller.Customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Customer;
import model.Hotel;

public class SaveEditingController implements ActionListener {
	JTextField txtN;
	JTextField txtF;
	JTextField txtE;
	JTextField txtT;
	Customer c;
	DefaultTableModel TableModel;
	Hotel h;
	int row;
	JDialog d;

	public SaveEditingController(JTextField txtN, JTextField txtF, 
			JTextField txtE,JTextField txtT,DefaultTableModel TableModel,Customer c, int row,Hotel h, JDialog d) {

		this.txtN = txtN;
		this.txtF = txtF;
		this.txtE = txtE;
		this.txtT = txtT;
		this.TableModel = TableModel ;
		this.c = c;
		this.row =row;
	    this.h = h;
	    this.d = d;
	    

	}

	public void actionPerformed(ActionEvent event) {

		String LastName = txtN.getText();
		String FirstName = txtF.getText();
		String Email = txtE.getText();
		String PhoneNumber = txtT.getText();
		
		
		if(h.CheckIn(FirstName, LastName)==null||(c.getFirstName().equals(FirstName) && c.getLastName().equals(LastName))) {
		c.setLastName(LastName);
		c.setFirstname(FirstName);
		c.setEmail(Email);
		c.setPhone(PhoneNumber);

		TableModel.setValueAt(txtN.getText(), row, 0); 
		TableModel.setValueAt(txtF.getText(), row, 1); 
		TableModel.setValueAt(txtE.getText(), row, 2); 
		TableModel.setValueAt(txtT.getText(), row, 3);
		d.dispose();
		}
		else {
			System.out.println("***DUPLICATE DATA AVOID***");
			JOptionPane.showMessageDialog(d, "Nom et prenom déjà present", "Erreur", JOptionPane.ERROR_MESSAGE);

		}
	}

}
