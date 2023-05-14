package view.custom;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Hotel;

public class SettingDiag extends JDialog {

    private JTextField nameField;
    private JTextField addressField;

    public SettingDiag(JFrame parent, Hotel hotel) {
        super(parent, "Modifier l'hôtel", true);
        
      
        JLabel nameLabel = new JLabel("Nom de l'hôtel:");
        JLabel addressLabel = new JLabel("Adresse:");

        nameField = new JTextField(hotel.name);
        addressField = new JTextField(hotel.address);

        JButton saveButton = new JButton("Enregistrer");
        JButton cancelButton = new JButton("Annuler");

     
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(addressLabel);
        mainPanel.add(addressField);
        mainPanel.add(saveButton);
        mainPanel.add(cancelButton);

        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
             
                hotel.setAddr(addressField.getText());
                hotel.setName(nameField.getText());
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        add(mainPanel);
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
    }
}
