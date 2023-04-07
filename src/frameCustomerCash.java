import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class frameCustomerCash {
    private Main main;
    private JFrame frame;
    private JButton buttonGoBack;
    private JButton buttonConfirm;
    private JPanel panelCustomerCash;
    private JPanel panelSecondary;
    private Blank blank;
    public frameCustomerCash(Main main, Blank blank) {
        this.main = main;
        this.blank = blank;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelCustomerCash);
        main.setUpBlankTopLabels(panelSecondary);
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        main.setUpBlankDataLabels(labelConstraints,blank,panelSecondary);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(ActionListener listener: buttonConfirm.getActionListeners()){
                    buttonConfirm.removeActionListener(listener);
                }
                new frameCustomerPurchase(main, blank);
            }
        });
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentConfirm();
                buttonConfirm.removeActionListener(this);
                new frameCustomer(main);
            }
        });
    }
    private void paymentConfirm(){
        try {
            User user = main.getUser();
            double discountPercentage = user.getDiscount() / 100.0;
            double actualTicketPrice = blank.getTicketPrice() * (1 - discountPercentage);
            int discountedPrice = (int) actualTicketPrice;
            double actualTicketPriceLocal = blank.getTicketPriceLocal() * (1 - discountPercentage);
            int discountedPriceLocal = (int) actualTicketPriceLocal;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE blanks SET customerUserID = ?, dateSold=?, cashCard=?, discountedTicketPrice=?, discountedTicketPriceLocal=?" +
                    " WHERE blankNumber = ? AND ticketType =?");
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formattedDate = currentDate.format(formatter);
            int actualDate = Integer.parseInt(formattedDate);
            preparedStatement.setInt(1, user.getUserID());
            preparedStatement.setInt(2, actualDate);
            preparedStatement.setString(3, "Cash");
            preparedStatement.setInt(4, discountedPrice);
            preparedStatement.setInt(5, discountedPriceLocal);
            preparedStatement.setInt(6, blank.getBlankNumber());
            preparedStatement.setInt(7, blank.getTicketType());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            blank.setCustomerUserID(user.getUserID());
            blank.setDateSold(actualDate);
            blank.setCashCard("Cash");
            blank.setDiscountedTicketPrice(discountedPrice);
            blank.setDiscountedTicketPriceLocal(discountedPriceLocal);
            JOptionPane.showMessageDialog(frame, "Purchased Ticket", "Success", JOptionPane.INFORMATION_MESSAGE);
            con.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
