package view.custom;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import model.Customer;
import view.CustomerP;

public class EditCustomerDialog extends JDialog {

	private CustomerP c;
	private JTextField txtLastName;
	private JTextField txtFirstName;
	private JTextField txtEmail;
	private JTextField txtPhone;

	public EditCustomerDialog(CustomerP c) {
		
		setTitle("Modifier les informations du client");
        setModal(true);
        setLocationRelativeTo((JFrame) SwingUtilities.getWindowAncestor(c));
		
		
		this.c = c;
		
		// Récupération de la ligne sélectionnée
		int selectedRow = c.JTableCustomer.getSelectedRow();
		if (selectedRow > -1) {
		String lastName = (String) c.JTableCustomer.getValueAt(selectedRow, 0);
		String firstName = (String) c.JTableCustomer.getValueAt(selectedRow, 1);
		// Recherche du client
		Customer customerEdit = c.h.CheckIn(firstName, lastName);

		
			JPanel contentPanel = new JPanel(new GridLayout(5, 2, 10, 10));

			contentPanel.add(new JLabel("Nom:"));
			txtLastName = new JTextField(customerEdit.getLastName());
			contentPanel.add(txtLastName);

			contentPanel.add(new JLabel("Prénom:"));
			txtFirstName = new JTextField(customerEdit.getFirstName());
			contentPanel.add(txtFirstName);

			contentPanel.add(new JLabel("Email:"));
			txtEmail = new JTextField(customerEdit.getEmail());
			contentPanel.add(txtEmail);

			contentPanel.add(new JLabel("Téléphone:"));
			txtPhone = new JTextField(customerEdit.getPhoneNumber());
			contentPanel.add(txtPhone);

			JButton btnSave = new JButton("Enregistrer");
			this.add(contentPanel, BorderLayout.CENTER);
			this.add(btnSave, BorderLayout.SOUTH);
			
			Dialog d = this;
			// Enregistrement des modifications
			btnSave.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String newFirstName = txtFirstName.getText().trim();
					String newLastName = txtLastName.getText().trim();

					if (newFirstName.isEmpty() || newLastName.isEmpty()) {
						JOptionPane.showMessageDialog(c, "Les champs nom et prénom sont obligatoires.", "Erreur",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					// Vérification de l'existence d'un client avec le même nom et prénom
					if (!newFirstName.equals(customerEdit.getFirstName()) || !newLastName.equals(customerEdit.getLastName())) {
						if (c.h.CheckIn(newFirstName, newLastName) != null) {
							JOptionPane.showMessageDialog(c, "Un client avec ce nom et prénom existe déjà.", "Erreur",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}

					customerEdit.setFirstname(newFirstName);
					customerEdit.setLastName(newLastName);
					customerEdit.setEmail(txtEmail.getText());
					customerEdit.setPhone(txtPhone.getText());
					c.updateTableModel();
					dispose();
				}
			});
		}
		this.pack();
		this.setVisible(true);
	}}   
