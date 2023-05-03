package controller.Room;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Options;
import view.RoomP;

public class AddOptionController implements ActionListener {
	private JTextField txtOptionName;
    private JTextField txtOptionPrice;
    private RoomP roomP;

    public AddOptionController(JTextField txtOptionName, JTextField txtOptionPrice, RoomP roomP) {
        this.txtOptionName = txtOptionName;
        this.txtOptionPrice = txtOptionPrice;
        this.roomP = roomP;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String optionName = txtOptionName.getText();
        String optionPriceStr = txtOptionPrice.getText();

        if (optionName.isEmpty() || optionPriceStr.isEmpty()) {
            JOptionPane.showMessageDialog(roomP, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int optionPrice = Integer.parseInt(optionPriceStr);

        // Vérifie si une option avec le même nom existe déjà
        for (Options option : roomP.optionsVector) {
            if (option.getName().equals(optionName)) {
                JOptionPane.showMessageDialog(roomP, "Une option avec ce nom existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Options newOption = new Options(optionName, optionPrice);
        roomP.optionsVector.add(newOption);
        roomP.updateTableModelOptions(roomP.optionsVector);

        txtOptionName.setText("");
        txtOptionPrice.setText("");
    }
}
