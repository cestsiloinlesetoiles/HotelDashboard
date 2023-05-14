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
import java.util.Observable;
import java.util.Observer;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.stayP.ControllerCreateBilling;
import controller.stayP.PrintControllerBilling;
import model.Customer;
import model.GuestStay;
import model.Hotel;
import model.Reservation;
import view.custom.BarDialog;
import view.ui.RoundedPanel;
import view.ui.theme;
public class StayP extends JPanel implements Observer{
	public Hotel h;
	public JTable JTableStay;
	public DefaultTableModel TableModelStay;
	public JPanel pnlMain = new JPanel();
	public JPanel pnlPageLevel = new JPanel();
	
	public RoundedPanel leftPanel;
	public RoundedPanel bottomRightPanel;
	public RoundedPanel topRightPanel;
	
	public JLabel stayLabel ;
	public JTextField stayTextField ;
    public JButton AddBtn;
    public JPanel invoice;
    public JTextArea invoiceArea;
    public JButton printButton;
    public JButton clearButton;
	
	public StayP (Hotel h) {
		
		this.h = h;
		// Mise en place du Pagelevel et du cadran Main
		setLayout(new BorderLayout());
		ImageIcon pgIco = new ImageIcon("resources/pagelevel/5.png");
		JLabel pg = new JLabel(pgIco);
		pg.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		pnlPageLevel.setLayout(new BoxLayout(pnlPageLevel, BoxLayout.Y_AXIS));
		pnlPageLevel.add(Box.createVerticalGlue());
		pnlPageLevel.add(pg);
		pnlPageLevel.add(Box.createVerticalGlue());
		add(pnlPageLevel,BorderLayout.EAST);
		
		add(pnlMain,BorderLayout.CENTER);
		pnlMain.setBackground(theme.SECONDARY_BACKGROUND);
		pnlPageLevel.setBackground(theme.SECONDARY_BACKGROUND);
		
		
		
		
		

        leftPanel = new RoundedPanel() ;
        bottomRightPanel = new RoundedPanel();
        topRightPanel = new RoundedPanel();
        topRightPanel.setBackground(theme.BACKGROUND_PANEL);
        leftPanel.setBackground(theme.BACKGROUND_PANEL);
        bottomRightPanel.setBackground(theme.BACKGROUND_PANEL);
        pnlMain.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.weightx = 0.8;
        gbc.weighty = 1;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 4;

        pnlMain.add(leftPanel, gbc);
      
        gbc.weightx = 0.2;
        gbc.weighty = 0.8;
        gbc.gridx = 3;
        gbc.gridy = 2; 
        gbc.gridwidth = 2;
        gbc.gridheight = 2; 

        pnlMain.add(bottomRightPanel, gbc);
        
        gbc.weighty = 0.2;
        gbc.gridx = 3; 
        gbc.gridy = 0;
        gbc.gridwidth = 1; 
        gbc.gridheight = 1; 
        pnlMain.add(topRightPanel, gbc);
        
        
        
        ///
      



		ImageIcon editIcon = new ImageIcon("resources/Autres/invoice.png");
		ImageIcon editIconActive = new ImageIcon("resources/Autres/invoiceA.png");
		JButton btnBilling = new JButton(editIcon);
		btnBilling.setPressedIcon(editIconActive);
		btnBilling.setContentAreaFilled(false);
		btnBilling.setBorderPainted(false);

		ImageIcon infoIcon = new ImageIcon("resources/Autres/beer.png");
		ImageIcon infoIconActive = new ImageIcon("resources/Autres/beerA.png");
		JButton btnBarMng = new JButton(infoIcon);
		btnBarMng.setPressedIcon(infoIconActive);
		btnBarMng.setContentAreaFilled(false);
		btnBarMng.setBorderPainted(false);



		ImageIcon deleteIcon = new ImageIcon("resources/table/delete.png");
		ImageIcon deleteIconActive = new ImageIcon("resources/table/deleteA.png");
		JButton btnDelete = new JButton(deleteIcon);
		btnDelete.setPressedIcon(deleteIconActive);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);



		RoundedPanel buttonsPanel = new RoundedPanel();
		buttonsPanel.setBackground(theme.SECONDARY_BACKGROUND);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(btnBilling);
		buttonsPanel.add(btnBarMng);
		buttonsPanel.add(btnDelete);






		CreateTableModel();
		JTableStay = new JTable(TableModelStay);


		JScrollPane tableScrollPane = new JScrollPane(JTableStay);
		tableScrollPane.setPreferredSize(new Dimension(350, 300));



		JTableHeader header = JTableStay.getTableHeader();
		header.setBackground(Color.decode("#232323"));
		header.setForeground(Color.WHITE);
		header.setFont(new Font("SansSerif", Font.BOLD, 14));
		header.setOpaque(false);
		header.setBorder(null);

		JTableStay.setShowGrid(false);

		buttonsPanel.setBackground(theme.BACKGROUND_PANEL);
		bottomRightPanel.setLayout(new BorderLayout());
		bottomRightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		bottomRightPanel.add(buttonsPanel,BorderLayout.NORTH);
		bottomRightPanel.add(tableScrollPane, BorderLayout.CENTER);
        
        
		//topRightPanel
		
		topRightPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(3, 3, 3, 3);

