package view.ui;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JPanel;

public class RoundedPanel extends JPanel {
	
    public RoundedPanel( ) {
        
        putClientProperty(FlatClientProperties.STYLE, "arc: 25");
        
    }
}
