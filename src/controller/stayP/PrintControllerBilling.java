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
    	// Si la facture est vide, on ne peut pas l'imprimer
        if (stayp.invoiceArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(stayp, "La facture est vide, impossible d'imprimer", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            // Sinon on imprime la facture
            try {
                // On imprime la facture
                boolean printed = stayp.invoiceArea.print();
                if (printed) { // Si l'impression a réussi
                    JOptionPane.showMessageDialog(stayp, "Impression réussie", "Résultat de l'impression", JOptionPane.INFORMATION_MESSAGE);
                } else { // Si l'impression a échoué
                    JOptionPane.showMessageDialog(stayp, "L'impression a été annulée", "Résultat de l'impression", JOptionPane.INFORMATION_MESSAGE);
                }
                // le catch pour les erreurs d'impression 
            } catch (PrinterException pe) { 
                // Affichage d'une boîte de dialogue d'erreur avec le message d'erreur pe.getMessage() qui est le message d'erreur de l'exception.
                JOptionPane.showMessageDialog(stayp, "Échec de l'impression : " + pe.getMessage(), "Résultat de l'impression", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

