import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/*
this class allows the travel advisor to be able to validate a blank
the user can set all the required information
an sql query is sent to update the selected blank from the previous frame
 */
public class frameTravelAdvisorValidateBlank {
    private Main main;
    private JFrame frame;
    private JPanel panelTravelAdvisorValidateBlank;
    private JButton buttonGenerateTicket;
    private JButton buttonGoBack;
    private JTextField fieldDestination;
    private JTextField fieldOrigin;
    private JTextField fieldSeatNumber;
    private JTextField fieldTicketPrice;
    private JTextField fieldLocalCurrencyRate;
    private Blank blank;
    public frameTravelAdvisorValidateBlank(Main main, Blank blank) {
        this.main = main;
        this.blank = blank;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelTravelAdvisorValidateBlank);
        frame.pack();
        buttonGenerateTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (ActionListener listener :buttonGenerateTicket.getActionListeners()) {
                    buttonGenerateTicket.removeActionListener(listener);
                }
                generateTicket();
            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeBlankActionListeners();
                for (ActionListener listener :buttonGenerateTicket.getActionListeners()) {
                    buttonGenerateTicket.removeActionListener(listener);
                }
                new frameTravelAdvisorEmptyBlanks(main);
            }
        });
    }
    private void generateTicket(){
        try{
            String destination = fieldDestination.getText();
            String origin = fieldOrigin.getText();
            int seatNumber = Integer.parseInt(fieldSeatNumber.getText());
            int ticketPrice = Integer.parseInt(fieldTicketPrice.getText());
            long localCurrencyRate = Long.parseLong(fieldLocalCurrencyRate.getText());
            long actualLocalCurrency = (long)ticketPrice*localCurrencyRate;
            int actualLocalCurrencyInt = (int)actualLocalCurrency;
            if (destination.isEmpty() || origin.isEmpty() || seatNumber ==0) {
                JOptionPane.showMessageDialog(frame, "One or more empty fields", "Try again", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE blanks SET isValidated=?, destination=?, origin=?, seatNumber=?, ticketPrice=?, ticketPriceLocal=? "
                    + "WHERE blankNumber=? AND ticketType=?");
            preparedStatement.setBoolean(1, true);
            preparedStatement.setString(2, destination);
            preparedStatement.setString(3, origin);
            preparedStatement.setInt(4, seatNumber);
            preparedStatement.setInt(5, ticketPrice);
            preparedStatement.setInt(6, actualLocalCurrencyInt);
            preparedStatement.setInt(7, blank.getBlankNumber());
            preparedStatement.setInt(8, blank.getTicketType());
            preparedStatement.executeUpdate();
            con.close();
            preparedStatement.close();
            blank.setIsValidated(true);
            blank.setDestination(destination);
            blank.setSeatNumber(seatNumber);
            blank.setOrigin(origin);
            blank.setTicketPrice(ticketPrice);
            blank.setTicketPriceLocal(actualLocalCurrencyInt);
            JOptionPane.showMessageDialog(frame, "Blank Validated", "Success", JOptionPane.INFORMATION_MESSAGE);
            main.removeUserActionListeners();
            new frameTravelAdvisorEmptyBlanks(main);
        }catch(Exception e){
            new frameTravelAdvisorEmptyBlanks(main);
            JOptionPane.showMessageDialog(frame, "Incorrect field", "Try again", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }
}
