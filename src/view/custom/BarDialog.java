package view.custom;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import model.Consumption;
import model.GuestStay;
import model.Product;
import view.StayP;

public class BarDialog extends JDialog {
	public JTable JconsumptionTable;
	public DefaultTableModel consumptionTableModel;
	public JTextField productNameField;
	public JTextField quantityField;
	public JTextField priceField;
	public JButton addConsumptionButton;
	public JButton deleteConsumptionButton;
	public JButton editConsumptionButton;
	public GuestStay guestStay;
	public StayP st;
	
	public BarDialog(GuestStay guestStay, StayP st) {
		this.guestStay = guestStay;
		this.st = st;
		setSize(500, 500);
		setTitle("MiniBar");
		setLayout(new BorderLayout());
	
		setLocationRelativeTo((JFrame) SwingUtilities.getWindowAncestor(st));
		// creation du model de la table des consommations et ajout des donnees.
		createTableModel();
		JconsumptionTable = new JTable(consumptionTableModel);
		JScrollPane scrollPane = new JScrollPane(JconsumptionTable);
		add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel(new GridBagLayout());
		add(panel, BorderLayout.SOUTH);

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Nom du produit : "), c);

		c.gridx = 1;
		c.gridy = 0;
		productNameField = new JTextField();
		panel.add(productNameField, c);

		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("Quantité : "), c);

		c.gridx = 1;
		c.gridy = 1;
		quantityField = new JTextField();
		panel.add(quantityField, c);

		c.gridx = 0;
		c.gridy = 2;
		panel.add(new JLabel("Prix : "), c);

		c.gridx = 1;
		c.gridy = 2;
		priceField = new JTextField();
		panel.add(priceField, c);

		c.gridwidth = 2; 
		c.gridx = 0;
		c.gridy = 3;
		addConsumptionButton = new JButton("Ajouter");
		panel.add(addConsumptionButton, c);

		c.gridy = 4;
		deleteConsumptionButton = new JButton("Supprimer");
		panel.add(deleteConsumptionButton, c);

		c.gridy = 5;
		editConsumptionButton = new JButton("Modifier");
		panel.add(editConsumptionButton, c);

		
		addConsumptionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String productName = productNameField.getText();
				String quantityString = quantityField.getText();
				String priceString = priceField.getText();
				// verification que tous les champs sont remplis
				if(productName.isEmpty() || quantityString.isEmpty() || priceString.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tous les champs doivent être remplis !", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// verification que la quantite et le prix sont des chiffres
				if(!quantityString.matches("\\d+") || !priceString.matches("\\d+")) {
					JOptionPane.showMessageDialog(null, "La quantité et le prix doivent être des chiffres !", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
					// recherche si le produit existe deja
				if (guestStay.getConsumptionByName(productName)!=null) {
		            JOptionPane.showMessageDialog(st, "L'option existe déjà !", "Erreur", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
				
				
				int quantity = Integer.parseInt(quantityString);
				int price = Integer.parseInt(priceString);

				Product product = new Product(productName, price);
				Consumption consumption = new Consumption(quantity);
				consumption.setProduct(product);
				consumption.setCurrentStay(guestStay);

				guestStay.listconspt.add(consumption);
				updateTableModel();
			}
		});

		// suppression d'une consommation
		deleteConsumptionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String productName = productNameField.getText();
				if(!productName.isEmpty()) {
					// recuperation de la consommation
					Consumption consumption = guestStay.getConsumptionByName(productName);
					if(consumption != null) {
						// suppression de la consommation et mise a jour du tableau
						guestStay.listconspt.remove(consumption);
						updateTableModel();
					} else {
						JOptionPane.showMessageDialog(null, "Aucun produit trouvé !", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Le nom du produit ne doit pas être vide !", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// modification d'une consommation
		editConsumptionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String productName = productNameField.getText();
				String quantityString = quantityField.getText();
				String priceString = priceField.getText();
				if(productName.isEmpty() || quantityString.isEmpty() || priceString.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tous les champs doivent être remplis !", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if(!quantityString.matches("\\d+") || !priceString.matches("\\d+")) {
					JOptionPane.showMessageDialog(null, "La quantité et le prix doivent être des chiffres !", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// conversion des champs en entiers
				int quantity = Integer.parseInt(quantityString);
				int price = Integer.parseInt(priceString);
				// recuperation de la consommation
				Consumption consumption = guestStay.getConsumptionByName(productName);
				if(consumption != null) {
					consumption.getProduct().name = productName;
					consumption.getProduct().price = price;
					consumption.qt = quantity;
					updateTableModel();
				
				} else {
					JOptionPane.showMessageDialog(null, "Aucun produit trouvé !", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void createTableModel() {
		String[] columnNames = {"Nom du produit", "Quantité", "Total"};

		Object[][] data = new Object[guestStay.listconspt.size()][3];

		for (int i = 0; i < guestStay.listconspt.size(); i++) {
			Consumption cspt = guestStay.listconspt.get(i);
			data[i][0] = cspt.product.name; 
			data[i][1] = cspt.qt;
			data[i][2] = cspt.getCost();
		}

		consumptionTableModel = new DefaultTableModel(data, columnNames){
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };;
	}

	public void updateTableModel() {
		createTableModel();
		JconsumptionTable.setModel(consumptionTableModel);
		consumptionTableModel.fireTableDataChanged();
		st.updateTableModel();
	}
}
