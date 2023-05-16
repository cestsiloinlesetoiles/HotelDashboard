package view.ui;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JPanel;

public class RoundedPanel extends JPanel {
	
    public RoundedPanel( ) {
        // On définit le style de notre panel en lui donnant un arc de 25 avec flatlaf
        putClientProperty(FlatClientProperties.STYLE, "arc: 25");
        
    }
}
