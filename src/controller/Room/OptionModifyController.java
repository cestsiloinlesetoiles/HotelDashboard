package controller.Room;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Options;
import view.RoomP;

public class OptionModifyController implements ActionListener {
    private JTextField txtOptionName;
    private JTextField txtOptionPrice;
    private RoomP roomP;

    public OptionModifyController(JTextField txtOptionName, JTextField txtOptionPrice, RoomP roomP) {
        this.txtOptionName = txtOptionName;
        this.txtOptionPrice = txtOptionPrice;
        this.roomP = roomP;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = txtOptionName.getText().trim();
        String price = txtOptionPrice.getText().trim();

        if (name.isEmpty() || price.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
            return;
        }

    
        boolean optionExists = false;
        for (int i = 0; i < roomP.optionsVector.size(); i++) {
            Options option = roomP.optionsVector.get(i);
            if (option.getName().equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(null, "Cette option existe déjà");
                optionExists = true;
                break;
            }
        }
        if (optionExists) {
            return;
        }

        int cost=Integer.parseInt(price);
        

        Options option = new Options(name, cost);
        int selected=roomP.tableOptions.getSelectedRow();
        roomP.optionsVector.set(selected, option);
        roomP.updateTableModelOptions(roomP.optionsVector);

        txtOptionName.setText("");
        txtOptionPrice.setText("");
    }
}
