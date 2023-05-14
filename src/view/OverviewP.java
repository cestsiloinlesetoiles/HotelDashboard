package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Hotel;
import view.ui.theme;

public class OverviewP extends JPanel implements Observer {
    public Hotel h;
    public JPanel pnlMain = new JPanel();
    public JPanel pnlPageLevel = new JPanel();

    public JLabel lblTitle;
    public JPanel pnlAddress;
    public JLabel lblHotelAddress;
    public JPanel pnlRooms;
    public JLabel lblNumRooms;
    public JPanel pnlCustomers;
    public JLabel lblNumCustomers;


    public OverviewP(Hotel h) {
        this.h = h;
        h.addObserver(this);
        setLayout(new BorderLayout());

        ImageIcon pgIco = new ImageIcon("resources/pagelevel/1.png");
        JLabel pg = new JLabel(pgIco);
        pg.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        pnlPageLevel.setLayout(new BoxLayout(pnlPageLevel, BoxLayout.Y_AXIS));
        pnlPageLevel.add(Box.createVerticalGlue());
        pnlPageLevel.add(pg);
        pnlPageLevel.add(Box.createVerticalGlue());
        add(pnlPageLevel, BorderLayout.EAST);

        add(pnlMain, BorderLayout.CENTER);
        pnlMain.setBackground(theme.BACKGROUND);
        pnlPageLevel.setBackground(theme.BACKGROUND);

        lblTitle = new JLabel(h.name);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(new EmptyBorder(0, 10, 0, 0));
        pnlAddress = new JPanel();
        pnlAddress.setBackground(theme.BACKGROUND);
        lblHotelAddress = new JLabel("Adresse: " + h.address);
        lblHotelAddress.setForeground(Color.WHITE);
        pnlRooms = new JPanel();
        pnlRooms.setBackground(theme.BACKGROUND);
        lblNumRooms = new JLabel("Chambres: " + h.rooms.size());
        lblNumRooms.setForeground(Color.WHITE);
        pnlCustomers = new JPanel();
        pnlCustomers.setBackground(theme.BACKGROUND);
        lblNumCustomers = new JLabel("Clients: " + h.customers.size());
        lblNumCustomers.setForeground(Color.WHITE);

        Font fontTitle = new Font("Monospaced", Font.BOLD, 30);
        Font fontInfo = new Font("Monospaced", Font.PLAIN, 20);
        lblTitle.setFont(fontTitle);
        lblNumRooms.setFont(fontInfo);
        lblNumCustomers.setFont(fontInfo);
        lblHotelAddress.setFont(fontInfo);
        
        pnlAddress.add(lblHotelAddress);
        pnlRooms.add(lblNumRooms);
        pnlCustomers.add(lblNumCustomers);

        pnlAddress.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlRooms.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlCustomers.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnlMain.setLayout(new GridLayout(4, 1));
        pnlMain.add(lblTitle);
        pnlMain.add(pnlAddress);
        pnlMain.add(pnlRooms);
        pnlMain.add(pnlCustomers);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Hotel) {
            Hotel hotel = (Hotel) o;

            lblTitle.setText(hotel.name);
            lblHotelAddress.setText("Adresse: " + hotel.address);
            lblNumRooms.setText("Chambres: " + hotel.rooms.size());
            lblNumCustomers.setText("Clients: " + hotel.customers.size());
        }
    }
}
