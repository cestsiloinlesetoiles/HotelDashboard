package controller.Customer;

import java.awt.TextField;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SearchSortController implements DocumentListener {
	public JTable table;
    public DefaultTableModel tableModel;
    public JTextField searchField;

    public SearchSortController(JTable table, DefaultTableModel tableModel,JTextField searchField) {
        this.table = table;
        this.tableModel = tableModel;
        this.searchField = searchField;
    }
    
    public void filter(String filter) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(filter));
    }
    
    

    @Override
    public void insertUpdate(DocumentEvent e) {
        filter(searchField.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        filter(searchField.getText());
    }
    
    @Override
    public void changedUpdate(DocumentEvent e) {
        
    }
}
