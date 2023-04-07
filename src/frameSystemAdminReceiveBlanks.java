import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class frameSystemAdminReceiveBlanks {
    private final Main main;
    private final JFrame frame;
    private JPanel panelAirVia;
    private JTextField fieldBlankAmount;
    private JButton buttonSend;
    private JButton buttonGoBack;
    private JTextField fieldTicketType;
    private JTextField fieldTicketPrice;
    private JTextField fieldCommissionRate;
    private JTextField fieldDate;
    private JTextField fieldLocalCurrencyRate;

    public frameSystemAdminReceiveBlanks(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelAirVia);
        frame.pack();
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSend.removeActionListener(this);
                sendEmptyBlanks();
            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (ActionListener listener: buttonSend.getActionListeners()){
                    buttonSend.removeActionListener(listener);
                }
                buttonGoBack.removeActionListener(this);
                new frameSystemAdmin(main);
            }
        });
    }
    private void sendEmptyBlanks(){
        try {
            int blankAmount = Integer.parseInt(fieldBlankAmount.getText());
            int ticketType = Integer.parseInt(fieldTicketType.getText());
            int ticketPrice = Integer.parseInt(fieldTicketPrice.getText());
            long localCurrencyRate = Long.parseLong(fieldLocalCurrencyRate.getText());
            int commissionRate = Integer.parseInt(fieldCommissionRate.getText());
            int dateIssued = Integer.parseInt(fieldDate.getText());
            long actualLocalCurrency = (long)ticketPrice*localCurrencyRate;
            int actualLocalCurrencyInt = (int)actualLocalCurrency;
            if (blankAmount>0 && blankAmount <11 && Arrays.asList(444, 440, 420, 201, 101, 451, 452).contains(ticketType) && ticketPrice>0 && commissionRate>0 && commissionRate<101){
                ArrayList<Blank> array = main.getBlankArrayList();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                        "in2018g30_a", "AqZonm86");
                for (int i=0 ; i<blankAmount; i++){
                    PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO blanks (blankNumber, dateIssued, ticketType, ticketPrice, ticketPriceLocal, commissionRate) " + "VALUES (?,?,?,?,?,?)");
                    int highestIDBlank = getCurrentBlanks(ticketType);
                    preparedStatement.setInt(1, highestIDBlank + 1);
                    preparedStatement.setInt(2, dateIssued);
                    preparedStatement.setInt(3, ticketType);
                    preparedStatement.setInt(4, ticketPrice);
                    preparedStatement.setInt(5, actualLocalCurrencyInt);
                    preparedStatement.setInt(6, commissionRate);
                    preparedStatement.executeUpdate();
                    Blank blank = new Blank(highestIDBlank+1, dateIssued, 0, ticketType, null, null, 0, ticketPrice,0,actualLocalCurrencyInt, 0,0, 0, 0, null, commissionRate);
                    array.add(blank);
                    preparedStatement.close();
                }
                con.close();
                JOptionPane.showMessageDialog(frame, blankAmount+" blanks have been added", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(frame, "Invalid, must be a whole number and input as specified", "Try again", JOptionPane.ERROR_MESSAGE);
            }
        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Invalid, must be a whole number and input as specified", "Try again", JOptionPane.ERROR_MESSAGE);
        }
    }
    private int getCurrentBlanks(int ticketType){
        int highest = 1;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement preparedStatement = con.prepareStatement("SELECT MAX(blankNumber) FROM blanks WHERE ticketType =?");
            preparedStatement.setInt(1, ticketType);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                highest = resultSet.getInt("MAX(blankNumber)");
            }
            preparedStatement.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return highest;
    }
}
