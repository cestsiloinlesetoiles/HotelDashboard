package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.Customer.AddingCustomerController;
import controller.Customer.SearchSortController;
import controller.Customer.TableSelectionController;
import controller.Customer.fieldcheck.EmailFieldController;
import controller.Customer.fieldcheck.NameFieldController;
import controller.Customer.fieldcheck.PhoneFieldController;
import model.Customer;
import model.Hotel;
import view.Custompanel.ActionMenuP;

public class CustomerP extends JPanel {
    JPanel mainPanel;
	JTable listCustomer;
    JPanel pnlTable = new JPanel();
    JPanel pnlAddingCustomer = new JPanel();
    JPanel pnlTopBoard = new JPanel();
    DefaultTableModel TableModelCustomer;
	
    
    public CustomerP(Hotel hotel) {
    	
    	
        pnlTopBoard.setBackground(Color.BLUE);
        pnlTable.setBackground(Color.GREEN);
        pnlAddingCustomer.setBackground(Color.GRAY);

        this.setBackground(Color.YELLOW);

        
        
        // AddingCustomer Components 
        
        JLabel lblLastName = new JLabel("Nom:");
        JLabel lblFirstName = new JLabel("Prénom:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblPhone = new JLabel("Téléphone:");
        JTextField txtLastName = new JTextField(15);
        JTextField txtFirstName = new JTextField(15);
        JTextField txtEmail = new JTextField(15);
        JTextField txtPhone = new JTextField(15);
        JButton btnAddingCustomer = new JButton("Ajouter");
        
        
        // AddingCustomer setgrid
        pnlAddingCustomer.setLayout(new GridBagLayout());
        GridBagConstraints gbcCustomer = new GridBagConstraints();
        gbcCustomer.gridx = 0;
        gbcCustomer.gridy = 0;
        gbcCustomer.anchor = GridBagConstraints.WEST;
        gbcCustomer.insets = new Insets(5, 5, 5, 5);

        pnlAddingCustomer.add(lblLastName, gbcCustomer);
        gbcCustomer.gridy++;
        pnlAddingCustomer.add(lblFirstName, gbcCustomer);
        gbcCustomer.gridy++;
        pnlAddingCustomer.add(lblEmail, gbcCustomer);
        gbcCustomer.gridy++;
        pnlAddingCustomer.add(lblPhone, gbcCustomer);

        gbcCustomer.gridx++;
        gbcCustomer.gridy = 0;
        pnlAddingCustomer.add(txtLastName, gbcCustomer);
        gbcCustomer.gridy++;
        pnlAddingCustomer.add(txtFirstName, gbcCustomer);
        gbcCustomer.gridy++;
        pnlAddingCustomer.add(txtEmail, gbcCustomer);
        gbcCustomer.gridy++;
        pnlAddingCustomer.add(txtPhone, gbcCustomer);

        gbcCustomer.gridx = 0;
        gbcCustomer.gridy++;
        gbcCustomer.gridwidth = 2;
        gbcCustomer.fill = GridBagConstraints.HORIZONTAL;
        pnlAddingCustomer.add(btnAddingCustomer, gbcCustomer);
       
        JLabel lblLastNameError = new JLabel("");
        JLabel lblFirstNameError = new JLabel("");
        JLabel lblEmailError = new JLabel("");
        JLabel lblPhoneError = new JLabel("");
        
        
        gbcCustomer.gridy++;
        pnlAddingCustomer.add(lblLastNameError, gbcCustomer);
        gbcCustomer.gridy++;
        pnlAddingCustomer.add(lblFirstNameError, gbcCustomer);
        gbcCustomer.gridy++;
        pnlAddingCustomer.add(lblEmailError, gbcCustomer);
        gbcCustomer.gridy++;
        pnlAddingCustomer.add(lblPhoneError, gbcCustomer);

        
        NameFieldController nfieldc = new NameFieldController(lblLastNameError,true);
        NameFieldController nfieldc2 = new NameFieldController(lblFirstNameError,false);
        EmailFieldController efieldc = new EmailFieldController(lblEmailError);
        PhoneFieldController phfieldc = new PhoneFieldController(lblPhoneError);
        
        txtLastName.addFocusListener(nfieldc);
        txtFirstName.addFocusListener(nfieldc2);
        txtEmail.addFocusListener(efieldc);
        txtPhone.addFocusListener(phfieldc);
        
        
        
     
        
        //SetTable Components
        
        pnlTable.setLayout(new BorderLayout());
        TableModelCustomer = TableModel(hotel);
        AddingCustomerController acc = new AddingCustomerController(txtLastName, txtFirstName, txtEmail, txtPhone, hotel,TableModelCustomer,this);
        JTable TableCustomer = new JTable(TableModelCustomer);
        JScrollPane scrollPane = new JScrollPane(TableCustomer);
        TableCustomer.setAutoCreateRowSorter(true);  
        pnlTable.add(scrollPane, BorderLayout.CENTER);
        
        
        btnAddingCustomer.addActionListener(acc);
        
        ActionMenuP actbtn = new ActionMenuP(TableCustomer,TableModelCustomer,hotel,this);
        pnlTable.add(actbtn,BorderLayout.EAST);
        
        TableCustomer.getSelectionModel().addListSelectionListener(new TableSelectionController(TableCustomer, actbtn));
        
        
        
        
       
        
        
        
        
        
       
        
        JLabel srch = new JLabel("Recherche");
        JTextField searchField = new JTextField(15);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(srch);
        searchPanel.add(searchField);
        pnlTable.add(searchPanel, BorderLayout.NORTH);
        SearchSortController srchC = new SearchSortController(TableCustomer, TableModelCustomer,searchField);
        searchField.getDocument().addDocumentListener(srchC);;
        
       
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(8, 8, 8, 8);
        
        // Top board
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.30;
        add(pnlTopBoard, gbc);
        
        // AddingCustomer panel
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weighty = 1;
        gbc.weightx = 0.20;
        gbc.gridheight = 3;
        add(pnlAddingCustomer, gbc);

        // Table panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.80;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        add(pnlTable, gbc);
    }
    
    
    public DefaultTableModel TableModel(Hotel hotel) {
    	
    	
    	
    	String[] columnNames = {"Nom", "Prénom", "Email", "Téléphone"};
    	
    	Object[][] data = new Object[hotel.customers.size()][5];
    	
    	
    	for (int i = 0; i < hotel.customers.size(); i++) {
    	    Customer customer = hotel.customers.get(i);
    	    data[i][0] = customer.getLastName();
    	    data[i][1] = customer.getFirstName();
    	    data[i][2] = customer.getEmail();
    	    data[i][3] = customer.getPhoneNumber();
    	}
    	DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
    	return tableModel;
	}
     
    
    
}