		stayLabel = new JLabel("Numéro de réservation :");
		
		c.weightx = 0.5; 
		c.gridx = 0;
        c.gridy = 0;
        topRightPanel.add(stayLabel, c);

        
        
        stayTextField = new JTextField(15);
        
        c.gridx = 0;
        c.gridy = 1;
        topRightPanel.add(stayTextField, c);

        
        
        AddBtn = new JButton("Crée un séjour");
        
        c.gridx = 1;
        c.gridy = 1;
 
        topRightPanel.add(AddBtn, c);
        
        
        
        
        //leftPanel 
        
        
        leftPanel.setLayout(new BorderLayout());
        
        String titleText = "<html>Stay<br>Manager</html>";
        JLabel title = new JLabel(titleText);
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
		title.setForeground(theme.BACKGROUND);
		title.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));
		
		leftPanel.add(title,BorderLayout.NORTH);
		
		
		
		invoice = new JPanel();
		invoice.setBackground(theme.BACKGROUND_PANEL);
		
		
		leftPanel.add(invoice,BorderLayout.CENTER);
		leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        
		invoice.setBorder(BorderFactory.createTitledBorder(
		        BorderFactory.createLineBorder(Color.black),"Facture",TitledBorder.LEFT, TitledBorder.TOP,
		        new Font("Arial", Font.BOLD, 13), theme.BACKGROUND));
		
		
		
		
		StayP stayp = this;
		AddBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String input = stayTextField.getText();
		        if (!input.isEmpty()) {
		            if (input.matches("\\d+")) {
		                int id = Integer.parseInt(input);
		                if (h.getReservationById(id)!= null) {
		                    Reservation r = h.getReservationById(id);
		                    if(h.getGuestStayById(id)==null) {
		                    GuestStay stay = new GuestStay(r);
		                    stay.setHotel(h);
		                    h.guestStays.add(stay);
		                    updateTableModel();
		                    }
		                    else {
		                    	JOptionPane.showMessageDialog(stayp, "Le sejour avec l'ID spécifié existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
			                
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(stayp, "La réservation avec l'ID spécifié n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
		                }
		            } else {
		                JOptionPane.showMessageDialog(stayp, "L'ID de réservation doit être un nombre entier", "Erreur", JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(stayp, "Veuillez entrer un ID de réservation", "Erreur", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		
		btnBarMng.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = JTableStay.getSelectedRow();
		        if (selectedRow != -1) {
		            
		            int id = (int) JTableStay.getValueAt(selectedRow, 0);
		            GuestStay stay = h.getGuestStayById(id); 
		            
		            BarDialog barDialog = new BarDialog(stay,stayp);
		            barDialog.setVisible(true);
		        } else {
		            JOptionPane.showMessageDialog(stayp, "Veuillez sélectionner un séjour", "Erreur", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		
		ImageIcon clearIcon = new ImageIcon("resources/Autres/clear.png");
		ImageIcon clearIconActive = new ImageIcon("resources/Autres/clearA.png");
		
		ImageIcon printIcon = new ImageIcon("resources/Autres/print.png");
		ImageIcon printIconActive = new ImageIcon("resources/Autres/printA.png");
		
		invoice.setLayout(new BorderLayout());
		JPanel buttonMenu = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonMenu.setBackground(theme.BACKGROUND_PANEL);
		
		printButton = new JButton(printIcon);
		printButton.setPressedIcon(printIconActive);
		printButton.setContentAreaFilled(false);
		printButton.setBorderPainted(false);
		
		clearButton = new JButton(clearIcon);
		clearButton.setPressedIcon(clearIconActive);
		clearButton.setContentAreaFilled(false);
		clearButton.setBorderPainted(false);
		
		
		buttonMenu.add(printButton);
		buttonMenu.add(clearButton);
		invoice.add(buttonMenu, BorderLayout.NORTH);	
		invoiceArea = new JTextArea();
		invoice.add(invoiceArea,BorderLayout.CENTER);
		btnBilling.addActionListener(new ControllerCreateBilling(stayp));
		printButton.addActionListener(new PrintControllerBilling(stayp));           
		clearButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                invoiceArea.setText("");
	            }
	        });
		
		
		
	}
	
	
	 public void CreateTableModel() {

	    	String[] columnNames = {"N° Res","Date de fin","Client","Totale"}
	    	;

			Object[][] data = new Object[h.guestStays.size()][5];

			for (int i = 0; i < h.guestStays.size(); i++) {
				GuestStay stay = h.guestStays.get(i);
				data[i][0] = stay.reservation.getId(); 
				data[i][1] = stay.reservation.getDateEnd().toString();
				stay.calculateTotalCost();
				data[i][3] = stay.totalCost; 
	
				Customer c = stay.reservation.getCustomer();
				if(c==null){
					data[i][2] = "N/A";
				}
				else {
					data[i][2] = c.getFirstName()+" "+ c.getLastName();
					
				}
			}
			
			TableModelStay = new DefaultTableModel(data, columnNames);
			
		}
	 
	 public void updateTableModel() {
			CreateTableModel();
			JTableStay.setModel(TableModelStay);
		
			TableModelStay.fireTableDataChanged();
		}

	 
	 
	@Override
	public void update(Observable o, Object arg) {
		updateTableModel();
		
	}

}
