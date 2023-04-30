package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.HotelManager.Customer;
import model.HotelManager.Hotel;


//Name regex (FirstName and LastName) source:
// https://stackoverflow.com/questions/2385701/regular-expression-for-first-and-last-name

//Email regex source:
// https://stackoverflow.com/questions/201323/how-to-validate-an-email-address-using-a-regular-expression

//Phone regex source: 
//https://ihateregex.io/expr/phone/


public class AddingCustomerController implements ActionListener {
	JTextField txtN;
	JTextField txtF;
	JTextField txtE;
	JTextField txtT;
	Hotel hotel;
	DefaultTableModel TableModel;

	public AddingCustomerController(JTextField txtN, JTextField txtF, 
			JTextField txtE,JTextField txtT, Hotel hotel,DefaultTableModel TableModel) {

		this.txtN = txtN;
		this.txtF = txtF;
		this.txtE = txtE;
		this.txtT = txtT;
		this.hotel = hotel;
		this.TableModel = TableModel ;
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		String LastName = txtN.getText();
		String FirstName = txtF.getText();
		String Email = txtE.getText();
		String PhoneNumber = txtT.getText();

		Customer newCustomer = new Customer(FirstName, LastName, Email, PhoneNumber);

		
		String nameRegex = "([a-zA-Z',.-]+( [a-zA-Z',.-]+)*){2,30}";
		String emailRegex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
		String phoneRegex = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";

		if(Email.isEmpty() || PhoneNumber.isEmpty()||FirstName.isEmpty()||LastName.isEmpty()) {

			System.out.println("***ERROR FIELD EMPTY***");
		}
		else if(!FirstName.matches(nameRegex) || !LastName.matches(nameRegex)) {
			System.out.println("***INVALID NAME***");
		} else if (!Email.matches(emailRegex)) {
			System.out.println("***INVALID EMAIL***");
		} else if (!PhoneNumber.matches(phoneRegex)) {
			System.out.println("***INVALID PHONE NUMBER***");
		}
		else if((hotel.CheckIn(FirstName, LastName)==null)) {
			hotel.addCustomer(newCustomer);
			TableModel.addRow(new Object[] {LastName, FirstName, Email, PhoneNumber});
			System.out.println("***ADDING CUSTOMER SUCCES***"+LastName+" "+FirstName);
		}
		else{
			System.out.println("***DUPLICATE DATA AVOID***");
		}
	}

}

