import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class frameAirVia {
    private final Main main;
    private final JFrame frame;
    private JPanel panelAirVia;
    private JTextField fieldBlankAmount;
    private JButton buttonSend;
    private JButton buttonLogOut;

    public frameAirVia(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelAirVia);
        frame.pack();
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendEmptyBlanks();

            }
        });
        buttonLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameLogin(main);
            }
        });
    }
    private void sendEmptyBlanks(){
        try {
            int blankAmount = Integer.parseInt(fieldBlankAmount.getText());
            if (blankAmount>0 && blankAmount <11){

                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                String formattedDate = currentDate.format(formatter);
                int actualDate = Integer.parseInt(formattedDate);

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                        "in2018g30_a", "AqZonm86");
                for (int i=0 ; i<blankAmount; i++){
                    PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO blanks (blankNumber, dateIssued) " + "VALUES (?,?)");
                    int highestIDBlank = getCurrentBlanks();
                    preparedStatement.setInt(1, highestIDBlank + 1);
                    preparedStatement.setInt(2, actualDate);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }
                con.close();
                JOptionPane.showMessageDialog(frame, blankAmount+" blanks have been added", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(frame, "Invalid, must be a whole number greater than 0 and less than 10", "Try again", JOptionPane.ERROR_MESSAGE);
            }
        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Invalid, must be a whole number greater than 0 and less than 10", "Try again", JOptionPane.ERROR_MESSAGE);
        }
    }
    private int getCurrentBlanks(){
        int highest = 1;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement preparedStatement = con.prepareStatement("SELECT MAX(blankNumber) FROM blanks");
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
