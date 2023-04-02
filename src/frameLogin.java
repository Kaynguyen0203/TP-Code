import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class frameLogin {
    private final Main main;
    private final JFrame frame;
    private JTextField fieldEmail;
    private JPasswordField fieldPassword;
    private JButton buttonEnter;
    private JButton buttonCreateAccount;
    private JPanel panelLogin;
    private JButton buttonExit;

    public frameLogin(Main main){
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelLogin);
        frame.pack();
        buttonCreateAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameCreateAccount(main);
            }
        });
        buttonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    private void login(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement statement = con.prepareStatement(
                    "SELECT UserID, name, password, email, address, role FROM users WHERE email = ? AND password = ?");
            statement.setString(1, fieldEmail.getText());
            statement.setString(2, String.valueOf(fieldPassword.getPassword()));
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int userID = resultSet.getInt("userID");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String name = resultSet.getString("name");
                String role = resultSet.getString("role");
                User user = new User(userID,name, password, email, address, role);
                main.setUser(user);
                switch (role) {
                        case "Customer" -> new frameCustomer(main);
                        case "Office Manager" -> new frameOfficeManager(main);
                        case "Travel Advisor" -> new frameTravelAdvisor(main);
                        case "System Admin" -> new frameSystemAdmin(main);
                        case "AirVia Staff" -> new frameAirVia(main);
                }
            } else{
                JOptionPane.showMessageDialog(frame, "Invalid", "Try again", JOptionPane.ERROR_MESSAGE);
            }
            statement.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
