import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class frameOfficeManagerAllocateBlank {
    private Main main;
    private JFrame frame;
    private Blank blank;
    private JButton buttonGoBack;
    private JPanel panelOfficeManagerAllocateBlank;
    private JPanel panelSecondary;
    private JPanel panelTertiary;

    public frameOfficeManagerAllocateBlank(Main main, Blank blank) {
        this.main = main;
        this.blank = blank;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerAllocateBlank);
        setUpTopLabels();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        setUpBlankLabels(labelConstraints, blank);
        setUpTravelAdvisors();
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerSystemStock(main);
            }
        });
    }
    private void setUpTopLabels(){
        frameOfficeManagerSystemStock.setUpLabels(panelSecondary);
    }
    private void setUpBlankLabels(GridBagConstraints labelConstraints, Blank blank){
        frameOfficeManagerSystemStock.setUpMoreLabels(labelConstraints, blank, panelSecondary);
    }
    private void setUpTravelAdvisors(){
        setUpTravelAdvisorsTopLabels();
        ArrayList<User> travelAdvisorArrayList = new ArrayList<User>();
        ArrayList<User> userArrayList = main.getUserArrayList();
        for (User user : userArrayList) {
            if (user.getRole().equals("Travel Advisor")) {
                travelAdvisorArrayList.add(user);
            }
        }
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.anchor = GridBagConstraints.NORTHEAST;
        buttonConstraints.gridx = 4;
        buttonConstraints.weightx = 1.0;
        buttonConstraints.weighty = 1.0;
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        for (int i=0; i<travelAdvisorArrayList.size(); i++) {
            buttonConstraints.gridy = i + 1;
            labelConstraints.gridy = i + 1;
            User user = travelAdvisorArrayList.get(i);
            JButton userButton = user.getButton();
            setUpDataLabels(labelConstraints, user);
            userButton.setText("Allocate");
            userButton.setBackground(Color.GREEN);
            userButton.setForeground(Color.BLACK);
            panelTertiary.add(userButton, buttonConstraints);
            userButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new frameOfficeManagerSystemStock(main);
                    JOptionPane.showMessageDialog(frame, "Blank allocated", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            });
        }
    }
    private void setUpDataLabels(GridBagConstraints labelConstraints, User user){
        for (int i=0; i<4; i++) {
            JLabel col = new JLabel();
            col.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
            labelConstraints.gridx = i;
            switch (i){
                case 0 -> col.setText(String.valueOf(user.getUserID()));
                case 1 -> col.setText(String.valueOf(user.getName()));
                case 2 -> col.setText(String.valueOf(user.getEmail()));
                case 3 -> col.setText(String.valueOf(user.getAddress()));
            }
            panelTertiary.add(col, labelConstraints);
        }
    }
    private void setUpTravelAdvisorsTopLabels(){
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        labelConstraints.gridy = 0;
        for (int i=0; i<4; i++){
            JLabel col = new JLabel();
            col.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
            labelConstraints.gridx = i;
            switch (i) {
                case 0 -> col.setText("User ID|");
                case 1 -> col.setText("Name|");
                case 2 -> col.setText("Email|");
                case 3 -> col.setText("Address|");
            }
            panelTertiary.add(col, labelConstraints);
        }
    }
}
