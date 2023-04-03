import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class frameTravelAdvisorValidateBlank {
    private Main main;
    private JFrame frame;
    private JPanel panelTravelAdvisorValidateBlank;
    private JButton buttonGenerateTicket;
    private JButton buttonGoBack;
    private JTextField fieldDestination;
    private JTextField fieldOrigin;
    private JTextField fieldSeatNumber;
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
            if (destination.isEmpty() || origin.isEmpty() || seatNumber ==0) {
                JOptionPane.showMessageDialog(frame, "One or more empty fields", "Try again", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE blanks SET dateValidated=?, destination=?, origin=?, seatNumber=? "
                    + "WHERE blankNumber=? AND ticketType=?");
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formattedDate = currentDate.format(formatter);
            int actualDate = Integer.parseInt(formattedDate);
            preparedStatement.setInt(1, actualDate);
            preparedStatement.setString(2, destination);
            preparedStatement.setString(3, origin);
            preparedStatement.setInt(4, seatNumber);
            preparedStatement.setInt(5, blank.getBlankNumber());
            preparedStatement.setInt(6, blank.getTicketType());
            preparedStatement.executeUpdate();
            con.close();
            preparedStatement.close();
            blank.setDateValidated(actualDate);
            blank.setDestination(destination);
            blank.setSeatNumber(seatNumber);
            blank.setOrigin(origin);
            JOptionPane.showMessageDialog(frame, "Blank Validated", "Success", JOptionPane.INFORMATION_MESSAGE);
            main.removeUserActionListeners();
            new frameTravelAdvisorEmptyBlanks(main);
        }catch(Exception e){
            JOptionPane.showMessageDialog(frame, "Incorrect field", "Try again", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }
}
