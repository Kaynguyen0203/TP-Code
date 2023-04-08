import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
/*
this class allows the office manager to be able to see rows of travel advisors
the sequence of methods sets up the panel with rows of labels and corresponding buttons
each label is the user information, each button allows the user to allocate the blank
these buttons and labels are under the top labels
 */
public class frameOfficeManagerAllocateBlank {
    private final Main main;
    private final JFrame frame;
    private final Blank blank;
    private JButton buttonGoBack;
    private JPanel panelOfficeManagerAllocateBlank;
    private JPanel panelSecondary;
    private JPanel panelTertiary;

    public frameOfficeManagerAllocateBlank(Main main, Blank blank) {
        this.main = main;
        this.blank = blank;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerAllocateBlank);
        main.setUpBlankTopLabels(panelSecondary);
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        main.setUpBlankDataLabels(labelConstraints, blank, panelSecondary);
        setUpTravelAdvisorsList();
        main.setUpUserTopLabels(panelTertiary);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeUserActionListeners();
                new frameOfficeManagerSystemStock(main);

            }
        });
    }

    private void setUpTravelAdvisorsList(){
        ArrayList<User> travelAdvisorArrayList = new ArrayList<User>();
        ArrayList<User> userArrayList = main.getUserArrayList();
        for (User user : userArrayList) {
            if (user.getRole().equals("Travel Advisor")) {
                travelAdvisorArrayList.add(user);
            }
        }
        GridBagConstraints buttonConstraints = main.setUserButtonConstraints();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        for (int i=0; i<travelAdvisorArrayList.size(); i++) {
            buttonConstraints.gridy =i+1;
            labelConstraints.gridy =i+1;
            User user = travelAdvisorArrayList.get(i);
            JButton userButton = user.getButton();
            main.setUpUserDataLabels(labelConstraints, user, panelTertiary);
            userButton.setText("Allocate");
            userButton.setBackground(Color.GREEN);
            userButton.setForeground(Color.BLACK);
            panelTertiary.add(userButton, buttonConstraints);
            userButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    allocateBlank(user, blank);
                    main.removeUserActionListeners();
                    new frameOfficeManagerSystemStock(main);
                    JOptionPane.showMessageDialog(frame, "Blank allocated", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            });
        }
        labelConstraints.gridy+=1;
        panelTertiary.add(Box.createVerticalStrut(300), labelConstraints);
    }
    private void allocateBlank(User user, Blank blank){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE blanks SET sellerUserID = ?" +
                    " WHERE blankNumber = ? AND ticketType = ?");
            preparedStatement.setInt(1, user.getUserID());
            preparedStatement.setInt(2, blank.getBlankNumber());
            preparedStatement.setInt(3, blank.getTicketType());
            preparedStatement.executeUpdate();
            con.close();
            preparedStatement.close();
            blank.setSellerUserID(user.getUserID());
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
