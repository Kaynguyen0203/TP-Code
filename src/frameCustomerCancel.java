import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class frameCustomerCancel {
    private Main main;
    private JFrame frame;
    private JButton buttonConfirm;
    private JButton buttonGoBack;
    private JPanel panelCustomerCancel;
    private JPanel panelSecondary;
    private Blank blank;
    public frameCustomerCancel(Main main, Blank blank) {
        this.main = main;
        this.blank = blank;
        frame = main.getMain().getMainFrame();
        main.setUpBlankTopLabels(panelSecondary);
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        main.setUpBlankDataLabels(labelConstraints,blank,panelSecondary);
        frame.setContentPane(panelCustomerCancel);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeBlankActionListeners();
                for(ActionListener listener: buttonConfirm.getActionListeners()){
                    buttonConfirm.removeActionListener(listener);
                }
                new frameCustomerTickets(main);
            }
        });
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmCancel();
                buttonConfirm.removeActionListener(this);
                new frameCustomerTickets(main);
            }
        });
    }
    /*
    this function is to get a refund from a blank
    this is done by making a sql query to update the information back to before it was purchased, essentially cancelling it
    a log is also created in a separate file called allRefundLogs.txt
    */
    private void confirmCancel(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE blanks SET customerUserID = ?, dateSold=?, cashCard=?, discountedTicketPrice=?, discountedTicketPriceLocal=?" +
                    " WHERE blankNumber = ? AND ticketType =?");
            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setNull(2, Types.INTEGER);
            preparedStatement.setString(3, null);
            preparedStatement.setNull(4, Types.INTEGER);
            preparedStatement.setInt(5, Types.INTEGER);
            preparedStatement.setInt(6,blank.getBlankNumber());
            preparedStatement.setInt(7, blank.getTicketType());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            blank.setCustomerUserID(0);
            blank.setDateSold(0);
            blank.setDiscountedTicketPrice(0);
            blank.setDiscountedTicketPriceLocal(0);
            blank.setCashCard("null");
            JOptionPane.showMessageDialog(frame, "Ticket Refunded", "Success", JOptionPane.INFORMATION_MESSAGE);
            con.close();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("allRefundLogs.txt",true))){
                long blankNumber = (blank.getTicketType()* 100000000L)+blank.getBlankNumber();
                writer.write(blankNumber+" returned and full refund given");
                writer.newLine();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
