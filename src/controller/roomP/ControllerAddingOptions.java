package controller.roomP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Options;
import view.RoomP;
import view.StayP;

public class ControllerAddingOptions implements ActionListener {

	 public RoomP r;
	 public StayP stayP;
	public ControllerAddingOptions(RoomP roomP) {
		r = roomP;

	}

    @Override
    public void actionPerformed(ActionEvent e) {
        String optionName = r.optionNameTextField.getText();
        String optionPriceStr = r.optionPriceTextField.getText();
        // On vérifie que tous les champs sont remplis
        if (optionName.isEmpty() || optionPriceStr.isEmpty()) {
            JOptionPane.showMessageDialog(r, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // On vérifie que le prix est un nombre
        if(!optionPriceStr.matches("\\d+")) {
        	JOptionPane.showMessageDialog(r, "Veuillez entrer des nombres valides pour le prix", "Erreur", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        int optionPrice = Integer.parseInt(optionPriceStr);
        
        
       
        for (Options option : r.optionsVector) {
            if (option.getName().equals(optionName)) {
                JOptionPane.showMessageDialog(r, "Une option avec ce nom existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        // On ajoute l'option
        Options newOption = new Options(optionName, optionPrice);
        r.optionsVector.add(newOption);
        // on update le tableau
        r.updateTableModelOptions();
        JOptionPane.showMessageDialog(r, "Option ajoutée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        // On vide les champs
        r.optionNameTextField.setText("");
        r.optionPriceTextField.setText("");
    }	
	
}
