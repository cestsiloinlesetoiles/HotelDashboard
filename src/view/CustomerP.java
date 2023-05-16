package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import controller.customerP.ControllerAddingCustomer;
import controller.customerP.ControllerDelCustomers;
import model.Customer;
import model.Hotel;
import view.custom.EditCustomerDialog;
import view.ui.RoundedPanel;
import view.ui.theme;

public class CustomerP extends JPanel {
	public Hotel h;
	public JTable JTableCustomer;
	public DefaultTableModel TableModelCustomer;
	public JPanel pnlMain = new JPanel();
	public JPanel pnlPageLevel = new JPanel();



	public JLabel lblLastName = new JLabel("Nom :");
	public JLabel lblFirstName = new JLabel("Prénom :");
	public JLabel lblEmail = new JLabel("Email :");
	public JLabel lblPhone = new JLabel("Téléphone :");
	public JTextField txtLastName = new JTextField(15);
	public JTextField txtFirstName = new JTextField(15);
	public JTextField txtEmail = new JTextField(15);
	public JTextField txtPhone = new JTextField(15);
	public JButton btnAddingCustomer = new JButton("Ajouter");

	public CustomerP(Hotel h) {
        
		this.h = h;
		
        // On rajoute des clients pour tester l'affichage de la JTable et la recherche etc...
        AddCustomertest();
        
        // Structure de la page Customer commune à toutes les pages
        setLayout(new BorderLayout());

        // Page level qui indique le niveau de la page dans le menu
        ImageIcon pgIco = new ImageIcon(getClass().getResource("/resources/pagelevel/3.png"));
        JLabel pg = new JLabel(pgIco);
        // Décalage du label vers le gauche avec un padding
        pg.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        pnlPageLevel.setLayout(new BoxLayout(pnlPageLevel, BoxLayout.Y_AXIS));
        pnlPageLevel.add(Box.createVerticalGlue());
        pnlPageLevel.add(pg);
        pnlPageLevel.add(Box.createVerticalGlue());
        add(pnlPageLevel, BorderLayout.EAST);
        
        // Main panel qui contient tous les éléments de la page structure commune à toutes les pages
        add(pnlMain, BorderLayout.CENTER);
        pnlMain.setBackground(theme.SECONDARY_BACKGROUND);
        pnlPageLevel.setBackground(theme.SECONDARY_BACKGROUND);

        // Strucuture interne du main panel specifique à la page Customer en gros les emplacements formulaire et la JTable.
        pnlMain.setLayout(new GridBagLayout());
       
        GridBagConstraints gbc = new GridBagConstraints();
        
        // RoundedPanel panel custome qui permet d'arrondir les bords du panel en uttilisant flatlaf style
        RoundedPanel pnlTopDiv = new RoundedPanel();
        pnlTopDiv.setBackground(theme.BACKGROUND_PANEL);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.30;
        gbc.insets = new Insets(15, 10, 5, 30);
        pnlMain.add(pnlTopDiv, gbc);

        RoundedPanel pnlJTable = new RoundedPanel();
        pnlJTable.setBackground(theme.BACKGROUND_PANEL);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.70;
        gbc.insets = new Insets(30, 10, 10, 30);
        pnlMain.add(pnlJTable, gbc);

        pnlTopDiv.setLayout(new GridBagLayout());

        //////////////////// DANS LE TOP PANEL ///////////////////////////////////
        // Formulaire et titre
        // Header de la page Customer HTLM pour pouvoir mettre le titre sur deux lignes
        String titleText = "<html>Customer<br>Manager</html>";
        JLabel title = new JLabel(titleText);
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        title.setForeground(theme.BACKGROUND);
        // Décalage du titre vers la droite avec un padding
        title.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));

        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.blue);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.20;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        pnlTopDiv.add(title, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.8;
        pnlTopDiv.add(formPanel, gbc);

      
        // Boutons du tableau (edit, info, delete)
        ImageIcon editIcon = new ImageIcon(getClass().getResource("/resources/table/edit.png"));
        ImageIcon editIconActive = new ImageIcon(getClass().getResource("/resources/table/editA.png"));
        JButton btnEdit = new JButton(editIcon);
        btnEdit.setPressedIcon(editIconActive);
        // On enlève le background du bouton
        btnEdit.setContentAreaFilled(false);
        btnEdit.setBorderPainted(false);
        
        // NON IMPLEMENTE FAUTE DE TEMPS
        ImageIcon infoIcon = new ImageIcon(getClass().getResource("/resources/table/info.png"));
        ImageIcon infoIconActive = new ImageIcon(getClass().getResource("/resources/table/infoA.png"));
        JButton btnInfo = new JButton(infoIcon);
        btnInfo.setPressedIcon(infoIconActive);
        btnInfo.setContentAreaFilled(false);
        btnInfo.setBorderPainted(false);

        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/resources/table/delete.png"));
        ImageIcon deleteIconActive = new ImageIcon(getClass().getResource("/resources/table/deleteA.png"));
        JButton btnDelete = new JButton(deleteIcon);
        btnDelete.setPressedIcon(deleteIconActive);
        btnDelete.setContentAreaFilled(false);
        btnDelete.setBorderPainted(false);
        
        
        // Initialisation de la jmodeltable avec les données avec la methode CreateTableModel pattern commun à toutes les pages
        CreateTableModel();
        JTableCustomer = new JTable(TableModelCustomer);
        
        JLabel searchLabel = new JLabel("Recherche:");
        JTextField searchTextField = new JTextField(15);
        
        // Configuration du des menu de la jtable
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);

        RoundedPanel buttonsPanel = new RoundedPanel();
        buttonsPanel.setBackground(theme.SECONDARY_BACKGROUND);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(btnEdit);
        buttonsPanel.add(btnInfo);
        buttonsPanel.add(btnDelete);

        JPanel searchAndButtonsPanel = new JPanel(new BorderLayout());
        searchAndButtonsPanel.add(searchPanel, BorderLayout.WEST);
        searchAndButtonsPanel.add(buttonsPanel, BorderLayout.EAST);

        
        pnlJTable.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
        
        //Ajout de la JTable dans un scrollPane pour pouvoir scroller
        JScrollPane tableScrollPane = new JScrollPane(JTableCustomer);
        tableScrollPane.setPreferredSize(new Dimension(870, 500));
        
        // Configuration du header de la JTable
        JTableHeader header = JTableCustomer.getTableHeader();
        header.setForeground(Color.decode("#232323"));
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBorder(BorderFactory.createEmptyBorder());
        header.setBackground(null);
        JTableCustomer.setShowGrid(false);
        
        
        searchPanel.setBackground(theme.BACKGROUND_PANEL);
        buttonsPanel.setBackground(theme.BACKGROUND_PANEL);
        searchAndButtonsPanel.setBackground(theme.BACKGROUND_PANEL);
        
        pnlJTable.setLayout(new BorderLayout());
        pnlJTable.add(searchAndButtonsPanel, BorderLayout.NORTH);
        pnlJTable.add(tableScrollPane, BorderLayout.CENTER);
        
        
        // panel formulaire du haut

        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(theme.BACKGROUND_PANEL);
    
        GridBagConstraints formGbc = new GridBagConstraints();
        // on place en incremantant le gridx et gridy
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        
        formGbc.insets = new Insets(5, 5, 5, 5);

        formGbc.gridx = 0;
        formGbc.gridy = 0;
        formPanel.add(lblLastName, formGbc);

        formGbc.gridx = 1;
        formGbc.gridy = 0;
        formPanel.add(txtLastName, formGbc);
        formGbc.gridx = 2;
        formGbc.gridy = 0;
        formPanel.add(lblFirstName, formGbc);

        formGbc.gridx = 3;
        formGbc.gridy = 0;
        formPanel.add(txtFirstName, formGbc);

        formGbc.gridx = 0;
        formGbc.gridy = 1;
        formPanel.add(lblEmail, formGbc);

        formGbc.gridx = 1;
        formGbc.gridy = 1;
        formPanel.add(txtEmail, formGbc);

        formGbc.gridx = 2;
        formGbc.gridy = 1;
        formPanel.add(lblPhone, formGbc);

        formGbc.gridx = 3;
        formGbc.gridy = 1;
        formPanel.add(txtPhone, formGbc);

        formGbc.gridx = 0;
        formGbc.gridy = 2;
        formGbc.gridwidth = 4;
        formGbc.weightx = 1;
        
        
        formPanel.add(btnAddingCustomer, formGbc);

        btnAddingCustomer.addActionListener(new ControllerAddingCustomer(this));
        btnDelete.addActionListener(new ControllerDelCustomers(this));

        CustomerP c = this;

        // Action listener pour le bouton edit qui ouvre une fenetre de modification
        btnEdit.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
                // on recupere la ligne selectionnée
            	int selectedRow = JTableCustomer.getSelectedRow();
            	if (selectedRow > -1) {
                new EditCustomerDialog(c);
            	}
            	else {
            		JOptionPane.showMessageDialog(c, "Aucune ligne sélectionnée.", "Information", JOptionPane.INFORMATION_MESSAGE);
            	}
                
            }
        });
        
        // sorter pour la recherche dans la JTable
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(TableModelCustomer);
        JTableCustomer.setRowSorter(sorter);
        
        // listener pour la recherche dans la JTable
        searchTextField.getDocument().addDocumentListener(new DocumentListener(){
            // on filtre la JTable en fonction de la recherche
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }
            // on filtre la JTable en fonction de la recherche
            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }
            // methode de filtre
            public void filter(){
                // Récupérer le texte saisi dans le champ de recherche
                String filterStr = searchTextField.getText();
                // Récupérer le trieur de lignes associé à la table JTableCustomer
                TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) JTableCustomer.getRowSorter();
                if(filterStr.length() == 0){
                    // Pas de text => pas de filtre, on affiche tout
                    sorter.setRowFilter(null);
                }else{
                    // Filtre sur toute les ligne
                    // "(?i)" -> insensible à la case
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterStr));
                }
            }
        });

       
        
        
	}
	
	 // Methode pour ajouter des clients pour tester l'affichage de la JTable et la recherche etc...
	 public void AddCustomertest() {
		 Customer customer1 = new Customer("Ben Ali", "Mohamed", "mohamedbenali@test.com", "1234567891");
		 h.addCustomer(customer1);

		 Customer customer2 = new Customer("Dupont", "Marie", "mariedupont@test.com", "1234567892");
		 h.addCustomer(customer2);

		 Customer customer3 = new Customer("Al-Masri", "Hassan", "hassanalmasri@test.com", "1234567893");
		 h.addCustomer(customer3);

		 Customer customer4 = new Customer("Leclerc", "Sophie", "sophieleclerc@test.com", "1234567894");
		 h.addCustomer(customer4);

		 Customer customer5 = new Customer("El-Said", "Ahmed", "ahmedelsaid@test.com", "1234567895");
		 h.addCustomer(customer5);

		 Customer customer6 = new Customer("Rousseau", "Gabriel", "gabrielrousseau@test.com", "1234567896");
		 h.addCustomer(customer6);

		 Customer customer7 = new Customer("Abdelkader", "Youssef", "youssefabdelkader@test.com", "1234567897");
		 h.addCustomer(customer7);

		 Customer customer8 = new Customer("Perrin", "Lucie", "lucieperrin@test.com", "1234567898");
		 h.addCustomer(customer8);

		 Customer customer9 = new Customer("Al-Hakim", "Faisal", "faisalalhakim@test.com", "1234567899");
		 h.addCustomer(customer9);

		 Customer customer10 = new Customer("Girard", "Isabelle", "isabellegirard@test.com", "1234567810");
		 h.addCustomer(customer10);

     }
	
    // Methode pour setup la JTable
	public void CreateTableModel() {



		String[] columnNames = {"Nom", "Prénom", "Email", "Téléphone"};

		Object[][] data = new Object[h.customers.size()][5];


		for (int i = 0; i < h.customers.size(); i++) {
			Customer customer = h.customers.get(i);
			data[i][0] = customer.getLastName();
			data[i][1] = customer.getFirstName();
			data[i][2] = customer.getEmail();
			data[i][3] = customer.getPhoneNumber();
		}
		TableModelCustomer = new DefaultTableModel(data, columnNames){
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };;
	}

    // Methode pour mettre à jour la JTable après une modification accessible par les controllers
	public void updateTableModel() {
		CreateTableModel();
		JTableCustomer.setModel(TableModelCustomer);
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(TableModelCustomer);
	    JTableCustomer.setRowSorter(sorter);
		TableModelCustomer.fireTableDataChanged();
		
	}
	
}
	
	
	