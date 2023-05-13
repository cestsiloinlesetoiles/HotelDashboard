package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import model.Customer;
import model.GuestStay;
import model.Hotel;
import view.ui.RoundedPanel;
import view.ui.theme;
public class StayP extends JPanel implements Observer{
	public Hotel h;
	public JTable JTableStay;
	public DefaultTableModel TableModelStay;
	public JPanel pnlMain = new JPanel();
	public JPanel pnlPageLevel = new JPanel();
	
	RoundedPanel leftPanel;
	RoundedPanel bottomRightPanel;
	RoundedPanel topRightPanel;
	
	JLabel stayLabel ;
    JTextField stayTextField ;
    JButton AddBtn;
    JPanel invoice;
	
	
	
	
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
      



		ImageIcon editIcon = new ImageIcon("resources/table/edit.png");
		ImageIcon editIconActive = new ImageIcon("resources/table/editA.png");
		JButton btnEdit = new JButton(editIcon);
		btnEdit.setPressedIcon(editIconActive);
		btnEdit.setContentAreaFilled(false);
		btnEdit.setBorderPainted(false);

		ImageIcon infoIcon = new ImageIcon("resources/table/info.png");
		ImageIcon infoIconActive = new ImageIcon("resources/table/infoA.png");
		JButton btnInfo = new JButton(infoIcon);
		btnInfo.setPressedIcon(infoIconActive);
		btnInfo.setContentAreaFilled(false);
		btnInfo.setBorderPainted(false);



		ImageIcon deleteIcon = new ImageIcon("resources/table/delete.png");
		ImageIcon deleteIconActive = new ImageIcon("resources/table/deleteA.png");
		JButton btnDelete = new JButton(deleteIcon);
		btnDelete.setPressedIcon(deleteIconActive);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);



		RoundedPanel buttonsPanel = new RoundedPanel();
		buttonsPanel.setBackground(theme.SECONDARY_BACKGROUND);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(btnEdit);
		buttonsPanel.add(btnInfo);
		buttonsPanel.add(btnDelete);






		TableModelStay = CreateTableModel(h);
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
		
		
	}
	
	
	 public DefaultTableModel CreateTableModel(Hotel hotel) {

	    	String[] columnNames = {"N° Res","Date de fin","Client","Totale"}
	    	;

			Object[][] data = new Object[hotel.reservations.size()][5];

			for (int i = 0; i < hotel.guestStays.size(); i++) {
				GuestStay stay = hotel.guestStays.get(i);

				data[i][0] = stay.reservation.getId(); 
				data[i][1] = stay.reservation.getDateEnd().toString();
				
				
				
				Customer c = stay.reservation.getCustomer();
				if(c==null){
					data[i][2] = "Aucune Client associer";
				}
				else {
					data[i][2] = c.getFirstName()+" "+ c.getLastName();
					
				}
			}
		
			DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
			return tableModel;
		}
	 
	 public void updateTableModel(Hotel hotel) {
			TableModelStay = CreateTableModel(hotel);
			JTableStay.setModel(TableModelStay);
			TableModelStay.fireTableDataChanged();
		}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
