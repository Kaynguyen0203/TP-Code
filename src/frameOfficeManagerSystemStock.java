import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class frameOfficeManagerSystemStock {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerSystemStock;
    private JButton buttonAllocate;
    private JButton buttonReallocateBlanks;
    private JButton buttonGoBack;
    private JPanel panelSecondary;

    public frameOfficeManagerSystemStock(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerSystemStock);
        setUpButtons();
        frame.pack();
        buttonReallocateBlanks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerAllocatedBlanks(main);
            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManager(main);
            }
        });
        buttonAllocate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerAllocateBlank(main);
            }
        });
    }
    private void setUpButtons(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "SELECT blankNumber, dateIssued, dateValidated, ticketType, destination, " +
                    "destination, flightDate, seatNumber, ticketPrice, sellerName, customerName, dateSold FROM blanks");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

            }
            preparedStatement.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
