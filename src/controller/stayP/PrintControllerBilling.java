package controller.stayP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.JOptionPane;

import view.StayP;

public class PrintControllerBilling implements ActionListener{
	StayP stayp;
	
	public PrintControllerBilling(StayP stayp){
	    this.stayp = stayp ; 
	}
	
    @Override
    public void actionPerformed(ActionEvent e) {
    	
        if (stayp.invoiceArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(stayp, "La facture est vide, impossible d'imprimer", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                boolean printed = stayp.invoiceArea.print();
                if (printed) {
                    JOptionPane.showMessageDialog(stayp, "Impression réussie", "Résultat de l'impression", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(stayp, "L'impression a été annulée", "Résultat de l'impression", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (PrinterException pe) {
                JOptionPane.showMessageDialog(stayp, "Échec de l'impression : " + pe.getMessage(), "Résultat de l'impression", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

