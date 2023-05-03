package controller.Customer;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.Custompanel.ActionMenuP;

public class TableSelectionController implements ListSelectionListener {

    private final JTable table;
    private final ActionMenuP actionMenu;

    public TableSelectionController(JTable table, ActionMenuP actionMenu) {
        this.table = table;
        this.actionMenu = actionMenu;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (table.getSelectedRowCount() > 0) {
            actionMenu.enableButtons(true);
            actionMenu.enablebtnConsult(true);
        } else {
            actionMenu.enableButtons(false);
            actionMenu.enablebtnConsult(false);
        }
    }
}
