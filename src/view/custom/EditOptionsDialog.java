package view.custom;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Options;
import view.RoomP;

public class EditOptionsDialog extends JDialog {
    private JTextField txtName;
    private JTextField txtCost;
    private RoomP r;

    public EditOptionsDialog(RoomP r, Options opt) {
        setTitle("Modifier les options");
        setModal(true);
        setLocationRelativeTo(r);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        form.add(new JLabel("Nom:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        txtName = new JTextField(10);
        txtName.setText(opt.getName());
        form.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        form.add(new JLabel("Prénom:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        txtCost = new JTextField(10);
        txtCost.setText(Integer.toString(opt.getCost()));
        form.add(txtCost, gbc);

        contentPanel.add(form);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton confirmButton = new JButton("Confirmer");
        JButton cancelButton = new JButton("Annuler");

        JDialog d = this;
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtName.getText().isEmpty() || txtCost.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(d, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else if (!txtCost.getText().matches("\\d+")) {
                    JOptionPane.showMessageDialog(r, "Veuillez entrer des nombres valides pour le prix", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    if (r.getOptionByName(txtName.getText()) != null && !opt.getName().equals(txtName.getText())) {
                        JOptionPane.showMessageDialog(d, "Le nom d'options que vous avez entré est déjà utilisé. Veuillez en choisir un autre.", "Nom déjà utilisé", JOptionPane.ERROR_MESSAGE);
                    } else {
                        opt.setCost(Integer.parseInt(txtCost.getText()));
                        opt.setName(txtName.getText());
                        r.updateTableModelOptions();
                        d.dispose();
                    }
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.dispose();
            }
        });

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        contentPanel.add(buttonPanel);

        getContentPane().add(contentPanel);
        pack();
        setVisible(true);
    }
}
