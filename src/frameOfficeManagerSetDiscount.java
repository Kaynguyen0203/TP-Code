import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
/*
this class is for the office manager to be able to set a fixed or a flexible discount for the customer chosen
there is a row that shows the user details and another that shows the current discount the customer has
when the office manager presses confirm, an sql query is sent to update the discount for the customer
 */
public class frameOfficeManagerSetDiscount {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerSetDiscount;
    private JRadioButton radioButtonFixed;
    private JRadioButton radioButtonFlex;
    private JTextField fieldDiscount;
    private JTextField fieldDiscountP;
    private JButton buttonConfirm;
    private JButton buttonGoBack;
    private JPanel panelSecondary;
    private JPanel panelTertiary;
    private JTextField fieldDiscountLimit;
    private User user;

    public frameOfficeManagerSetDiscount(Main main, User user) {
        this.main = main;
        this.user = user;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerSetDiscount);
        main.setUpUserTopLabels(panelSecondary);
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        main.setUpUserDataLabels(labelConstraints, user, panelSecondary);
        setUpDiscountLabel();
        buttonGroup();
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeUserActionListeners();
                removeRadioListeners();
                for (ActionListener listener : buttonConfirm.getActionListeners()){
                    buttonConfirm.removeActionListener(listener);
                }
                new frameOfficeManagerCurrentCustomers(main);
            }
        });
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyDiscount();
                removeRadioListeners();
                buttonConfirm.removeActionListener(this);
                new frameOfficeManagerCurrentCustomers(main);
            }
        });
    }

    private void setUpDiscountLabel(){
        JLabel col = new JLabel();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        col.setText("Current Discount: "+ user.getDiscount());
        panelTertiary.add(col, labelConstraints);
    }
    private void applyDiscount(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            int discount;
            if (radioButtonFixed.isSelected()){
                PreparedStatement preparedStatement = con.prepareStatement("UPDATE usersCustomers SET discountPercent = ?" +
                        " WHERE userID = ?");
                discount = Integer.parseInt(fieldDiscount.getText());
                preparedStatement.setInt(1, discount);
                preparedStatement.setInt(2, user.getUserID());
                preparedStatement.executeUpdate();
                preparedStatement.close();
                user.setDiscount(discount);
            }
            if (radioButtonFlex.isSelected()){
                PreparedStatement preparedStatement = con.prepareStatement("UPDATE usersCustomers SET discountPercent =?" +
                        " WHERE userID = ?");
                int numberOfSalesPer = Integer.parseInt(fieldDiscountP.getText());
                int saleAmount=0;
                ArrayList<Blank> array = main.getBlankArrayList();
                for (Blank blank : array){
                    if (blank.getCustomerUserID() == user.getUserID()){
                        saleAmount = saleAmount + blank.getTicketPrice();
                    }
                }
                int discountLimit = Integer.parseInt(fieldDiscountLimit.getText());
                discount = saleAmount/numberOfSalesPer;
                if (discount>discountLimit){
                    discount = discountLimit;
                }
                preparedStatement.setInt(1, discount);
                preparedStatement.setInt(2, user.getUserID());
                preparedStatement.executeUpdate();
                preparedStatement.close();
                user.setDiscount(discount);
            }
            JOptionPane.showMessageDialog(frame, "Discount Set", "Success", JOptionPane.INFORMATION_MESSAGE);
            con.close();
        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "fields not set properly", "Invalid", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void buttonGroup(){
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonFixed);
        buttonGroup.add(radioButtonFlex);
        fieldDiscountP.setEnabled(false);
        fieldDiscountLimit.setEnabled(false);
        radioButtonFixed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldDiscount.setEnabled(true);
                fieldDiscountP.setEnabled(false);
                fieldDiscountLimit.setEnabled(false);
            }
        });
        radioButtonFlex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldDiscountP.setEnabled(true);
                fieldDiscountLimit.setEnabled(true);
                fieldDiscount.setEnabled(false);
            }
        });
    }
    private void removeRadioListeners(){
        for (ActionListener listener: radioButtonFlex.getActionListeners()){
            radioButtonFlex.removeActionListener(listener);
        }
        for (ActionListener listener: radioButtonFixed.getActionListeners()) {
            radioButtonFixed.removeActionListener(listener);
        }
    }
}
