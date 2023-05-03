package view.Dialogs;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.Customer.SaveEditingController;
import model.Customer;
import model.Hotel;

public class EditCustomerDialog extends JDialog {

    private JTextField txtLastName;
    private JTextField txtFirstName;
    private JTextField txtEmail;
    private JTextField txtPhone;

    public EditCustomerDialog(JFrame Mainframe, Customer customer, int row , DefaultTableModel TableModel, Hotel h) {
    	//modal : lorsqu’il est actif toute interaction avec les autres fenêtres sont bloquées. Les dialogues JOptionPane sont modaux
        super(Mainframe, "Modifier les informations du client", true);

        JPanel contentPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        contentPanel.add(new JLabel("Nom:"));
        txtLastName = new JTextField(customer.getLastName());
        contentPanel.add(txtLastName);

        contentPanel.add(new JLabel("Prénom:"));
        txtFirstName = new JTextField(customer.getFirstName());
        contentPanel.add(txtFirstName);
        JLabel lblSingleAdding = new JLabel("Ajout Unitaires");
        contentPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField(customer.getEmail());
        contentPanel.add(txtEmail);

        contentPanel.add(new JLabel("Téléphone:"));
        txtPhone = new JTextField(customer.getPhoneNumber());
        contentPanel.add(txtPhone);

        JButton btnSave = new JButton("Enregistrer");
        SaveEditingController sed = new SaveEditingController(txtLastName, txtFirstName, txtEmail, txtEmail, TableModel , customer, row, h, this);
        btnSave.addActionListener(sed);
        contentPanel.add(btnSave);

        add(contentPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(Mainframe);
    }
}
