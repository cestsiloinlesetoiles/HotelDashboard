package controller.stayP;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.GuestStay;
import view.StayP;

public class ControllerDelStay implements ActionListener {
    private StayP stayP;

    public ControllerDelStay(StayP stayP) {
        this.stayP = stayP;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selectedRows = stayP.JTableStay.getSelectedRows();
        int numRows = selectedRows.length;

        if (numRows == 0) {
            JOptionPane.showMessageDialog(stayP, "Aucun séjour sélectionné.", "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            int result = JOptionPane.showConfirmDialog(stayP,
                    "Êtes-vous sûr de vouloir supprimer " + numRows + " séjours ?", "Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                for (int i = numRows - 1; i >= 0; i--) {
                    int selectedRow = selectedRows[i];
                    int stayId = (int) stayP.JTableStay.getValueAt(selectedRow, 0);
                    GuestStay stay = stayP.h.getGuestStayById(stayId);
                    stayP.h.guestStays.remove(stay);
                    stayP.updateTableModel();
                }
            }
        }
    }
}
